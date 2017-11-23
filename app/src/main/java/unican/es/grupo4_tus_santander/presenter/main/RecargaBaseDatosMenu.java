package unican.es.grupo4_tus_santander.presenter.main;

import android.content.Context;
import android.net.ConnectivityManager;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import unican.es.grupo4_tus_santander.models.basedatos.helper.DatabaseHelper;
import unican.es.grupo4_tus_santander.models.pojos.*;
import unican.es.grupo4_tus_santander.models.webservice.dataloaders.ParserJSON;
import unican.es.grupo4_tus_santander.models.webservice.dataloaders.RemoteFetch;
import unican.es.grupo4_tus_santander.presenter.main.asynctasks.GetDataServicio;
import unican.es.grupo4_tus_santander.presenter.paradas.RecargaBaseDatosParadas;
import unican.es.grupo4_tus_santander.view.main.MainActivity;


public class RecargaBaseDatosMenu {

    private static final Logger LOGGER = Logger.getLogger( RecargaBaseDatosParadas.class.getName() );

    private MainActivity activityMenu;
    private Context contextMenu;

    private List<Linea> listLineas = new ArrayList<>();
    private List<Parada> listParadas = new ArrayList<>();
    private List<ParadaConNombre> listParadasConNombre = new ArrayList<>();

    private ServicioListener listenerMenu;

    private ConnectivityManager cm = null;


    private  RemoteFetch remoteFetchMenu = new RemoteFetch();

    DatabaseHelper dbMenu;

    public RecargaBaseDatosMenu(Context context, MainActivity activity){
        this.activityMenu = activity;
        this.contextMenu = context;
        this.cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    }// MainPresenter


    public void start(){
        activityMenu.showProgress(true, 0);
        new GetDataServicio(this).execute();
    }// start


    public boolean obtenData(){

        //SI NO TIENE INTERNET DEVUELVE FALSE
        if(cm == null)
            return false;

        //LINEAS
        try {
            remoteFetchMenu.getJSON(RemoteFetch.URL_LINEAS_BUS);
        } catch (Exception e) {
            LOGGER.log(Level.FINE, "Excepción al obtener datos del JSON de líneas");
        }
        try {
            listLineas = ParserJSON.readArrayLineasBus(remoteFetchMenu.getBufferedData());
        } catch (Exception e) {
            LOGGER.log(Level.FINE, "Excepción al obtener datos del array de líneas");
        }
        //PARADAS
        try {
            remoteFetchMenu.getJSON(RemoteFetch.URL_PARADAS);
        } catch (Exception e) {
            LOGGER.log(Level.FINE, "Excepción al obtener datos del JSON de paradas");
        }
        try {
            listParadas = ParserJSON.readArrayParadas(remoteFetchMenu.getBufferedData());
        } catch (Exception e) {
            LOGGER.log(Level.FINE, "Excepción al obtener datos del array de paradas");
        }
        //PARADAS CON NOMBRE
        try {
            remoteFetchMenu.getJSON(RemoteFetch.URL_PARADAS_NOMBRE);
        } catch (Exception e) {
            LOGGER.log(Level.FINE, "Excepción al obtener datos del JSON de paradas con nombre");
        }
        try {
            listParadasConNombre = ParserJSON.readArrayParadasConNombre(remoteFetchMenu.getBufferedData());
        } catch (Exception e) {
            LOGGER.log(Level.FINE, "Excepción al obtener datos del array de paradas con nombre");
        }

        return !listLineas.isEmpty() && !listParadas.isEmpty();
    }



    public void guardaDataEnBaseDatos() {
        dbMenu = new DatabaseHelper(this.contextMenu,1);
        dbMenu.reiniciarTablas();

        for(Linea l: listLineas) {
            long colorMenuID = -1;

            switch (l.getNumero()+"") {
                case "1":
                    colorMenuID = dbMenu.createColor(new Color(255, 255, 0, 0));
                    break;
                case "2":
                    colorMenuID = dbMenu.createColor(new Color(255, 171, 68, 206));
                    break;
                case "3":
                    colorMenuID = dbMenu.createColor(new Color(255, 253, 205, 47));
                    break;
                case "4":
                    colorMenuID = dbMenu.createColor(new Color(255, 48, 180, 214));
                    break;
                case "5C1":
                    colorMenuID = dbMenu.createColor(new Color(255, 150, 150, 150));
                    break;
                case "5C2":
                    colorMenuID = dbMenu.createColor(new Color(255, 150, 150, 150));
                    break;
                case "6C1":
                    colorMenuID = dbMenu.createColor(new Color(255, 15, 127, 52));
                    break;
                case "6C2":
                    colorMenuID = dbMenu.createColor(new Color(255, 15, 127, 52));
                    break;
                case "7C1":
                    colorMenuID = dbMenu.createColor(new Color(255, 244, 98, 38));
                    break;
                case "7C2":
                    colorMenuID = dbMenu.createColor(new Color(255, 244, 98, 38));
                    break;
                case "11":
                    colorMenuID = dbMenu.createColor(new Color(255, 2, 23, 91));
                    break;
                case "12":
                    colorMenuID = dbMenu.createColor(new Color(255, 164, 211, 99));
                    break;
                case "13":
                    colorMenuID = dbMenu.createColor(new Color(255, 144, 129, 176));
                    break;
                case "14":
                    colorMenuID = dbMenu.createColor(new Color(255, 14, 105, 175));
                    break;
                case "16":
                    colorMenuID = dbMenu.createColor(new Color(255, 98, 24, 54));
                    break;
                case "17":
                    colorMenuID = dbMenu.createColor(new Color(255, 246, 128, 132));
                    break;
                case "18":
                    colorMenuID = dbMenu.createColor(new Color(255, 177, 232, 224));
                    break;
                case "19":
                    colorMenuID = dbMenu.createColor(new Color(255, 18, 132, 147));
                    break;
                case "20":
                    colorMenuID = dbMenu.createColor(new Color(255, 136, 248, 170));
                    break;
                case "21":
                    colorMenuID = dbMenu.createColor(new Color(255, 163, 211, 98));
                    break;
                case "23":
                    colorMenuID = dbMenu.createColor(new Color(255, 202, 202, 202));
                    break;
                default:
                    colorMenuID = dbMenu.createColor(new Color(255, 0, 0, 0));
                    break;
            }
            long lineaID = dbMenu.createLinea(l, colorMenuID);
            l.setId((int) lineaID);

            for(Parada parada : listParadas){
                if(parada.getIdentifierLinea() == l.getIdentifier()){
                    for(ParadaConNombre paradaConNombre : listParadasConNombre){
                        if(parada.getNumParada() == paradaConNombre.getNumero()) {
                            parada.setNombre(paradaConNombre.getParada());
                            parada.setFavorito(0);
                        }
                    }

                    dbMenu.createParada(parada, l.getId());
                }
            }
        }
        dbMenu.closeDB();
    }


    public static interface ServicioListener {
        public void onComplete();
    }


    public ServicioListener getListener() {
        return listenerMenu;
    }

    public void setListener(ServicioListener listener) {
        this.listenerMenu = listener;
    }


    public ConnectivityManager getCm() {
        return cm;
    }

    public void setCm(ConnectivityManager cm) {
        this.cm = cm;
    }

    public List<Linea> getListLineas() {
        return listLineas;
    }

    public List<Parada> getListParadas() {
        return listParadas;
    }

    public List<ParadaConNombre> getListParadasConNombre() {
        return listParadasConNombre;
    }

    public MainActivity getActivity() {
        return activityMenu;
    }

    public void setActivity(MainActivity activity) {
        this.activityMenu = activity;
    }
}