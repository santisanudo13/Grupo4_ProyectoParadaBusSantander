package unican.es.grupo4_tus_santander.PruebasUnitarias.Lineas;

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

import static org.junit.Assert.*;

/**
 * Created by Luis on 30/10/17.
 */
@RunWith(AndroidJUnit4.class)
public class DatabaseHelperLineasTest {

    public static DatabaseHelper cd=new DatabaseHelper(InstrumentationRegistry.getTargetContext(),1);

    public static Color c1,c2,c3,c4;

    public static Linea l1,l2,l3,l4;

    public static List<Long> c_ids= new ArrayList<>();;

    public static List<Long> l_ids= new ArrayList<>();;

    @BeforeClass
    public static void setUpBeforeClass(){
        cd.reiniciarTablas();
        cd.closeDB();
        c1 = new Color(23,245,12,24);

        c2 = new Color(249,23,123,98);

        c3 = new Color(-1,-1,256,-1);

        c4 = new Color(2,123,123,123);

        l1= new Linea("1","uno",1);
        l2= new Linea("2","dos",2);
        l3= new Linea("3","tres",234);
        l4= new Linea("4","cuatro",999);
    }

    @Test
    public void createColor() throws Exception {
        c_ids.add(cd.createColor(c1));
        c_ids.add(cd.createColor(c2));
        c_ids.add(cd.createColor(c3));
        c_ids.add(cd.createColor(c4));

        assertNotEquals(-1.0,c_ids.get(0));
        assertNotEquals(-1.0,c_ids.get(1));
        assertEquals(-1,c_ids.get(2).intValue());
        assertNotEquals(-1.0,c_ids.get(3));
        c_ids.remove(2);

    }

    @Test
    public void getColor() throws Exception {
        assertEquals(c1.getAlpha(),cd.getColor(c_ids.get(0)).getAlpha());
        assertEquals(c1.getRed(),cd.getColor(c_ids.get(0)).getRed());
        assertEquals(c1.getGreen(),cd.getColor(c_ids.get(0)).getGreen());
        assertEquals(c1.getBlue(),cd.getColor(c_ids.get(0)).getBlue());


        assertEquals(c2.getAlpha(),cd.getColor(c_ids.get(1)).getAlpha());
        assertEquals(c2.getRed(),cd.getColor(c_ids.get(1)).getRed());
        assertEquals(c2.getGreen(),cd.getColor(c_ids.get(1)).getGreen());
        assertEquals(c2.getBlue(),cd.getColor(c_ids.get(1)).getBlue());


        assertEquals(c4.getAlpha(),cd.getColor(c_ids.get(2)).getAlpha());
        assertEquals(c4.getRed(),cd.getColor(c_ids.get(2)).getRed());
        assertEquals(c4.getGreen(),cd.getColor(c_ids.get(2)).getGreen());
        assertEquals(c4.getBlue(),cd.getColor(c_ids.get(2)).getBlue());
    }

    @Test
    public void getAllColor() throws Exception {

        List<Color> t= cd.getAllColor();

        for (int i =0;i<t.size();i++) {
            assertEquals(t.get(i).getAlpha(),cd.getColor(c_ids.get(i)).getAlpha());
            assertEquals(t.get(i).getRed(),cd.getColor(c_ids.get(i)).getRed());
            assertEquals(t.get(i).getGreen(),cd.getColor(c_ids.get(i)).getGreen());
            assertEquals(t.get(i).getBlue(),cd.getColor(c_ids.get(i)).getBlue());

        }
    }

    @Test
    public void createLinea() throws Exception {
        l1.setIdColor(c_ids.get(0).intValue());
        l2.setIdColor(c_ids.get(1).intValue());
        l3.setIdColor(c_ids.get(0).intValue());
        l4.setIdColor(c_ids.get(2).intValue());

        l_ids.add(cd.createLinea(l1,c_ids.get(0)));
        l_ids.add(cd.createLinea(l2,c_ids.get(1)));
        l_ids.add(cd.createLinea(l3,c_ids.get(0)));
        l_ids.add(cd.createLinea(l4,c_ids.get(2)));

        for (long c: l_ids) {
            assertNotNull(c);
        }

    }

