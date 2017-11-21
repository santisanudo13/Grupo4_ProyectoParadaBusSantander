package unican.es.grupo4_tus_santander.pruebasunitarias.lineas;

import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import unican.es.grupo4_tus_santander.models.pojos.Linea;
import unican.es.grupo4_tus_santander.presenter.lineas.RecargaBaseDatosLineas;
import unican.es.grupo4_tus_santander.view.lineas.LineasActivity;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.mock;

/**
 * Created by Luis on 20/11/17.
 */

@RunWith(AndroidJUnit4.class)
public class CargaDatosIncompleta {

    public static LineasActivity activity = mock(LineasActivity.class);

    public static Linea l1,l2,l3;
    public static List<Linea> lista;
    @BeforeClass
    public static void setUpBeforeClass() {
        l1 = new Linea("1", "uno", 1);
        l2 = new Linea("2", "dos", 2);
        l3 = new Linea("3", "tres", 234);

        lista = new ArrayList<>();
        lista.add(l1);
        lista.add(l2);
        lista.add(l3);

    }



    @Test
    public void u1(){
        RecargaBaseDatosLineas rd = new RecargaBaseDatosLineas(InstrumentationRegistry.getTargetContext(),activity);
        rd.setNumLineasExpected(3);
        rd.setListLineasBuffer(lista);
        assertTrue(rd.guardaDataEnBaseDatos());

    }

    @Test
    public void u2(){
        RecargaBaseDatosLineas rd = new RecargaBaseDatosLineas(InstrumentationRegistry.getTargetContext(),activity);
        rd.setNumLineasExpected(13);
        rd.setListLineasBuffer(lista);
        assertFalse(rd.guardaDataEnBaseDatos());

    }
}
