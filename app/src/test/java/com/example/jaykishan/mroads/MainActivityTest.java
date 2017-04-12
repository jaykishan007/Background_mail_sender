package com.example.jaykishan.mroads;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;

/**
 * Created by jaykishan on 11/4/17.
 */
public class MainActivityTest extends TestCase {



    MainActivity main;
    GMail gmail;
    String mail_to_send;
    static boolean validity;
    @Before
    public void setUp() throws Exception {
        super.setUp();
        main= new MainActivity();

        mail_to_send= "jayakishan100gmail.com";
        gmail = new GMail(mail_to_send);


    }

    @After
    public void tearDown() throws Exception {

    }

//
//    public void testIsValidEmail() {
//
//
//        validity= main.isValidEmail(mail_to_send);
//        assertEquals(true,validity);
//
//    }


    public void test_Mail_Sent() throws UnsupportedEncodingException, MessagingException {
        boolean result = false;

        if (main.isValidEmail(mail_to_send))
        {
            gmail.createEmailMessage();
            int x = gmail.sendEmail();

            if(x==1)
            {
                result = true;
            }



        }

        assertEquals(true,result);


    }



}
