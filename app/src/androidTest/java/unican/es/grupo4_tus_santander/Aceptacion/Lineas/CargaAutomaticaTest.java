package unican.es.grupo4_tus_santander.Aceptacion.Lineas;


import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.DataInteraction;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ListView;
import android.widget.ProgressBar;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

import unican.es.grupo4_tus_santander.Models.WebService.DataLoaders.RemoteFetch;
import unican.es.grupo4_tus_santander.R;
import unican.es.grupo4_tus_santander.View.Lineas.LineasActivity;
import unican.es.grupo4_tus_santander.View.Main.MainActivity;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class CargaAutomaticaTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);
    @Rule
    public ActivityTestRule<LineasActivity> lActivityTestRule = new ActivityTestRule<>(LineasActivity.class);


    RemoteFetch remoteFetch = mock(RemoteFetch.class);



    @Test
    public void cargaAutomaticaTest() throws IOException {
        final InputStream isLineas = mActivityTestRule.getActivity().getApplicationContext().getResources().openRawResource(R.raw.lineas_test);
        final InputStream isParadas = mActivityTestRule.getActivity().getApplicationContext().getResources().openRawResource(R.raw.paradas_bus);
        final InputStream isLineasParadas = mActivityTestRule.getActivity().getApplicationContext().getResources().openRawResource(R.raw.lineas_bus_paradas);


        doAnswer(new Answer() {
            public Object answer(InvocationOnMock invocation) {
                remoteFetch.setBufferedData((BufferedInputStream) isLineas);
                return 0;
            }})
                .when(remoteFetch).getJSON(RemoteFetch.URL_LINEAS_BUS);

        doAnswer(new Answer() {
            public Object answer(InvocationOnMock invocation) {
                remoteFetch.setBufferedData((BufferedInputStream) isParadas);
                return 0;
            }})
                .when(remoteFetch).getJSON(RemoteFetch.URL_PARADAS);

        doAnswer(new Answer() {
            public Object answer(InvocationOnMock invocation) {
                remoteFetch.setBufferedData((BufferedInputStream) isLineasParadas);
                return 0;
            }})
                .when(remoteFetch).getJSON(RemoteFetch.URL_PARADAS_NOMBRE);



        lActivityTestRule.getActivity().getListLineasPresenter().getR().setRemoteFetch(remoteFetch);


        DataInteraction relativeLayout = onData(anything())
                .inAdapterView(allOf(withId(R.id.listFuncionesMenu),
                        childAtPosition(
                                withClassName(is("android.widget.LinearLayout")),
                                1)))
                .atPosition(1);
        relativeLayout.perform(click());



        try {
            Thread.sleep(1000*15);
        } catch (InterruptedException e) {
        }

        onView(withId(R.id.listLineas)).check(ViewAssertions.matches(listaNoVacia()));


    }

    public static Matcher<View> listaNoVacia (){
        return new TypeSafeMatcher<View>() {
            @Override
            protected boolean matchesSafely(View item) {
                // Comprueba que el el progressBar se muestra
                ListView list = (ListView) item;

                return (list.getCount() != 0);
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
