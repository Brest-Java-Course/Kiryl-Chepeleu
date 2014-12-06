package com.epam.brest.courses.dao;

import com.epam.brest.courses.domain.Course;
import com.epam.brest.courses.domain.Lecturer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.util.Assert;

import javax.sql.DataSource;
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
    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedJdbcTemplate;
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        namedJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public Long addCourse(Course course) {
        /*Assert.notNull(course);
        Assert.isNull(course.getLectorId());
        Assert.notNull(course.getCourseName(), "Course name should be specified.");
        Map<String, Object> parameters = new HashMap(3);
        parameters.put(COURSENAME, course.getCourseName());
        parameters.put(COURSEID, course.getCourseId());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedJdbcTemplate.update("insert into LECTURER (lecturerid, lecturername) values (:lecturerid, :lecturername)", new MapSqlParameterSource(parameters), keyHolder);
        return keyHolder.getKey().longValue();*/
        return null;
    }

    @Override
    public List<Course> getCourses() {
        return null;
    }

    @Override
    public void removeCourse(Long courseId) {

    }

    @Override
    public Lecturer getCourseByName(String courseName) {
        return null;
    }

    @Override
    public Lecturer getCourseById(Long courseId) {
        return null;
    }

    @Override
    public void updateCourse(Course course) {

    }

    public class UserMapper implements RowMapper<Lecturer> {
        public Lecturer mapRow(ResultSet rs, int i) throws SQLException {
            Lecturer lecturer= new Lecturer();
            lecturer.setLectorId(rs.getLong("lecturerid"));
            lecturer.setLectorName(rs.getString("lecturername"));
            return lecturer;
        }
    }
}