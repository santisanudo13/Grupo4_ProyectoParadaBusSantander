package unican.es.grupo4_tus_santander.presenter.lineas;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import unican.es.grupo4_tus_santander.models.basedatos.helper.DatabaseHelper;
import unican.es.grupo4_tus_santander.models.pojos.Linea;
import unican.es.grupo4_tus_santander.presenter.lineas.asynctasks.GetLineas;
import unican.es.grupo4_tus_santander.view.lineas.LineasActivity;



public class ListLineasPresenter {
    LineasActivity listLineasView;
    private List<Linea> listaLineasBus;
    private Context context;

    public void setLd(DatabaseHelper ld) {
        this.ld = ld;
    }

    DatabaseHelper ld;

    RecargaBaseDatosLineas r;

    public ListLineasPresenter(Context context, LineasActivity listLineasView){
        this.listLineasView = listLineasView;
        this.context = context;
        this.listaLineasBus=new ArrayList<>();
        this.ld = new DatabaseHelper(this.context,1);
        if(ld.getAllColor().isEmpty()){
            r = new RecargaBaseDatosLineas(context,listLineasView);
            r.start();
        }
        start();
    }// ListLineasPresenter

    public void start(){
        new GetLineas().execute(this);

    }// start

    public void continua(boolean result){

        if(result){
            listLineasView.showLista(getListaLineasBus());
        }

    }

    public boolean obtenLineas(){

        try {
            listaLineasBus=ld.getAllLinea();
            if( listaLineasBus==null || listaLineasBus.isEmpty()){
                return false;
            }
            ld.closeDB();
            return true;
        }catch(Exception e){
            Log.e("ERROR","Error en la obtención de las lineas de Bus: "+e.getMessage());
            return false;
        }
    }//obtenLineas


    public List<Linea> getListaLineasBus() {
        return listaLineasBus;
    }//getListaLineasBus

    public RecargaBaseDatosLineas getR() {
        return r;
    }

    public void setR(RecargaBaseDatosLineas r) {
        this.r = r;
    }
}// ListLineasPresenter
