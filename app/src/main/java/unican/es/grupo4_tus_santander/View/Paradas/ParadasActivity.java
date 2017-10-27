package unican.es.grupo4_tus_santander.View.Paradas;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;
import unican.es.grupo4_tus_santander.Models.Pojos.Parada;
import unican.es.grupo4_tus_santander.Presenter.Lineas.RecargaBaseDatosLineas;
import unican.es.grupo4_tus_santander.Presenter.Paradas.ListParadasPresenter;
import unican.es.grupo4_tus_santander.R;

/**
 * Created by Asier on 25/10/17.
 */

public class ParadasActivity extends AppCompatActivity  implements SearchView.OnQueryTextListener {
    private ListParadasPresenter listParadasPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paradas);
        Bundle extras = getIntent().getExtras();
        int lineaId = -1;
        if(extras != null){
            lineaId = (int) extras.get("lineaID");
        }

        this.listParadasPresenter = new ListParadasPresenter(getApplicationContext(),this);
        listParadasPresenter.setIdLinea(lineaId);
        listParadasPresenter.start();
    }//onCreate

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_busqueda_paradas, menu);
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



    public void showList(List<Parada> paradasList) {
        ListParadasAdapter listParadasAdapter = new  ListParadasAdapter(this,paradasList);
        ListView listview = (ListView) findViewById(R.id.listParadas);
        listview.setAdapter(listParadasAdapter);
    }



    public void showToastEmptyParadas() {
        Toast.makeText(getApplicationContext(), "No existen paradas asociadas a esta linea", Toast.LENGTH_SHORT).show();

    }
}// ParadasActivity