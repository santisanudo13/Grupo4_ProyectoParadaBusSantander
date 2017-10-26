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
import unican.es.grupo4_tus_santander.Models.BaseDatos.DBModel.Parada;
import unican.es.grupo4_tus_santander.Presenter.ListParadasPresenter;
import unican.es.grupo4_tus_santander.R;

/**
 * Created by Asier on 25/10/17.
 */

public class ParadasActivity extends AppCompatActivity  implements SearchView.OnQueryTextListener, IListParadasView{

    private int lineaIdentifier;
    private int paradaIdentifier;
    private ListParadasPresenter listParadasPresenter;
    private ProgressBar progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paradas);
        this.progress=(ProgressBar)findViewById(R.id.progressParadas);
        this.listParadasPresenter = new ListParadasPresenter(getApplicationContext(),this);
        this.listParadasPresenter.start();
    }//onCreate

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_busqueda, menu);

        MenuItem searchItem = menu.findItem(R.id.search);
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
    public void showList(List<Parada> paradaList) {
        ListParadasAdapter listParadasAdapter = new  ListParadasAdapter(getApplicationContext(), paradaList);
        ListView listview = (ListView) findViewById(R.id.listParadas);
        listview.setAdapter(listParadasAdapter);
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
}// ParadasActivity