package unican.es.grupo4_tus_santander.aceptacion.paradas;


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
import unican.es.grupo4_tus_santander.view.main.MainActivity;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.pressImeActionButton;
import static android.support.test.espresso.action.ViewActions.replaceText;
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
public class BusquedaParadasTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

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
                .atPosition(0);
        relativeLayout2.perform(click());

        ViewInteraction appCompatImageView = onView(
                allOf(withClassName(is("android.support.v7.widget.AppCompatImageView")), withContentDescription("Search"),
                        childAtPosition(
                                allOf(withClassName(is("android.widget.LinearLayout")),
                                        childAtPosition(
                                                withId(R.id.search),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatImageView.perform(click());

        ViewInteraction searchAutoComplete = onView(
                allOf(withClassName(is("android.widget.SearchView$SearchAutoComplete")),
                        childAtPosition(
                                allOf(withClassName(is("android.widget.LinearLayout")),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                1)),
                                0),
                        isDisplayed()));
        searchAutoComplete.perform(replaceText("Canalejas 42"), closeSoftKeyboard());

        ViewInteraction textView = onView(
                allOf(withId(R.id.textViewName), withText("Canalejas 42"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.listParadas),
                                        0),
                                1),
                        isDisplayed()));
        textView.check(matches(withText("Canalejas 42")));

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

        DataInteraction relativeLayout2 = onData(anything())
                .inAdapterView(allOf(withId(R.id.listLineas),
                        childAtPosition(
                                withClassName(is("android.widget.RelativeLayout")),
                                1)))
                .atPosition(0);
        relativeLayout2.perform(click());

        ViewInteraction appCompatImageView = onView(
                allOf(withClassName(is("android.support.v7.widget.AppCompatImageView")), withContentDescription("Search"),
                        childAtPosition(
                                allOf(withClassName(is("android.widget.LinearLayout")),
                                        childAtPosition(
                                                withId(R.id.search),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatImageView.perform(click());

        ViewInteraction searchAutoComplete = onView(
                allOf(withClassName(is("android.widget.SearchView$SearchAutoComplete")),
                        childAtPosition(
                                allOf(withClassName(is("android.widget.LinearLayout")),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                1)),
                                0),
                        isDisplayed()));
        searchAutoComplete.perform(replaceText("Canale"), closeSoftKeyboard());

        ViewInteraction textView = onView(
                allOf(withId(R.id.textViewName), withText("Canalejas 26"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.listParadas),
                                        0),
                                1),
                        isDisplayed()));
        textView.check(matches(withText("Canalejas 26")));

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.textViewName), withText("Canalejas 42"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.listParadas),
                                        1),
                                1),
                        isDisplayed()));
        textView2.check(matches(withText("Canalejas 42")));

    }

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
                .atPosition(1);
        relativeLayout2.perform(click());

        ViewInteraction appCompatImageView = onView(
                allOf(withClassName(is("android.support.v7.widget.AppCompatImageView")), withContentDescription("Search"),
                        childAtPosition(
                                allOf(withClassName(is("android.widget.LinearLayout")),
                                        childAtPosition(
                                                withId(R.id.search),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatImageView.perform(click());

        ViewInteraction searchAutoComplete = onView(
                allOf(withClassName(is("android.widget.SearchView$SearchAutoComplete")),
                        childAtPosition(
                                allOf(withClassName(is("android.widget.LinearLayout")),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                1)),
                                0),
                        isDisplayed()));
        searchAutoComplete.perform(replaceText("General Dávila 41"), closeSoftKeyboard());

        ViewInteraction textView = onView(
                allOf(withId(R.id.textViewNumero), withText("125"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.listParadas),
                                        0),
                                0),
                        isDisplayed()));
        textView.check(matches(withText("125")));

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.textViewName), withText("General Dávila 41"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.listParadas),
                                        0),
                                1),
                        isDisplayed()));
        textView2.check(matches(withText("General Dávila 41")));

    }

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
                .atPosition(1);
        relativeLayout2.perform(click());

        ViewInteraction appCompatImageView = onView(
                allOf(withClassName(is("android.support.v7.widget.AppCompatImageView")), withContentDescription("Search"),
                        childAtPosition(
                                allOf(withClassName(is("android.widget.LinearLayout")),
                                        childAtPosition(
                                                withId(R.id.search),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatImageView.perform(click());

        ViewInteraction searchAutoComplete = onView(
                allOf(withClassName(is("android.widget.SearchView$SearchAutoComplete")),
                        childAtPosition(
                                allOf(withClassName(is("android.widget.LinearLayout")),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                1)),
                                0),
                        isDisplayed()));
        searchAutoComplete.perform(replaceText("General Davila 41"), closeSoftKeyboard());

        ViewInteraction textView = onView(
                allOf(withId(R.id.textViewName), withText("General Dávila 41"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.listParadas),
                                        0),
                                1),
                        isDisplayed()));
        textView.check(matches(withText("General Dávila 41")));

    }

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
                .atPosition(30);
        relativeLayout2.perform(click());

        ViewInteraction appCompatImageView = onView(
                allOf(withClassName(is("android.support.v7.widget.AppCompatImageView")), withContentDescription("Search"),
                        childAtPosition(
                                allOf(withClassName(is("android.widget.LinearLayout")),
                                        childAtPosition(
                                                withId(R.id.search),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatImageView.perform(click());

        ViewInteraction searchAutoComplete = onView(
                allOf(withClassName(is("android.widget.SearchView$SearchAutoComplete")),
                        childAtPosition(
                                allOf(withClassName(is("android.widget.LinearLayout")),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                1)),
                                0),
                        isDisplayed()));
        searchAutoComplete.perform(replaceText("Peñacastillo"), closeSoftKeyboard());

        ViewInteraction textView = onView(
                allOf(withId(R.id.textViewName), withText("Carrefour Peñacastillo"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.listParadas),
                                        0),
                                1),
                        isDisplayed()));
        textView.check(matches(withText("Carrefour Peñacastillo")));

    }

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
                .atPosition(0);
        relativeLayout2.perform(click());

        ViewInteraction appCompatImageView = onView(
                allOf(withClassName(is("android.support.v7.widget.AppCompatImageView")), withContentDescription("Search"),
                        childAtPosition(
                                allOf(withClassName(is("android.widget.LinearLayout")),
                                        childAtPosition(
                                                withId(R.id.search),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatImageView.perform(click());

        ViewInteraction searchAutoComplete = onView(
                allOf(withClassName(is("android.widget.SearchView$SearchAutoComplete")),
                        childAtPosition(
                                allOf(withClassName(is("android.widget.LinearLayout")),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                1)),
                                0),
                        isDisplayed()));
        searchAutoComplete.perform(replaceText("CANALEJAS 42"), closeSoftKeyboard());

        ViewInteraction textView = onView(
                allOf(withId(R.id.textViewName), withText("Canalejas 42"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.listParadas),
                                        0),
                                1),
                        isDisplayed()));
        textView.check(matches(withText("Canalejas 42")));

    }

    @Test
    public void a7() {
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

        ViewInteraction appCompatImageView = onView(
                allOf(withClassName(is("android.support.v7.widget.AppCompatImageView")), withContentDescription("Search"),
                        childAtPosition(
                                allOf(withClassName(is("android.widget.LinearLayout")),
                                        childAtPosition(
                                                withId(R.id.search),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatImageView.perform(click());

        ViewInteraction searchAutoComplete = onView(
                allOf(withClassName(is("android.widget.SearchView$SearchAutoComplete")),
                        childAtPosition(
                                allOf(withClassName(is("android.widget.LinearLayout")),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                1)),
                                0),
                        isDisplayed()));
        searchAutoComplete.perform(replaceText("canalejas 42"), closeSoftKeyboard());

        ViewInteraction textView = onView(
                allOf(withId(R.id.textViewName), withText("Canalejas 42"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.listParadas),
                                        0),
                                1),
                        isDisplayed()));
        textView.check(matches(withText("Canalejas 42")));

    }

    @Test
    public void a8() {
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

        ViewInteraction appCompatImageView = onView(
                allOf(withClassName(is("android.support.v7.widget.AppCompatImageView")), withContentDescription("Search"),
                        childAtPosition(
                                allOf(withClassName(is("android.widget.LinearLayout")),
                                        childAtPosition(
                                                withId(R.id.search),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatImageView.perform(click());

        ViewInteraction searchAutoComplete = onView(
                allOf(withClassName(is("android.widget.SearchView$SearchAutoComplete")),
                        childAtPosition(
                                allOf(withClassName(is("android.widget.LinearLayout")),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                1)),
                                0),
                        isDisplayed()));
        searchAutoComplete.perform(replaceText("310"), closeSoftKeyboard());

        ViewInteraction textView = onView(
                allOf(withId(R.id.textViewNumero), withText("310"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.listParadas),
                                        0),
                                0),
                        isDisplayed()));
        textView.check(matches(withText("310")));

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.textViewName), withText("Francisco Tomas y Valiente 23"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.listParadas),
                                        0),
                                1),
                        isDisplayed()));
        textView2.check(matches(withText("Francisco Tomas y Valiente 23")));

    }

    @Test
    public void a9() {
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

        ViewInteraction appCompatImageView = onView(
                allOf(withClassName(is("android.support.v7.widget.AppCompatImageView")), withContentDescription("Search"),
                        childAtPosition(
                                allOf(withClassName(is("android.widget.LinearLayout")),
                                        childAtPosition(
                                                withId(R.id.search),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatImageView.perform(click());

        ViewInteraction searchAutoComplete = onView(
                allOf(withClassName(is("android.widget.SearchView$SearchAutoComplete")),
                        childAtPosition(
                                allOf(withClassName(is("android.widget.LinearLayout")),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                1)),
                                0),
                        isDisplayed()));
        searchAutoComplete.perform(replaceText("30"), closeSoftKeyboard());

        ViewInteraction textView = onView(
                allOf(withId(R.id.textViewName), withText("Reina Victoria  117"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.listParadas),
                                        0),
                                1),
                        isDisplayed()));
        textView.check(matches(withText("Reina Victoria  117")));

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.textViewNumero), withText("130"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.listParadas),
                                        0),
                                0),
                        isDisplayed()));
        textView2.check(matches(withText("130")));

        ViewInteraction textView3 = onView(
                allOf(withId(R.id.textViewNumero), withText("305"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.listParadas),
                                        1),
                                0),
                        isDisplayed()));
        textView3.check(matches(withText("305")));

        ViewInteraction textView4 = onView(
                allOf(withId(R.id.textViewName), withText("Carrefour Peñacastillo"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.listParadas),
                                        1),
                                1),
                        isDisplayed()));
        textView4.check(matches(withText("Carrefour Peñacastillo")));

        ViewInteraction textView5 = onView(
                allOf(withId(R.id.textViewNumero), withText("306"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.listParadas),
                                        2),
                                0),
                        isDisplayed()));
        textView5.check(matches(withText("306")));

        ViewInteraction textView6 = onView(
                allOf(withId(R.id.textViewName), withText("Francisco Rivas Moreno"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.listParadas),
                                        2),
                                1),
                        isDisplayed()));
        textView6.check(matches(withText("Francisco Rivas Moreno")));

        ViewInteraction textView7 = onView(
                allOf(withId(R.id.textViewNumero), withText("307"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.listParadas),
                                        3),
                                0),
                        isDisplayed()));
        textView7.check(matches(withText("307")));

        ViewInteraction textView8 = onView(
                allOf(withId(R.id.textViewName), withText("Nuevo Parque"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.listParadas),
                                        3),
                                1),
                        isDisplayed()));
        textView8.check(matches(withText("Nuevo Parque")));

        ViewInteraction textView9 = onView(
                allOf(withId(R.id.textViewNumero), withText("308"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.listParadas),
                                        4),
                                0),
                        isDisplayed()));
        textView9.check(matches(withText("308")));

        ViewInteraction textView10 = onView(
                allOf(withId(R.id.textViewName), withText("San Martin Del Pino 8"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.listParadas),
                                        4),
                                1),
                        isDisplayed()));
        textView10.check(matches(withText("San Martin Del Pino 8")));

        ViewInteraction textView11 = onView(
                allOf(withId(R.id.textViewNumero), withText("309"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.listParadas),
                                        5),
                                0),
                        isDisplayed()));
        textView11.check(matches(withText("309")));

        ViewInteraction textView12 = onView(
                allOf(withId(R.id.textViewName), withText("Santiago el Mayor 9"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.listParadas),
                                        5),
                                1),
                        isDisplayed()));
        textView12.check(matches(withText("Santiago el Mayor 9")));

    }


    @Test
    public void a10() {
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

        ViewInteraction appCompatImageView = onView(
                allOf(withClassName(is("android.support.v7.widget.AppCompatImageView")), withContentDescription("Search"),
                        childAtPosition(
                                allOf(withClassName(is("android.widget.LinearLayout")),
                                        childAtPosition(
                                                withId(R.id.search),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatImageView.perform(click());

        ViewInteraction searchAutoComplete = onView(
                allOf(withClassName(is("android.widget.SearchView$SearchAutoComplete")),
                        childAtPosition(
                                allOf(withClassName(is("android.widget.LinearLayout")),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                1)),
                                0),
                        isDisplayed()));
        searchAutoComplete.perform(replaceText("35"), closeSoftKeyboard());

        ViewInteraction textView = onView(
                allOf(withId(R.id.textViewNumero), withText("135"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.listParadas),
                                        0),
                                0),
                        isDisplayed()));
        textView.check(matches(withText("135")));

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.textViewName), withText("Miranda"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.listParadas),
                                        0),
                                1),
                        isDisplayed()));
        textView2.check(matches(withText("Miranda")));

        ViewInteraction textView3 = onView(
                allOf(withId(R.id.textViewNumero), withText("352"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.listParadas),
                                        1),
                                0),
                        isDisplayed()));
        textView3.check(matches(withText("352")));

        ViewInteraction textView4 = onView(
                allOf(withId(R.id.textViewName), withText("Camarreal 135"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.listParadas),
                                        1),
                                1),
                        isDisplayed()));
        textView4.check(matches(withText("Camarreal 135")));

        ViewInteraction textView5 = onView(
                allOf(withId(R.id.textViewNumero), withText("353"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.listParadas),
                                        2),
                                0),
                        isDisplayed()));
        textView5.check(matches(withText("353")));

        ViewInteraction textView6 = onView(
                allOf(withId(R.id.textViewName), withText("Camarreal 136"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.listParadas),
                                        2),
                                1),
                        isDisplayed()));
        textView6.check(matches(withText("Camarreal 136")));

        ViewInteraction textView7 = onView(
                allOf(withId(R.id.textViewNumero), withText("355"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.listParadas),
                                        3),
                                0),
                        isDisplayed()));
        textView7.check(matches(withText("355")));

        ViewInteraction textView8 = onView(
                allOf(withId(R.id.textViewName), withText("Ojaiz"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.listParadas),
                                        3),
                                1),
                        isDisplayed()));
        textView8.check(matches(withText("Ojaiz")));

        ViewInteraction textView9 = onView(
                allOf(withId(R.id.textViewNumero), withText("444"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.listParadas),
                                        4),
                                0),
                        isDisplayed()));
        textView9.check(matches(withText("444")));

        ViewInteraction textView10 = onView(
                allOf(withId(R.id.textViewName), withText("Paseo de Pereda nº 35"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.listParadas),
                                        4),
                                1),
                        isDisplayed()));
        textView10.check(matches(withText("Paseo de Pereda nº 35")));

    }


    @Test
    public void a11() {
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

        ViewInteraction appCompatImageView = onView(
                allOf(withClassName(is("android.support.v7.widget.AppCompatImageView")), withContentDescription("Search"),
                        childAtPosition(
                                allOf(withClassName(is("android.widget.LinearLayout")),
                                        childAtPosition(
                                                withId(R.id.search),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatImageView.perform(click());

        ViewInteraction searchAutoComplete = onView(
                allOf(withClassName(is("android.widget.SearchView$SearchAutoComplete")),
                        childAtPosition(
                                allOf(withClassName(is("android.widget.LinearLayout")),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                1)),
                                0),
                        isDisplayed()));
        searchAutoComplete.perform(replaceText("ya"), closeSoftKeyboard());

        ViewInteraction textView = onView(
                allOf(withId(R.id.txtVacio), withText("No hay resultados"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                1),
                        isDisplayed()));
        textView.check(matches(withText("No hay resultados")));

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
