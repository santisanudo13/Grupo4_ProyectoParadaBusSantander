package unican.es.grupo4_tus_santander.pruebasunitarias.paradas;

import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import unican.es.grupo4_tus_santander.models.basedatos.helper.DatabaseHelper;
import unican.es.grupo4_tus_santander.models.pojos.Parada;
import unican.es.grupo4_tus_santander.presenter.paradas.ListParadasPresenter;
import unican.es.grupo4_tus_santander.view.paradas.ParadasActivity;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Asier on 31/10/17.
 */

@RunWith(AndroidJUnit4.class)
public class ParadasPresenterTest {
    static DatabaseHelper db;
    static List<Parada> p;

    @BeforeClass
    public static void setUpBeforeClass(){
        db= mock(DatabaseHelper.class);
        p = new ArrayList<>();

        Parada p1 = new Parada(0, 10, 1.0, 1.0, 1.0, 1.0, 0);
        Parada p2 = new Parada(1, 20, 1.0, 1.0, 1.0, 1.0, 1);
        Parada p3 = new Parada(1, 30, 1.0, 1.0, 1.0, 1.0, 2);
        Parada p4 = new Parada(2, 40, 1.0, 1.0, 1.0, 1.0, 3);

        p.add(p1);
        p.add(p2);
        p.add(p3);
        p.add(p4);
    }

    @Test
    public void obtenParadasCorrecto(){
        when(db.getParadasByLinea(1)).thenReturn(p);

        ParadasActivity pa = mock(ParadasActivity.class);
        ListParadasPresenter p = new ListParadasPresenter(InstrumentationRegistry.getTargetContext(), pa);
        p.setDb(db);
        p.setIdLinea(1);


        assertTrue(p.obtenParadas());
    }

    @Test
    public void obtenParadasError(){
        ParadasActivity pa = mock(ParadasActivity.class);
        ListParadasPresenter p =new ListParadasPresenter(InstrumentationRegistry.getTargetContext(),pa);
        assertFalse(p.obtenParadas());
    }
}