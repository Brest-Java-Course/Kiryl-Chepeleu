package com.epam.brest.courses.dao;

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
public class LecturerDaoImpl implements LecturerDao {

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedJdbcTemplate;
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        namedJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public Long addLector(Lecturer lector) {
        Assert.notNull(lector);
        Assert.isNull(lector.getLectorId());
        Assert.notNull(lector.getLectorName(), "Lecturer name should be specified.");
        Map<String, Object> parameters = new HashMap(3);
        parameters.put("lecturername", lector.getLectorName());
        parameters.put("lecturerid", lector.getLectorId());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedJdbcTemplate.update("insert into LECTURER (lecturerid, lecturername) values (:lecturerid, :lecturername)", new MapSqlParameterSource(parameters), keyHolder);
        return keyHolder.getKey().longValue();
    }

    @Override
    public List<Lecturer> getLecturers() {
        return jdbcTemplate.query("select lecturerid, lecturername from LECTURER", new UserMapper());
    }

    @Override
    public void removeUser(Long userId) {
        jdbcTemplate.update("delete from LECTURER where lecturerid = ?", userId);
    }

    @Override
    public Lecturer getLecturerByName(String lecturerName) {
        return jdbcTemplate.queryForObject("select lecturerid, lecturername from LECTURER where lecturername = ?",new String[]{lecturerName}, new UserMapper());
    }

    @Override
    public Lecturer getLecturerById(Long lecturerId) {
        return jdbcTemplate.queryForObject("select lecturerid, lecturername from LECTURER where lecturerid = ?",new String[]{String.valueOf(lecturerId)}, new UserMapper());
    }

    @Override
    public void updateLecturer(Lecturer lecturer) {

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
