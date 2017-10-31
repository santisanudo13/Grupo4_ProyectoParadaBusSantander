package unican.es.grupo4_tus_santander.Presenter.Paradas;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import unican.es.grupo4_tus_santander.Models.BaseDatos.helper.DatabaseHelper;
import unican.es.grupo4_tus_santander.Models.Pojos.Parada;
import unican.es.grupo4_tus_santander.View.Paradas.ParadasActivity;


/**
 * Created by Asier on 25/10/17.
 */

public class ListParadasPresenter {
    public ParadasActivity paradasActivity;
    private int idLinea;
    private List<Parada> listaParadasBusPorLinea;
    private Context context;
    public DatabaseHelper ld;

    public ListParadasPresenter(Context context, ParadasActivity paradasActivity){
        this.paradasActivity = paradasActivity;
        this.context = context;
        this.listaParadasBusPorLinea = new ArrayList<>();
        this.ld = new DatabaseHelper(context,1);
        start();
    }// ListParadasPresenter

    public void start(){
        new getParadasPorLinea().execute(this);
    }// start

    public void continua(boolean result){
        if(result){
            paradasActivity.showList(getListaParadasBusPorLinea());
        }else{
            paradasActivity.showProgress(false,-2);
        }
    }

    public Boolean obtenParadasPorLinea() {
        try {
            if(idLinea == -1){
                listaParadasBusPorLinea = ld.getAllParada();
                ld.closeDB();
            }else {
                listaParadasBusPorLinea = ld.getParadasByLinea(getIdLinea());
                ld.closeDB();
            }
            return !(getListaParadasBusPorLinea() == null || getListaParadasBusPorLinea().isEmpty());
        }catch(Exception e){
            Log.e("ERROR","Error en la obtención de las paradas de la linea: "+e.getMessage());
            return false;
        }
    }

    public int getIdLinea() {
        return idLinea;
    }

    public void setIdLinea(int idLinea) {
        this.idLinea = idLinea;
    }

    public List<Parada> getListaParadasBusPorLinea() {
        return listaParadasBusPorLinea;
    }//getListaParadasBusPorLinea

    public void showToastEmptyParadas() {
        Toast.makeText(context, "No existen paradas asociadas a esta linea", Toast.LENGTH_SHORT).show();
    }
}// ListParadasPresenter