package unican.es.grupo4_tus_santander.Views;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.sql.Date;
import java.util.ArrayList;

import unican.es.grupo4_tus_santander.Model.Linea;
import unican.es.grupo4_tus_santander.Model.Parada;
import unican.es.grupo4_tus_santander.R;

/**
 * Created by guill on 23/10/2017.
 */

public class ParadasActivity extends Activity {
    Linea linea;
    ArrayList<Parada> paradas;
    ListView lv;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paradas);

        paradas = linea.getParadas();
        lv = findViewById(R.id.listView_paradas);

        // Listar paradas en el layout (Pruebas)
        Parada p = new Parada("A1", 1, "Inverso", 50, 50, 50, "Avda España", 50, 1, Date.valueOf("24/10/2017"));
        paradas.add(p);
        for(int i=0; i<paradas.size(); i++){
            TextView textView = new TextView(this);
            textView.setText(paradas.get(i).getParada());
            lv.addView(textView);
        }
    }
}
