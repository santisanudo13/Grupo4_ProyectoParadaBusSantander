package unican.es.grupo4_tus_santander.view.paradas;


import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import unican.es.grupo4_tus_santander.models.pojos.Parada;
import unican.es.grupo4_tus_santander.presenter.paradas.ListParadasPresenter;

import unican.es.grupo4_tus_santander.R;

/**
 * Created by Asier on 25/10/17.
 */

public class ParadasActivity extends AppCompatActivity  implements SearchView.OnQueryTextListener {
    private ListParadasPresenter listParadasPresenter;
    private ProgressBar progressBar;
    private Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.context = getApplicationContext();
        setContentView(R.layout.activity_paradas);
        this.progressBar=(ProgressBar)findViewById(R.id.progressParada);
        Bundle extras = getIntent().getExtras();
        int lineaId = -1;
        if(extras != null){
            lineaId = (int) extras.get("lineaID");
        }

        this.listParadasPresenter = new ListParadasPresenter(getApplicationContext(),this);
        this.listParadasPresenter.setIdLinea(lineaId);

        listParadasPresenter.start();


        //this.listParadasPresenter.start();
    }//onCreate

    public void start(){
        listParadasPresenter.start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_busqueda_paradas, menu);
        MenuItem searchItem = menu.findItem(R.id.search);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == R.id.toolbar_actualizar)
        {
            listParadasPresenter.recargarDatos();
            return(true);
        }
        return(super.onOptionsItemSelected(item));
    }

    public void showProgress (boolean state, int tipo){
        if(state)
        {
            Toast.makeText(getApplicationContext(), "Cargando datos", Toast.LENGTH_SHORT).show();
            progressBar.setVisibility(View.VISIBLE);
        }else{
            if(tipo == 1)
                Toast.makeText(getApplicationContext(), "Carga de datos exitosa", Toast.LENGTH_SHORT).show();
            if(tipo == -1)
                Toast.makeText(getApplicationContext(), "Carga de datos fallida", Toast.LENGTH_SHORT).show();
            if(tipo == -2)
                Toast.makeText(getApplicationContext(), "Actualiza los datos, por favor", Toast.LENGTH_SHORT).show();

            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    public void showList(List<Parada> paradasList) {
        ListParadasAdapter listParadasAdapter = new  ListParadasAdapter(this,paradasList);
        ListView listview = (ListView) findViewById(R.id.listParadas);
        listview.setAdapter(listParadasAdapter);
    }

    public ListParadasPresenter getListParadasPresenter() {
        return listParadasPresenter;
    }

    public void setListParadasPresenter(ListParadasPresenter listParadasPresenter) {
        this.listParadasPresenter = listParadasPresenter;
    }

    public void showToasEmptyParadas() {
        Toast.makeText(getApplicationContext(), "No hay paradas en la línea actualmente", Toast.LENGTH_SHORT).show();
    }
}// ParadasActivity