package com.epam.brest.courses.domain;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
/**
 * Created by kirill-good on 12/6/14.
 */
public class LectorTest {
    Lector lector;
    @Before
    public void setUp()throws Exception{
        lector = new Lector();
    }

    public void testSetGetName()throws Exception{
        final String lectorname = "lectorname";
        lector.setLectorName(lectorname);
        assertEquals(lectorname,lector.getLectorName());
    }
}
