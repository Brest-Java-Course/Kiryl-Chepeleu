package com.epam.brest.courses.service;

import com.epam.brest.courses.domain.Course;
import com.epam.brest.courses.domain.Lecturer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static junit.framework.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

/**
 * Created by kirill-good on 12/7/14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/spring-services-test.xml" })
public class CourseServiceImplTest {
    @Autowired
    private CourseService courseService;

    @Test
    public void getCourses(){
        List<Course> courses = courseService.getCourses();
        assertNotNull(courses);
        assertFalse(courses.isEmpty());
    }
}
