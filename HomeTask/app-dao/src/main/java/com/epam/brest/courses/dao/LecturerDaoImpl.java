package com.epam.brest.courses.dao;

import com.epam.brest.courses.domain.Lecturer;

import java.util.List;

/**
 * Created by kirill-good on 12/6/14.
 */
public class LecturerDaoImpl implements LecturerDao {
    public static final String ADD_NEW_USER_SQL = "insert into USER (userid, login, name) values (:userid, :login, :name)";


    public static final String DELETE_USER_SQL = "delete from USER where userid = ?";
    public static final String UPDATE_USER_SQL = "update user set name = :name, login = :login where userid = :userid";

    public static final String SELECT_USER_BY_LOGIN_SQL = "select userid, login, name from USER where LCASE(login) = ?";
    public static final String SELECT_ALL_USERS_SQL = "select userid, login, name from USER";
    public static final String SELECT_USER_BY_ID_SQL = "select userid, login, name from USER where userid = ?";

    public static final String USER_ID = "userid";
    public static final String LOGIN = "login";
    public static final String NAME = "name";

    @Override
    public Long addLector(Lecturer lector) {
        return null;
    }

    @Override
    public List<Lecturer> getLecturers() {
        return null;
    }

    @Override
    public void removeUser(Long userId) {

    }

    @Override
    public Lecturer getLecturerName(String name) {
        return null;
    }

    @Override
    public Lecturer getLecturerById(long userId) {
        return null;
    }

    @Override
    public void updateUser(Lecturer lecturer) {

    }
}
