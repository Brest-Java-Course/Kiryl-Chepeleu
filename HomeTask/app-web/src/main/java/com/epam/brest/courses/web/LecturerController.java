package com.epam.brest.courses.web;

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

import java.util.List;

/**
 * Created by kirill-good on 12/7/14.
 */
@Controller
public class LecturerController {
    @Autowired
    LecturerService lecturerService;
    @Autowired
    CourseService courseService;
    private static final Logger LOGGER = LogManager.getLogger();

    @RequestMapping("/lecturers")
    public ModelAndView getListLecturersView() {

        List<Lecturer> lecturers = lecturerService.getLecturers();
        LOGGER.debug("lecturers.size = " + lecturers.size());
        ModelAndView view = new ModelAndView("lecturersList", "lecturers", lecturers);
        lecturerService.getHours(lecturers,courseService);
        return view;
    }

    @RequestMapping("/submitLecturer")
    public String getInputFormLecturer(@RequestParam("name")String lecturerName) {
        Lecturer lecturer = new Lecturer(null,lecturerName);
        lecturerService.addLecturer(lecturer);
        return "redirect:/lecturers";
    }
    @RequestMapping("/deleteLecturer")
    public String getDeleteLecturer(@RequestParam("lecturerid")Long lecturerid) {
        lecturerService.removeLecturer(lecturerid);
        return "redirect:/lecturers";
    }

    @RequestMapping("/updateLecturer")
    public String getUpdateLecturer(@RequestParam("id")Long id,
                                  @RequestParam("name")String lecturerName) {
        Lecturer lecturer = new Lecturer();
        lecturer.setLecturerId(id);
        lecturer.setLecturerName(lecturerName);
        lecturerService.updateLecturer(lecturer);
        return "redirect:/lecturers";
    }
    @RequestMapping("/updateFormLecturer")
    public ModelAndView getUpdateLecturer(@RequestParam("id")Long id) {

        Lecturer lecturer = lecturerService.getLecturerById(id);
        ModelAndView view = new ModelAndView("FormLecturer", "lecturer", lecturer);
        return view;
    }
}
