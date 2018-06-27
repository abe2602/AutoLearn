package com.example.abe.mobiletrabalho;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.espresso.contrib.RecyclerViewActions;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class DangerActivityTests {
    /*
    @Rule
    public ActivityTestRule<com.example.abe.mobiletrabalho.danger.DangerActivity> mActivityRule =
            new ActivityTestRule<>(com.example.abe.mobiletrabalho.danger.DangerActivity.class);

    @Test
    public void marksAsDangerous() {
        Espresso.onView(ViewMatchers.withId(R.id.recycler_view_danger))
                .perform(RecyclerViewActions.actionOnItem(
                        ViewMatchers.hasDescendant(ViewMatchers.withId(R.id.textViewSeguro)), ViewActions.click()));

       Espresso.onView(ViewMatchers.withId(R.id.recycler_view_danger))
                .perform(RecyclerViewActions.actionOnItem(ViewMatchers.withText("Perigoso"),ViewActions.click()));
    }
    */
}
