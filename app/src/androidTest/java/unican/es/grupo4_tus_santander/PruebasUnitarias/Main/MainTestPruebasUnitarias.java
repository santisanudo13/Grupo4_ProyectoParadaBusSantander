package unican.es.grupo4_tus_santander.PruebasUnitarias.Main;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import unican.es.grupo4_tus_santander.Presenter.Main.RecargaBaseDatosMenu;
import unican.es.grupo4_tus_santander.R;
import unican.es.grupo4_tus_santander.View.Main.MainActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.mock;


/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class MainTestPruebasUnitarias{

    /**
     * U1. Comprobamos que el meotodo obtenData() de RecargaBaseDatos efectivamente rellena los valores en la lista de parámetros de la clase.
     * a.	Cuando tenemos todo correcto
     */
    @Test
    public void u1a(){
        MainActivity mainActivity = mock(MainActivity.class);
        RecargaBaseDatosMenu refresh = new RecargaBaseDatosMenu(InstrumentationRegistry.getTargetContext(), mainActivity);

        //Comprobamos que los datos todavia no estan en las listas
        assertTrue( refresh.getListLineas().isEmpty());
        assertTrue( refresh.getListParadas().isEmpty());
        assertTrue( refresh.getListParadasConNombre().isEmpty());
        //Recargamos los datos
        refresh.obtenData();
        //Comprobamos que estan rellenadas las listas
        assertTrue( !refresh.getListLineas().isEmpty());
        assertTrue( !refresh.getListParadas().isEmpty());
        assertTrue( !refresh.getListParadasConNombre().isEmpty());
    }

    /**
     * U1. Comprobamos que el meotodo obtenData() de RecargaBaseDatos efectivamente rellena los valores en la lista de parámetros de la clase.
     * b.	Cuando no tenemos conexiona internet
     */
    @Test
    public void u1b() {
        MainActivity mainActivity = mock(MainActivity.class);
        RecargaBaseDatosMenu refresh = new RecargaBaseDatosMenu(InstrumentationRegistry.getTargetContext(), mainActivity);

        //Comprobamos que los datos todavia no estan en las listas
        assertTrue(refresh.getListLineas().isEmpty());
        assertTrue(refresh.getListParadas().isEmpty());
        assertTrue(refresh.getListParadasConNombre().isEmpty());
        //Quitamos internet
        refresh.setCm(null);
        //Recargamos los datos
        refresh.obtenData();
        //Comprobamos que estan rellenadas las listas
        assertTrue(refresh.getListLineas().isEmpty());
        assertTrue(refresh.getListParadas().isEmpty());
        assertTrue(refresh.getListParadasConNombre().isEmpty());
    }


}
