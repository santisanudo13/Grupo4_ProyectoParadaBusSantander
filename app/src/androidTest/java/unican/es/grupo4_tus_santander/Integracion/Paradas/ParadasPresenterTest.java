package unican.es.grupo4_tus_santander.Integracion.Paradas;

import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import unican.es.grupo4_tus_santander.Models.BaseDatos.helper.DatabaseHelper;
import unican.es.grupo4_tus_santander.Models.Pojos.Parada;
import unican.es.grupo4_tus_santander.Presenter.Paradas.ListParadasPresenter;
import unican.es.grupo4_tus_santander.View.Paradas.ParadasActivity;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.mock;

/**
 * Created by Asier on 31/10/17.
 */

@RunWith(AndroidJUnit4.class)
public class ParadasPresenterTest {
    static DatabaseHelper db;
    static List<Parada> p;

    @BeforeClass
    public static void setUpBeforeClass(){
        db= new DatabaseHelper(InstrumentationRegistry.getTargetContext(),1);
        p = new ArrayList<>();
        Parada p1 = new Parada(1, 10, 1.0, 1.0, 1.0, 1.0, 0);
        Parada p2 = new Parada(2, 20, 1.0, 1.0, 1.0, 1.0, 1);
        Parada p3 = new Parada(500, 30, 1.0, 1.0, 1.0, 1.0, 2);
        Parada p4 = new Parada(999, 40, 1.0, 1.0, 1.0, 1.0, 3);
        p.add(p1);
        p.add(p2);
        p.add(p3);
        p.add(p4);
    }

    @Test
    public void obtenParadasCorrecto(){
        ParadasActivity la = mock(ParadasActivity.class);
        ListParadasPresenter p =new ListParadasPresenter(InstrumentationRegistry.getTargetContext(),la);
        p.ld=db;
        assertTrue(p.obtenParadasPorLinea());
    }

    @Test
    public void obtenParadasError(){
        db.reiniciarTablas();
        ParadasActivity la = mock(ParadasActivity.class);
        ListParadasPresenter p =new ListParadasPresenter(InstrumentationRegistry.getTargetContext(),la);
        assertFalse(p.obtenParadasPorLinea());
    }
}