    @Test
    public void getLinea() throws Exception{
        assertEquals(l1.getIdentifier(),cd.getLinea(l_ids.get(0)).getIdentifier());
        assertEquals(l1.getName(),cd.getLinea(l_ids.get(0)).getName());
        assertEquals(l1.getNumero(),cd.getLinea(l_ids.get(0)).getNumero());
        assertEquals(l1.getIdColor(),cd.getLinea(l_ids.get(0)).getIdColor());

        assertEquals(l2.getIdentifier(),cd.getLinea(l_ids.get(1)).getIdentifier());
        assertEquals(l2.getName(),cd.getLinea(l_ids.get(1)).getName());
        assertEquals(l2.getNumero(),cd.getLinea(l_ids.get(1)).getNumero());
        assertEquals(l2.getIdColor(),cd.getLinea(l_ids.get(1)).getIdColor());

        assertEquals(l3.getIdentifier(),cd.getLinea(l_ids.get(2)).getIdentifier());
        assertEquals(l3.getName(),cd.getLinea(l_ids.get(2)).getName());
        assertEquals(l3.getNumero(),cd.getLinea(l_ids.get(2)).getNumero());
        assertEquals(l3.getIdColor(),cd.getLinea(l_ids.get(2)).getIdColor());

        assertEquals(l4.getIdentifier(),cd.getLinea(l_ids.get(3)).getIdentifier());
        assertEquals(l4.getName(),cd.getLinea(l_ids.get(3)).getName());
        assertEquals(l4.getNumero(),cd.getLinea(l_ids.get(3)).getNumero());
        assertEquals(l4.getIdColor(),cd.getLinea(l_ids.get(3)).getIdColor());
    }

    @Test
    public void getAllLinea() throws Exception {
        cd=new DatabaseHelper(InstrumentationRegistry.getTargetContext(),1);

        cd.reiniciarTablas();

        cd=new DatabaseHelper(InstrumentationRegistry.getTargetContext(),1);

        List<Long> c_id= new ArrayList<>();;

        c_id.add(cd.createColor(c1));
        c_id.add(cd.createColor(c2));
        c_id.add(cd.createColor(c4));


        l1.setIdColor(c_id.get(0).intValue());
        l2.setIdColor(c_id.get(1).intValue());
        l3.setIdColor(c_id.get(0).intValue());
        l4.setIdColor(c_id.get(2).intValue());

        l_ids.clear();

        l_ids.add(cd.createLinea(l1,c_id.get(0)));
        l_ids.add(cd.createLinea(l2,c_id.get(1)));
        l_ids.add(cd.createLinea(l3,c_id.get(0)));
        l_ids.add(cd.createLinea(l4,c_id.get(2)));

        List<Linea> t= cd.getAllLinea();

        for (int i =0;i<t.size();i++) {
            assertEquals(t.get(i).getIdentifier(),cd.getLinea(l_ids.get(i)).getIdentifier());
            assertEquals(t.get(i).getName(),cd.getLinea(l_ids.get(i)).getName());
            assertEquals(t.get(i).getNumero(),cd.getLinea(l_ids.get(i)).getNumero());
            assertEquals(t.get(i).getIdColor(),cd.getLinea(l_ids.get(i)).getIdColor());

        }
    }

    @Test
    public void getColorByLinea() throws Exception {
        assertEquals(c1.getAlpha(),cd.getColorByLinea(l_ids.get(0)).getAlpha());
        assertEquals(c1.getRed(),cd.getColorByLinea(l_ids.get(0)).getRed());
        assertEquals(c1.getGreen(),cd.getColorByLinea(l_ids.get(0)).getGreen());
        assertEquals(c1.getBlue(),cd.getColorByLinea(l_ids.get(0)).getBlue());


        assertEquals(c2.getAlpha(),cd.getColorByLinea(l_ids.get(1)).getAlpha());
        assertEquals(c2.getRed(),cd.getColorByLinea(l_ids.get(1)).getRed());
        assertEquals(c2.getGreen(),cd.getColorByLinea(l_ids.get(1)).getGreen());
        assertEquals(c2.getBlue(),cd.getColorByLinea(l_ids.get(1)).getBlue());


        assertEquals(c1.getAlpha(),cd.getColorByLinea(l_ids.get(2)).getAlpha());
        assertEquals(c1.getRed(),cd.getColorByLinea(l_ids.get(2)).getRed());
        assertEquals(c1.getGreen(),cd.getColorByLinea(l_ids.get(2)).getGreen());
        assertEquals(c1.getBlue(),cd.getColorByLinea(l_ids.get(2)).getBlue());


        assertEquals(c4.getAlpha(),cd.getColorByLinea(l_ids.get(3)).getAlpha());
        assertEquals(c4.getRed(),cd.getColorByLinea(l_ids.get(3)).getRed());
        assertEquals(c4.getGreen(),cd.getColorByLinea(l_ids.get(3)).getGreen());
        assertEquals(c4.getBlue(),cd.getColorByLinea(l_ids.get(3)).getBlue());


    }

}