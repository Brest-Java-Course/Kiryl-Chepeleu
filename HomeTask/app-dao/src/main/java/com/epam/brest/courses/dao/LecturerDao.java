package com.epam.brest.courses.dao;

import com.epam.brest.courses.domain.Lecturer;

import java.util.List;

/**
 * Created by kirill-good on 12/6/14.
 */
public interface LecturerDao {
    public Long addLecturer(Lecturer lecturer);
    public List<Lecturer> getLecturers();
    public void removeLecturer(Long userId);

    public Lecturer getLecturerByName(String lecturerName);
    public Lecturer getLecturerById(Long lecturerId);
    public void updateLecturer(Lecturer lecturer);
}
