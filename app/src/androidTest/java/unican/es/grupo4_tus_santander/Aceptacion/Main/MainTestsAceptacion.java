package unican.es.grupo4_tus_santander.Aceptacion.Main;


import android.support.test.espresso.DataInteraction;
import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import unican.es.grupo4_tus_santander.R;
import unican.es.grupo4_tus_santander.Aceptacion.Main.Main.MainActivity;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static junit.framework.Assert.assertTrue;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainTestsAceptacion {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);


    @Test
    public void a1() {
        DataInteraction relativeLayout = onData(anything())
                .inAdapterView(allOf(withId(R.id.listFuncionesMenu),
                        childAtPosition(
                                withClassName(is("android.widget.LinearLayout")),
                                1)))
                .atPosition(0);
        relativeLayout.perform(click());

        ViewInteraction textView = onView(
                allOf(withText("FavsActivity"),
                        childAtPosition(
                                allOf(withId(R.id.action_bar),
                                        childAtPosition(
                                                withId(R.id.action_bar_container),
                                                0)),
                                0),
                        isDisplayed()));
        textView.check(matches(withText("FavsActivity")));

    }


    @Test
    public void a2() {
        DataInteraction relativeLayout = onData(anything())
                .inAdapterView(allOf(withId(R.id.listFuncionesMenu),
                        childAtPosition(
                                withClassName(is("android.widget.LinearLayout")),
                                1)))
                .atPosition(1);
        relativeLayout.perform(click());

        ViewInteraction textView = onView(
                allOf(withText("LineasActivity"),
                        childAtPosition(
                                allOf(withId(R.id.action_bar),
                                        childAtPosition(
                                                withId(R.id.action_bar_container),
                                                0)),
                                0),
                        isDisplayed()));
        textView.check(matches(withText("LineasActivity")));

    }

    @Test
    public void a3() {
        DataInteraction relativeLayout = onData(anything())
                .inAdapterView(allOf(withId(R.id.listFuncionesMenu),
                        childAtPosition(
                                withClassName(is("android.widget.LinearLayout")),
                                1)))
                .atPosition(2);
        relativeLayout.perform(click());

        ViewInteraction textView = onView(
                allOf(withText("ParadasActivity"),
                        childAtPosition(
                                allOf(withId(R.id.action_bar),
                                        childAtPosition(
                                                withId(R.id.action_bar_container),
                                                0)),
                                0),
                        isDisplayed()));
        textView.check(matches(withText("ParadasActivity")));

    }

    @Test
    public void a4() {
        DataInteraction relativeLayout = onData(anything())
                .inAdapterView(allOf(withId(R.id.listFuncionesMenu),
                        childAtPosition(
                                withClassName(is("android.widget.LinearLayout")),
                                1)))
                .atPosition(3);
        relativeLayout.perform(click());

        ViewInteraction textView = onView(
                allOf(withText("TarifasActivity"),
                        childAtPosition(
                                allOf(withId(R.id.action_bar),
                                        childAtPosition(
                                                withId(R.id.action_bar_container),
                                                0)),
                                0),
                        isDisplayed()));
        textView.check(matches(withText("TarifasActivity")));

    }

    @Test
    public void a5() {
        DataInteraction relativeLayout = onData(anything())
                .inAdapterView(allOf(withId(R.id.listFuncionesMenu),
                        childAtPosition(
                                withClassName(is("android.widget.LinearLayout")),
                                1)))
                .atPosition(4);
        relativeLayout.perform(click());

        ViewInteraction textView = onView(
                allOf(withText("RestriccionesActivity"),
                        childAtPosition(
                                allOf(withId(R.id.action_bar),
                                        childAtPosition(
                                                withId(R.id.action_bar_container),
                                                0)),
                                0),
                        isDisplayed()));
        textView.check(matches(withText("RestriccionesActivity")));
    }

    @Test
    public void a6() {
        DataInteraction relativeLayout = onData(anything())
                .inAdapterView(allOf(withId(R.id.listFuncionesMenu),
                        childAtPosition(
                                withClassName(is("android.widget.LinearLayout")),
                                1)))
                .atPosition(5);
        relativeLayout.perform(click());

        ViewInteraction textView = onView(
                allOf(withText("TransportesAlternativosActivity"),
                        childAtPosition(
                                allOf(withId(R.id.action_bar),
                                        childAtPosition(
                                                withId(R.id.action_bar_container),
                                                0)),
                                0),
                        isDisplayed()));
        textView.check(matches(withText("TransportesAlternativosActivity")));
    }

    @Test
    public void a7() {
        DataInteraction relativeLayout = onData(anything())
                .inAdapterView(allOf(withId(R.id.listFuncionesMenu),
                        childAtPosition(
                                withClassName(is("android.widget.LinearLayout")),
                                1)))
                .atPosition(6);
        relativeLayout.perform(click());

        ViewInteraction textView = onView(
                allOf(withText("AjustesActivity"),
                        childAtPosition(
                                allOf(withId(R.id.action_bar),
                                        childAtPosition(
                                                withId(R.id.action_bar_container),
                                                0)),
                                0),
                        isDisplayed()));
        textView.check(matches(withText("AjustesActivity")));
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
