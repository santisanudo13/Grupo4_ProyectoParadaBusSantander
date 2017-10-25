package unican.es.grupo4_tus_santander.Presenter;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import unican.es.grupo4_tus_santander.Models.BaseDatos.DBModel.Color;
import unican.es.grupo4_tus_santander.Models.BaseDatos.DBModel.Linea;
import unican.es.grupo4_tus_santander.Models.BaseDatos.DBModel.Parada;
import unican.es.grupo4_tus_santander.Models.BaseDatos.helper.DatabaseHelper;
import unican.es.grupo4_tus_santander.View.*;




public class MainPresenter {
    private MainActivity mainActivity;
    private Context context;
    
    private List<LineaYColor> lineasYColores = new ArrayList<LineaYColor>();
    private List<LineaYParadas> lineaYParadas = new ArrayList<LineaYParadas>();


    DatabaseHelper db;

    public MainPresenter(Context context, MainActivity mainActivity){
        this.mainActivity = mainActivity;
        this.context = context;
        this.db = new DatabaseHelper(this.context,1);
        
        start();
    }// MainPresenter

    public void start(){
        mainActivity.showProgress(true);
        new getDataServicio().execute();
    }// start


    public boolean obtenData() {

            //TODO
            return true;
        
    }

        private class getDataServicio extends AsyncTask<Void, Void, Boolean> {
            @Override
            protected Boolean doInBackground(Void... v) {
                return obtenData();
            }

            @Override
            protected void onPostExecute(Boolean result) {
                if (result) {
                    Toast.makeText(context, "Datos obtenidos con éxito", Toast.LENGTH_SHORT).show();
                    guardaDataEnBaseDatos();
                } else {
                    Toast.makeText(context, "Error al obtener los datos", Toast.LENGTH_SHORT).show();
                }
                mainActivity.showProgress(false);
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
            db.createLinea(l.getLinea(), id_color);

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

    private class LineaYColor{
        private Linea linea;
        private Color color;

        public LineaYColor(Linea linea, Color color) {
            this.linea = linea;
            this.color = color;
        }

        public Linea getLinea() {
            return linea;
        }

        public void setLinea(Linea linea) {
            this.linea = linea;
        }

        public Color getColor() {
            return color;
        }

        public void setColor(Color color) {
            this.color = color;
        }
    }
}