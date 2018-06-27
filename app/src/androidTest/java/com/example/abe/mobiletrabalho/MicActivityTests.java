package com.example.abe.mobiletrabalho;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.intent.Intents;
import android.support.test.espresso.intent.matcher.IntentMatchers;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Locale;

@RunWith(AndroidJUnit4.class)
public class MicActivityTests {

    @Rule
    public IntentsTestRule<com.example.abe.mobiletrabalho.mic.MicActivity> mActivityRule =
            new IntentsTestRule(com.example.abe.mobiletrabalho.mic.MicActivity.class);

    @Before
    public void stubAllExternalIntents() {
        Intents.intending(Matchers.not(IntentMatchers.isInternal())).respondWith(new Instrumentation.ActivityResult(Activity.RESULT_OK, null));
    }

    @Test
    public void testSpeak() {
        Intent intentMic = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intentMic.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);

        intentMic.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intentMic.putExtra(RecognizerIntent.EXTRA_PROMPT, "Fale agora!!!");
        Instrumentation.ActivityResult result = new Instrumentation.ActivityResult(Activity.RESULT_OK, intentMic);

        Intents.intending(IntentMatchers.toPackage("android.speech.RecognizerIntent")).respondWith(result);

        Espresso.onView(ViewMatchers.withId(R.id.auxTextView)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }
}
