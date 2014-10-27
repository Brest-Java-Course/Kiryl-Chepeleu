package com.epam.brest.courses.dao;

import com.epam.brest.courses.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;
/**
 * Created by kirill on 27.10.14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/testApplicationContextSpring.xml" })
public class UserDaoImplTest {

    @Autowired
    private UserDao userDao;

    @Test
    public void getUsers() {
        List<User> users = userDao.getUsers();
        assertNotNull(users);
        assertFalse(users.isEmpty());
    }

    @Test
    public void addUser() {
        List<User> users = userDao.getUsers();
        int sizeBefore = users.size();

        User user = new User();
        user.setUserId(10L);
        user.setLogin("userLogin10");
        user.setName("userName10");

        userDao.addUser(user);

        users = userDao.getUsers();
        assertEquals(sizeBefore, users.size() - 1);
    }
    @Test
    public void removeUser(){
        List<User> users = userDao.getUsers();
        int sizeBefore = users.size();

        User user = new User();
        user.setUserId(4L);
        user.setLogin("userLogin4");
        user.setName("userName4");
        userDao.addUser(user);
        userDao.removeUser(4L);

        users = userDao.getUsers();
        assertEquals(sizeBefore, users.size());
    }
    @Test
    public void getUserByLogin(){
        List<User> users = userDao.getUsers();

        User user = new User();
        user.setUserId(5L);
        user.setLogin("userLogin5");
        user.setName("userName5");
        userDao.addUser(user);
        User userGetted = userDao.getUserByLogin(user.getLogin());
        assertNotNull(userGetted);
        assertEquals(userGetted.getUserId(), user.getUserId());
        assertEquals(userGetted.getName(), user.getName());
    }
    @Test
    public void getUserById(){
        List<User> users = userDao.getUsers();

        User user = new User();
        user.setUserId(6L);
        user.setLogin("userLogin6");
        user.setName("userName6");
        userDao.addUser(user);
        User userGetted = userDao.getUserById(user.getUserId());
        assertNotNull(userGetted);
        assertEquals(userGetted.getLogin(), user.getLogin());
        assertEquals(userGetted.getName(), user.getName());
    }

    @Test
    public void updateUser(){
        List<User> users = userDao.getUsers();
        int sizeBefore = users.size();

        User user = new User();
        user.setUserId(7L);
        user.setLogin("userLogin7");
        user.setName("userName7");
        userDao.addUser(user);
        user=new User(7L,"newlogin","newname");
        userDao.updateUser(user);
        User userGetted = userDao.getUserById(7L);
        assertNotNull(userGetted);
        assertEquals(userGetted.getLogin(), user.getLogin());
        assertEquals(userGetted.getName(), user.getName());
    }
}
