package unican.es.grupo4_tus_santander.Models.BaseDatos.DBAdapters;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import unican.es.grupo4_tus_santander.Models.BaseDatos.DBModel.Color;
import unican.es.grupo4_tus_santander.Models.BaseDatos.helper.DatabaseHelper;

import static android.support.test.InstrumentationRegistry.getContext;
import static org.junit.Assert.*;

/**
 * Created by Luis on 24/10/17.
 */
public class ColorDBAdapterTest {
    @Test
    public void addColor() throws Exception {

        DatabaseHelper cd= new DatabaseHelper(getContext(),1);

        List<Color> a= cd.getAllColor();
        cd.close();

        assertEquals(0,a.size(),0);


    }

}