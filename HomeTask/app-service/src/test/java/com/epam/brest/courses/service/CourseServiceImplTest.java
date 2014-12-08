package com.epam.brest.courses.service;

import com.epam.brest.courses.domain.Course;
import com.epam.brest.courses.domain.Lecturer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Date;
import java.util.List;

import static junit.framework.Assert.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
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

    @Test
    public void addLecturer(){
        List<Course> courses= courseService.getCourses();
        int sizeBefore = courses.size();
        Course course= new Course();
        course.setCourseName("Name10");
        course.setStartdate(new Date(2014, 10, 22));
        course.setListeners(12L);
        course.setHours(333L);
        course.setLecturerId(2L);
        courseService.addCourse(course);
        courses = courseService.getCourses();
        assertEquals(sizeBefore, courses.size() - 1);
    }

    @Test
    public void removeLecturer(){
        Course course=new Course(null,"course_name1",3L,111L,13L,new Date(2014,9,26));
        Long id=courseService.addCourse(course);
        courseService.removeCourse(id);
        List<Course> courses = courseService.getCourses();
        assertNotNull(courses);
        for(Course i:courses){
            assertNotEquals(id,i.getCourseId());
        }
    }

    @Test
    public void getCourseByName(){
        Course course=new Course(null,"course_name2",4L,1111L,13L,new Date(2014,9,26));
        Long id=courseService.addCourse(course);
        Course resCourse = courseService.getCourseByName(course.getCourseName());
        assertNotNull(resCourse);
        assertEquals(resCourse.getCourseId(),id);
        assertEquals(resCourse.getCourseName(), course.getCourseName());
    }

    @Test
    public void updateCourse(){
        Course course=new Course(null,"course_name2",4L,1111L,13L,new Date(2014,9,26));
        Long id=courseService.addCourse(course);
        Course toUpdatCourse = new Course(id,"course_name3",5L,1111L,13L,new Date(2014,9,22));
        courseService.updateCourse(toUpdatCourse);
        Course resCourse = courseService.getCourseById(id);
        assertEquals(resCourse.getCourseName(),toUpdatCourse.getCourseName());
        assertEquals(resCourse.getCourseId(),toUpdatCourse.getCourseId());
        assertEquals(resCourse.getHours(),toUpdatCourse.getHours());
        assertEquals(resCourse.getLecturerId(),toUpdatCourse.getLecturerId());
        assertEquals(resCourse.getStartdate(),toUpdatCourse.getStartdate());
    }
    @Test
    public void getCoursesBetweenDates(){
        List<Course> courses= courseService.getCoursesBetweenDates(new Date(2014-1900,12-1,1),new Date(2014-1900,12-1,31));
        assertEquals(courses.size(),1);
        assertEquals(courses.get(0).getCourseId().longValue(), 5L);
    }
    @Test
    public void getHoursByLecturerId(){
        List<Course> courses= courseService.getCoursesByLecturerId(3L);
        long etalon=0;
        for(Course i:courses){
            etalon+=i.getHours();
        }
        long res = courseService.getHoursByLecturerId(3L);
        assertEquals(etalon,res);
    }
}
