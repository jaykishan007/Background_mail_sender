package com.example.jaykishan.mroads;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by jaykishan on 11/4/17.
 */
public class MainActivityTest extends TestCase {



    MainActivity main;
    @Before
    public void setUp() throws Exception {
        super.setUp();
        main= new MainActivity();


    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testIsValidEmail() throws Exception {


        boolean validity=main.isValidEmail("vjdfvjfdj@.djjj");
        assertEquals(false,validity);


    }
}
