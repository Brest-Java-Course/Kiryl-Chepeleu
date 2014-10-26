package com.epam.brest.coueses.dao;

import com.epam.brest.cources.dao.UserDao;
import com.epam.brest.courses.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;



/**
 * Created by kirill on 22.10.14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/testApplicationContextSpring.xml"})
public class UserDaoImplTest {
    @Autowired
    private UserDao userDao;

    @Test
    public void getUsers(){
        List<User> users=userDao.getUsers();
        assertNotNull(users);
    }
    @Test
    public void addUser(){
        List<User> users = userDao.getUsers();
        int sizeBefore = users.size();
        User user = new User();
        user.setUserId(3L);
        user.setLogin("userLogin3");
        user.setUserName("userName3");
        userDao.addUser(user);
        users = userDao.getUsers();
        assertEquals(sizeBefore,users.size()-1);
    }
    @Test
    public void removeUser(){
        List<User> users = userDao.getUsers();
        int sizeBefore = users.size();
        userDao.removeUser(1);
        users = userDao.getUsers();
        assertEquals(sizeBefore-1,users.size());
    }
}