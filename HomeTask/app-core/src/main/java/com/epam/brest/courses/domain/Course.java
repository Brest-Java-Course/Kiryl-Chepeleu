package com.epam.brest.courses.domain;

import java.sql.Date;

/**
 * Created by kirill-good on 11/29/14.
 */
public class Course {
    private Long courseId;
    private Long lecturerId;
    private Long hours;
    private Long listeners;
    private Date startdate;
    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Long getLectorId() {
        return lecturerId;
    }

    public void setLectorId(Long lectorId) {
        this.lecturerId = lectorId;
    }

    public Long getHours() {
        return hours;
    }

    public void setHours(Long hours) {
        this.hours = hours;
    }

    public Long getListeners() {
        return listeners;
    }

    public void setListeners(Long listeners) {
        this.listeners = listeners;
    }

    private String courseName;
}
