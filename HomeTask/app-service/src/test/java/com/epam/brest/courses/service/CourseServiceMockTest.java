package com.epam.brest.courses.service;

import com.epam.brest.courses.dao.CourseDao;
import com.epam.brest.courses.domain.Course;
import com.epam.brest.courses.domain.Lecturer;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static org.easymock.EasyMock.*;
import static org.junit.Assert.*;

/**
 * Created by kirill-good on 12/7/14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/spring-services-mock-test.xml" })
public class CourseServiceMockTest {
    @Autowired
    private CourseService courseService;
    @Autowired
    private CourseDao courseDao;
    @After
    public void clean() {
        reset(courseDao);
    }
    @Test
    public void getCourses(){
        List<Course> courses=new ArrayList<Course>();
        courses.add(new Course());
        courseDao.getCourses();
        expectLastCall().andReturn(courses);
        replay(courseDao);
        courseService.getCourses();
        verify(courseDao);
    }

    @Test
    public void addCourse(){
        Course course = new Course(null,"name",1L,2L,3L,new Date(2000,10,10));
        expect(courseDao.getCourseByName("name")).andReturn(null);
        expect(courseDao.addCourse(course)).andReturn(10L);
        replay(courseDao);
        courseService.addCourse(course);
        verify(courseDao);
    }
    @Test(expected = IllegalArgumentException.class)
    public void addNullCourse(){
        courseService.addCourse(null);
    }
    @Test(expected = IllegalArgumentException.class)
    public void notNullIdCourse(){
        Course course = new Course();
        course.setCourseId(1L);
        courseService.addCourse(course);
    }
    @Test(expected = IllegalArgumentException.class)
    public void addNullNameCourse(){
        Course course = new Course();
        course.setCourseName(null);
        courseService.addCourse(course);
    }

    @Test
    public void removeLecturer(){
        Course course=new Course(3L,"course_name1",3L,111L,13L,new Date(2014,9,26));
        courseDao.removeCourse(course.getCourseId());
        expectLastCall();
        replay(courseDao);
        courseService.removeCourse(course.getCourseId());
        verify(courseDao);
    }

    @Test(expected = IllegalArgumentException.class)
    public void removeNUllIdLecturer(){
        Course course=new Course(null,"course_name1",3L,111L,13L,new Date(2014,9,26));
        courseService.removeCourse(course.getCourseId());
    }

    @Test
    public void getCourseByName(){
        Course course=new Course(null,"course_name2",4L,1111L,13L,new Date(2014,9,26));
        expect(courseDao.getCourseByName(course.getCourseName())).andReturn(course);
        replay(courseDao);
        Course resCourse = courseService.getCourseByName(course.getCourseName());
        verify(courseDao);
    }
    @Test(expected = IllegalArgumentException.class)
    public void getCourseByNullName(){
        courseService.getCourseByName(null);
    }

    @Test
    public void updateCourse(){
        Course course=new Course(3L,"course_name2",4L,1111L,13L,new Date(2014,9,26));
        courseDao.updateCourse(course);
        expectLastCall();
        expect(courseDao.getCourseById(course.getCourseId())).andReturn(course);
        replay(courseDao);
        courseService.updateCourse(course);
        verify(courseDao);
    }
    @Test(expected = IllegalArgumentException.class)
    public void updateNotExistCourse(){
        Course course=new Course(3L,"course_name2",4L,1111L,13L,new Date(2014,9,26));
        courseDao.updateCourse(course);
        expectLastCall();
        expect(courseDao.getCourseById(course.getCourseId())).andReturn(null);
        replay(courseDao);
        courseService.updateCourse(course);
        verify(courseDao);
    }
    @Test(expected = IllegalArgumentException.class)
    public void updateNullCourse(){
        courseService.updateCourse(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getCoursesBetweenNullDates(){

        courseService.getCoursesBetweenDates(null,null);
    }
    @Test
    public void getCoursesBetweenDates(){
        Date firstDate = new Date(2000,10,10);
        Date secondDate = new Date(2000,10,10);
        courseDao.getCoursesBetweenDates(firstDate,secondDate);
        expectLastCall().andReturn(new ArrayList<Course>());
        replay(courseDao);
        courseService.getCoursesBetweenDates(firstDate,secondDate);
        verify(courseDao);
    }

    @Test
    public void getHoursByLecturerId(){
        courseDao.getHoursByLecturerId(3L);
        expectLastCall().andReturn(new Long(1));
        replay(courseDao);
        courseService.getHoursByLecturerId(3L);
        verify(courseDao);
    }
    @Test(expected = IllegalArgumentException.class)
    public void getHoursByNullLecturerId(){
        courseService.getHoursByLecturerId(null);
    }
}
