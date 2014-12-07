package com.epam.brest.courses.dao;

import com.epam.brest.courses.domain.Course;
import com.epam.brest.courses.domain.Lecturer;
import junit.framework.Assert;
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
 * Created by kirill-good on 12/6/14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/spring-dao-test.xml" })
public class CourseDaoImplTest {
    @Autowired
    private CourseDao courseDao;
    @Test
    public void getCourses(){
        List<Course> courses = courseDao.getCourses();
        assertNotNull(courses);
        assertFalse(courses.isEmpty());
    }
    @Test
    public void getCoursesByLecturerId(){
        Long ids[]={2L,3L};
        for(Long i:ids) {
            List<Course> courses = courseDao.getCoursesByLecturerId(i);
            assertNotNull(courses);
            for(Course j:courses){
                assertEquals(j.getLecturerId(),i);
            }
        }
    }
    @Test
    public void addCourse() {
        List<Course> courses= courseDao.getCourses();
        int sizeBefore = courses.size();
        Course course= new Course();

        course.setCourseName("Name10");
        course.setStartdate(new Date(2014, 10, 22));
        course.setListeners(12L);
        course.setHours(333L);
        course.setLecturerId(2L);
        courseDao.addCourse(course);
        courses = courseDao.getCourses();
        assertEquals(sizeBefore, courses.size() - 1);
    }
    @Test
    public void removeCourse() {
        Course course=new Course(null,"course_name1",3L,111L,13L,new Date(2014,9,26));
        Long id=courseDao.addCourse(course);
        courseDao.removeCourse(id);
        List<Course> courses = courseDao.getCourses();
        assertNotNull(courses);
        for(Course i:courses){
            assertNotEquals(id,i.getCourseId());
        }
    }

    @Test
    public void getCourseByName(){
        Course course=new Course(null,"course_name2",4L,1111L,13L,new Date(2014,9,26));
        Long id=courseDao.addCourse(course);
        Course resCourse = courseDao.getCourseByName(course.getCourseName());
        assertNotNull(resCourse);
        assertEquals(resCourse.getCourseId(),id);
        assertEquals(resCourse.getCourseName(), course.getCourseName());
    }

    @Test
    public void updateCourse(){
        Course course=new Course(null,"course_name2",4L,1111L,13L,new Date(2014,9,26));
        Long id=courseDao.addCourse(course);
        Course toUpdatCourse = new Course(id,"course_name3",5L,1111L,13L,new Date(2014,9,22));
        courseDao.updateCourse(toUpdatCourse);
        Course resCourse = courseDao.getCourseById(id);
        assertEquals(resCourse.getCourseName(),toUpdatCourse.getCourseName());
        assertEquals(resCourse.getCourseId(),toUpdatCourse.getCourseId());
        assertEquals(resCourse.getHours(),toUpdatCourse.getHours());
        assertEquals(resCourse.getLecturerId(),toUpdatCourse.getLecturerId());
        assertEquals(resCourse.getStartdate(),toUpdatCourse.getStartdate());

    }
    @Test
    public void getCoursesBetweenDates(){
        List<Course> courses= courseDao.getCoursesBetweenDates(new Date(2014-1900,12-1,1),new Date(2014-1900,12-1,31));
        assertEquals(courses.size(),1);
        assertEquals(courses.get(0).getCourseId().longValue(), 5L);
    }
}
