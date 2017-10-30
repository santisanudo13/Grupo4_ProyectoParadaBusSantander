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
 * Created by Asier on 30/10/17.
 */
@RunWith(AndroidJUnit4.class)
public class DatabaseHelperParadasTest {

    public static DatabaseHelper cd=new DatabaseHelper(InstrumentationRegistry.getTargetContext(),1);

    public static Parada p1, p2, p3, p4;

    public static List<Long> p_ids = new ArrayList<>();;

    @BeforeClass
    public static void setUpBeforeClass(){
        cd.reiniciarTablas();
        cd.closeDB();

        p1 = new Parada();
        p2 = new Parada();
        p3 = new Parada();
        p4 = new Parada();
    }

    @Test
    public void createParada() throws Exception {
        p1.setIdColor(c_ids.get(0).intValue());
        p2.setIdColor(c_ids.get(1).intValue());
        p3.setIdColor(c_ids.get(0).intValue());
        p4.setIdColor(c_ids.get(2).intValue());

        p_ids.add(cd.createLinea(p1,c_ids.get(0)));
        p_ids.add(cd.createLinea(p2,c_ids.get(1)));
        p_ids.add(cd.createLinea(p3,c_ids.get(0)));
        p_ids.add(cd.createLinea(p4,c_ids.get(2)));

        for (long c: p_ids) {
            assertNotNull(c);
        }
    }

    @Test
    public void getLinea() throws Exception{
        assertEquals(p1.getIdentifier(),cd.getLinea(p_ids.get(0)).getIdentifier());
        assertEquals(p1.getName(),cd.getLinea(p_ids.get(0)).getName());
        assertEquals(p1.getNumero(),cd.getLinea(p_ids.get(0)).getNumero());
        assertEquals(p1.getIdColor(),cd.getLinea(p_ids.get(0)).getIdColor());

        assertEquals(p2.getIdentifier(),cd.getLinea(p_ids.get(1)).getIdentifier());
        assertEquals(p2.getName(),cd.getLinea(p_ids.get(1)).getName());
        assertEquals(p2.getNumero(),cd.getLinea(p_ids.get(1)).getNumero());
        assertEquals(p2.getIdColor(),cd.getLinea(p_ids.get(1)).getIdColor());

        assertEquals(p3.getIdentifier(),cd.getLinea(p_ids.get(2)).getIdentifier());
        assertEquals(p3.getName(),cd.getLinea(p_ids.get(2)).getName());
        assertEquals(p3.getNumero(),cd.getLinea(p_ids.get(2)).getNumero());
        assertEquals(p3.getIdColor(),cd.getLinea(p_ids.get(2)).getIdColor());

        assertEquals(p4.getIdentifier(),cd.getLinea(p_ids.get(3)).getIdentifier());
        assertEquals(p4.getName(),cd.getLinea(p_ids.get(3)).getName());
        assertEquals(p4.getNumero(),cd.getLinea(p_ids.get(3)).getNumero());
        assertEquals(p4.getIdColor(),cd.getLinea(p_ids.get(3)).getIdColor());
    }

    @Test
    public void getAllLinea() throws Exception {
        List<Linea> t= cd.getAllLinea();

        for (int i =0;i<t.size();i++) {
            assertEquals(t.get(i).getIdentifier(),cd.getLinea(p_ids.get(i)).getIdentifier());
            assertEquals(t.get(i).getName(),cd.getLinea(p_ids.get(i)).getName());
            assertEquals(t.get(i).getNumero(),cd.getLinea(p_ids.get(i)).getNumero());
            assertEquals(t.get(i).getIdColor(),cd.getLinea(p_ids.get(i)).getIdColor());

        }
    }
}