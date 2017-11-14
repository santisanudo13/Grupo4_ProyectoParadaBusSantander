package unican.es.grupo4_tus_santander.view.main;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;


import unican.es.grupo4_tus_santander.presenter.main.MainPresenter;
import unican.es.grupo4_tus_santander.R;
import unican.es.grupo4_tus_santander.view.ajustes.AjustesActivity;
import unican.es.grupo4_tus_santander.view.favoritos.FavsActivity;
import unican.es.grupo4_tus_santander.view.lineas.LineasActivity;
import unican.es.grupo4_tus_santander.view.paradas.ParadasActivity;
import unican.es.grupo4_tus_santander.view.restricciones.RestriccionesActivity;
import unican.es.grupo4_tus_santander.view.serviciosalternativos.ServiciosAlternativosActivity;
import unican.es.grupo4_tus_santander.view.tarifas.TarifasActivity;

public class MainActivity extends AppCompatActivity {

    private Context context;
    private MainPresenter mainPresenter;
    private ProgressBar progressBarMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.context = getApplicationContext();
        this.progressBarMain = (ProgressBar) findViewById(R.id.progressBarMenu);
        this.mainPresenter = new MainPresenter(context, this);
        mainPresenter.start();


    }//onCreate



    /**
     * Metodo encargado de generar el toolbar
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /**
     * Metodo encargado de determinar las acciones a realizar por los botones del toolbar
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        if(item.getItemId() == R.id.toolbar_actualizar)
        {
            mainPresenter.recargarDatos();
            return(true);
        }
        return(super.onOptionsItemSelected(item));
    }

    public void showList() {
        ListFuncionesMainAdapter listFuncionesMainAdapter = new ListFuncionesMainAdapter(context);
        ListView listview = (ListView) findViewById(R.id.listFuncionesMenu);
        listview.setAdapter(listFuncionesMainAdapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                Intent intent;
                switch (position){
                    case 0:
                        intent = new Intent(MainActivity.this, FavsActivity.class);
                        startActivity(intent);
                        break;
                    case 1:
                        intent = new Intent(MainActivity.this, LineasActivity.class);
                        startActivity(intent);
                        break;
                    case 2:
                        intent = new Intent(MainActivity.this, ParadasActivity.class);
                        startActivity(intent);
                        break;
                    case 3:
                        intent = new Intent(MainActivity.this, TarifasActivity.class);
                        startActivity(intent);
                        break;
                    case 4:
                        intent = new Intent(MainActivity.this, RestriccionesActivity.class);
                        startActivity(intent);
                        break;
                    case 5:
                        intent = new Intent(MainActivity.this, ServiciosAlternativosActivity.class);
                        startActivity(intent);
                        break;
                    case 6:
                        intent = new Intent(MainActivity.this, AjustesActivity.class);
                        startActivity(intent);
                        break;
                }
            }
        });


    }

    public void showProgress (boolean stateMain, int tipoMain){
        if(stateMain)
        {
            Toast.makeText(getApplicationContext(), "Cargando datos", Toast.LENGTH_SHORT).show();
            progressBarMain.setVisibility(View.VISIBLE);
        }else{
            if(tipoMain == 1)
                Toast.makeText(getApplicationContext(), "Carga de datos exitosa", Toast.LENGTH_SHORT).show();
            if(tipoMain == -1)
                Toast.makeText(getApplicationContext(), "Carga de datos fallida", Toast.LENGTH_SHORT).show();

            progressBarMain.setVisibility(View.GONE);
        }
    }

    public ProgressBar getProgressBar() {
        return progressBarMain;
    }

    public void setProgressBar(ProgressBar progressBar) {
        this.progressBarMain = progressBar;
    }

    public MainPresenter getMainPresenter() {
        return mainPresenter;
    }

    public void setMainPresenter(MainPresenter mainPresenter) {
        this.mainPresenter = mainPresenter;
    }
}// MainActivity
