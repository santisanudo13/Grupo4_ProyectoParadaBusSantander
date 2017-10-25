package unican.es.grupo4_tus_santander.Views;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.widget.ListView;

import java.util.List;

import unican.es.grupo4_tus_santander.Models.BaseDatos.DBModel.Parada;
import unican.es.grupo4_tus_santander.Presenter.ListParadasPresenter;
import unican.es.grupo4_tus_santander.R;

public class ParadasActivity extends AppCompatActivity  implements DataCommunication,
        SearchView.OnQueryTextListener, IListParadasView{

    //private ListView listViewLineas;
    private int lineaIdentifier;
    private int paradaIdentifier;
    private DataCommunication dataCommunication;
    private ProgressDialog dialog;
    private ListParadasPresenter listParadasPresenter;
    private ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paradas);
        this.listParadasPresenter = new ListParadasPresenter(getApplicationContext(),this);
        this.dialog = new ProgressDialog(getApplicationContext());
        this.listParadasPresenter.start();
    }//onCreate

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_busqueda, menu);

        //MenuItem searchItem = menu.findItem(R.id.search);
        //SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        //searchView.setOnQueryTextListener(this);

        return true;
    }

    @Override
    public int getLineaIdentifier() {
        return lineaIdentifier;
    }

    @Override
    public void setLineaIdentifier(int identifier) {
        this.lineaIdentifier=identifier;
    }

    @Override
    public int getParadaIdentifier() { return paradaIdentifier; }

    @Override
    public void setParadaIdentifier(int paradaIdentifier) { this.paradaIdentifier = paradaIdentifier; }

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
        ListParadasAdapter listParadasAdapter = new ListParadasAdapter(getApplicationContext(), paradaList);
        ListView listview = (ListView) findViewById(R.id.listParadas);
        listview.setAdapter(listParadasAdapter);
    }

    @Override
    public void showProgress(boolean state) {

    }

    @Override
    public void showToast() {

    }
}// MainActivity
