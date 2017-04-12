package com.example.jaykishan.mroads;

import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.core.AllOf.allOf;

/**
 * Created by jaykishan on 11/4/17.
 */
@RunWith(AndroidJUnit4.class)//test runner
public class MainActivityInstrumentationTest{

    @Rule public final ActivityTestRule<MainActivity> main = new ActivityTestRule<>(MainActivity.class);


    @Test
    public void shouldLoadMainScreenCardView()
    {

        onView(withId(R.id.card_view)).check(matches(isDisplayed()));
    }

    @Test
    public void shouldLoadComposeButton()
    {
        //Locate and click the fab button
        onView(withId(R.id.fab)).perform(click());
        //check if dialog box appers or not
        onView(withId(R.id.editText)).check(matches(allOf(isDescendantOfA(withId(R.id.dialog_layout)), isDisplayed())));
    }

    @Test
    public void sendMail()
    {
        String email = "jayakishan100@gmail.com";

        onView(withId(R.id.fab)).perform(click());

        onView(withId(R.id.editText)).perform(typeText(email),closeSoftKeyboard());

        onView(withId(R.id.innerfab)).perform(click());
        String successString = InstrumentationRegistry.getTargetContext().getString(R.string.email_id);
        onView(withId(R.id.card_view)).check(matches(isDisplayed()));

    }



}
