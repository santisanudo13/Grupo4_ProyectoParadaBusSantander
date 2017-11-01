package unican.es.grupo4_tus_santander.Presenter.Paradas;

import android.content.Context;
import android.net.ConnectivityManager;

import java.util.ArrayList;
import java.util.List;

import unican.es.grupo4_tus_santander.Models.BaseDatos.helper.DatabaseHelper;
import unican.es.grupo4_tus_santander.Models.Pojos.Color;
import unican.es.grupo4_tus_santander.Models.Pojos.Linea;
import unican.es.grupo4_tus_santander.Models.Pojos.Parada;
import unican.es.grupo4_tus_santander.Models.Pojos.ParadaConNombre;
import unican.es.grupo4_tus_santander.Models.WebService.DataLoaders.ParserJSON;
import unican.es.grupo4_tus_santander.Models.WebService.DataLoaders.RemoteFetch;
import unican.es.grupo4_tus_santander.Presenter.Paradas.AsyncTasks.GetDataServicioParadas;
import unican.es.grupo4_tus_santander.View.Paradas.ParadasActivity;


public class RecargaBaseDatosParadas {
    private ParadasActivity activity;
    private Context context;
    private ListParadasPresenter presenter;

    private List<Linea> listLineas = new ArrayList<Linea>();
    private List<Parada> listParadas = new ArrayList<Parada>();
    private List<ParadaConNombre> listParadasConNombre = new ArrayList<ParadaConNombre>();

    private ServicioListener listener;

    private ConnectivityManager cm = null;


    private  RemoteFetch remoteFetch = new RemoteFetch();

    DatabaseHelper db;

    public RecargaBaseDatosParadas(Context context, ParadasActivity activity, ListParadasPresenter presenter){
        this.activity = activity;
        this.context = context;
        this.presenter = presenter;
        this.cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    }// MainPresenter


    public void start(){
        activity.showProgress(true, 0);
        new GetDataServicioParadas(this).execute();
    }// start


    public boolean obtenData(){

        //SI NO TIENE INTERNET DEVUELVE FALSE
        if(cm == null)
            return false;

        //LINEAS
        try {
            remoteFetch.getJSON(RemoteFetch.URL_LINEAS_BUS);
        } catch (Exception e) {
            
        }
        try {
            listLineas = ParserJSON.readArrayLineasBus(remoteFetch.getBufferedData());
        } catch (Exception e) {
            
        }
        //PARADAS
        try {
            remoteFetch.getJSON(RemoteFetch.URL_PARADAS);
        } catch (Exception e) {
            
        }
        try {
            listParadas = ParserJSON.readArrayParadas(remoteFetch.getBufferedData());
        } catch (Exception e) {
            
        }
        //PARADAS CON NOMBRE
        try {
            remoteFetch.getJSON(RemoteFetch.URL_PARADAS_NOMBRE);
        } catch (Exception e) {
            
        }
        try {
            listParadasConNombre = ParserJSON.readArrayParadasConNombre(remoteFetch.getBufferedData());
        } catch (Exception e) {
            
        }

        return !listLineas.isEmpty() && !listParadas.isEmpty();
    }



    public void guardaDataEnBaseDatos() {
        db = new DatabaseHelper(this.context,1);
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
            long id_linea = db.createLinea(l, id_color);
            l.setId((int) id_linea);

            for(Parada parada : listParadas){
                if(parada.getIdentifierLinea() == l.getIdentifier()){
                    for(ParadaConNombre paradaConNombre : listParadasConNombre){
                        if(parada.getNumParada() == paradaConNombre.getNumero()) {
                            parada.setNombre(paradaConNombre.getParada());
                            parada.setFavorito(0);
                        }
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


    public ServicioListener getListener() {
        return listener;
    }

    public void setListener(ServicioListener listener) {
        this.listener = listener;
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

    public ParadasActivity getActivity() {
        return activity;
    }

    public void setActivity(ParadasActivity activity) {
        this.activity = activity;
    }

    public ListParadasPresenter getPresenter() {
        return presenter;
    }

    public void setPresenter(ListParadasPresenter presenter) {
        this.presenter = presenter;
    }
}