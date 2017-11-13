package unican.es.grupo4_tus_santander.aceptacion.main;


import android.support.test.espresso.DataInteraction;
import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ProgressBar;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import unican.es.grupo4_tus_santander.R;
import unican.es.grupo4_tus_santander.view.main.MainActivity;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainTestsAceptacion {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);


    /**
     * A1: Acceso favoritos
     a.	Acceso válido (se muestra la pantalla con la lista de favoritos).
     */
    @Test
    public void a1a() {
        DataInteraction relativeLayout = onData(anything())
                .inAdapterView(allOf(withId(R.id.listFuncionesMenu),
                        childAtPosition(
                                withClassName(is("android.widget.LinearLayout")),
                                1)))
                .atPosition(0);
        relativeLayout.perform(click());

        ViewInteraction textView = onView(
                allOf(withText("Favoritos"),
                        childAtPosition(
                                allOf(withId(R.id.action_bar),
                                        childAtPosition(
                                                withId(R.id.action_bar_container),
                                                0)),
                                0),
                        isDisplayed()));
        textView.check(matches(withText("Favoritos")));

    }


    /**
     * A2: Acceso líneas
     b.	Acceso válido (se muestra la pantalla de las líneas).
     */
    @Test
    public void a2a() {
        DataInteraction relativeLayout = onData(anything())
                .inAdapterView(allOf(withId(R.id.listFuncionesMenu),
                        childAtPosition(
                                withClassName(is("android.widget.LinearLayout")),
                                1)))
                .atPosition(1);
        relativeLayout.perform(click());

        ViewInteraction textView = onView(
                allOf(withText("Lineas"),
                        childAtPosition(
                                allOf(withId(R.id.action_bar),
                                        childAtPosition(
                                                withId(R.id.action_bar_container),
                                                0)),
                                0),
                        isDisplayed()));
        textView.check(matches(withText("Lineas")));

    }

    /**
     * A3: Paradas
     a.	Acceso válido (se muestra la pantalla con el campo de búsqueda y todas las paradas).
     */
    @Test
    public void a3a() {
        DataInteraction relativeLayout = onData(anything())
                .inAdapterView(allOf(withId(R.id.listFuncionesMenu),
                        childAtPosition(
                                withClassName(is("android.widget.LinearLayout")),
                                1)))
                .atPosition(2);
        relativeLayout.perform(click());

        ViewInteraction textView = onView(
                allOf(withText("Paradas"),
                        childAtPosition(
                                allOf(withId(R.id.action_bar),
                                        childAtPosition(
                                                withId(R.id.action_bar_container),
                                                0)),
                                0),
                        isDisplayed()));
        textView.check(matches(withText("Paradas")));

    }

    /**
     * A4: Acceso tarifas
     a.	Acceso válido (se muestra la pantalla de tarifas).
     */
    @Test
    public void a4a() {
        DataInteraction relativeLayout = onData(anything())
                .inAdapterView(allOf(withId(R.id.listFuncionesMenu),
                        childAtPosition(
                                withClassName(is("android.widget.LinearLayout")),
                                1)))
                .atPosition(3);
        relativeLayout.perform(click());

        ViewInteraction textView = onView(
                allOf(withText("Tarifas"),
                        childAtPosition(
                                allOf(withId(R.id.action_bar),
                                        childAtPosition(
                                                withId(R.id.action_bar_container),
                                                0)),
                                0),
                        isDisplayed()));
        textView.check(matches(withText("Tarifas")));
    }

    /**
     * A5: Acceso restricciones
     a.	Acceso válido (se muestra la pantalla de las restricciones).
     */
    @Test
    public void a5a() {
        DataInteraction relativeLayout = onData(anything())
                .inAdapterView(allOf(withId(R.id.listFuncionesMenu),
                        childAtPosition(
                                withClassName(is("android.widget.LinearLayout")),
                                1)))
                .atPosition(4);
        relativeLayout.perform(click());

        ViewInteraction textView = onView(
                allOf(withText("Restricciones"),
                        childAtPosition(
                                allOf(withId(R.id.action_bar),
                                        childAtPosition(
                                                withId(R.id.action_bar_container),
                                                0)),
                                0),
                        isDisplayed()));
        textView.check(matches(withText("Restricciones")));
    }

    /**
     * A6: Acceso servicios alternativos
     a.	Acceso válido (se muestra la pantalla de las líneas).
     */
    @Test
    public void a6a() {
        DataInteraction relativeLayout = onData(anything())
                .inAdapterView(allOf(withId(R.id.listFuncionesMenu),
                        childAtPosition(
                                withClassName(is("android.widget.LinearLayout")),
                                1)))
                .atPosition(5);
        relativeLayout.perform(click());

        ViewInteraction textView = onView(
                allOf(withText("Transportes Alternativos"),
                        childAtPosition(
                                allOf(withId(R.id.action_bar),
                                        childAtPosition(
                                                withId(R.id.action_bar_container),
                                                0)),
                                0),
                        isDisplayed()));
        textView.check(matches(withText("Transportes Alternativos")));
    }

    /**
     * A7: Acceso ajustes
     a.	Acceso válido (se muestra la pantalla de los ajustes).
     */
    @Test
    public void a7a() {
        DataInteraction relativeLayout = onData(anything())
                .inAdapterView(allOf(withId(R.id.listFuncionesMenu),
                        childAtPosition(
                                withClassName(is("android.widget.LinearLayout")),
                                1)))
                .atPosition(6);
        relativeLayout.perform(click());

        ViewInteraction textView = onView(
                allOf(withText("Ajustes"),
                        childAtPosition(
                                allOf(withId(R.id.action_bar),
                                        childAtPosition(
                                                withId(R.id.action_bar_container),
                                                0)),
                                0),
                        isDisplayed()));
        textView.check(matches(withText("Ajustes")));
    }

    /**
     * Comprobamos que el progress bar se retira correctamente al terminar la operacion de actualizar
     */
    @Test
    public void a8a() {
        onView(withId(R.id.toolbar_actualizar)).perform(click());
        onView(withId(R.id.progressBarMenu)).check(ViewAssertions.matches(progressBarStatus(false)));
    }


    public static Matcher<View> progressBarStatus (final boolean estado){
        return new TypeSafeMatcher<View>() {
            @Override
            protected boolean matchesSafely(View item) {
                // Comprueba que el el progressBar se muestra
                ProgressBar progressBar = (ProgressBar) item;

                return (estado == progressBar.isShown());
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("El estado no coincide");
            }
        };
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
