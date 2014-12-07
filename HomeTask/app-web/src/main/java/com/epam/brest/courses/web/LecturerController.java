package com.epam.brest.courses.web;

import com.epam.brest.courses.domain.Lecturer;
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
public class LecturerController {
    @Autowired
    LecturerService lecturerService;
    private static final Logger LOGGER = LogManager.getLogger();

    @RequestMapping("/lecturers")
    public ModelAndView getListUsersView() {
        List<Lecturer> lecturers = lecturerService.getLecturers();
        LOGGER.debug("lecturers.size = " + lecturers.size());
        ModelAndView view = new ModelAndView("lecturersList", "lecturers", lecturers);
        return view;
    }
}