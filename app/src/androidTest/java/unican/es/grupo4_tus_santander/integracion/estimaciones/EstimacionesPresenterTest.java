package unican.es.grupo4_tus_santander.integracion.estimaciones;

import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import unican.es.grupo4_tus_santander.presenter.estimaciones.EstimacionesPresenter;

import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNull;
import static junit.framework.Assert.assertNotNull;

/**
 * Created by ASIER on 25/11/2017.
 */

@RunWith(AndroidJUnit4.class)
public class EstimacionesPresenterTest {
    @Test
    public void i1() {
        EstimacionesPresenter ep = new EstimacionesPresenter(InstrumentationRegistry.getTargetContext(),null, null, null);
        assertTrue(ep.obtenData());
        assertNotNull(ep.getEstimaciones());
    }

    @Test
    public void i2() {
        EstimacionesPresenter ep = new EstimacionesPresenter(InstrumentationRegistry.getTargetContext(),null, null, null);
        ep.setActiveNetworkInfo(null);
        assertFalse(ep.obtenData());
        assertNull(ep.getEstimaciones());
    }
}