package unican.es.grupo4_tus_santander.Presenter.Main;

import android.content.Context;
import android.os.AsyncTask;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import unican.es.grupo4_tus_santander.Models.BaseDatos.DBModel.Color;
import unican.es.grupo4_tus_santander.Models.BaseDatos.DBModel.Linea;
import unican.es.grupo4_tus_santander.Models.BaseDatos.DBModel.Parada;
import unican.es.grupo4_tus_santander.Models.BaseDatos.helper.DatabaseHelper;
import unican.es.grupo4_tus_santander.Models.WebService.DataLoaders.ParserJSON;
import unican.es.grupo4_tus_santander.Models.WebService.DataLoaders.RemoteFetch;
import unican.es.grupo4_tus_santander.Models.WebService.WSModel.LineaJSON;
import unican.es.grupo4_tus_santander.Models.WebService.WSModel.ParadaJSON;
import unican.es.grupo4_tus_santander.View.Activity;


public class RecargaBaseDatos {
    private Activity activity;
    private Context context;

    private List<LineaYParadas> lineaYParadas = new ArrayList<LineaYParadas>();
    private  RemoteFetch remoteFetch = new RemoteFetch();

    DatabaseHelper db;

    public RecargaBaseDatos(Context context, Activity activity){
        this.activity = activity;
        this.context = context;
        this.db = new DatabaseHelper(this.context,1);
        db.reiniciarTablas();
        start();
        
    }// MainPresenter

    public void start(){
        activity.showProgress(true, 0);
        new getDataServicio().execute();
    }// start


    public boolean obtenData(){
        List<LineaJSON> listLineasJson = new ArrayList<LineaJSON>();
        try {
            remoteFetch.getJSON(RemoteFetch.URL_LINEAS_BUS);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            listLineasJson = ParserJSON.readArrayLineasBus(remoteFetch.getBufferedData());
        } catch (IOException e) {
            e.printStackTrace();
        }


        List<ParadaJSON> listParadaJson = new ArrayList<ParadaJSON>();
        for(LineaJSON lineaJson:listLineasJson){

            try {
                remoteFetch.getJSON(RemoteFetch.URL_PARADAS_BUS_LINEA+lineaJson.getIdentifier());
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                listParadaJson = ParserJSON.readArrayParadasBus(remoteFetch.getBufferedData());
            } catch (IOException e) {
                e.printStackTrace();
            }

            List<Parada> listParadaDb = new ArrayList<Parada>();

            for(ParadaJSON paradaJson: listParadaJson){
                listParadaDb.add(paradaJsontoDB(paradaJson));
            }

            lineaYParadas.add(new LineaYParadas(lineaJsonToDB(lineaJson), listParadaDb));
        }

        return !lineaYParadas.isEmpty();
    }

    private Parada paradaJsontoDB(ParadaJSON paradaJson) {
        Parada parada = new Parada();

        parada.setCoordX(paradaJson.getPosX());
        parada.setCoordY(paradaJson.getPosY());
        parada.setNombreParada(paradaJson.getNombreParada());
        parada.setNumeroParada(paradaJson.getnParada());

        return parada;
    }

    private Linea lineaJsonToDB(LineaJSON lineaJson) {
        Linea linea = new Linea();
        linea.setNombre(lineaJson.getName());
        linea.setNumero(lineaJson.getNumero());

        return linea;
    }

    private class getDataServicio extends AsyncTask<Void, Void, Boolean> {
        @Override
        protected Boolean doInBackground(Void... v) {
            return obtenData();
        }

        @Override
        protected void onPostExecute(Boolean result) {
            if (result) {
                new getSaveDataIntoDataBase().execute();
            } else {
                activity.showProgress(false, -1);
            }

        }
    }

    private class getSaveDataIntoDataBase extends AsyncTask<Void, Void, Boolean> {
        @Override
        protected Boolean doInBackground(Void... v) {
            guardaDataEnBaseDatos();
            return true;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            activity.showProgress(false, 1);
        }
    }


    private void guardaDataEnBaseDatos() {

        for(LineaYParadas l: lineaYParadas) {
            long id_color = -1;

            switch (l.getLinea().getNumero()+"") {
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
            long id_linea =db.createLinea(l.getLinea(), id_color);

            if(!l.getParadas().isEmpty())
                for(Parada parada : l.getParadas()){
                    db.createParada(parada, id_linea);
                }

        }
    }

    private class LineaYParadas{
        private Linea linea;
        private List<Parada> paradas;

        public LineaYParadas(Linea linea, List<Parada> paradas){
            this.linea = linea;
            this.paradas = paradas;
        }

        public Linea getLinea() {
            return linea;
        }

        public void setLinea(Linea linea) {
            this.linea = linea;
        }

        public List<Parada> getParadas() {
            return paradas;
        }

        public void setParadas(List<Parada> paradas) {
            this.paradas = paradas;
        }
    }


}