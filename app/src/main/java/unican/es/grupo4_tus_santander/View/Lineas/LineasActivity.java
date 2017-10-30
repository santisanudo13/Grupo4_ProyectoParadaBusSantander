package unican.es.grupo4_tus_santander.View.Lineas;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import unican.es.grupo4_tus_santander.Models.Pojos.Linea;
import unican.es.grupo4_tus_santander.Presenter.Lineas.RecargaBaseDatosLineas;
import unican.es.grupo4_tus_santander.Presenter.Lineas.ListLineasPresenter;
import unican.es.grupo4_tus_santander.R;
import unican.es.grupo4_tus_santander.View.Paradas.ParadasActivity;


public class LineasActivity extends AppCompatActivity  implements SearchView.OnQueryTextListener{

    //private ListView listViewLineas;
    private int lineaIdentifier;
    private int paradaIdentifier;
    private ListLineasPresenter listLineasPresenter;
    private ProgressBar progressBar;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.context = getApplicationContext();
        setContentView(R.layout.activity_lineas);
        this.progressBar=(ProgressBar)findViewById(R.id.progressLinea);
        this.listLineasPresenter = new ListLineasPresenter(getApplicationContext(),this);
        this.listLineasPresenter.start();
    }//onCreate

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_busqueda, menu);
        MenuItem searchItem = menu.findItem(R.id.search);
        //SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        //searchView.setOnQueryTextListener(this);

        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }


    public void showLista(final List<Linea> lineaList) {
        ListLineasAdapter listLineasAdapter = new ListLineasAdapter(getApplicationContext(), lineaList);
        final ListView listview = (ListView) findViewById(R.id.listLineas);
        listview.setAdapter(listLineasAdapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                Intent intent = new Intent(LineasActivity.this, ParadasActivity.class);
                intent.putExtra("lineaID",lineaList.get(position).getId());
                startActivity(intent);
            }
        });


    }
    public void start(){
        listLineasPresenter.start();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == R.id.refresh)
        {
            new RecargaBaseDatosLineas(getApplicationContext(), this);
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

}// MainActivity
