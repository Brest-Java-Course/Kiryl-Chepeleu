package com.epam.brest.courses.domain;

import java.sql.Date;

/**
 * Created by kirill-good on 11/29/14.
 */
public class Course {
    private Long courseId;
    private String courseName;
    private Long lecturerId;
    private Long hours;
    private Long listeners;
    private Date startdate;

    public Course(Long courseId, String courseName, Long lecturerId, Long hours, Long listeners, Date startdate) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.lecturerId = lecturerId;
        this.hours = hours;
        this.listeners = listeners;
        this.startdate = startdate;
    }
    public Course(){

    }
    @Override
    public String toString() {
        return "Course{" +
                "courseName='" + courseName + '\'' +
                ", lecturerId=" + lecturerId +
                ", hours=" + hours +
                ", listeners=" + listeners +
                ", startdate=" + startdate +
                ", courseId=" + courseId +
                '}';
    }

    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }


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

    public Long getLecturerId() {
        return lecturerId;
    }

    public void setLecturerId(Long lectorId) {
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


}
