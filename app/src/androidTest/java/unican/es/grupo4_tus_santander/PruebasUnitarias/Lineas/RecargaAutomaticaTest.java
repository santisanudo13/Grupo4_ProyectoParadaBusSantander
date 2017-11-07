package unican.es.grupo4_tus_santander.PruebasUnitarias.Lineas;

import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import unican.es.grupo4_tus_santander.Models.Pojos.Linea;
import unican.es.grupo4_tus_santander.Models.Pojos.Parada;
import unican.es.grupo4_tus_santander.Models.Pojos.ParadaConNombre;
import unican.es.grupo4_tus_santander.Models.WebService.DataLoaders.ParserJSON;
import unican.es.grupo4_tus_santander.Models.WebService.DataLoaders.RemoteFetch;
import unican.es.grupo4_tus_santander.R;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Luis on 30/10/17.
 */

@RunWith(AndroidJUnit4.class)
public class RecargaAutomaticaTest {
    
    public static RemoteFetch remoteFetch = mock(RemoteFetch.class);
    
    @BeforeClass
    public void setUp() {
    }

    @Test
    public void testCargaAutomatica() throws Exception {
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

    @Test
    public void testParserParadasBus() throws Exception {
        //Obtenemos el InputStream para el json almacenado en la carpeta raw del proyecto
        InputStream is = InstrumentationRegistry.getTargetContext().getResources().openRawResource(R.raw.lineas_bus_paradas);
        List<Parada> listParadasBus= ParserJSON.readArrayParadas(is);

        assertEquals(75,listParadasBus.get(0).getIdentifier());
        assertEquals(19,listParadasBus.get(0).getIdentifierLinea());
        assertEquals(429713.84,listParadasBus.get(0).getCoordX());
        assertEquals(4811697.5,listParadasBus.get(0).getCoordY());
        assertEquals(43.45300506221107,listParadasBus.get(0).getWgs64Lat());
        assertEquals(-3.8700401309569807,listParadasBus.get(0).getWgs64Long());
        assertEquals(489,listParadasBus.get(0).getNumParada());

        assertEquals(34,listParadasBus.get(1).getIdentifier());
        assertEquals(19,listParadasBus.get(1).getIdentifierLinea());
        assertEquals(436339.12,listParadasBus.get(1).getCoordX());
        assertEquals(4812815.5,listParadasBus.get(1).getCoordY());
        assertEquals(43.46366420538056,listParadasBus.get(1).getWgs64Lat());
        assertEquals(-3.788291427047982,listParadasBus.get(1).getWgs64Long());
        assertEquals(36,listParadasBus.get(1).getNumParada());

        assertEquals(35,listParadasBus.get(2).getIdentifier());
        assertEquals(20,listParadasBus.get(2).getIdentifierLinea());
        assertEquals(435952.47,listParadasBus.get(2).getCoordX());
        assertEquals(4812737.5,listParadasBus.get(2).getCoordY());
        assertEquals(43.46292891213499,listParadasBus.get(2).getWgs64Lat());
        assertEquals(-3.7930615771148126,listParadasBus.get(2).getWgs64Long());
        assertEquals(37,listParadasBus.get(2).getNumParada());

    }

    @Test
    public void testParserParadasNombre() throws Exception {
        //Obtenemos el InputStream para el json almacenado en la carpeta raw del proyecto
        InputStream is = InstrumentationRegistry.getTargetContext().getResources().openRawResource(R.raw.paradas_bus);
        List<ParadaConNombre> listParadasBus= ParserJSON.readArrayParadasConNombre(is);

        assertEquals(499,listParadasBus.get(0).getNumero());
        assertEquals( "Camarreal Peñacastillo",listParadasBus.get(0).getParada());
        assertEquals(42063,listParadasBus.get(0).getIdentifier());

        assertEquals(500,listParadasBus.get(1).getNumero());
        assertEquals("Ortega y Gasset.28",listParadasBus.get(1).getParada());
        assertEquals(42064,listParadasBus.get(1).getIdentifier());

        assertEquals(505,listParadasBus.get(2).getNumero());
        assertEquals("Avenida de Cantabria nº 35",listParadasBus.get(2).getParada());
        assertEquals(50693,listParadasBus.get(2).getIdentifier());
    }
}
