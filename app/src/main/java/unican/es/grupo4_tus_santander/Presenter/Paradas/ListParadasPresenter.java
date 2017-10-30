package unican.es.grupo4_tus_santander.Presenter.Paradas;

import android.content.Context;
import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;


import unican.es.grupo4_tus_santander.Models.BaseDatos.helper.DatabaseHelper;
import unican.es.grupo4_tus_santander.Models.Pojos.Parada;
import unican.es.grupo4_tus_santander.View.Paradas.ParadasActivity;


/**
 * Created by Asier on 25/10/17.
 */

public class ListParadasPresenter {
    private ParadasActivity paradasActivity;
    private int idLinea;
    private List<Parada> listaParadasBusPorLinea;
    private Context context;
    private DatabaseHelper ld;

    public ListParadasPresenter(Context context, ParadasActivity paradasActivity){
        this.paradasActivity = paradasActivity;
        this.context = context;
        this.listaParadasBusPorLinea = new ArrayList<>();
        this.ld = new DatabaseHelper(context,1);
    }// ListParadasPresenter

    public void start(){
        new getParadasPorLinea().execute();
    }// start


    public int getIdLinea() {
        return idLinea;
    }

    public void setIdLinea(int idLinea) {
        this.idLinea = idLinea;
    }

    private class getParadasPorLinea extends AsyncTask<Void, Void, Boolean> {
        @Override
        protected Boolean doInBackground(Void... v) {
                return obtenParadasPorLinea();
        }

        @Override
        protected void onPostExecute(Boolean result){
            if(result){
                paradasActivity.showList(listaParadasBusPorLinea);
            }else{
                paradasActivity.showToastEmptyParadas();
            }
        }
    }

    private Boolean obtenParadasPorLinea() {
        if(idLinea == -1){
            listaParadasBusPorLinea = ld.getAllParada();
        }else{
            listaParadasBusPorLinea = ld.getParadasByLinea(idLinea);
        }
        return !listaParadasBusPorLinea.isEmpty();
    }

}// ListParadasPresenter
