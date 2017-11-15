package unican.es.grupo4_tus_santander.integracion.paradas;

import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.test.InstrumentationTestCase;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import unican.es.grupo4_tus_santander.models.basedatos.helper.DatabaseHelper;
import unican.es.grupo4_tus_santander.models.pojos.Color;
import unican.es.grupo4_tus_santander.models.pojos.Linea;
import unican.es.grupo4_tus_santander.models.pojos.Parada;
import unican.es.grupo4_tus_santander.presenter.paradas.ListParadasPresenter;
import unican.es.grupo4_tus_santander.view.paradas.ParadasActivity;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.mock;

/**
 * Created by Asier on 31/10/17.
 */

@RunWith(AndroidJUnit4.class)
public class ParadasPresenterTest extends InstrumentationTestCase {
    static DatabaseHelper db;





    @Test
    public void obtenParadasCorrecto(){
        db= new DatabaseHelper(InstrumentationRegistry.getTargetContext(),1);
        db.reiniciarTablas();
        Parada p1= new Parada(1, 10, 1.0, 1.0, 1.0, 1.0, 0);
        Linea linea= new Linea("100","cien",105464);;
        Color c= new Color(1,1,1,1);;

        long idColor = db.createColor(c);
        long t =db.createLinea(linea,idColor );


        p1.setNombre("Nombre_");

        db.createParada(p1, t);

        ListParadasPresenter p =new ListParadasPresenter(InstrumentationRegistry.getTargetContext(),null);

        p.setIdLinea((int)t);
        assertTrue(p.obtenParadas());
        db.reiniciarTablas();
        db.closeDB();
    }

    @Test
    public void obtenParadasError(){
        db= new DatabaseHelper(InstrumentationRegistry.getTargetContext(),1);

        db.reiniciarTablas();

        ListParadasPresenter p =new ListParadasPresenter(InstrumentationRegistry.getTargetContext(),null);
        p.setIdLinea(50);
        assertFalse(p.obtenParadas());
        db.reiniciarTablas();
        db.closeDB();
    }
}