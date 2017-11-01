package unican.es.grupo4_tus_santander.Integracion.Paradas;

import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import unican.es.grupo4_tus_santander.Models.BaseDatos.helper.DatabaseHelper;
import unican.es.grupo4_tus_santander.Models.Pojos.Color;
import unican.es.grupo4_tus_santander.Models.Pojos.Linea;
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
    static Parada p1;
    static Linea linea;
    static Color c;


    @BeforeClass
    public static void setUpBeforeClass(){
        db= new DatabaseHelper(InstrumentationRegistry.getTargetContext(),1);

        p1 = new Parada(1, 10, 1.0, 1.0, 1.0, 1.0, 0);
        linea = new Linea("100","cien",105464);
        c= new Color(1,1,1,1);

    }

    @Test
    public void obtenParadasCorrecto(){
        db.reiniciarTablas();


        long idColor = db.createColor(c);
        linea.setIdColor((int) idColor);
        linea.setId((int) db.createLinea(linea,idColor ));


        p1.setFavorito(0);
        p1.setNombre("Nombre_"+p1.getIdentifier());
        p1.setId((int) db.createParada(p1, linea.getId()));



        ParadasActivity la = mock(ParadasActivity.class);
        ListParadasPresenter p =new ListParadasPresenter(InstrumentationRegistry.getTargetContext(),la);
        p.setIdLinea(linea.getId());

        assertTrue(p.obtenParadas());
    }

    @Test
    public void obtenParadasError(){
        db.reiniciarTablas();
        db.closeDB();
        ParadasActivity la = mock(ParadasActivity.class);
        ListParadasPresenter p =new ListParadasPresenter(InstrumentationRegistry.getTargetContext(),la);

        assertFalse(p.obtenParadas());
    }
}


