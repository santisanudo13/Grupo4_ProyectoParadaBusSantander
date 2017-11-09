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
        return activityLineas;
    }

    LineasActivity activityLineas;
    private Context contextLineas;

    List<Linea> listLineas = new ArrayList<Linea>();
    List<Parada> listParadas = new ArrayList<Parada>();
    List<ParadaConNombre> listParadasConNombre = new ArrayList<ParadaConNombre>();

    public void setListener(ServicioListener listener) {
        this.listenerLineas = listener;
    }

    ServicioListener listenerLineas;

    public void setCm(ConnectivityManager cm) {
        this.cm = cm;
    }

    ConnectivityManager cm = null;

    private  RemoteFetch remoteFetchLineas = new RemoteFetch();

    DatabaseHelper dbLineas;

    public RecargaBaseDatosLineas(Context context, LineasActivity activity){
        this.activityLineas = activity;
        this.contextLineas = context;
        this.cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        
    }// MainPresenter

    public void start(){
        activityLineas.showProgress(true, 0);
        new GetDataServicio(this).execute();
    }// start


    public boolean obtenData(){

        //SI NO TIENE INTERNET DEVUELVE FALSE
        if(cm == null)
            return false;

        //LINEAS
        try {
            remoteFetchLineas.getJSON(RemoteFetch.URL_LINEAS_BUS);
        } catch (Exception e) {
            Log.e("ERROR","Error : "+e.getMessage());
        }
        try {
            listLineas = ParserJSON.readArrayLineasBus(remoteFetchLineas.getBufferedData());
        } catch (Exception e) {
            Log.e("ERROR","Error : "+e.getMessage());
        }
        //PARADAS
        try {
            remoteFetchLineas.getJSON(RemoteFetch.URL_PARADAS);
        } catch (Exception e) {
            Log.e("ERROR","Error : "+e.getMessage());
        }
        try {
            listParadas = ParserJSON.readArrayParadas(remoteFetchLineas.getBufferedData());
        } catch (Exception e) {
            Log.e("ERROR","Error : "+e.getMessage());
        }
        //PARADAS CON NOMBRE
        try {
            remoteFetchLineas.getJSON(RemoteFetch.URL_PARADAS_NOMBRE);
        } catch (Exception e) {
            Log.e("ERROR","Error : "+e.getMessage());
        }
        try {
            listParadasConNombre = ParserJSON.readArrayParadasConNombre(remoteFetchLineas.getBufferedData());
        } catch (Exception e) {
            Log.e("ERROR","Error : "+e.getMessage());
        }

        return !listLineas.isEmpty() && !listParadas.isEmpty();
    }

    public ServicioListener getListener() {
        return listenerLineas;
    }


    public void guardaDataEnBaseDatos() {
        this.dbLineas = new DatabaseHelper(this.contextLineas,1);
        dbLineas.reiniciarTablas();

        for(Linea l: listLineas) {
            long id_colorLineas = -1;

            switch (l.getNumero()+"") {
                case "1":
                    id_colorLineas = dbLineas.createColor(new Color(255, 255, 0, 0));
                    break;
                case "2":
                    id_colorLineas = dbLineas.createColor(new Color(255, 171, 68, 206));
                    break;
                case "3":
                    id_colorLineas = dbLineas.createColor(new Color(255, 253, 205, 47));
                    break;
                case "4":
                    id_colorLineas = dbLineas.createColor(new Color(255, 48, 180, 214));
                    break;
                case "5C1":
                    id_colorLineas = dbLineas.createColor(new Color(255, 150, 150, 150));
                    break;
                case "5C2":
                    id_colorLineas = dbLineas.createColor(new Color(255, 150, 150, 150));
                    break;
                case "6C1":
                    id_colorLineas = dbLineas.createColor(new Color(255, 15, 127, 52));
                    break;
                case "6C2":
                    id_colorLineas = dbLineas.createColor(new Color(255, 15, 127, 52));
                    break;
                case "7C1":
                    id_colorLineas = dbLineas.createColor(new Color(255, 244, 98, 38));
                    break;
                case "7C2":
                    id_colorLineas = dbLineas.createColor(new Color(255, 244, 98, 38));
                    break;
                case "11":
                    id_colorLineas = dbLineas.createColor(new Color(255, 2, 23, 91));
                    break;
                case "12":
                    id_colorLineas = dbLineas.createColor(new Color(255, 164, 211, 99));
                    break;
                case "13":
                    id_colorLineas = dbLineas.createColor(new Color(255, 144, 129, 176));
                    break;
                case "14":
                    id_colorLineas = dbLineas.createColor(new Color(255, 14, 105, 175));
                    break;
                case "16":
                    id_colorLineas = dbLineas.createColor(new Color(255, 98, 24, 54));
                    break;
                case "17":
                    id_colorLineas = dbLineas.createColor(new Color(255, 246, 128, 132));
                    break;
                case "18":
                    id_colorLineas = dbLineas.createColor(new Color(255, 177, 232, 224));
                    break;
                case "19":
                    id_colorLineas = dbLineas.createColor(new Color(255, 18, 132, 147));
                    break;
                case "20":
                    id_colorLineas = dbLineas.createColor(new Color(255, 136, 248, 170));
                    break;
                case "21":
                    id_colorLineas = dbLineas.createColor(new Color(255, 163, 211, 98));
                    break;
                case "23":
                    id_colorLineas = dbLineas.createColor(new Color(255, 202, 202, 202));
                    break;
                default:
                    id_colorLineas = dbLineas.createColor(new Color(255, 0, 0, 0));
                    break;
            }
            long id_linea =dbLineas.createLinea(l, id_colorLineas);
            l.setId((int) id_linea);

            for(Parada parada : listParadas){
                if(parada.getIdentifierLinea() == l.getIdentifier()){
                    for(ParadaConNombre paradaConNombre : listParadasConNombre){
                        if(parada.getNumParada() == paradaConNombre.getNumero())
                            parada.setNombre(paradaConNombre.getParada());
                            parada.setFavorito(0);
                    }

                    long id_parada = dbLineas.createParada(parada, l.getId());
                }
            }
        }
        dbLineas.closeDB();
    }

    public static interface ServicioListener {
        public void onComplete();
    }

    public RemoteFetch getRemoteFetch() {
        return remoteFetchLineas;
    }

    public void setRemoteFetch(RemoteFetch remoteFetch) {
        this.remoteFetchLineas = remoteFetch;
    }
}