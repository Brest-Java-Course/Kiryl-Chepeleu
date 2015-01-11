package com.epam.brest.courses.domain;

/**
 * Created by kirill-good on 11/29/14.
 */
public class Lecturer {


    public Long getLecturerId() {
        return lecturerId;
    }

    public void setLecturerId(Long lecturerId) {
        this.lecturerId = lecturerId;
    }

    public String getLecturerName() {
        return lecturerName;
    }

    public void setLecturerName(String lecturerName) {
        this.lecturerName = lecturerName;
    }

    private Long lecturerId;

    @Override
    public String toString() {
        return "Lecturer{" +
                "lecturerId=" + lecturerId +
                ", lecturerName='" + lecturerName + '\'' +
                ", totalHours=" + totalHours +
                '}';
    }

    public Lecturer(Long lecturerId, String lecturerName) {
        this.lecturerId = lecturerId;
        this.lecturerName = lecturerName;
    }

    private String lecturerName;
    public Lecturer(){

    }

    public Long getTotalHours() {
        return totalHours;
    }

    public void setTotalHours(Long totalHours) {
        this.totalHours = totalHours;
    }

    private Long totalHours;
}
