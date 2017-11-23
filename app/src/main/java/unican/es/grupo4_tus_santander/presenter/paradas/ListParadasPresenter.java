package unican.es.grupo4_tus_santander.presenter.paradas;

import android.content.Context;
import android.util.Log;


import java.util.ArrayList;
import java.util.List;

import unican.es.grupo4_tus_santander.models.basedatos.helper.DatabaseHelper;
import unican.es.grupo4_tus_santander.models.pojos.Parada;
import unican.es.grupo4_tus_santander.presenter.paradas.asynctasks.GetParadas;
import unican.es.grupo4_tus_santander.view.paradas.ParadasActivity;


public class ListParadasPresenter {
    ParadasActivity paradasActivity;
    private List<Parada> listaParadas;
    private List<Parada> temp;
    private Context context;

    private int idLinea;

    DatabaseHelper db;

    public ListParadasPresenter(Context context, ParadasActivity paradasActivity){
        this.paradasActivity = paradasActivity;
        this.context = context;
        this.listaParadas=new ArrayList<>();
        this.db = new DatabaseHelper(this.context,1);

    }// ListLineasPresenter

    public void start(){
        new GetParadas().execute(this);

    }// start


    public void setLd(DatabaseHelper ld) {
        this.db = ld;
    }

    public void continua(boolean result){

        if(result){
            paradasActivity.showList(listaParadas);
        }else{
           paradasActivity.showProgress(false,-2);
        }

    }

    public void recargarDatos(){
        RecargaBaseDatosParadas refresh = new RecargaBaseDatosParadas(context, paradasActivity, this);
        refresh.start();
    }
    public boolean obtenParadas(){
        if(idLinea == -1){
            listaParadas = db.getAllParada();
        }else{
            listaParadas = db.getParadasByLinea(idLinea);
        }

        try {
            db.closeDB();
            if( listaParadas==null ||listaParadas.isEmpty()){
                return false;
            }

        }catch(Exception e){
            Log.e("ERROR","Error en la obtención de las lineas de Bus: "+e.getMessage());
            return false;
        }
        return true;
    }//obtenLineas

    public List<Parada> getListaParadas() {
        return listaParadas;
    }

    public void setListaParadas(List<Parada> listaParadas) {
        this.listaParadas = listaParadas;
    }

    public int getIdLinea() {
        return idLinea;
    }

    public void setIdLinea(int idLinea) {
        this.idLinea = idLinea;
    }

    public DatabaseHelper getDb() {
        return db;
    }

    public void setDb(DatabaseHelper db) {
        this.db = db;
    }

    public void filtra(String s){

        String buscado=normaliza(s);
        String nombre;
        String numero;
        temp = new ArrayList<>();
        for(Parada x: listaParadas){
            if(x.getNombre()==null){
                continue;
            }
            nombre=normaliza(x.getNombre());
            numero=normaliza(Integer.toString(x.getNumParada())+"");
            if(nombre.contains(buscado)||(numero.contains(buscado))){
                temp.add(x);
            }
        }
        paradasActivity.showList(temp);
    }

    private String normaliza(String t){
        return t.toLowerCase().replace('á','a').replace('é','e')
                .replace('í','i').replace('ó','o')
                .replace('ú','u');

    }

    public List<Parada> getTemp() {
        return temp;
    }
}// ListLineasPresenter