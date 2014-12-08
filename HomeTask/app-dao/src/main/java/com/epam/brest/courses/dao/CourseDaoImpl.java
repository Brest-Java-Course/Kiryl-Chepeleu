package com.epam.brest.courses.dao;

import com.epam.brest.courses.domain.Course;
import com.epam.brest.courses.domain.Lecturer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.util.Assert;

import javax.sql.DataSource;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by kirill-good on 12/6/14.
 */
public class CourseDaoImpl implements CourseDao {

    public static final String COURSENAME = "coursename";
    public static final String COURSEID = "courseid";
    public static final String STARTDATE = "startdate";
    public static final String LISTENERS = "listeners";
    public static final String HOURS = "hours";
    public static final String LECTURERID = "lecturerid";
    private static final Logger LOGGER = LogManager.getLogger();
    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedJdbcTemplate;
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        namedJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public Long addCourse(Course course) {
        LOGGER.debug("addCourse({})",course);
        Assert.notNull(course);
        Assert.isNull(course.getCourseId());
        Assert.notNull(course.getCourseName(), "Course name should be specified.");
        Assert.notNull(course.getLecturerId(), "Course lector id should be specified.");
        Assert.notNull(course.getHours(), "Course hours should be specified.");
        Assert.notNull(course.getListeners(), "Course listeners should be specified.");
        Assert.notNull(course.getStartdate(), "Course startdate should be specified.");
        Map<String, Object> parameters = new HashMap(6);
        parameters.put(COURSEID, course.getCourseId());
        parameters.put(COURSENAME, course.getCourseName());
        parameters.put(LECTURERID, course.getLecturerId());
        parameters.put(HOURS, course.getHours());
        parameters.put(LISTENERS, course.getListeners());
        parameters.put(STARTDATE, course.getStartdate());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedJdbcTemplate.update("insert into COURSE (courseid,coursename,lecturerid, hours,listeners,startdate) " +
                "values (:courseid,:coursename,:lecturerid,:hours,:listeners,:startdate)", new MapSqlParameterSource(parameters), keyHolder);
        return keyHolder.getKey().longValue();
    }
    @Override
    public List<Course> getCourses() {
        LOGGER.debug("getCourses()");
        return jdbcTemplate.query("select courseid,coursename,lecturerid, hours,listeners,startdate from COURSE", new CourseMapper());
    }

    @Override
    public List<Course> getCoursesBetweenDates(Date firstDate, Date secondDate) {
        LOGGER.debug("getCoursesBetweenDates({},{})",firstDate,secondDate);
        Map<String, Object> parameters = new HashMap(2);
        parameters.put("firstdate", firstDate);
        parameters.put("seconddate", secondDate);
        return namedJdbcTemplate.query("select courseid,coursename,lecturerid, hours,listeners,startdate" +
                " from COURSE where (startdate >= :firstdate AND startdate <= :seconddate)"
                , parameters ,new CourseMapper());
    }

    @Override
    public List<Course> getCoursesByLecturerId(Long lecturerId) {
        LOGGER.debug("getCoursesByLecturerId({})",lecturerId);
        return jdbcTemplate.query("select courseid,coursename,lecturerid, hours,listeners,startdate from COURSE where lecturerid = " + lecturerId, new CourseMapper());
    }

    @Override
    public void removeCourse(Long courseId) {
        LOGGER.debug("removeCourse({})",courseId);
        jdbcTemplate.update("delete from COURSE where courseid = ?", courseId);
    }

    @Override
    public Course getCourseByName(String courseName) {
        LOGGER.debug("getCourseByName({})",courseName);
        return jdbcTemplate.queryForObject("select courseid,coursename,lecturerid, hours,listeners,startdate from COURSE where coursename = ?",new String[]{courseName}, new CourseMapper());
    }

    @Override
    public Course getCourseById(Long courseId) {
        LOGGER.debug("getCourseById({})",courseId);
        return jdbcTemplate.queryForObject("select courseid,coursename,lecturerid, hours,listeners,startdate from COURSE where courseid = ?", new String[]{String.valueOf(courseId)}, new CourseMapper());
    }

    @Override
    public void updateCourse(Course course) {
        LOGGER.debug("updateCourse({})",course);
        Map<String, Object> parameters = new HashMap(6);
        parameters.put(COURSEID, course.getCourseId());
        parameters.put(COURSENAME, course.getCourseName());
        parameters.put("listeners", course.getListeners());
        parameters.put("hours", course.getHours());
        parameters.put("lecturerid", course.getLecturerId());
        parameters.put("startdate", course.getStartdate());
        namedJdbcTemplate.update("update COURSE set " +
                " lecturerid = :lecturerid," +
                " startdate = :startdate," +
                " coursename = :coursename," +
                " listeners = :listeners," +
                " hours = :hours" +
                " where courseid = :courseid", parameters);
    }

    @Override
    public Long getHoursByLecturerId(Long lecturerId) {
        LOGGER.debug("getHoursByLecturerId({})",lecturerId);
        return jdbcTemplate.queryForLong("select SUM(hours) from COURSE where lecturerid = ?", new String[]{String.valueOf(lecturerId)});
    }

    public class CourseMapper implements RowMapper<Course> {
        public Course mapRow(ResultSet rs, int i) throws SQLException {
            Course course= new Course();
            course.setCourseId(rs.getLong("courseid"));
            course.setCourseName(rs.getString("coursename"));
            course.setLecturerId(rs.getLong("lecturerid"));
            course.setHours(rs.getLong("hours"));
            course.setListeners(rs.getLong("listeners"));
            course.setStartdate(rs.getDate("startdate"));
            return course;
        }
    }
}