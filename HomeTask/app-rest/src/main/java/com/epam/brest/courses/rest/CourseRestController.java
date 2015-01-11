package com.epam.brest.courses.rest;
import com.epam.brest.courses.domain.Course;
import com.epam.brest.courses.domain.Lecturer;
import com.epam.brest.courses.service.CourseService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Date;
import java.util.List;

/**
 * Created by kirill-good on 12/7/14.
 */
@Controller
@RequestMapping("/rest/courses")
public class CourseRestController {
    @Autowired
    CourseService courseService;
    private static final Logger LOGGER = LogManager.getLogger();
    @ResponseBody
    @RequestMapping(method= RequestMethod.GET)
    public ResponseEntity<List<Lecturer>> getListCourses() {

        List<Course> courses = courseService.getCourses();
        LOGGER.debug("lecturers.size = " + courses.size());
        return new ResponseEntity(courses, HttpStatus.OK);
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Lecturer> getCourseById(@PathVariable Long id) {
        try {
            Course course = courseService.getCourseById(id);
            return new ResponseEntity(course, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity("Lecturer not found for id=" + id + " error:"
                    + ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @ResponseBody
    @RequestMapping(method= RequestMethod.POST)
    public ResponseEntity<Long> addCourse(@RequestBody Course course) {
        Long id = courseService.addCourse(course);
        return new ResponseEntity(id, HttpStatus.CREATED);
    }
    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity updateCourse(@RequestBody Course course) {
        courseService.updateCourse(course);
        return new ResponseEntity("", HttpStatus.OK);
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity removeCourse(@PathVariable Long id) {
        courseService.removeCourse(id);
        return new ResponseEntity("", HttpStatus.OK);
    }
}
