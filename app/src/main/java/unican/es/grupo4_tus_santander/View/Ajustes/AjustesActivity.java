package unican.es.grupo4_tus_santander.View.Ajustes;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import unican.es.grupo4_tus_santander.R;
import unican.es.grupo4_tus_santander.View.Interfaz.ActivityInterface;

public class AjustesActivity extends AppCompatActivity implements ActivityInterface {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajustes);

    }

    @Override
    public void showProgress(boolean b, int i) {

    }

    @Override
    public void showList() {

    }
}