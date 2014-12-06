package com.epam.brest.courses.dao;

import com.epam.brest.courses.domain.Course;
import com.epam.brest.courses.domain.Lecturer;

import java.util.List;

/**
 * Created by kirill-good on 12/6/14.
 */
public interface CourseDao {
    public Long addCourse(Course course);
    public List<Course> getCourses();
    public void removeCourse(Long courseId);
    public Lecturer getCourseByName(String courseName);
    public Lecturer getCourseById(Long courseId);
    public void updateCourse(Course course);
}
