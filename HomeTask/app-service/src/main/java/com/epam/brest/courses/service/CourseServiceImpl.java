package com.epam.brest.courses.service;

import com.epam.brest.courses.dao.CourseDao;
import com.epam.brest.courses.dao.CourseDaoImpl;
import com.epam.brest.courses.domain.Course;
import com.epam.brest.courses.domain.Lecturer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.sql.Date;
import java.util.List;

/**
 * Created by kirill on 24.10.14.
 */
public class CourseServiceImpl implements CourseService {

    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
    private CourseDao courseDao;

    public void setCourseDao(CourseDao courseDao) {
        this.courseDao = courseDao;
    }

    @Override
    @Transactional
    public Long addCourse(Course course) {
        LOGGER.debug("addCourse({}) ", course);
        Assert.notNull(course);
        Assert.isNull(course.getCourseId());
        Assert.notNull(course.getCourseName(), "Course name should be specified.");
        Assert.notNull(course.getLecturerId(), "Course lector id should be specified.");
        Assert.notNull(course.getHours(), "Course hours should be specified.");
        Assert.notNull(course.getListeners(), "Course listeners should be specified.");
        Assert.notNull(course.getStartdate(), "Course startdate should be specified.");
        Course existCourse = getCourseByName(course.getCourseName());
        if(existCourse!=null){
            throw new IllegalArgumentException("Course is present in DB");
        }
        return courseDao.addCourse(course);
    }

    @Override
    @Transactional
    public List<Course> getCourses() {
        LOGGER.debug("getCourses() ");
        return courseDao.getCourses();
    }

    @Override
    @Transactional
    public List<Course> getCoursesByLecturerId(Long lecturerId) {
        LOGGER.debug("getCoursesByLecturerId({})",lecturerId);
        return courseDao.getCoursesByLecturerId(lecturerId);
    }

    @Override
    @Transactional
    public void removeCourse(Long courseId) {
        LOGGER.debug("removeCourse({})"+courseId);
        courseDao.removeCourse(courseId);
    }

    @Override
    @Transactional
    public Course getCourseByName(String courseName) {
        LOGGER.debug("getCourseByName({}) ", courseName);
        Course course= null;
        try {
            course= courseDao.getCourseByName(courseName);
        } catch (EmptyResultDataAccessException e) {
            LOGGER.error("getCourseByName({}) ", courseName);
        }
        return course;
    }

    @Override
    @Transactional
    public Course getCourseById(Long courseId) {
        LOGGER.debug("getCourseById({}) ", courseId);
        Course course= null;
        try {
            course= courseDao.getCourseById(courseId);
        } catch (EmptyResultDataAccessException e) {
            LOGGER.error("getCourseById({}) ", courseId);
        }
        return course;
    }

    @Override
    @Transactional
    public void updateCourse(Course course) {
        LOGGER.debug("updateCourse({})",course);
        courseDao.updateCourse(course);
    }

    @Override
    public List<Course> getCoursesBetweenDates(Date firstDate, Date secondDate) {
        LOGGER.debug("getCoursesBetweenDates({},{})",firstDate,secondDate);
        return courseDao.getCoursesBetweenDates(firstDate,secondDate);
    }

    @Override
    public Long getHoursByLecturerId(Long lecturerId) {
        LOGGER.debug("getHoursByLecturerId({})",lecturerId);
        return courseDao.getHoursByLecturerId(lecturerId);
    }
}
