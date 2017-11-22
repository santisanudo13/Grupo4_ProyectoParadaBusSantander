package unican.es.grupo4_tus_santander.presenter.estimaciones;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import unican.es.grupo4_tus_santander.models.pojos.Estimacion;
import unican.es.grupo4_tus_santander.models.webservice.dataloaders.ParserJSON;
import unican.es.grupo4_tus_santander.models.webservice.dataloaders.RemoteFetch;
import unican.es.grupo4_tus_santander.presenter.estimaciones.asynctasks.GetEstimaciones;
import unican.es.grupo4_tus_santander.view.estimaciones.EstimacionesActivity;

/**
 * Created by ssm87 on 21/11/2017.
 */

public class EstimacionesPresenter {


    private String lineaId;
    private String paradaId;

    public EstimacionesActivity getActividad() {
        return actividad;
    }

    private EstimacionesActivity actividad;
    private List<Estimacion> estimaciones;


    public EstimacionesPresenter(String linea, String parada, EstimacionesActivity activity){
        this.lineaId=linea;
        this.paradaId=parada;
        this.actividad=activity;
    }


    public void start(){
        actividad.showProgress(true,0);
        new GetEstimaciones().execute(this);
    }

    public boolean obtenData(){
        try {
            RemoteFetch remoteFetch = new RemoteFetch();
            remoteFetch.getJSON(RemoteFetch.URL_ESTIMACIONES);
            estimaciones = ParserJSON.readArrayEstimaciones(remoteFetch.getBufferedData());
            return true;
        } catch (Exception e) {
            Log.e("Estimaciones","error al obtener estimaciones");
            e.printStackTrace();
            return false;
        }
    }

    public void ordena(){
        List<Estimacion> parada = new ArrayList<>();
        for (Estimacion x: estimaciones) {
            if(x.getParada().equals(paradaId)){
                parada.add(x);
            }
        }

        Collections.sort(parada);
        Estimacion temp=null;
        List<Estimacion> paradaOut = new ArrayList<>();

        for(int i=0;i<parada.size();i++){
            if(parada.get(i).getNombreLinea().equals(lineaId)){
                paradaOut.add(parada.remove(i));
            }
        }
        paradaOut.addAll(parada);




        actividad.showList(paradaOut);
        actividad.showProgress(false,1);

    }
}
