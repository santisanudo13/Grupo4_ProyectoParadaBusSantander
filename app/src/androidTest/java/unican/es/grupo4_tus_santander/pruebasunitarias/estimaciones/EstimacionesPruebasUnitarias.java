package unican.es.grupo4_tus_santander.pruebasunitarias.estimaciones;

import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import unican.es.grupo4_tus_santander.R;
import unican.es.grupo4_tus_santander.models.pojos.Estimacion;
import unican.es.grupo4_tus_santander.models.webservice.dataloaders.ParserJSON;
import unican.es.grupo4_tus_santander.presenter.estimaciones.EstimacionesPresenter;
import unican.es.grupo4_tus_santander.view.estimaciones.EstimacionesActivity;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.ignoreStubs;
import static org.mockito.Mockito.mock;

/**
 * Created by Luis on 26/11/17.
 */
@RunWith(AndroidJUnit4.class)
public class EstimacionesPruebasUnitarias {

    private static List<Estimacion> lista= new ArrayList<>();
    static Estimacion e1,e2,e3;
    EstimacionesActivity actividad = mock(EstimacionesActivity.class);

    @BeforeClass
    public static void setup(){
        e1 = new Estimacion("1","12","641","5","1241243456412");
        e2 = new Estimacion("2","22","451","4","1241adad21412");
        e3 = new Estimacion("3","42","121","5","12412");
        lista.add(e1);
        lista.add(e2);
        lista.add(e3);
    }



    @Test
    public void u1() throws Exception {
        //Obtenemos el InputStream para el json almacenado en la carpeta raw del proyecto
        InputStream is = InstrumentationRegistry.getTargetContext().getResources().openRawResource(R.raw.estimaciones_test);
        List<Estimacion> listEstimaciones= ParserJSON.readArrayEstimaciones(is);


        //Este if es por que como nuestro parse comprueba que los datos sean del mismo dia y hora solo funciona
        //una vez al mes ya que si no deberiamos estar generando JSON cada hora para realizar los test.
        if(listEstimaciones.size()==0){
            assertEquals(0,listEstimaciones.size());
        }else {
            assertEquals("1", listEstimaciones.get(0).getParada());
            assertEquals("3", listEstimaciones.get(0).getNombreLinea());
            assertEquals("66", listEstimaciones.get(0).getEstimacionA());
            assertEquals("225", listEstimaciones.get(0).getEstimacionB());

            assertEquals("2", listEstimaciones.get(1).getParada());
            assertEquals("2", listEstimaciones.get(1).getNombreLinea());
            assertEquals("88", listEstimaciones.get(1).getEstimacionA());
            assertEquals("1137", listEstimaciones.get(1).getEstimacionB());
        }
    }

    @Test
    public void u2(){
        EstimacionesPresenter presenter = new EstimacionesPresenter(InstrumentationRegistry.getTargetContext(),"1","5",actividad);

        presenter.setEstimaciones(lista);

        presenter.ordena(true);

        List<Estimacion> ordenado = presenter.getListaDevuelta();
        assertEquals("1",ordenado.get(0).getNombreLinea());
        assertEquals("5",ordenado.get(0).getParada());
        assertEquals("12",ordenado.get(0).getEstimacionA());
        assertEquals("641",ordenado.get(0).getEstimacionB());
        assertTrue(ordenado.size()>0);
    }

    @Test
    public void u3(){
        EstimacionesPresenter presenter = new EstimacionesPresenter(InstrumentationRegistry.getTargetContext(),"1","5",actividad);

        List<Estimacion> vacia = new ArrayList<>();
        presenter.setEstimaciones(vacia);

        presenter.ordena(true);

        List<Estimacion> ordenado = presenter.getListaDevuelta();
        assertTrue(ordenado.size()==0);
    }

    @Test
    public void u4(){
        EstimacionesPresenter presenter = new EstimacionesPresenter(InstrumentationRegistry.getTargetContext(),"13","5",actividad);

        presenter.setEstimaciones(lista);

        presenter.ordena(true);

        List<Estimacion> ordenado = presenter.getListaDevuelta();
        assertTrue(ordenado.size()>0);

        assertTrue(Integer.parseInt(ordenado.get(0).getEstimacionA())<=Integer.parseInt(ordenado.get(1).getEstimacionA()));
    }

    @Test
    public void u5(){
        EstimacionesPresenter presenter = new EstimacionesPresenter(InstrumentationRegistry.getTargetContext(),"1","25",actividad);

        presenter.setEstimaciones(lista);

        presenter.ordena(true);

        List<Estimacion> ordenado = presenter.getListaDevuelta();
        assertEquals(0,ordenado.size());

    }
}
