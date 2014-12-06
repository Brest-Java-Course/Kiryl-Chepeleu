package com.epam.brest.courses.service;

import com.epam.brest.courses.domain.Lecturer;

import java.util.List;

/**
 * Created by kirill on 24.10.14.
 */
public interface LecturerService {

    public Long addLecturer(Lecturer lecturer);
    public List<Lecturer> getLecturers();
    public void removeLecturer(Long userId);
    public Lecturer getLecturerByName(String lecturerName);
    public Lecturer getLecturerById(Long lecturerId);
    public void updateLecturer(Lecturer lecturer);

}
