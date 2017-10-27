package unican.es.grupo4_tus_santander.Presenter;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import unican.es.grupo4_tus_santander.Models.BaseDatos.helper.DatabaseHelper;
import unican.es.grupo4_tus_santander.Models.BaseDatos.helper.DatabaseInterface;
import unican.es.grupo4_tus_santander.Models.Pojos.Linea;
import unican.es.grupo4_tus_santander.View.Lineas.IListLineasView;


/**
 * Created by alejandro on 11/10/17.
 */

public class ListLineasPresenter {
    private IListLineasView listLineasView;
    private List<Linea> listaLineasBus;
    private Context context;
    DatabaseInterface ld;

    public ListLineasPresenter(Context context, IListLineasView listLineasView){
        this.listLineasView = listLineasView;
        this.context = context;
        this.listaLineasBus=new ArrayList<>();
        this.ld = new DatabaseHelper(this.context,1);
    }// ListLineasPresenter

    public void start(){

        listLineasView.showProgress(true);
        new getLineas().execute();

    }// start

    public void continua(boolean result){

        if(result){
            listLineasView.showList(getListaLineasBus());
            listLineasView.showProgress(false);
            listLineasView.showToast();
        }

    }

    public boolean obtenLineas(){

        try {
            listaLineasBus=ld.getAllLinea();
            ld.closeDB();
            return true;
        }catch(Exception e){
            Log.e("ERROR","Error en la obtención de las lineas de Bus: "+e.getMessage());
            e.printStackTrace();
            listLineasView.showProgress(false);
            Toast.makeText(this.context, "Error de la base de datos, actualizala", Toast.LENGTH_SHORT).show();
            return false;
        }
    }//obtenLineas


    public List<Linea> getListaLineasBus() {
        return listaLineasBus;
    }//getListaLineasBus


    private class getLineas extends AsyncTask<Void, Void, Boolean> {
        @Override
        protected Boolean doInBackground(Void... v) {
                return obtenLineas();
        }

        @Override
        protected void onPostExecute(Boolean result){
            continua(result);
        }
    }

}// ListLineasPresenter
