package com.epam.brest.courses.service;

import com.epam.brest.courses.dao.LecturerDao;
import com.epam.brest.courses.domain.Course;
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
        Assert.notNull(lecturer);
        Assert.isNull(lecturer.getLecturerId());
        Assert.notNull(lecturer.getLecturerName(), "Lecturer name should be specified.");
        return lecturerDao.addLector(lecturer);
    }

    @Override
    @Transactional
    public List<Lecturer> getLecturers() {
        return lecturerDao.getLecturers();
    }

    @Override
    @Transactional
    public void removeLecturer(Long userId) {
        lecturerDao.removeLecturer(userId);
    }

    @Override
    @Transactional
    public Lecturer getLecturerByName(String lecturerName) {
        LOGGER.debug("getLecturerByName({}) ", lecturerName);
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
        return lecturerDao.getLecturerById(lecturerId);
    }

    @Override
    @Transactional
    public void updateLecturer(Lecturer lecturer) {
        LOGGER.debug("updateLecturer({})",lecturer);
        lecturerDao.updateLecturer(lecturer);
    }
}
