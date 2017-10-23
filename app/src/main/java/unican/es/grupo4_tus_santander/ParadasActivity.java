package unican.es.grupo4_tus_santander;

import android.app.Activity;
import android.os.Bundle;

import java.util.ArrayList;

/**
 * Created by guill on 23/10/2017.
 */

public class ParadasActivity extends Activity {
    ArrayList<Parada> paradas = new ArrayList<Parada>();
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paradas);
    }
}
