package unican.es.grupo4_tus_santander.presenter.estimaciones;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import java.io.IOException;
import java.text.ParseException;
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

    private Context context;

    public EstimacionesActivity getActividad() {
        return actividad;
    }

    private EstimacionesActivity actividad;
    private List<Estimacion> estimaciones;
    public void setCm(ConnectivityManager cm) {
        this.cm = cm;
    }

    private ConnectivityManager cm = null;


    public EstimacionesPresenter(Context context,String linea, String parada, EstimacionesActivity activity){
        this.lineaId=linea;
        this.paradaId=parada;
        this.actividad=activity;
        this.context=context;
        cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    }


    public void start(){
        actividad.showProgress(true,0);
        new GetEstimaciones().execute(this);
    }

    public boolean obtenData(){
        cm = (ConnectivityManager) actividad.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = cm.getActiveNetworkInfo();

        if(activeNetworkInfo == null){
            return false;
        }
        try {
            RemoteFetch remoteFetch = new RemoteFetch();
            remoteFetch.getJSON(RemoteFetch.URL_ESTIMACIONES);
            estimaciones = ParserJSON.readArrayEstimaciones(remoteFetch.getBufferedData());
            return true;
        } catch (IOException e) {
            Log.e("Estimaciones","error al obtener estimaciones");
            e.printStackTrace();
            return false;
        } catch (ParseException e) {
            Log.e("Estimaciones","error al parsear estimaciones");
            e.printStackTrace();
            return false;
        }
    }

    public void ordena(boolean type){
        if(type) {
            List<Estimacion> parada = new ArrayList<>();
            for (Estimacion x : estimaciones) {
                if (x.getParada().equals(paradaId)) {
                    parada.add(x);
                }
            }

            Collections.sort(parada);
            Estimacion temp = null;
            List<Estimacion> paradaOut = new ArrayList<>();

            for (int i = 0; i < parada.size(); i++) {
                if (parada.get(i).getNombreLinea().equals(lineaId)) {
                    paradaOut.add(parada.remove(i));
                }
            }
            paradaOut.addAll(parada);


            actividad.showList(paradaOut);
            actividad.showProgress(false, 1);
        }else{
            List<Estimacion> paradaOut = new ArrayList<>();

            actividad.showList(paradaOut);
            actividad.showProgress(false, -1);
        }
    }
}
