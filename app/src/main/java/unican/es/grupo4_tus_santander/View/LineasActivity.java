package unican.es.grupo4_tus_santander.View;


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

import unican.es.grupo4_tus_santander.Models.BaseDatos.DBModel.Linea;
import unican.es.grupo4_tus_santander.Presenter.ListLineasPresenter;
import unican.es.grupo4_tus_santander.R;


public class LineasActivity extends AppCompatActivity  implements SearchView.OnQueryTextListener, IListLineasView{

    //private ListView listViewLineas;
    private int lineaIdentifier;
    private int paradaIdentifier;
    private ListLineasPresenter listLineasPresenter;
    private ProgressBar progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lineas);
        this.progress=(ProgressBar)findViewById(R.id.progressLineas);
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

    @Override
    public void showList(List<Linea> lineaList) {
        ListLineasAdapter listLineasAdapter = new ListLineasAdapter(getApplicationContext(), lineaList);
        ListView listview = (ListView) findViewById(R.id.listLineas);
        listview.setAdapter(listLineasAdapter);
    }

    @Override
    public void showProgress(boolean state) {
        if(state){
            progress.setMax(100);
            progress.setProgress(0);
            progress.setVisibility(View.VISIBLE);

        }else{
            progress.setProgress(100);
            progress.setVisibility(View.GONE);
        }

    }

    @Override
    public void showToast() {
        Toast.makeText(getApplicationContext(), "Datos cargados correctamente", Toast.LENGTH_SHORT).show();

    }
}// MainActivity
