package com.epam.brest.courses.service;

import com.epam.brest.courses.dao.LecturerDao;
import com.epam.brest.courses.domain.Course;
import com.epam.brest.courses.domain.Lecturer;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by kirill-good on 12/7/14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/spring-services-test.xml" })
public class LecturerServiceImplTest {
    @Autowired
    private LecturerService lecturerService;

    @Test
    public void getLecturers(){
        List<Lecturer> courses = lecturerService.getLecturers();
        assertNotNull(courses);
        assertFalse(courses.isEmpty());
    }

    @Test
    public void addLecturer(){
        Lecturer lecturer = new Lecturer(null,"testnamelecturer13");
        Long id = lecturerService.addLecturer(lecturer);
        Lecturer resLecturer = lecturerService.getLecturerById(id);
        assertEquals(resLecturer.getLecturerName(),lecturer.getLecturerName());
    }

    @Test
    public void removeLecturer() {
        Lecturer lecturer=new Lecturer(null,"name");
        Long id=lecturerService.addLecturer(lecturer);
        lecturerService.removeLecturer(id);
        List<Lecturer> lecturers = lecturerService.getLecturers();
        assertNotNull(lecturers);
        for(Lecturer i:lecturers){
            assertNotEquals(id,i.getLecturerId());
        }
    }
    @Test
    public void getLecturerByName(){
        Lecturer lecturer=new Lecturer(null,"name1");
        Long id=lecturerService.addLecturer(lecturer);
        Lecturer resLecturer = lecturerService.getLecturerByName(lecturer.getLecturerName());
        assertNotNull(resLecturer);
        Assert.assertEquals(resLecturer.getLecturerId(), id);
        Assert.assertEquals(resLecturer.getLecturerName(), lecturer.getLecturerName());
    }
    @Test
    public void getLecturerById(){
        Lecturer lecturer=new Lecturer(null,"name2");
        Long id=lecturerService.addLecturer(lecturer);
        Lecturer resLecturer = lecturerService.getLecturerById(id);
        assertNotNull(resLecturer);
        Assert.assertEquals(resLecturer.getLecturerId(), id);
        Assert.assertEquals(resLecturer.getLecturerName(), lecturer.getLecturerName());
    }

    @Test
    public void updateLecturer(){
        Lecturer lecturer=new Lecturer(null,"name3");
        Long id=lecturerService.addLecturer(lecturer);
        Lecturer toUpdatlecturer = new Lecturer(id,"name31");
        lecturerService.updateLecturer(toUpdatlecturer);
        Lecturer resLecturer = lecturerService.getLecturerById(id);
        Assert.assertEquals(resLecturer.getLecturerName(), toUpdatlecturer.getLecturerName());

    }
}
