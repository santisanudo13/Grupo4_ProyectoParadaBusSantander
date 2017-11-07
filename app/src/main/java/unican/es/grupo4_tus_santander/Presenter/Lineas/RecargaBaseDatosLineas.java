package unican.es.grupo4_tus_santander.Presenter.Lineas;

import android.content.Context;
import android.net.ConnectivityManager;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import unican.es.grupo4_tus_santander.Models.BaseDatos.helper.DatabaseHelper;
import unican.es.grupo4_tus_santander.Models.Pojos.Color;
import unican.es.grupo4_tus_santander.Models.Pojos.Linea;
import unican.es.grupo4_tus_santander.Models.Pojos.Parada;
import unican.es.grupo4_tus_santander.Models.Pojos.ParadaConNombre;
import unican.es.grupo4_tus_santander.Models.WebService.DataLoaders.ParserJSON;
import unican.es.grupo4_tus_santander.Models.WebService.DataLoaders.RemoteFetch;
import unican.es.grupo4_tus_santander.Presenter.Lineas.AsyncTasks.GetDataServicio;
import unican.es.grupo4_tus_santander.View.Lineas.LineasActivity;


public class RecargaBaseDatosLineas {

    public LineasActivity getActivity() {
        return activity;
    }

    LineasActivity activity;
    private Context context;

    List<Linea> listLineas = new ArrayList<Linea>();
    List<Parada> listParadas = new ArrayList<Parada>();
    List<ParadaConNombre> listParadasConNombre = new ArrayList<ParadaConNombre>();

    public void setListener(ServicioListener listener) {
        this.listener = listener;
    }

    ServicioListener listener;

    public void setCm(ConnectivityManager cm) {
        this.cm = cm;
    }

    ConnectivityManager cm = null;

    private  RemoteFetch remoteFetch = new RemoteFetch();

    DatabaseHelper db;

    public RecargaBaseDatosLineas(Context context, LineasActivity activity){
        this.activity = activity;
        this.context = context;
        this.cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        
    }// MainPresenter

    public void start(){
        activity.showProgress(true, 0);
        new GetDataServicio(this).execute();
    }// start


    public boolean obtenData(){

        //SI NO TIENE INTERNET DEVUELVE FALSE
        if(cm == null)
            return false;

        //LINEAS
        try {
            remoteFetch.getJSON(RemoteFetch.URL_LINEAS_BUS);
        } catch (Exception e) {
            Log.e("ERROR","Error : "+e.getMessage());
        }
        try {
            listLineas = ParserJSON.readArrayLineasBus(remoteFetch.getBufferedData());
        } catch (Exception e) {
            Log.e("ERROR","Error : "+e.getMessage());
        }
        //PARADAS
        try {
            remoteFetch.getJSON(RemoteFetch.URL_PARADAS);
        } catch (Exception e) {
            Log.e("ERROR","Error : "+e.getMessage());
        }
        try {
            listParadas = ParserJSON.readArrayParadas(remoteFetch.getBufferedData());
        } catch (Exception e) {
            Log.e("ERROR","Error : "+e.getMessage());
        }
        //PARADAS CON NOMBRE
        try {
            remoteFetch.getJSON(RemoteFetch.URL_PARADAS_NOMBRE);
        } catch (Exception e) {
            Log.e("ERROR","Error : "+e.getMessage());
        }
        try {
            listParadasConNombre = ParserJSON.readArrayParadasConNombre(remoteFetch.getBufferedData());
        } catch (Exception e) {
            Log.e("ERROR","Error : "+e.getMessage());
        }

        return !listLineas.isEmpty() && !listParadas.isEmpty();
    }

    public ServicioListener getListener() {
        return listener;
    }


    public void guardaDataEnBaseDatos() {
        this.db = new DatabaseHelper(this.context,1);
        db.reiniciarTablas();

        for(Linea l: listLineas) {
            long id_color = -1;

            switch (l.getNumero()+"") {
                case "1":
                    id_color = db.createColor(new Color(255, 255, 0, 0));
                    break;
                case "2":
                    id_color = db.createColor(new Color(255, 171, 68, 206));
                    break;
                case "3":
                    id_color = db.createColor(new Color(255, 253, 205, 47));
                    break;
                case "4":
                    id_color = db.createColor(new Color(255, 48, 180, 214));
                    break;
                case "5C1":
                    id_color = db.createColor(new Color(255, 150, 150, 150));
                    break;
                case "5C2":
                    id_color = db.createColor(new Color(255, 150, 150, 150));
                    break;
                case "6C1":
                    id_color = db.createColor(new Color(255, 15, 127, 52));
                    break;
                case "6C2":
                    id_color = db.createColor(new Color(255, 15, 127, 52));
                    break;
                case "7C1":
                    id_color = db.createColor(new Color(255, 244, 98, 38));
                    break;
                case "7C2":
                    id_color = db.createColor(new Color(255, 244, 98, 38));
                    break;
                case "11":
                    id_color = db.createColor(new Color(255, 2, 23, 91));
                    break;
                case "12":
                    id_color = db.createColor(new Color(255, 164, 211, 99));
                    break;
                case "13":
                    id_color = db.createColor(new Color(255, 144, 129, 176));
                    break;
                case "14":
                    id_color = db.createColor(new Color(255, 14, 105, 175));
                    break;
                case "16":
                    id_color = db.createColor(new Color(255, 98, 24, 54));
                    break;
                case "17":
                    id_color = db.createColor(new Color(255, 246, 128, 132));
                    break;
                case "18":
                    id_color = db.createColor(new Color(255, 177, 232, 224));
                    break;
                case "19":
                    id_color = db.createColor(new Color(255, 18, 132, 147));
                    break;
                case "20":
                    id_color = db.createColor(new Color(255, 136, 248, 170));
                    break;
                case "21":
                    id_color = db.createColor(new Color(255, 163, 211, 98));
                    break;
                case "23":
                    id_color = db.createColor(new Color(255, 202, 202, 202));
                    break;
                default:
                    id_color = db.createColor(new Color(255, 0, 0, 0));
                    break;
            }
            long id_linea =db.createLinea(l, id_color);
            l.setId((int) id_linea);

            for(Parada parada : listParadas){
                if(parada.getIdentifierLinea() == l.getIdentifier()){
                    for(ParadaConNombre paradaConNombre : listParadasConNombre){
                        if(parada.getNumParada() == paradaConNombre.getNumero())
                            parada.setNombre(paradaConNombre.getParada());
                            parada.setFavorito(0);
                    }

                    long id_parada = db.createParada(parada, l.getId());
                }
            }
        }
        db.closeDB();
    }

    public static interface ServicioListener {
        public void onComplete();
    }

    public RemoteFetch getRemoteFetch() {
        return remoteFetch;
    }

    public void setRemoteFetch(RemoteFetch remoteFetch) {
        this.remoteFetch = remoteFetch;
    }
}