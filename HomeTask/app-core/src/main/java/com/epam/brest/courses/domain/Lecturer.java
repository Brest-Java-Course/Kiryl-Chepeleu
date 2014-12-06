package com.epam.brest.courses.domain;

/**
 * Created by kirill-good on 11/29/14.
 */
public class Lecturer {
    public Long getLectorId() {
        return lectorId;
    }

    public void setLectorId(Long lectorId) {
        this.lectorId = lectorId;
    }

    public String getLectorName() {
        return lectorName;
    }

    public void setLectorName(String lectorName) {
        this.lectorName = lectorName;
    }

    private Long lectorId;
    private String lectorName;


}
