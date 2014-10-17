package com.epam.brest.courses.domain;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UserTest extends TestCase {

    User user;

    @Before
    public void setUp() throws Exception {
        //
        user = new User();
    }
    @Test
    public void testGetUserName() throws Exception {
        user.setUserName("User name");
        assertEquals("User name", user.getUserName());
    }
}