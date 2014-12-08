package com.epam.brest.courses.service;

import com.epam.brest.courses.dao.LecturerDao;
import com.epam.brest.courses.domain.Lecturer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

/**
 * Created by kirill on 24.10.14.
 */
public class LecturerServiceImpl implements LecturerService{

    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
    private LecturerDao lecturerDao;

    public void setLecturerDao(LecturerDao lecturerDao) {
        this.lecturerDao = lecturerDao;
    }

    @Override
    @Transactional
    public Long addLecturer(Lecturer lecturer) {
        LOGGER.debug("addLecturer({})",lecturer);
        Assert.notNull(lecturer);
        Assert.isNull(lecturer.getLecturerId());
        Assert.notNull(lecturer.getLecturerName(), "Lecturer name should be specified.");
        Lecturer existLecturer = getLecturerByName(lecturer.getLecturerName());
        if(existLecturer!=null){
            throw new IllegalArgumentException("Lecturer is present in DB");
        }
        return lecturerDao.addLecturer(lecturer);
    }

    @Override
    @Transactional
    public List<Lecturer> getLecturers() {
        LOGGER.debug("getLecturers()");
        return lecturerDao.getLecturers();
    }

    @Override
    @Transactional
    public void removeLecturer(Long lecturerId) {
        LOGGER.debug("removeLecturer({})", lecturerId);
        Assert.notNull(lecturerId);
        lecturerDao.removeLecturer(lecturerId);
    }

    @Override
    @Transactional
    public Lecturer getLecturerByName(String lecturerName) {
        LOGGER.debug("getLecturerByName({}) ", lecturerName);
        Assert.notNull(lecturerName);
        Lecturer lecturer= null;
        try {
            lecturer = lecturerDao.getLecturerByName(lecturerName);
        } catch (EmptyResultDataAccessException e) {
            LOGGER.error("getLecturerByName({}) ", lecturerName);
        }
        return lecturer;
    }

    @Override
    @Transactional
    public Lecturer getLecturerById(Long lecturerId) {
        LOGGER.debug("getLecturerById({})",lecturerId);
        Assert.notNull(lecturerId);
        Lecturer lecturer= null;
        try {
            lecturer = lecturerDao.getLecturerById(lecturerId);
        } catch (EmptyResultDataAccessException e) {
            LOGGER.error("getLecturerById({}) ", lecturerId);
        }
        return lecturer;
    }

    @Override
    @Transactional
    public void updateLecturer(Lecturer lecturer) {
        LOGGER.debug("updateLecturer({})",lecturer);
        Assert.notNull(lecturer);
        Assert.notNull(lecturer.getLecturerId());
        Assert.notNull(lecturer.getLecturerName());
        Lecturer existLecturer = getLecturerById(lecturer.getLecturerId());
        if(existLecturer==null){
            throw new IllegalArgumentException("Lecturer is not present in DB");
        }
        lecturerDao.updateLecturer(lecturer);
    }

    @Override
    public void getHours(List<Lecturer> lecturers,CourseService courseService) {
        LOGGER.debug("getHours({},{})",lecturers,courseService);
        Assert.notNull(lecturers);
        Assert.notNull(courseService);
        for(Lecturer i:lecturers){
            i.setTotalHours(courseService.getHoursByLecturerId(i.getLecturerId()));
        }
    }
}
