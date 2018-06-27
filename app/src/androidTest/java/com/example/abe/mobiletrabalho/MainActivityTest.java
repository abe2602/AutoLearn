package com.example.abe.mobiletrabalho;

import android.app.Activity;
import android.app.Instrumentation;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.intent.Intents;
import android.support.test.espresso.intent.matcher.IntentMatchers;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.LargeTest;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import com.example.abe.mobiletrabalho.order.OrderActivity;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTest {

    @Rule
    public IntentsTestRule<MainActivity> mActivityRule =
            new IntentsTestRule(MainActivity.class);

    @Before
    public void stubAllExternalIntents() {
        // By default Espresso Intents does not stub any Intents. Stubbing needs to be setup before
        // every test run. In this case all external Intents will be blocked.
        Intents.intending(Matchers.not(IntentMatchers.isInternal())).respondWith(new Instrumentation.ActivityResult(Activity.RESULT_OK, null));
    }

    @Test
    public void launchesDangerActivity() {
        Espresso.onView(ViewMatchers.withId(R.id.imageViewDanger)).perform(ViewActions.click());
        Intents.intended(IntentMatchers.hasComponent(com.example.abe.mobiletrabalho.danger.DangerActivity.class.getName()));
    }

    @Test
    public void launchesEmotionsActivity() {
        Espresso.onView(ViewMatchers.withId(R.id.imageViewEmotions)).perform(ViewActions.click());
        Intents.intended(IntentMatchers.hasComponent(com.example.abe.mobiletrabalho.Emotions.EmotionsActivity.class.getName()));
    }

    @Test
    public void launchesMapActivity() {
        Espresso.onView(ViewMatchers.withId(R.id.imageViewOrder)).perform(ViewActions.click());
        Intents.intended(IntentMatchers.hasComponent(OrderActivity.class.getName()));
    }

    @Test
    public void launchesMicActivity() {
        Espresso.onView(ViewMatchers.withId(R.id.imageViewMic)).perform(ViewActions.click());
        Intents.intended(IntentMatchers.hasComponent(com.example.abe.mobiletrabalho.mic.MicActivity.class.getName()));
    }

    @Test
    public void launchesVibraActivity() {
        Espresso.onView(ViewMatchers.withId(R.id.imageViewVibra)).perform(ViewActions.click());
        Intents.intended(IntentMatchers.hasComponent(com.example.abe.mobiletrabalho.vibra.VibraActivity.class.getName()));
    }
}