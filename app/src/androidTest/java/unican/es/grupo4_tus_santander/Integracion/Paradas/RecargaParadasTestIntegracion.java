package unican.es.grupo4_tus_santander.integracion.paradas;

import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.test.InstrumentationTestCase;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;
import java.util.concurrent.CountDownLatch;

import unican.es.grupo4_tus_santander.models.basedatos.helper.DatabaseHelper;
import unican.es.grupo4_tus_santander.models.pojos.Parada;
import unican.es.grupo4_tus_santander.presenter.paradas.ListParadasPresenter;
import unican.es.grupo4_tus_santander.presenter.paradas.RecargaBaseDatosParadas;
import unican.es.grupo4_tus_santander.view.paradas.ParadasActivity;

import static org.mockito.Mockito.mock;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

@RunWith(AndroidJUnit4.class)
public class RecargaParadasTestIntegracion extends InstrumentationTestCase {

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

        List<Parada> paradas = db.getAllParada();
        assertTrue(paradas.isEmpty());


        // create  a signal to let us know when our task is done.
        final CountDownLatch signal = new CountDownLatch(1);

        ParadasActivity activity = mock(ParadasActivity.class);
        ListParadasPresenter presenter = new ListParadasPresenter(InstrumentationRegistry.getTargetContext(), activity);
        //Realizamos la recarga de la base de datos
       RecargaBaseDatosParadas refresh = new RecargaBaseDatosParadas(InstrumentationRegistry.getTargetContext(), activity, presenter);

        refresh.setListener(new RecargaBaseDatosParadas.ServicioListener(){
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
        paradas = db.getAllParada();
        db.closeDB();

        assertTrue(!paradas.isEmpty());
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

        List<Parada> paradas = db.getAllParada();
        assertTrue(paradas.isEmpty());

        // create  a signal to let us know when our task is done. y el mock de la main activity para poder crear el objeto de RecargaBaseDatosmenu
        final CountDownLatch signal = new CountDownLatch(1);
        ParadasActivity activity = mock(ParadasActivity.class);
        ListParadasPresenter presenter = new ListParadasPresenter(InstrumentationRegistry.getTargetContext(), activity);
        //Realizamos la recarga de la base de datos
        RecargaBaseDatosParadas refresh = new RecargaBaseDatosParadas(InstrumentationRegistry.getTargetContext(), activity, presenter);

        refresh.setListener(new RecargaBaseDatosParadas.ServicioListener(){
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
        paradas = db.getAllParada();
        db.closeDB();

        assertTrue(paradas.isEmpty());
    }

}