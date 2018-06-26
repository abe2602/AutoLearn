package com.example.abe.mobiletrabalho;

import android.app.Activity;
import android.app.Instrumentation;
import android.os.Build;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.intent.Intents;
import android.support.test.espresso.intent.matcher.IntentMatchers;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.espresso.intent.rule.IntentsTestRule;

import com.example.abe.mobiletrabalho.LoginActivity;
import static org.hamcrest.Matchers.not;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import java.util.regex.Matcher;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class LoginActivityTest {

    @Rule
    public IntentsTestRule<LoginActivity> mActivityRule =
            new IntentsTestRule(LoginActivity.class);


    @Before
    public void stubAllExternalIntents() {
        // By default Espresso Intents does not stub any Intents. Stubbing needs to be setup before
        // every test run. In this case all external Intents will be blocked.
        Intents.intending(Matchers.not(IntentMatchers.isInternal())).respondWith(new Instrumentation.ActivityResult(Activity.RESULT_OK, null));
    }

    @Test
    public void launchesCadastroActivity() {
        Espresso.onView(ViewMatchers.withId(R.id.buttonCadastrar)).perform(ViewActions.click());
        Intents.intended(IntentMatchers.hasComponent(CadastroActivity.class.getName()));
    }

    @Test
    public void testLoginAttempt() {
        Espresso.onView(ViewMatchers.withId(R.id.editTextEmail)).perform(ViewActions.clearText()).perform(ViewActions.typeText("invaliduser"), ViewActions.closeSoftKeyboard());
        Espresso.onView(ViewMatchers.withId(R.id.editTextPassword)).perform(ViewActions.clearText()).perform(ViewActions.typeText("invalidpassword"), ViewActions.closeSoftKeyboard());
        Espresso.onView(ViewMatchers.withId(R.id.buttonEntrar)).perform(ViewActions.clearText()).perform(ViewActions.click());
    }
}