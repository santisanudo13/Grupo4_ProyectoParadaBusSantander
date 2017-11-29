package unican.es.grupo4_tus_santander.view.estimaciones;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import unican.es.grupo4_tus_santander.R;
import unican.es.grupo4_tus_santander.models.pojos.Estimacion;
import unican.es.grupo4_tus_santander.presenter.estimaciones.EstimacionesPresenter;

public class EstimacionesActivity extends AppCompatActivity {

    private EstimacionesPresenter estimacionesPresenter;
    private ProgressBar progressBarEstimaciones;
    private TextView recarga;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estimaciones);

        String lineaId="";
        String paradaId="";
        Bundle extras = getIntent().getExtras();
        if(extras!=null) {
            lineaId = extras.getString("lineaId");

            paradaId = extras.getString("parada");


            Log.d("liena", lineaId + "");
            Log.d("parada", paradaId + "");
        }

        this.recarga= (TextView) findViewById(R.id.txtRecargaEstimaciones);
        this.progressBarEstimaciones = (ProgressBar) findViewById(R.id.progressBarEstimaciones);
        this.estimacionesPresenter = new EstimacionesPresenter( getApplicationContext(),lineaId,paradaId,this);
        this.estimacionesPresenter.start();



    }//onCreate

    /**
     * Metodo encargado de generar el toolbar
     *
     * @param menu
     * @return si la creacion fue correcta
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /**
     * Metodo encargado de determinar las acciones a realizar por los botones del toolbar
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.toolbar_actualizar) {
            estimacionesPresenter.start();
            return (true);
        }
        return (super.onOptionsItemSelected(item));
    }


    public void showProgress(boolean stateEstimaciones, int tipo) {
        if (stateEstimaciones) {
            Toast.makeText(getApplicationContext(), "Cargando datos", Toast.LENGTH_SHORT).show();
            progressBarEstimaciones.setVisibility(View.VISIBLE);
        } else {
            if (tipo == 1)
                Toast.makeText(getApplicationContext(), "Carga de datos exitosa", Toast.LENGTH_SHORT).show();
            if (tipo == -1)
                Toast.makeText(getApplicationContext(), "Carga de datos fallida", Toast.LENGTH_SHORT).show();

            progressBarEstimaciones.setVisibility(View.GONE);
        }
    }

    public void showList(final List<Estimacion> estimaciones) {

        if (estimaciones.isEmpty()) {
            recarga.setVisibility(View.VISIBLE);
            ListEstimacionesAdapter estimacionesAdapter = new ListEstimacionesAdapter(getApplicationContext(), estimaciones);
            final ListView listview = (ListView) findViewById(R.id.listEstimacionesTotal);
            listview.setAdapter(estimacionesAdapter);
        } else {
            recarga.setVisibility(View.INVISIBLE);

            ListEstimacionesAdapter estimacionesAdapter = new ListEstimacionesAdapter(getApplicationContext(), estimaciones);
            final ListView listview = (ListView) findViewById(R.id.listEstimacionesTotal);
            listview.setAdapter(estimacionesAdapter);



        }
    }

    public EstimacionesPresenter getEstimacionesPresenter() {
        return estimacionesPresenter;
    }

    public void setEstimacionesPresenter(EstimacionesPresenter estimacionesPresenter) {
        this.estimacionesPresenter = estimacionesPresenter;
    }

    public ProgressBar getProgressBarEstimaciones() {
        return progressBarEstimaciones;
    }

    public void setProgressBarEstimaciones(ProgressBar progressBarEstimaciones) {
        this.progressBarEstimaciones = progressBarEstimaciones;
    }
}