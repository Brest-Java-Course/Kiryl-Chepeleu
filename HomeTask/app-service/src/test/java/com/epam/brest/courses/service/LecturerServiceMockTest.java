package com.epam.brest.courses.service;

import com.epam.brest.courses.dao.LecturerDao;
import com.epam.brest.courses.domain.Lecturer;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static org.easymock.EasyMock.*;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by kirill-good on 12/7/14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:/spring-services-mock-test.xml" })
public class LecturerServiceMockTest {
    @Autowired
    private LecturerService lecturerService;
    @Autowired
    private LecturerDao lecturerDao;
    @After
    public void clean() {
        reset(lecturerDao);
    }
    @Test
    public void getLecturers(){
        List<Lecturer> lecturers=new ArrayList<Lecturer>();
        lecturers.add(new Lecturer());
        lecturerDao.getLecturers();
        expectLastCall().andReturn(lecturers);
        replay(lecturerDao);
        lecturerService.getLecturers();
        verify(lecturerDao);
    }
    @Test(expected = IllegalArgumentException.class)
    public void addLecturerNotNullId(){
        Lecturer lecturer = new Lecturer(11L,"testnamelecturer13");
        Long id = lecturerService.addLecturer(lecturer);
    }
    @Test
    public void addLecturer(){
        Lecturer lecturer = new Lecturer(null,"testnamelecturer13");
        lecturerDao.getLecturerByName(lecturer.getLecturerName());
        expectLastCall().andReturn(null);
        lecturerDao.addLecturer(lecturer);
        expectLastCall().andReturn(5L);
        replay(lecturerDao);
        Long id = lecturerService.addLecturer(lecturer);
        verify(lecturerDao);
    }
    @Test(expected = IllegalArgumentException.class)
    public void addExistLecturer(){
        Lecturer lecturer = new Lecturer(null,"testnamelecturer13");
        lecturerDao.getLecturerByName(lecturer.getLecturerName());
        expectLastCall().andReturn(lecturer);
        replay(lecturerDao);
        Long id = lecturerService.addLecturer(lecturer);
        verify(lecturerDao);
    }
    @Test
    public void removeLecturer() {
        Long id = 5L;
        lecturerDao.removeLecturer(id);
        expectLastCall();
        replay(lecturerDao);
        lecturerService.removeLecturer(id);
        verify(lecturerDao);
    }
    @Test(expected = IllegalArgumentException.class)
    public void removeLecturerNullId() {
        Long id = null;
        lecturerService.removeLecturer(id);
    }
    @Test
    public void getLecturerByName(){
        lecturerDao.getLecturerByName("name");
        expectLastCall().andReturn(null);
        replay(lecturerDao);
        lecturerService.getLecturerByName("name");
        verify(lecturerDao);
    }
    @Test(expected = IllegalArgumentException.class)
    public void getLecturerByNullName(){
        lecturerService.getLecturerByName(null);
    }
    @Test
    public void getLecturerById(){
        lecturerDao.getLecturerById(5L);
        expectLastCall().andReturn(null);
        replay(lecturerDao);
        lecturerService.getLecturerById(5L);
        verify(lecturerDao);
    }
    @Test(expected = IllegalArgumentException.class)
    public void getLecturerByNullId(){
        lecturerService.getLecturerById(null);
    }
    @Test
    public void updateLecturer(){
        Lecturer lecturer = new Lecturer(5L,"name");
        lecturerDao.getLecturerById(lecturer.getLecturerId());
        expectLastCall().andReturn(lecturer);
        lecturerDao.updateLecturer(lecturer);
        expectLastCall();
        replay(lecturerDao);
        lecturerService.updateLecturer(lecturer);
        verify(lecturerDao);
    }
    @Test(expected = IllegalArgumentException.class)
    public void updateNullLecturer(){
        lecturerService.updateLecturer(null);
    }
    @Test(expected = IllegalArgumentException.class)
    public void updateNullNameLecturer(){
        lecturerService.updateLecturer(new Lecturer(1L,null));
    }
    @Test(expected = IllegalArgumentException.class)
    public void updateNullIdLecturer(){
        lecturerService.updateLecturer(new Lecturer(null,""));
    }
    @Test(expected = IllegalArgumentException.class)
    public void updateNotExistLecturer(){
        Lecturer lecturer = new Lecturer(5L,"name");
        lecturerDao.getLecturerById(lecturer.getLecturerId());
        expectLastCall().andReturn(null);
        replay(lecturerDao);
        lecturerService.updateLecturer(lecturer);
        verify(lecturerDao);
    }
    @Test(expected = IllegalArgumentException.class)
    public void getHoursNullService(){
        lecturerService.getHours(new ArrayList<Lecturer>(1),null);
    }
    @Test(expected = IllegalArgumentException.class)
    public void getHoursNullList(){
        lecturerService.getHours(null,null);
    }
}
