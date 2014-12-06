package com.epam.brest.courses.dao;

import com.epam.brest.courses.domain.Lecturer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.junit.Assert.*;
import static org.junit.Assert.assertNotEquals;

import java.util.List;

/**
 * Created by kirill-good on 12/6/14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/spring-dao-test.xml" })
public class LecturerDaoImplTest {
    @Autowired
    private LecturerDao lecturerDao;

    @Test
    public void addUser() {
        List<Lecturer> lecturers= lecturerDao.getLecturers();
        int sizeBefore = lecturers.size();
        Lecturer lecturer= new Lecturer();

        lecturer.setLectorName("Name10");
        lecturerDao.addLector(lecturer);
        lecturers = lecturerDao.getLecturers();
        assertEquals(sizeBefore, lecturers.size() - 1);
    }

    @Test
    public void getUsers() {
        List<Lecturer> lecturers = lecturerDao.getLecturers();
        assertNotNull(lecturers);

    }

    @Test
    public void removeLecturer() {
        Lecturer lecturer=new Lecturer(null,"name");
        Long id=lecturerDao.addLector(lecturer);
        lecturerDao.removeLecturer(id);
        List<Lecturer> lecturers = lecturerDao.getLecturers();
        assertNotNull(lecturers);
        for(Lecturer i:lecturers){
            assertNotEquals(id,i.getLectorId());
        }
    }
    @Test
    public void getLecturerByName(){
        Lecturer lecturer=new Lecturer(null,"name1");
        Long id=lecturerDao.addLector(lecturer);
        Lecturer resLecturer = lecturerDao.getLecturerByName(lecturer.getLectorName());
        assertNotNull(resLecturer);
        assertEquals(resLecturer.getLectorId(),id);
        assertEquals(resLecturer.getLectorName(),lecturer.getLectorName());
    }
    @Test
    public void getLecturerById(){
        Lecturer lecturer=new Lecturer(null,"name2");
        Long id=lecturerDao.addLector(lecturer);
        Lecturer resLecturer = lecturerDao.getLecturerById(id);
        assertNotNull(resLecturer);
        assertEquals(resLecturer.getLectorId(),id);
        assertEquals(resLecturer.getLectorName(),lecturer.getLectorName());
    }

    @Test
    public void updateLecturer(){
        Lecturer lecturer=new Lecturer(null,"name3");
        Long id=lecturerDao.addLector(lecturer);
        Lecturer toUpdatlecturer = new Lecturer(id,"name31");
        lecturerDao.updateLecturer(toUpdatlecturer);
        Lecturer resLecturer = lecturerDao.getLecturerById(id);
        assertEquals(resLecturer.getLectorName(),toUpdatlecturer.getLectorName());

    }
}
