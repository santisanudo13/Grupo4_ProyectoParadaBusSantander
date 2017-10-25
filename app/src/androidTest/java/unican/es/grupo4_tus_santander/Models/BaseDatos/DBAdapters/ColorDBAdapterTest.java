package unican.es.grupo4_tus_santander.Models.BaseDatos.DBAdapters;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import unican.es.grupo4_tus_santander.Models.BaseDatos.DBPojos.Color;

import static android.support.test.InstrumentationRegistry.getContext;
import static org.junit.Assert.*;

/**
 * Created by Luis on 24/10/17.
 */
public class ColorDBAdapterTest {
    @Test
    public void addColor() throws Exception {

        ColorDBAdapter cd= new ColorDBAdapter(getContext());
        cd.open();

        List<Color> a= cd.getAllColors();
        cd.close();

        assertEquals(0,a.size(),0);


    }

}