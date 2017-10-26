package unican.es.grupo4_tus_santander.Presenter;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import unican.es.grupo4_tus_santander.Models.BaseDatos.DBModel.Parada;
import unican.es.grupo4_tus_santander.Models.BaseDatos.helper.DatabaseHelper;
import unican.es.grupo4_tus_santander.View.IListParadasView;


/**
 * Created by Asier on 25/10/17.
 */

public class ListParadasPresenter {
    private IListParadasView listParadasView;
    private List<Parada> listaParadasBus;
    private Context context;
    private DatabaseHelper ld;

    public ListParadasPresenter(Context context, IListParadasView listParadasView){
        this.listParadasView = listParadasView;
        this.context = context;
        this.listaParadasBus =new ArrayList<>();
        this.ld = new DatabaseHelper(this.context,1);
    }// ListParadasPresenter

    public void start(){
        listParadasView.showProgress(true);
        new getParadas().execute();

    }// start

    public void continua(boolean result){

        if(result){
            listParadasView.showList(getListaParadasBus());
            listParadasView.showProgress(false);
            listParadasView.showToast();
        }

    }

    public boolean obtenParadas(){

        try {
            listaParadasBus =ld.getAllParada();
            ld.close();
            return true;
        }catch(Exception e){
            Log.e("ERROR","Error en la obtención de las paradas de Bus: "+e.getMessage());
            e.printStackTrace();
            listParadasView.showProgress(false);
            Toast.makeText(this.context, "Error de conexion", Toast.LENGTH_SHORT).show();
            return false;
        }
    }//obtenParadas


    public List<Parada> getListaParadasBus() {
        return listaParadasBus;
    }//getListaParadasBus


    public String getTextoParadas(){
        String textoParadas="";
        if(listaParadasBus !=null){
            for (int i = 0; i< listaParadasBus.size(); i++){
                textoParadas=textoParadas+ listaParadasBus.get(i).getNumeroParada()+"\n\n";
            }//for
        }else{
            textoParadas="Sin lineas";
        }//if
        return textoParadas;
    }//getTextoParadas


    private class getParadas extends AsyncTask<Void, Void, Boolean> {
        @Override
        protected Boolean doInBackground(Void... v) {
                return obtenParadas();
        }

        @Override
        protected void onPostExecute(Boolean result){
            continua(result);
        }
    }

}// ListParadasPresenter
