package unican.es.grupo4_tus_santander.View.Main;


import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;


import unican.es.grupo4_tus_santander.Presenter.MainPresenter;
import unican.es.grupo4_tus_santander.R;
import unican.es.grupo4_tus_santander.View.Activity;

public class MainActivity extends AppCompatActivity  implements Activity{

    private Context context;
    private MainPresenter mainPresenter;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.context = getApplicationContext();
        this.progressBar =(ProgressBar) findViewById(R.id.progressBar);
        this.mainPresenter = new MainPresenter(context, this);
        mainPresenter.start();
    }//onCreate

    public void showList() {
        ListFuncionesMainAdapter listFuncionesMainAdapter = new ListFuncionesMainAdapter(context);
        ListView listview = (ListView) findViewById(R.id.listLineas);
        listview.setAdapter(listFuncionesMainAdapter);
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

            progressBar.setVisibility(View.GONE);
        }
    }


}// MainActivity
