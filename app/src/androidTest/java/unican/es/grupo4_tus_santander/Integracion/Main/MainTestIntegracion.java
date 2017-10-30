package unican.es.grupo4_tus_santander.Integracion.Main;

import android.os.AsyncTask;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.core.deps.guava.util.concurrent.Service;
import android.support.test.runner.AndroidJUnit4;
import android.test.InstrumentationTestCase;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;
import java.util.concurrent.CountDownLatch;

import javax.security.auth.callback.Callback;

import unican.es.grupo4_tus_santander.Models.BaseDatos.helper.DatabaseHelper;
import unican.es.grupo4_tus_santander.Models.Pojos.Linea;
import unican.es.grupo4_tus_santander.Presenter.Main.AsyncTasks.GetDataServicio;
import unican.es.grupo4_tus_santander.Presenter.Main.MainPresenter;
import unican.es.grupo4_tus_santander.Presenter.Main.RecargaBaseDatosMenu;
import unican.es.grupo4_tus_santander.View.Interfaz.ActivityInterface;
import unican.es.grupo4_tus_santander.View.Main.MainActivity;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class MainTestIntegracion extends InstrumentationTestCase {


    /**
     * I1. Realizamos una actualización de los datos de la aplicación en la base de
     * datos satisfactoria al obtener los datos del servicio web del ayuntamiento de Santander.
     * @throws Exception
     */
    @Test
    public void i1() {
        //Vaciamos la base de datos para asegurarnos que se rellena correctamente.
        DatabaseHelper db = new DatabaseHelper(InstrumentationRegistry.getTargetContext(), 1);
        db.reiniciarTablas();

        List<Linea> lineas = db.getAllLinea();
        db.closeDB();
        assertTrue(lineas.isEmpty());



        // create  a signal to let us know when our task is done.
        final CountDownLatch signal = new CountDownLatch(1);


        MainActivity mainActivity = mock(MainActivity.class);

        //Realizamos la recarga de la base de datos
        RecargaBaseDatosMenu refresh = new RecargaBaseDatosMenu(InstrumentationRegistry.getTargetContext(), mainActivity);

        refresh.setListener(new RecargaBaseDatosMenu.ServicioListener(){
            @Override
            public void onComplete() {
                signal.countDown();
            }
        });

        refresh.start();

        try {
            signal.await();
        } catch (InterruptedException e) {}


        //Comprobamos que la base de datos se ha actualizado
        db = new DatabaseHelper(InstrumentationRegistry.getTargetContext(), 1);
        lineas = db.getAllLinea();
        db.closeDB();
        assertTrue(!lineas.isEmpty());

    }


    /**
     * I2. Realizamos una actualización de los datos de la aplicación en la base de datos incorrecta al no poder
     * obtener los datos del servicio web del ayuntamiento de Santander dado que no tenemos acceso a internet.
     * @throws Exception
     */
    @Test
    public void i2() {
        //Vaciamos la base de datos para asegurarnos que se rellena correctamente.
        DatabaseHelper db = new DatabaseHelper(InstrumentationRegistry.getTargetContext(), 1);
        db.reiniciarTablas();

        List<Linea> lineas = db.getAllLinea();
        db.closeDB();
        assertTrue(lineas.isEmpty());

        // create  a signal to let us know when our task is done. y el mock de la main activity para poder crear el objeto de RecargaBaseDatosmenu
        final CountDownLatch signal = new CountDownLatch(1);
        MainActivity mainActivity = mock(MainActivity.class);

        //Realizamos la recarga de la base de datos
        RecargaBaseDatosMenu refresh = new RecargaBaseDatosMenu(InstrumentationRegistry.getTargetContext(), mainActivity);

        refresh.setListener(new RecargaBaseDatosMenu.ServicioListener(){
            @Override
            public void onComplete() {
                signal.countDown();
            }
        });

        //CANCELAMOS LAS FUNCIONES DE ACCESO A INTERNET
        refresh.setCm(null);
        //Comenzamos la carga de datos
        refresh.start();

        try {
            signal.await();
        } catch (InterruptedException e) {}


        //Comprobamos que la base de datos se ha actualizado
        db = new DatabaseHelper(InstrumentationRegistry.getTargetContext(), 1);
        lineas = db.getAllLinea();

        assertTrue(lineas.isEmpty());
    }

}
