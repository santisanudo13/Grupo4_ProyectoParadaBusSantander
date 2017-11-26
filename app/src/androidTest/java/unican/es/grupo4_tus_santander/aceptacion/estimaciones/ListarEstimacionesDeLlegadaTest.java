package unican.es.grupo4_tus_santander.aceptacion.estimaciones;


import android.support.test.espresso.DataInteraction;
import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ListView;

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
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class ListarEstimacionesDeLlegadaTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    /**
     * A1: Se comprueba que se cargan correctamente estimaciones sobre una parada
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

        DataInteraction relativeLayout2 = onData(anything())
                .inAdapterView(allOf(withId(R.id.listLineas),
                        childAtPosition(
                                withClassName(is("android.widget.RelativeLayout")),
                                1)))
                .atPosition(2);
        relativeLayout2.perform(click());

        DataInteraction relativeLayout3 = onData(anything())
                .inAdapterView(allOf(withId(R.id.listParadas),
                        childAtPosition(
                                withClassName(is("android.widget.RelativeLayout")),
                                2)))
                .atPosition(6);
        relativeLayout3.perform(click());

        onView(withId(R.id.listParadas)).check(ViewAssertions.matches(cargaCorrecta()));

    }

    public static Matcher<View> cargaCorrecta() {
        return new TypeSafeMatcher<View>() {
            @Override
            public boolean matchesSafely(View view) {
                ListView list = (ListView) view;
                return (list.getCount() != 0); // TODO Comprobar que la primera parada es la adecuada
            }
            @Override
            public void describeTo(Description description) {
                description.appendText("Carga de datos incorrecta");
            }
        };
    }

    /**
     * A2: Se comprueba que si hay fallo de conexión se muestra el mensaje adecuado
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
                .atPosition(2);
        relativeLayout2.perform(click());

        DataInteraction relativeLayout3 = onData(anything())
                .inAdapterView(allOf(withId(R.id.listParadas),
                        childAtPosition(
                                withClassName(is("android.widget.RelativeLayout")),
                                2)))
                .atPosition(6);
        relativeLayout3.perform(click());

        onView(withId(R.id.listParadas)).check(ViewAssertions.matches(falloConexion()));

    }

    public static Matcher<View> falloConexion() {
        return new TypeSafeMatcher<View>() {
            @Override
            public boolean matchesSafely(View view) {
                ListView list = (ListView) view;
                return (list.getCount() == 0);
            }
            @Override
            public void describeTo(Description description) {
                description.appendText("Carga de datos correcta (no debería)");
            }
        };
    }

    /**
     * A3: Se comprueba que el sistema redondea de manera correcta a la baja
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

        DataInteraction relativeLayout2 = onData(anything())
                .inAdapterView(allOf(withId(R.id.listLineas),
                        childAtPosition(
                                withClassName(is("android.widget.RelativeLayout")),
                                1)))
                .atPosition(2);
        relativeLayout2.perform(click());

        DataInteraction relativeLayout3 = onData(anything())
                .inAdapterView(allOf(withId(R.id.listParadas),
                        childAtPosition(
                                withClassName(is("android.widget.RelativeLayout")),
                                2)))
                .atPosition(6);
        relativeLayout3.perform(click());

        onView(withId(R.id.listParadas)).check(ViewAssertions.matches(redondeaBaja()));

    }

    public static Matcher<View> redondeaBaja() {
        return new TypeSafeMatcher<View>() {
            @Override
            public boolean matchesSafely(View view) {
                ListView list = (ListView) view;
                return (list.getCount() == 0); // TODO No retornaría esto
            }
            @Override
            public void describeTo(Description description) {
                description.appendText("No se ha redondeado a la baja");
            }
        };
    }

    /**
     * A4: Se comprueba que si la segunda estimación es 0 no se muestra por pantalla
     */
    @Test
    public void a4() {
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
                .atPosition(2);
        relativeLayout2.perform(click());

        DataInteraction relativeLayout3 = onData(anything())
                .inAdapterView(allOf(withId(R.id.listParadas),
                        childAtPosition(
                                withClassName(is("android.widget.RelativeLayout")),
                                2)))
                .atPosition(6);
        relativeLayout3.perform(click());

        onView(withId(R.id.listParadas)).check(ViewAssertions.matches(segundaEstimacionCero()));

    }

    public static Matcher<View> segundaEstimacionCero() {
        return new TypeSafeMatcher<View>() {
            @Override
            public boolean matchesSafely(View view) {
                ListView list = (ListView) view;
                return (list.getCount() == 0);
            }
            @Override
            public void describeTo(Description description) {
                description.appendText("Se ha mostrado una lista (no debería)");
            }
        };
    }

    /**
     * A5: Se comprueba que el nombre de cada línea tiene el color correspondiente
     */
    @Test
    public void a5() {
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
                .atPosition(2);
        relativeLayout2.perform(click());

        DataInteraction relativeLayout3 = onData(anything())
                .inAdapterView(allOf(withId(R.id.listParadas),
                        childAtPosition(
                                withClassName(is("android.widget.RelativeLayout")),
                                2)))
                .atPosition(6);
        relativeLayout3.perform(click());

        onView(withId(R.id.listParadas)).check(ViewAssertions.matches(colorAdecuado()));

    }

    public static Matcher<View> colorAdecuado() {
        return new TypeSafeMatcher<View>() {
            @Override
            public boolean matchesSafely(View view) {
                ListView list = (ListView) view;
                return (list.getCount() == 0); // TODO No retornaría esto
            }
            @Override
            public void describeTo(Description description) {
                description.appendText("Los colores mostrados no son adecuados");
            }
        };
    }

    /**
     * A6: Se comprueba que se hace la recarga de datos correctamente
     */
    @Test
    public void a6() {
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
                .atPosition(2);
        relativeLayout2.perform(click());

        DataInteraction relativeLayout3 = onData(anything())
                .inAdapterView(allOf(withId(R.id.listParadas),
                        childAtPosition(
                                withClassName(is("android.widget.RelativeLayout")),
                                2)))
                .atPosition(6);
        relativeLayout3.perform(click());

        ViewInteraction actionMenuItemView = onView(
                allOf(withId(R.id.toolbar_actualizar),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.action_bar),
                                        1),
                                0),
                        isDisplayed()));
        actionMenuItemView.perform(click());

        onView(withId(R.id.listParadas)).check(ViewAssertions.matches(recargaCorrecta()));
    }

    public static Matcher<View> recargaCorrecta() {
        return new TypeSafeMatcher<View>() {
            @Override
            public boolean matchesSafely(View view) {
                ListView list = (ListView) view;
                return (list.getCount() != 0);
            }
            @Override
            public void describeTo(Description description) {
                description.appendText("Recarga de datos incorrecta");
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

