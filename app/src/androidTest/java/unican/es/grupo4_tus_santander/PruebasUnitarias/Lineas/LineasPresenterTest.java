package unican.es.grupo4_tus_santander.PruebasUnitarias.Lineas;

import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import unican.es.grupo4_tus_santander.Models.BaseDatos.helper.DatabaseHelper;
import unican.es.grupo4_tus_santander.Models.BaseDatos.helper.DatabaseInterface;
import unican.es.grupo4_tus_santander.Models.Pojos.Color;
import unican.es.grupo4_tus_santander.Models.Pojos.Linea;
import unican.es.grupo4_tus_santander.Presenter.Lineas.ListLineasPresenter;
import unican.es.grupo4_tus_santander.View.Lineas.LineasActivity;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Luis on 30/10/17.
 */

@RunWith(AndroidJUnit4.class)
public class LineasPresenterTest {
    static DatabaseHelper db;
    static List<Linea> l;

    @BeforeClass
    public static void setUpBeforeClass(){
        db= mock(DatabaseHelper.class);
        l= new ArrayList<>();
        Linea l1= new Linea("1","uno",1);
        Linea l2= new Linea("2","dos",2);
        Linea l3= new Linea("3","tres",234);
        Linea l4= new Linea("4","cuatro",999);
        l.add(l1);
        l.add(l2);
        l.add(l3);
        l.add(l4);
    }

    @Test
    public void obtenLineasCorrecto(){

        when(db.getAllLinea()).thenReturn(l);
        LineasActivity la = mock(LineasActivity.class);
        ListLineasPresenter p =new ListLineasPresenter(InstrumentationRegistry.getTargetContext(),la);
        p.ld=db;
        assertTrue(p.obtenLineas());
    }

    @Test
    public void obtenLineasError(){
        LineasActivity la = mock(LineasActivity.class);
        ListLineasPresenter p =new ListLineasPresenter(InstrumentationRegistry.getTargetContext(),la);
        assertFalse(p.obtenLineas());

    }

}


