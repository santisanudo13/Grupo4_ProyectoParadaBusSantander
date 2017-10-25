package unican.es.grupo4_tus_santander.Presenter;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import unican.es.grupo4_tus_santander.Models.BaseDatos.DBModel.Linea;
import unican.es.grupo4_tus_santander.Models.BaseDatos.helper.DatabaseHelper;
import unican.es.grupo4_tus_santander.View.*;


/**
 * Created by alejandro on 11/10/17.
 */

public class ListLineasPresenter {
    private IListLineasView listLineasView;
    private List<Linea> listaLineasBus;
    private Context context;
    DatabaseHelper ld;

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
            ld.close();
            return true;
        }catch(Exception e){
            Log.e("ERROR","Error en la obtención de las lineas de Bus: "+e.getMessage());
            e.printStackTrace();
            listLineasView.showProgress(false);
            Toast.makeText(this.context, "Error de conexion", Toast.LENGTH_SHORT).show();
            return false;
        }
    }//obtenLineas


    public List<Linea> getListaLineasBus() {
        return listaLineasBus;
    }//getListaLineasBus


    /**
     * Método para obtener un cadena de texto con todas las lineas. En esta cadena
     * se muestra unicamente el nombre de la linea
     *  @return String con todas las gasolineras separadas por un doble salto de línea
     */
    public String getTextoLineas(){
        String textoLineas="";
        if(listaLineasBus!=null){
            for (int i=0; i<listaLineasBus.size(); i++){
                textoLineas=textoLineas+listaLineasBus.get(i).getNumero()+"\n\n";
            }//for
        }else{
            textoLineas="Sin lineas";
        }//if
        return textoLineas;
    }//getTextoLineas


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
