package com.epam.brest.cources.dao;

import com.epam.brest.courses.domain.User;

import java.util.List;

/**
 * Created by kirill on 20.10.14.
 */
public interface UserDao {
    public List<User> getUsers();
    public void removeUser(long userId);
    public void addUser(User user);
}
