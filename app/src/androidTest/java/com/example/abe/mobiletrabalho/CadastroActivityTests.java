package com.example.abe.mobiletrabalho;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.intent.Intents;
import android.support.test.espresso.intent.matcher.IntentMatchers;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.espresso.matcher.RootMatchers;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class CadastroActivityTests {
    @Rule
    public IntentsTestRule<CadastroActivity> intentsTestRule =
            new IntentsTestRule<>(CadastroActivity.class);

    @Test
    public void testCadastrar() {
        Espresso.onView(ViewMatchers.withId(R.id.username)).perform(ViewActions.clearText()).perform(ViewActions.typeText("user")).perform(ViewActions.closeSoftKeyboard());
        Espresso.onView(ViewMatchers.withId(R.id.email)).perform(ViewActions.clearText()).perform(ViewActions.typeText("email@email.com")).perform(ViewActions.closeSoftKeyboard());
        Espresso.onView(ViewMatchers.withId(R.id.password)).perform(ViewActions.typeText("password")).perform(ViewActions.closeSoftKeyboard());
        Espresso.onView(ViewMatchers.withId(R.id.passwordAgain)).perform(ViewActions.typeText("password")).perform(ViewActions.closeSoftKeyboard());
        Espresso.onView(ViewMatchers.withId(R.id.buttonCadastrar)).perform(ViewActions.click());
    }

    @Test
    public void cancelCadastrar() {
        Espresso.onView(ViewMatchers.withId(R.id.buttonCancelar)).perform(ViewActions.click());
        Intents.intended(IntentMatchers.hasComponent(LoginActivity.class.getName()));
    }
}
