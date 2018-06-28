package com.example.abe.mobiletrabalho;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.abe.mobiletrabalho.Data.ImageClass;
import com.example.abe.mobiletrabalho.emotion.EmotionActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class EmotionActitivyTests {
    @Rule
    public ActivityTestRule<EmotionActivity> mActivityRule =
            new ActivityTestRule<>(EmotionActivity.class);

    @Test
    public void testEmotions() {
        EmotionActivity activity  = mActivityRule.getActivity();
        for (ImageClass image : activity.getImageList()) {
            Espresso.onView(ViewMatchers.withId(R.id.angryButton)).perform(ViewActions.click());
            if (!activity.getImageDescription().equals("raiva")) {
                Espresso.onView(ViewMatchers.withId(R.id.sadButton)).perform(ViewActions.click());
                if (!activity.getImageDescription().equals("tristeza")) {
                    Espresso.onView(ViewMatchers.withId(R.id.happyButton)).perform(ViewActions.click());
                    if (!activity.getImageDescription().equals("alegria")) {
                        Espresso.onView(ViewMatchers.withId(R.id.disgustingButton)).perform(ViewActions.click());
                        if (!activity.getImageDescription().equals("nojo")) {
                            Espresso.onView(ViewMatchers.withId(R.id.afraidButton)).perform(ViewActions.click());
                        }
                    }
                }
            }
        }
    }
}
