package unican.es.grupo4_tus_santander.pruebasunitarias.paradas;

import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.test.InstrumentationTestCase;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import unican.es.grupo4_tus_santander.models.pojos.Parada;
import unican.es.grupo4_tus_santander.presenter.paradas.ListParadasPresenter;
import unican.es.grupo4_tus_santander.view.paradas.ParadasActivity;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.mock;

/**
 * Created by Santi on 31/10/17.
 */

@RunWith(AndroidJUnit4.class)
public class BusquedaParadasUnitariaTest extends InstrumentationTestCase {
    static ListParadasPresenter presenter;
    static List<Parada> listaParadas;
    static ParadasActivity mockParadaActivity = mock(ParadasActivity.class);

    @BeforeClass
    public static void setUpBeforeClass(){
        listaParadas = new ArrayList<>();

        Parada p1 = new Parada(0, 10, 1.0, 1.0, 1.0, 1.0, 38);
        Parada p2 = new Parada(1, 20, 1.0, 1.0, 1.0, 1.0, 31);
        Parada p3 = new Parada(1, 30, 1.0, 1.0, 1.0, 1.0, 33);
        Parada p4 = new Parada(2, 40, 1.0, 1.0, 1.0, 1.0, 4);

        p1.setNombre("Parada 1");
        p2.setNombre("Parada 2");
        p3.setNombre("Parada 3");
        p4.setNombre("Parada Ávila 4");

        listaParadas.add(p1);
        listaParadas.add(p2);
        listaParadas.add(p3);
        listaParadas.add(p4);

        presenter = new ListParadasPresenter(InstrumentationRegistry.getTargetContext(), mockParadaActivity);
    }

    @Test
    public void u1(){
        presenter.setListaParadas(listaParadas);
        presenter.filtra("Parada 1");

        assertTrue(presenter.getTemp().size() == 1);
    }
    @Test
    public void u2(){
        presenter.setListaParadas(listaParadas);
        presenter.filtra("Parada");

        assertTrue(presenter.getTemp().size() == 4);
    }
    @Test
    public void u3(){
        presenter.setListaParadas(listaParadas);
        presenter.filtra("4");

        assertTrue(presenter.getTemp().size() == 1);
    }
    @Test
    public void u4(){
        presenter.setListaParadas(listaParadas);
        presenter.filtra("3");

        assertTrue(presenter.getTemp().size() == 3);
    }
    @Test
    public void u5(){
        presenter.setListaParadas(listaParadas);
        presenter.filtra("PARADA 3");

        assertTrue(presenter.getTemp().size() == 1);
    }
    @Test
    public void u6(){
        presenter.setListaParadas(listaParadas);
        presenter.filtra("parada 3");

        assertTrue(presenter.getTemp().size() == 1);

    }
    @Test
    public void u7(){
        presenter.setListaParadas(listaParadas);
        presenter.filtra("Parada Ávila 4");

        assertTrue(presenter.getTemp().size() == 1);
    }
    @Test
    public void u8(){
        presenter.setListaParadas(listaParadas);
        presenter.filtra("Parada Avila 4");

        assertTrue(presenter.getTemp().size() == 1);
    }
    @Test
    public void u9(){
        presenter.setListaParadas(listaParadas);
        presenter.filtra("Jack Sparrow");

        assertTrue(presenter.getTemp().size() == 0);
    }


}