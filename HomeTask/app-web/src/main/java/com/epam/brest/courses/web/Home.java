package com.epam.brest.courses.web;

import com.epam.brest.courses.service.CourseService;
import com.epam.brest.courses.service.LecturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.ModelMap;

@Controller
public class Home {
    @Autowired
    LecturerService lecturerService;
    @Autowired
    CourseService courseService;

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String printHello(ModelMap model) {
        return "hello";
    }
}