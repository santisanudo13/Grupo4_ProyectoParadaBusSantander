package unican.es.grupo4_tus_santander.Unitarias;

import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.InputStream;
import java.util.List;

import unican.es.grupo4_tus_santander.Models.Pojos.Linea;
import unican.es.grupo4_tus_santander.Models.WebService.DataLoaders.ParserJSON;
import unican.es.grupo4_tus_santander.R;

import static junit.framework.Assert.assertEquals;

/**
 * Created by Luis on 30/10/17.
 */

@RunWith(AndroidJUnit4.class)
public class ParseJsonLineasTest {

    @Test
    public void testParserLineas() throws Exception {
        //Obtenemos el InputStream para el json almacenado en la carpeta raw del proyecto
        InputStream is = InstrumentationRegistry.getTargetContext().getResources().openRawResource(R.raw.lineas_test);
        List<Linea> listLineasBus= ParserJSON.readArrayLineasBus(is);

        assertEquals(20,listLineasBus.get(0).getIdentifier());
        assertEquals("20",listLineasBus.get(0).getNumero());
        assertEquals("ESTACIONES-BARRIO LA TORRE",listLineasBus.get(0).getName());
        assertEquals(19,listLineasBus.get(1).getIdentifier());
        assertEquals("19",listLineasBus.get(1).getNumero());
        assertEquals("ESTACIONES-RICARDO L. ARANDA",listLineasBus.get(1).getName());
    }// testParserLin
}
