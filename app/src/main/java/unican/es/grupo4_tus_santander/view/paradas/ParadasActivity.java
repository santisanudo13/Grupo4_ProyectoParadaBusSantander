package unican.es.grupo4_tus_santander.view.paradas;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.AdapterView;
import android.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import unican.es.grupo4_tus_santander.models.pojos.Parada;
import unican.es.grupo4_tus_santander.presenter.paradas.ListParadasPresenter;

import unican.es.grupo4_tus_santander.R;
import unican.es.grupo4_tus_santander.view.estimaciones.EstimacionesActivity;

/**
 * Created by Asier on 25/10/17.
 */

public class ParadasActivity extends AppCompatActivity  implements SearchView.OnQueryTextListener {
    private ListParadasPresenter listParadasPresenter;
    private ProgressBar progressBar;
    private TextView vacio;
    private Context context;
    private int lineaId = -1;
    private String lineaNum="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.context = getApplicationContext();

        setContentView(R.layout.activity_paradas);
        this.progressBar=(ProgressBar)findViewById(R.id.progressParada);
        Bundle extras = getIntent().getExtras();

        if(extras != null){
            lineaId = (int) extras.get("lineaID");
            lineaNum = extras.getString("lineaNum");
        }

        this.listParadasPresenter = new ListParadasPresenter(getApplicationContext(),this);
        this.listParadasPresenter.setIdLinea(lineaId);

        vacio = (TextView)findViewById(R.id.txtVacio);
        this.listParadasPresenter.start();
    }//onCreate

    public void start(){
        listParadasPresenter.start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_busqueda_paradas, menu);
        SearchView searchItem = (SearchView) menu.findItem(R.id.search).getActionView();
        searchItem.setOnQueryTextListener(this);

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
        listParadasPresenter.filtra(query);
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        listParadasPresenter.filtra(newText);
        return true;
    }

    public void showList(final List<Parada> paradasList) {
        ListParadasAdapter listParadasAdapter;
        if(!paradasList.isEmpty()) {
            vacio.setVisibility(View.INVISIBLE);
            listParadasAdapter = new ListParadasAdapter(this, paradasList);

        }else{
            List<Parada> v= new ArrayList<>();
            listParadasAdapter = new ListParadasAdapter(this, v);
            vacio.setVisibility(View.VISIBLE);
        }
        ListView listview = (ListView) findViewById(R.id.listParadas);
        listview.setAdapter(listParadasAdapter);


        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                Intent intent = new Intent(context , EstimacionesActivity.class);
                intent.putExtra("lineaId",lineaNum+"");
                intent.putExtra("parada",paradasList.get(position).getNumParada()+"");
                startActivity(intent);
            }
        });
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