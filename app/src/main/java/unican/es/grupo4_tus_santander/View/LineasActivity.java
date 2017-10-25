package unican.es.grupo4_tus_santander.View;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.widget.ListView;

import java.util.List;

import unican.es.grupo4_tus_santander.Models.BaseDatos.DBModel.Linea;
import unican.es.grupo4_tus_santander.Presenter.ListLineasPresenter;
import unican.es.grupo4_tus_santander.R;

public class LineasActivity extends AppCompatActivity  implements DataCommunication, SearchView.OnQueryTextListener, IListLineasView{

    //private ListView listViewLineas;
    private int lineaIdentifier;
    private int paradaIdentifier;
    private DataCommunication dataCommunication;
    private ProgressDialog dialog;
    private ListLineasPresenter listLineasPresenter;
    private ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lineas);
        this.listLineasPresenter = new ListLineasPresenter(getApplicationContext(),this);
        this.dialog = new ProgressDialog(getApplicationContext());
        this.listLineasPresenter.start();
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
    public void showList(List<Linea> lineaList) {
        ListLineasAdapter listLineasAdapter = new ListLineasAdapter(getApplicationContext(), lineaList);
        ListView listview = (ListView) findViewById(R.id.listLineas);
        listview.setAdapter(listLineasAdapter);
    }

    @Override
    public void showProgress(boolean state) {

    }

    @Override
    public void showToast() {

    }
}// MainActivity
