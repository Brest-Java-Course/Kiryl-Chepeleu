package com.epam.brest.cources.dao;

import com.epam.brest.courses.domain.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by kirill on 20.10.14.
 */
public class UserDaoImpl implements UserDao{
    private JdbcTemplate jdbcTemplate;
    public void setDataSource(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate((javax.sql.DataSource) dataSource);
    }
    @Override
    public List<User> getUsers() {
        return jdbcTemplate.query("SELECT userid, login, name FROM USER", new UserMapper());
    }

    @Override
    public void removeUser(long userId) {

    }

    @Override
    public void addUser(User user) {
        jdbcTemplate.update("INSERT INTO USER (userid,login,name)=(?,?,?)",
                            user.getUserId(),user.getLogin(),user.getUserName());
    }
    public class UserMapper implements RowMapper<User> {

        public User mapRow(ResultSet rs,int i)throws SQLException{
            User user=new User();
            user.setUserId(rs.getLong("userid"));
            user.setLogin(rs.getString("login"));
            user.setUserName(rs.getString("name"));
            return user;
        }
    }
}
