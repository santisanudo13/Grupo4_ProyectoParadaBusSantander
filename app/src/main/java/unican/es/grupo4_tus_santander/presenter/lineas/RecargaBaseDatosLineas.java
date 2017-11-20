package unican.es.grupo4_tus_santander.presenter.lineas;

import android.content.Context;
import android.net.ConnectivityManager;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import unican.es.grupo4_tus_santander.models.basedatos.helper.DatabaseHelper;
import unican.es.grupo4_tus_santander.models.pojos.Color;
import unican.es.grupo4_tus_santander.models.pojos.Linea;
import unican.es.grupo4_tus_santander.models.pojos.Parada;
import unican.es.grupo4_tus_santander.models.pojos.ParadaConNombre;
import unican.es.grupo4_tus_santander.models.webservice.dataloaders.ParserJSON;
import unican.es.grupo4_tus_santander.models.webservice.dataloaders.RemoteFetch;
import unican.es.grupo4_tus_santander.presenter.lineas.asynctasks.GetDataServicio;
import unican.es.grupo4_tus_santander.view.lineas.LineasActivity;


public class RecargaBaseDatosLineas {

    public LineasActivity getActivity() {
        return activityLineas;
    }

    LineasActivity activityLineas;
    private Context contextLineas;

    private static final String ERROR1 = "ERROR";
    private static final String ERROR2 = "Error : ";

    private static int numLineasExpected = 33;

    List<Linea> listLineasBuffer = new ArrayList<>();

    List<Linea> listLineas = new ArrayList<>();
    List<Parada> listParadas = new ArrayList<>();
    List<ParadaConNombre> listParadasConNombre = new ArrayList<>();

    public void setListener(ServicioListener listener) {
        this.listenerLineas = listener;
    }

    ServicioListener listenerLineas;

    public void setCm(ConnectivityManager cm) {
        this.cm = cm;
    }

    ConnectivityManager cm = null;

    private RemoteFetch remoteFetchLineas = new RemoteFetch();

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
            Log.e(ERROR1,ERROR2+e.getMessage());
        }
        try {
            listLineasBuffer = ParserJSON.readArrayLineasBus(remoteFetchLineas.getBufferedData());
        } catch (Exception e) {
            Log.e(ERROR1,ERROR2+e.getMessage());
        }
        //PARADAS
        try {
            remoteFetchLineas.getJSON(RemoteFetch.URL_PARADAS);
        } catch (Exception e) {
            Log.e(ERROR1,ERROR2+e.getMessage());
        }
        try {
            listParadas = ParserJSON.readArrayParadas(remoteFetchLineas.getBufferedData());
        } catch (Exception e) {
            Log.e(ERROR1,ERROR2+e.getMessage());
        }
        //PARADAS CON NOMBRE
        try {
            remoteFetchLineas.getJSON(RemoteFetch.URL_PARADAS_NOMBRE);
        } catch (Exception e) {
            Log.e(ERROR1,ERROR2+e.getMessage());
        }
        try {
            listParadasConNombre = ParserJSON.readArrayParadasConNombre(remoteFetchLineas.getBufferedData());
        } catch (Exception e) {
            Log.e(ERROR1,ERROR2+e.getMessage());
        }

        return !listLineas.isEmpty() && !listParadas.isEmpty();
    }

    public ServicioListener getListener() {
        return listenerLineas;
    }


    public boolean guardaDataEnBaseDatos() {
        if(listLineasBuffer.size() != numLineasExpected) {
            return false;
            }else {
            listLineas.clear();
            listLineas.addAll(listLineasBuffer);
            listLineasBuffer.clear();


            this.dbLineas = new DatabaseHelper(this.contextLineas, 1);
            dbLineas.reiniciarTablas();

            for (Linea l : listLineas) {
                long colorLineasID = -1;

                switch (l.getNumero() + "") {
                    case "1":
                        colorLineasID = dbLineas.createColor(new Color(255, 255, 0, 0));
                        break;
                    case "2":
                        colorLineasID = dbLineas.createColor(new Color(255, 171, 68, 206));
                        break;
                    case "3":
                        colorLineasID = dbLineas.createColor(new Color(255, 253, 205, 47));
                        break;
                    case "4":
                        colorLineasID = dbLineas.createColor(new Color(255, 48, 180, 214));
                        break;
                    case "5C1":
                        colorLineasID = dbLineas.createColor(new Color(255, 150, 150, 150));
                        break;
                    case "5C2":
                        colorLineasID = dbLineas.createColor(new Color(255, 150, 150, 150));
                        break;
                    case "6C1":
                        colorLineasID = dbLineas.createColor(new Color(255, 15, 127, 52));
                        break;
                    case "6C2":
                        colorLineasID = dbLineas.createColor(new Color(255, 15, 127, 52));
                        break;
                    case "7C1":
                        colorLineasID = dbLineas.createColor(new Color(255, 244, 98, 38));
                        break;
                    case "7C2":
                        colorLineasID = dbLineas.createColor(new Color(255, 244, 98, 38));
                        break;
                    case "11":
                        colorLineasID = dbLineas.createColor(new Color(255, 2, 23, 91));
                        break;
                    case "12":
                        colorLineasID = dbLineas.createColor(new Color(255, 164, 211, 99));
                        break;
                    case "13":
                        colorLineasID = dbLineas.createColor(new Color(255, 144, 129, 176));
                        break;
                    case "14":
                        colorLineasID = dbLineas.createColor(new Color(255, 14, 105, 175));
                        break;
                    case "16":
                        colorLineasID = dbLineas.createColor(new Color(255, 98, 24, 54));
                        break;
                    case "17":
                        colorLineasID = dbLineas.createColor(new Color(255, 246, 128, 132));
                        break;
                    case "18":
                        colorLineasID = dbLineas.createColor(new Color(255, 177, 232, 224));
                        break;
                    case "19":
                        colorLineasID = dbLineas.createColor(new Color(255, 18, 132, 147));
                        break;
                    case "20":
                        colorLineasID = dbLineas.createColor(new Color(255, 136, 248, 170));
                        break;
                    case "21":
                        colorLineasID = dbLineas.createColor(new Color(255, 163, 211, 98));
                        break;
                    case "23":
                        colorLineasID = dbLineas.createColor(new Color(255, 202, 202, 202));
                        break;
                    default:
                        colorLineasID = dbLineas.createColor(new Color(255, 0, 0, 0));
                        break;
                }
                long lineaID = dbLineas.createLinea(l, colorLineasID);
                l.setId((int) lineaID);

                for (Parada parada : listParadas) {
                    if (parada.getIdentifierLinea() == l.getIdentifier()) {
                        for (ParadaConNombre paradaConNombre : listParadasConNombre) {
                            if (parada.getNumParada() == paradaConNombre.getNumero()) {
                                parada.setNombre(paradaConNombre.getParada());
                                parada.setFavorito(0);
                            }
                        }

                        dbLineas.createParada(parada, l.getId());
                    }
                }
            }
            dbLineas.closeDB();
            return true;
        }
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

    public static int getNumLineasExpected() {
        return numLineasExpected;
    }

    public static void setNumLineasExpected(int numLineasExpected) {
        RecargaBaseDatosLineas.numLineasExpected = numLineasExpected;
    }

    public List<Linea> getListLineasBuffer() {
        return listLineasBuffer;
    }

    public void setListLineasBuffer(List<Linea> listLineasBuffer) {
        this.listLineasBuffer = listLineasBuffer;
    }
}