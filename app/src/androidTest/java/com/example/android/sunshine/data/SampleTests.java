package com.example.android.sunshine.data;

import android.provider.ContactsContract;
import android.support.test.espresso.DataInteraction;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;

import com.example.android.sunshine.MainActivity;
import com.example.android.sunshine.R;

import org.junit.Rule;
import org.junit.Test;
import android.content.Intent;
import android.support.test.espresso.intent.Intents;
import android.view.View;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasData;
import static android.support.test.espresso.intent.matcher.IntentMatchers.isInternal;
import static android.support.test.espresso.intent.matcher.IntentMatchers.toPackage;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isEnabled;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

/**
 * Created by ankita.admuthe on 3/14/2018.
 */

public class SampleTests {
    public static final String ITEM_TO_BE_SELECTED = "Tommorrow";
    protected static final String ROW_TEXT = "ROW_TEXT";

    @Rule
    public IntentsTestRule<MainActivity> mActivityRule = new IntentsTestRule(
            MainActivity.class);


    @Test
    public void goToLocation()
    {
        onView(ViewMatchers.withContentDescription("More options")).perform(click());
        onView(ViewMatchers.withText("Map Location")).perform(click());
        intended(allOf(hasAction(Intent.ACTION_VIEW),toPackage("com.google.android.apps.maps")));

    }
    //Test to check if we can click perticular
    @Test
   public void clickWeather()

    {//onView(allOf(withId(R.id.date), withText("Tomorrow"), withParent(withParent(withId(R.id.recyclerview_forecast))))).perform(click());
        onView(allOf(withId(R.id.date), withText("Tomorrow"))).perform(click());
        onView(ViewMatchers.withText("Tomorrow, 16 March")).check(matches(isDisplayed()));
        onView(withId(R.id.humidity_label)).check(matches(isDisplayed()));
        onView(withId(R.id.pressure_label)).check(matches(isDisplayed()));
        onView(withId(R.id.wind_label)).check(matches(isDisplayed()));
    }
    @Test
    public void enableWeatherNotifications()
    {
        onView(ViewMatchers.withContentDescription("More options")).perform(click());
        onView(ViewMatchers.withText("Settings")).perform(click());
        onView(ViewMatchers.withText("Weather Notifications")).perform(click());
       // onView(allOf(withId(R.id.checkbox),withParent(withId(android.R.id.widget_frame)))).check(matches(isEnabled()));
        onView(ViewMatchers.withText("Enabled")).check(matches(isDisplayed()));

    }

    @Test
    public void changeLocation()
    {
        onView(ViewMatchers.withContentDescription("More options")).perform(click());
        onView(ViewMatchers.withText("Settings")).perform(click());
        onView(ViewMatchers.withText("Location")).perform(click());
        onView(withId(android.R.id.edit)).perform(clearText());
        onView(withId(android.R.id.edit)).perform(typeText("Test"), closeSoftKeyboard());
        onView(withId(android.R.id.button1)).perform(click());
        onView(allOf(withId(android.R.id.summary), withText("Test"))).check(matches(withText("Test")));
        //onView(allOf(android.R.id.summary),ViewMatchers.withText("Test")).check(matches(withText("Test")));

    }
    @Test
    public void clickOnShareIcon()
    {
        onView(allOf(withId(R.id.date), withText("Tomorrow"))).perform(click());
        onView(allOf(withId(R.id.action_share))).perform(click());


    }


    private static DataInteraction onRow(String str) {
        return onData(hasEntry(equalTo(SampleTests.ROW_TEXT), is(str)));
    }
    }

