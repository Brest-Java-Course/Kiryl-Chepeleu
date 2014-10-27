package com.epam.brest.courses.service;

import com.epam.brest.courses.domain.User;

/**
 * Created by kirill
 */
public class UserDataFixture {
    public static User getNewUser(){
        User user = new User();
        user.setLogin("login");
        user.setName("name");
        return  user;
    }

    public static User getExistUser(long id){
        User user = new User();
        user.setUserId(id);
        user.setLogin("login");
        user.setName("name");
        return  user;
    }
}
