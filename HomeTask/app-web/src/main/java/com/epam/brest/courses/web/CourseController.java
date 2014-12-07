package com.epam.brest.courses.web;
import com.epam.brest.courses.domain.Course;
import com.epam.brest.courses.domain.Lecturer;
import com.epam.brest.courses.service.CourseService;
import com.epam.brest.courses.service.LecturerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Date;
import java.util.List;

/**
 * Created by kirill-good on 12/7/14.
 */
@Controller
public class CourseController {
    @Autowired
    CourseService courseService;
    private static final Logger LOGGER = LogManager.getLogger();

    @RequestMapping("/courses")
    public ModelAndView getListCoursesView() {
        List<Course> courses= courseService.getCourses();
        LOGGER.debug("courses.size = " + courses.size());
        ModelAndView view = new ModelAndView("coursesList", "courses", courses);
        return view;
    }
    @RequestMapping("/submitCourse")
    public String getInputFormCourse(@RequestParam("name")String courseName,
                                       @RequestParam("lecturerid")Long courseLecturerId,
                                       @RequestParam("hours")Long hours,
                                       @RequestParam("listeners")Long listeners,
                                       @RequestParam("startdate")Date startdate) {
        Course course = new Course();
        course.setStartdate(startdate);
        course.setCourseName(courseName);
        course.setLecturerId(listeners);
        course.setHours(hours);
        course.setListeners(Long.valueOf(listeners));
        courseService.addCourse(course);
        return "redirect:/courses";
    }
    @RequestMapping("/updateCourse")
    public String getUpdateCourse(@RequestParam("id")Long id,
                                     @RequestParam("name")String courseName,
                                     @RequestParam("lecturerid")Long courseLecturerId,
                                     @RequestParam("hours")Long hours,
                                     @RequestParam("listeners")Long listeners,
                                     @RequestParam("startdate")Date startdate) {
        Course course = new Course();
        course.setCourseId(id);
        course.setStartdate(startdate);
        course.setCourseName(courseName);
        course.setLecturerId(listeners);
        course.setHours(hours);
        course.setListeners(Long.valueOf(listeners));
        courseService.updateCourse(course);
        return "redirect:/courses";
    }

    @RequestMapping("/deleteCourse")
    public String getDeleteCourse(@RequestParam("courserid")Long courseid) {
        courseService.removeCourse(courseid);
        return "redirect:/courses";
    }

    @RequestMapping("/filter")
    public String getDeleteCourse() {
        return "filterByDate";
    }

    @RequestMapping("/updateFormCourse")
    public ModelAndView getUpdateCourse(@RequestParam("id")Long id) {
        Course course = courseService.getCourseById(id);
        ModelAndView view = new ModelAndView("FormCourse", "course", course);
        return view;
    }

    @RequestMapping("/coursesbetweendates")
    public ModelAndView getListCoursesBetweenDatesView(@RequestParam("firstdate")Date firstdate,@RequestParam("seconddate")Date seconddate) {
        List<Course> courses= courseService.getCoursesBetweenDates(firstdate,seconddate);

        LOGGER.debug("courses.size = " + courses.size());
        ModelAndView view = new ModelAndView("coursesList", "courses", courses);

        return view;
    }
}
