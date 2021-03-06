package com.epam.brest.courses.dao;

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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by kirill-good on 12/6/14.
 */
public class LecturerDaoImpl implements LecturerDao {

    public static final String LECTURERNAME = "lecturername";
    public static final String LECTURERID = "lecturerid";
    public static final String SQL_ADD_LECTURER = "insert into LECTURER (lecturerid, lecturername) values (:lecturerid, :lecturername)";
    public static final String SQL_SELECT_ALL_LECTURERS = "select lecturerid, lecturername from LECTURER";
    public static final String SQL_DELETE_LECTURER_BY_ID = "delete from LECTURER where lecturerid = ?";
    public static final String SQL_SELECT_LECTURERS_BY_NAME = "select lecturerid, lecturername from LECTURER where lecturername = ?";
    public static final String SQL_SELECT_LECTURERS_BY_ID = "select lecturerid, lecturername from LECTURER where lecturerid = ?";
    public static final String SQL_UPDATE_LECTURER = "update LECTURER set lecturername = :lecturername where lecturerid = :lecturerid";
    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedJdbcTemplate;
    private static final Logger LOGGER = LogManager.getLogger();
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        namedJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public Long addLecturer(Lecturer lecturer) {
        LOGGER.debug("addLecturer({})",lecturer);
        Assert.notNull(lecturer);
        Assert.isNull(lecturer.getLecturerId());
        Assert.notNull(lecturer.getLecturerName(), "Lecturer name should be specified.");
        Map<String, Object> parameters = new HashMap(3);
        parameters.put("lecturername", lecturer.getLecturerName());
        parameters.put("lecturerid", lecturer.getLecturerId());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedJdbcTemplate.update(SQL_ADD_LECTURER, new MapSqlParameterSource(parameters), keyHolder);
        return keyHolder.getKey().longValue();
    }

    @Override
    public List<Lecturer> getLecturers() {
        LOGGER.debug("getLecturers()");
        return jdbcTemplate.query(SQL_SELECT_ALL_LECTURERS, new LecturerMapper());
    }

    @Override
    public void removeLecturer(Long lecturerId) {
        LOGGER.debug("removeLecturer({})",lecturerId);
        jdbcTemplate.update(SQL_DELETE_LECTURER_BY_ID, lecturerId);
    }

    @Override
    public Lecturer getLecturerByName(String lecturerName) {
        LOGGER.debug("getLecturerByName({}) ", lecturerName);
        return jdbcTemplate.queryForObject(SQL_SELECT_LECTURERS_BY_NAME,new String[]{lecturerName}, new LecturerMapper());
    }

    @Override
    public Lecturer getLecturerById(Long lecturerId) {
        LOGGER.debug("getLecturerById({})",lecturerId);
        return jdbcTemplate.queryForObject(SQL_SELECT_LECTURERS_BY_ID, new String[]{String.valueOf(lecturerId)}, new LecturerMapper());
    }

    @Override
    public void updateLecturer(Lecturer lecturer) {
        LOGGER.debug("updateLecturer({})",lecturer);
        Map<String, Object> parameters = new HashMap(3);
        parameters.put(LECTURERNAME, lecturer.getLecturerName());
        parameters.put(LECTURERID, lecturer.getLecturerId());
        namedJdbcTemplate.update(SQL_UPDATE_LECTURER, parameters);
    }

    public class LecturerMapper implements RowMapper<Lecturer> {
        public Lecturer mapRow(ResultSet rs, int i) throws SQLException {
            Lecturer lecturer= new Lecturer();
            lecturer.setLecturerId(rs.getLong("lecturerid"));
            lecturer.setLecturerName(rs.getString("lecturername"));
            return lecturer;
        }
    }

}
