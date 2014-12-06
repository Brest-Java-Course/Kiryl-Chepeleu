package com.epam.brest.courses.domain;
import org.junit.Before;

import static org.junit.Assert.assertEquals;
/**
 * Created by kirill-good on 12/6/14.
 */
public class LectorTest {
    Lecturer lecturer;
    @Before
    public void setUp()throws Exception{
        lecturer = new Lecturer();
    }

    public void testSetGetName()throws Exception{
        final String lectorname = "lectorname";
        lecturer.setLectorName(lectorname);
        assertEquals(lectorname,lecturer.getLectorName());
    }
}
