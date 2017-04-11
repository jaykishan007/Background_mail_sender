package com.example.jaykishan.mroads;

import android.app.AlertDialog;
import android.support.design.widget.FloatingActionButton;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.EditText;

import org.junit.Test;

/**
 * Created by jaykishan on 11/4/17.
 */

public class MainActivityInstrumentationTest extends ActivityInstrumentationTestCase2<MainActivity> {

    private MainActivity mTestActivity;
    private EditText mTestText;
    private AlertDialog mDialog;
    private FloatingActionButton mFabMail;
    private FloatingActionButton mFabSend;


    public MainActivityInstrumentationTest(Class<MainActivity> activityClass) {
        super(activityClass);
    }


    @Override
    protected void setUp() throws Exception {
        super.setUp();

        mTestActivity = getActivity();
        mFabMail = (FloatingActionButton) mTestActivity.findViewById(R.id.fab);

//        mDialog = mTestActivity.getDialog();
//
//        mFabSend = (FloatingActionButton) mDialog.findViewById(R.id.innerfab);
//        mTestText = (EditText)mDialog.findViewById(R.id.editText);

    }

    @Test
    public void testPreconditions() {
        // Try to add a message to add context to your assertions.
        // These messages will be shown if
        // a tests fails and make it easy to
        // understand why a test failed
        assertNotNull(mFabMail);

    }


}
