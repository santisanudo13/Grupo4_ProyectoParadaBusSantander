package unican.es.grupo4_tus_santander.View;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.widget.ListView;

import java.util.List;

import unican.es.grupo4_tus_santander.Models.BaseDatos.DBModel.Linea;
import unican.es.grupo4_tus_santander.Presenter.MainPresenter;

public class MainActivity extends AppCompatActivity {

    //private ListView listViewLineas;

    private ProgressDialog dialog;
    private MainPresenter mainPresenter;
    private ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lineas);
        this.mainPresenter = new MainPresenter(getApplicationContext(), this);
        this.dialog = new ProgressDialog(getApplicationContext());
        this.mainPresenter.start();
    }//onCreate





    @Override
    public void showList(List<Linea> lineaList) {
        ListFuncionesMainAdapter listFuncionesMainAdapter = new ListFuncionesMainAdapter(getApplicationContext(), lineaList);
        ListView listview = (ListView) findViewById(R.id.listLineas);
        listview.setAdapter(listFuncionesMainAdapter);
    }

    @Override
    public void showProgress(boolean state) {

    }

    @Override
    public void showToast() {

    }
}// MainActivity
