package unican.es.grupo4_tus_santander.Aceptacion.Lineas;


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
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import unican.es.grupo4_tus_santander.R;
import unican.es.grupo4_tus_santander.View.Main.MainActivity;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class LneasAceptacion {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    /**
     * A1: Comprueba que aparecen la lista de lineas
     */
    @Test
    public void a1() {
        DataInteraction relativeLayout = onData(anything())
                .inAdapterView(allOf(withId(R.id.listFuncionesMenu),
                        childAtPosition(
                                withClassName(is("android.widget.LinearLayout")),
                                1)))
                .atPosition(1);
        relativeLayout.perform(click());

        ViewInteraction textView = onView(
                allOf(withId(R.id.textViewNumero), withText("2"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.RelativeLayout.class),
                                        1),
                                0),
                        isDisplayed()));
        textView.check(matches(isDisplayed()));

        ViewInteraction imageView = onView(
                allOf(withId(R.id.ImagenLinea),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.listLineas),
                                        0),
                                0),
                        isDisplayed()));
        imageView.check(matches(isDisplayed()));

    }


    /**
     * A2: Se comprueba que al clickar en una linea se va a la activity de paradas
     */
    @Test
    public void a2() {
        DataInteraction relativeLayout = onData(anything())
                .inAdapterView(allOf(withId(R.id.listFuncionesMenu),
                        childAtPosition(
                                withClassName(is("android.widget.LinearLayout")),
                                1)))
                .atPosition(1);
        relativeLayout.perform(click());

        DataInteraction relativeLayout2 = onData(anything())
                .inAdapterView(allOf(withId(R.id.listLineas),
                        childAtPosition(
                                withClassName(is("android.widget.RelativeLayout")),
                                1)))
                .atPosition(0);
        relativeLayout2.perform(click());

        ViewInteraction textView3 = onView(
                allOf(withText("Paradas"),
                        childAtPosition(
                                allOf(withId(R.id.action_bar),
                                        childAtPosition(
                                                withId(R.id.action_bar_container),
                                                0)),
                                0),
                        isDisplayed()));
        textView3.check(matches(withText(R.string.title_activity_paradas)));

    }

    /**
     * A3: Se comprueba que se actualizan los datos al pulsar el boton de actualizar
     */
    @Test
    public void a3() {
        DataInteraction relativeLayout = onData(anything())
                .inAdapterView(allOf(withId(R.id.listFuncionesMenu),
                        childAtPosition(
                                withClassName(is("android.widget.LinearLayout")),
                                1)))
                .atPosition(1);
        relativeLayout.perform(click());

        ViewInteraction actionMenuItemView = onView(
                allOf(withId(R.id.refresh), withContentDescription("Actualizar"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.action_bar),
                                        1),
                                1),
                        isDisplayed()));
        actionMenuItemView.perform(click());

        ViewInteraction textView = onView(
                allOf(withId(R.id.textViewNumero), withText("2"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.RelativeLayout.class),
                                        1),
                                0),
                        isDisplayed()));
        textView.check(matches(isDisplayed()));

        ViewInteraction imageView = onView(
                allOf(withId(R.id.ImagenLinea),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.listLineas),
                                        0),
                                0),
                        isDisplayed()));
        imageView.check(matches(isDisplayed()));



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
