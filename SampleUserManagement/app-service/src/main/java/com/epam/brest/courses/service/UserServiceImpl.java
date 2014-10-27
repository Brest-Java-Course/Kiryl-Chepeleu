package com.epam.brest.courses.service;

import com.epam.brest.courses.dao.UserDao;
import com.epam.brest.courses.domain.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.util.Assert;

import java.util.List;

/**
 * Created by mentee-42 on 24.10.14.
 */
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LogManager.getLogger();

    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void addUser(User user) {
        Assert.notNull(user);
        Assert.isNull(user.getUserId());
        Assert.notNull(user.getLogin(), "User login should be specified.");
        Assert.notNull(user.getName(), "User name should be specified.");
        User existingUser = getUserByLogin(user.getLogin());
        if (existingUser != null) {
            throw new IllegalArgumentException("User is present in DB");
        }
        userDao.addUser(user);
    }


    @Override
    public User getUserByLogin(String login) {
        User user = null;
        try {
            user = userDao.getUserByLogin(login);
        } catch (EmptyResultDataAccessException e) {
            LOGGER.debug("EmptyResultDataAccessException");
        }
        return user;
    }

    @Override
    public void removeUser(Long userId) {
        User user = userDao.getUserById(userId);
        Assert.isTrue(!user.getLogin().equals("login"));
        LOGGER.debug("removeUser(userId={}) ", userId);
        userDao.removeUser(userId);
    }

    @Override
    public User getUserById(long userId) {
        LOGGER.debug("getUserById(userId={}) ", userId);
        return userDao.getUserById(userId);
    }

    @Override
    public void updateUser(User user) {
        User oldUser = userDao.getUserById(user.getUserId());
        if(oldUser.getLogin().equals("login")) {
            Assert.isTrue(oldUser.getLogin().equals(user.getLogin()));
            Assert.isTrue(oldUser.getUserId().equals(user.getUserId()));
        }
        LOGGER.debug("updateUser(user={}) ", user);
        userDao.updateUser(user);
    }

    @Override
    public List<User> getUsers() {
        LOGGER.debug("getUsers()");
        return userDao.getUsers();
    }
}
