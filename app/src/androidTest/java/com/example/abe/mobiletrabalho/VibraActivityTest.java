package com.example.abe.mobiletrabalho;

import android.os.SystemClock;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;


import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class VibraActivityTest {
    @Rule
    public ActivityTestRule<com.example.abe.mobiletrabalho.vibra.VibraActivity> mActivityRule =
            new ActivityTestRule<>(com.example.abe.mobiletrabalho.vibra.VibraActivity.class);

    @Test
    public void testMusic1() {
        Espresso.onView(ViewMatchers.withId(R.id.switch_music_1)).perform(ViewActions.click());
        Espresso.onView(ViewMatchers.withId(R.id.buttonPlayMusic)).perform(ViewActions.click());
        SystemClock.sleep(3000);
        Espresso.onView(ViewMatchers.withId(R.id.switch_music_1)).perform(ViewActions.click());
        Espresso.onView(ViewMatchers.withId(R.id.buttonStopMusic)).perform(ViewActions.click());
    }

    @Test
    public void testMusic2() {
        Espresso.onView(ViewMatchers.withId(R.id.switch_music_2)).perform(ViewActions.click());
        Espresso.onView(ViewMatchers.withId(R.id.buttonPlayMusic)).perform(ViewActions.click());
        SystemClock.sleep(3000);
        Espresso.onView(ViewMatchers.withId(R.id.switch_music_2)).perform(ViewActions.click());
        Espresso.onView(ViewMatchers.withId(R.id.buttonStopMusic)).perform(ViewActions.click());
    }

    @Test
    public void testMusic3() {
        Espresso.onView(ViewMatchers.withId(R.id.switch_music_3)).perform(ViewActions.click());
        Espresso.onView(ViewMatchers.withId(R.id.buttonPlayMusic)).perform(ViewActions.click());
        SystemClock.sleep(3000);
        Espresso.onView(ViewMatchers.withId(R.id.switch_music_3)).perform(ViewActions.click());
        Espresso.onView(ViewMatchers.withId(R.id.buttonStopMusic)).perform(ViewActions.click());
    }
}
