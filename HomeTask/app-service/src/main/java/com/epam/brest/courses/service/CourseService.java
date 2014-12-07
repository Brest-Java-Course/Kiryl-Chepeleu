package com.epam.brest.courses.service;

import com.epam.brest.courses.domain.Course;

import java.sql.Date;
import java.util.List;

/**
 * Created by kirill on 24.10.14.
 */
public interface CourseService {

    public Long addCourse(Course course);
    public List<Course> getCourses();
    public List<Course> getCoursesByLecturerId(Long lecturerId);
    public void removeCourse(Long courseId);
    public Course getCourseByName(String courseName);
    public Course getCourseById(Long courseId);
    public void updateCourse(Course course);
    public List<Course> getCoursesBetweenDates(Date firstDate,Date secondDate);
}
