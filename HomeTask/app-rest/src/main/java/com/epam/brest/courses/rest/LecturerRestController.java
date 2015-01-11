package com.epam.brest.courses.rest;

import com.epam.brest.courses.domain.Lecturer;
import com.epam.brest.courses.service.CourseService;
import com.epam.brest.courses.service.LecturerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by kirill-good on 12/7/14.
 */
@Controller
@RequestMapping("/rest/lecturers")
public class LecturerRestController {
    @Autowired
    LecturerService lecturerService;
    @Autowired
    CourseService courseService;
    private static final Logger LOGGER = LogManager.getLogger();

    @ResponseBody
    @RequestMapping(method= RequestMethod.GET)
    public ResponseEntity<List<Lecturer>> getListLecturers() {

        List<Lecturer> lecturers = lecturerService.getLecturers();
        LOGGER.debug("lecturers.size = " + lecturers.size());
        lecturerService.getHours(lecturers,courseService);
        return new ResponseEntity(lecturers, HttpStatus.OK);
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Lecturer> getLecturerById(@PathVariable Long id) {
        try {
            Lecturer lecturer = lecturerService.getLecturerById(id);
            return new ResponseEntity(lecturer, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity("Lecturer not found for id=" + id + " error:"
                    + ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @ResponseBody
    @RequestMapping(method= RequestMethod.POST)
    public ResponseEntity<Long> addLecturer(@RequestBody Lecturer lecturer) {
        Long id = lecturerService.addLecturer(lecturer);
        return new ResponseEntity(id, HttpStatus.CREATED);
    }
    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity updateLecturer(@RequestBody Lecturer lecturer) {
        lecturerService.updateLecturer(lecturer);
        return new ResponseEntity("", HttpStatus.OK);
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity removeLecturer(@PathVariable Long id) {
        lecturerService.removeLecturer(id);
        return new ResponseEntity("", HttpStatus.OK);
    }
}
