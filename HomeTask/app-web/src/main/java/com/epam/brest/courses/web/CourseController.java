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
import org.springframework.web.servlet.ModelAndView;

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
}
