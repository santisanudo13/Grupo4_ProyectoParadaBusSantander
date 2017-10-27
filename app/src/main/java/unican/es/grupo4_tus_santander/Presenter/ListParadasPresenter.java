package unican.es.grupo4_tus_santander.Presenter;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


import unican.es.grupo4_tus_santander.Models.BaseDatos.helper.DatabaseHelper;
import unican.es.grupo4_tus_santander.Models.Pojos.Linea;
import unican.es.grupo4_tus_santander.Models.Pojos.Parada;
import unican.es.grupo4_tus_santander.View.IListParadasView;


/**
 * Created by Asier on 25/10/17.
 */

public class ListParadasPresenter {
    private IListParadasView listParadasView;
    private List<Parada> listaParadasBusPorLinea;
    private List<Linea> listaLineasBus;
    private Context context;
    private DatabaseHelper ld;

    public ListParadasPresenter(Context context, IListParadasView listParadasView){
        this.listParadasView = listParadasView;
        this.context = context;
        this.listaParadasBusPorLinea = new ArrayList<>();
        this.listaLineasBus = new ArrayList<>();
        this.ld = new DatabaseHelper(this.context,1);
    }// ListParadasPresenter

    public void start(){
        listParadasView.showProgress(true,0);
        new getParadasPorLinea().execute();

    }// start

    public void continua(boolean result){
        if(result){
            listParadasView.showList(getListaParadasBusPorLinea(), getListaLineasBus());
            listParadasView.showProgress(result,0);
        }
    }

    public boolean obtenParadasPorLinea(){
        try {
            for(Parada parada : getListaParadasTotal()){
                listaParadasBusPorLinea = ld.getParadasByLinea(parada.getIdentifierLinea());
            }
            ld.close();
            return true;
        }catch(Exception e){
            Log.e("ERROR","Error en la obtención de las paradas de la linea: "+e.getMessage());
            e.printStackTrace();
            listParadasView.showProgress(false, -1);
            Toast.makeText(this.context, "Error de conexion", Toast.LENGTH_SHORT).show();
            return false;
        }
    }//obtenParadasPorLinea


    public List<Parada> getListaParadasTotal() {
        return ld.getAllParada();
    }//getListaParadasBusPorLinea

    public List<Parada> getListaParadasBusPorLinea() {
        return listaParadasBusPorLinea;
    }

    private List<Linea> getListaLineasBus() {
        return listaLineasBus;
    }//getListaLineasBus

    private class getParadasPorLinea extends AsyncTask<Void, Void, Boolean> {
        @Override
        protected Boolean doInBackground(Void... v) {
                return obtenParadasPorLinea();
        }

        @Override
        protected void onPostExecute(Boolean result){
            continua(result);
        }
    }

}// ListParadasPresenter
