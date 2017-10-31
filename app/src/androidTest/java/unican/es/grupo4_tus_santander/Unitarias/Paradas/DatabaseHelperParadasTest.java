package unican.es.grupo4_tus_santander.Unitarias.Paradas;

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

import static org.junit.Assert.*;

/**
 * Created by Asier on 31/10/17.
 */
@RunWith(AndroidJUnit4.class)
public class DatabaseHelperParadasTest {

    public static DatabaseHelper cd=new DatabaseHelper(InstrumentationRegistry.getTargetContext(),1);

    public static Color c1,c2,c3,c4;

    public static Linea l1,l2,l3,l4;

    public static Parada p1,p2,p3,p4;

    public static List<Long> c_ids= new ArrayList<>();

    public static List<Long> l_ids= new ArrayList<>();

    public static List<Long> p_ids= new ArrayList<>();

    @BeforeClass
    public static void setUpBeforeClass(){
        cd.reiniciarTablas();
        cd.closeDB();
        c1 = new Color(23,245,12,24);

        c2 = new Color(249,23,123,98);

        c3 = new Color(-1,-1,256,-1);

        c4 = new Color(2,123,123,123);

        c_ids.add(cd.createColor(c1));
        c_ids.add(cd.createColor(c2));
        c_ids.add(cd.createColor(c3));
        c_ids.add(cd.createColor(c4));

        l1= new Linea("1","uno",1);
        l2= new Linea("2","dos",2);
        l3= new Linea("3","tres",234);
        l4= new Linea("4","cuatro",999);

        l1.setIdColor(c_ids.get(0).intValue());
        l2.setIdColor(c_ids.get(1).intValue());
        l3.setIdColor(c_ids.get(0).intValue());
        l4.setIdColor(c_ids.get(2).intValue());

        l_ids.add(cd.createLinea(l1,c_ids.get(0)));
        l_ids.add(cd.createLinea(l2,c_ids.get(1)));
        l_ids.add(cd.createLinea(l3,c_ids.get(0)));
        l_ids.add(cd.createLinea(l4,c_ids.get(2)));

        p1 = new Parada(1, 10, 1.0, 1.0, 1.0, 1.0, 0);
        p2 = new Parada(2, 20, 1.0, 1.0, 1.0, 1.0, 1);
        p3 = new Parada(234, 30, 1.0, 1.0, 1.0, 1.0, 2);
        p4 = new Parada(999, 40, 1.0, 1.0, 1.0, 1.0, 3);
    }

    @Test
    public void createParada() throws Exception {
        p1.setIdentifierLinea(l_ids.get(0).intValue());
        p2.setIdentifierLinea(l_ids.get(1).intValue());
        p3.setIdentifierLinea(l_ids.get(0).intValue());
        p4.setIdentifierLinea(l_ids.get(2).intValue());

        p_ids.add(cd.createParada(p1,l_ids.get(0)));
        p_ids.add(cd.createParada(p2,l_ids.get(1)));
        p_ids.add(cd.createParada(p3,l_ids.get(0)));
        p_ids.add(cd.createParada(p4,l_ids.get(2)));

        for (long c: p_ids) {
            assertNotNull(c);
        }
    }

    @Test
    public void getParada() throws Exception{
        assertEquals(p1.getIdentifier(),cd.getParada(p_ids.get(0)).getIdentifier());
        assertEquals(p1.getNombre(),cd.getParada(p_ids.get(0)).getNombre());
        assertEquals(p1.getNumParada(),cd.getParada(p_ids.get(0)).getNumParada());

        assertEquals(p2.getIdentifier(),cd.getParada(p_ids.get(1)).getIdentifier());
        assertEquals(p2.getNombre(),cd.getParada(p_ids.get(1)).getNombre());
        assertEquals(p2.getNumParada(),cd.getParada(p_ids.get(1)).getNumParada());

        assertEquals(p3.getIdentifier(),cd.getParada(p_ids.get(2)).getIdentifier());
        assertEquals(p3.getNombre(),cd.getParada(p_ids.get(2)).getNombre());
        assertEquals(p3.getNumParada(),cd.getParada(p_ids.get(2)).getNumParada());

        assertEquals(p4.getIdentifier(),cd.getParada(p_ids.get(3)).getIdentifier());
        assertEquals(p4.getNombre(),cd.getParada(p_ids.get(3)).getNombre());
        assertEquals(p4.getNumParada(),cd.getParada(p_ids.get(3)).getNumParada());
    }

    @Test
    public void getAllParada() throws Exception {
        List<Parada> t= cd.getAllParada();

        for (int i =0;i<t.size();i++) {
            assertEquals(t.get(i).getIdentifier(),cd.getParada(p_ids.get(i)).getIdentifier());
            assertEquals(t.get(i).getNombre(),cd.getParada(p_ids.get(i)).getNombre());
            assertEquals(t.get(i).getNumParada(),cd.getParada(p_ids.get(i)).getNumParada());
        }
    }
}