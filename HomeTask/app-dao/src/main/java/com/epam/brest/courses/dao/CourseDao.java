package com.epam.brest.courses.dao;

import com.epam.brest.courses.domain.Course;
import com.epam.brest.courses.domain.Lecturer;

import java.sql.Date;
import java.util.List;

/**
 * Created by kirill-good on 12/6/14.
 */
public interface CourseDao {
    public Long addCourse(Course course);
    public List<Course> getCourses();
    public List<Course> getCoursesBetweenDates(Date firstDate,Date secondDate);
    public List<Course> getCoursesByLecturerId(Long lecturerId);
    public void removeCourse(Long courseId);
    public Course getCourseByName(String courseName);
    public Course getCourseById(Long courseId);
    public void updateCourse(Course course);
}
