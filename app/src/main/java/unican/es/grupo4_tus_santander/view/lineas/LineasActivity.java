package unican.es.grupo4_tus_santander.view.lineas;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import unican.es.grupo4_tus_santander.models.pojos.Linea;
import unican.es.grupo4_tus_santander.presenter.lineas.RecargaBaseDatosLineas;
import unican.es.grupo4_tus_santander.presenter.lineas.ListLineasPresenter;
import unican.es.grupo4_tus_santander.R;
import unican.es.grupo4_tus_santander.view.paradas.ParadasActivity;


public class LineasActivity extends AppCompatActivity  implements SearchView.OnQueryTextListener{

    private ListLineasPresenter listLineasPresenter;
    private ProgressBar progressBarLineas;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_lineas);
        this.progressBarLineas= findViewById(R.id.progressLinea);
        this.listLineasPresenter = new ListLineasPresenter(getApplicationContext(),this);

    }//onCreate

    /**
     * Metodo encargado de generar el toolbar
     * @param menu
     * @return si la creacion fue correcta
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_busqueda, menu);


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
        final ListView listview = findViewById(R.id.listLineas);
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

    /**
     * Metodo encargado de determinar las acciones a realizar por los botones del toolbar
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == R.id.refresh)
        {
            RecargaBaseDatosLineas r =new RecargaBaseDatosLineas(getApplicationContext(), this);
            r.start();
            return(true);
        }
        return(super.onOptionsItemSelected(item));
    }



    public void showProgress (boolean stateLineas, int tipoLineas){
        if(stateLineas)
        {
            Toast.makeText(getApplicationContext(), "Cargando datos", Toast.LENGTH_SHORT).show();
            progressBarLineas.setVisibility(View.VISIBLE);
        }else{
            if(tipoLineas == 1)
                Toast.makeText(getApplicationContext(), "Carga de datos exitosa", Toast.LENGTH_SHORT).show();
            if(tipoLineas == -1)
                Toast.makeText(getApplicationContext(), "Carga de datos fallida", Toast.LENGTH_SHORT).show();

            progressBarLineas.setVisibility(View.GONE);
        }
    }

    public ListLineasPresenter getListLineasPresenter() {
        return listLineasPresenter;
    }

    public void setListLineasPresenter(ListLineasPresenter listLineasPresenter) {
        this.listLineasPresenter = listLineasPresenter;
    }
}// LineasActivity