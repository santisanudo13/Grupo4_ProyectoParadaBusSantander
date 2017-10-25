package unican.es.grupo4_tus_santander.Models.WebService.DataLoaders;

import android.util.JsonReader;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import unican.es.grupo4_tus_santander.Models.WebService.WSModel.LineaJSON;
import unican.es.grupo4_tus_santander.Models.WebService.WSModel.ParadaJSON;


/**
 * Created by alejandro on 27/09/16.
 * Clase que contiene los metodos necesarios para parsear el JSON que devuelve el servicio "REST" con
 * los diferentes datos del TUS de Santander
 */
public class ParserJSON{

    /**
     * Método para obtener todas las lineas de buses
     * @param in InputStream del JSON con las lineas de buses
     * @return Lista con todas las lineas
     * @throws IOException
     */
    public static List<LineaJSON> readArrayLineasBus (InputStream in) throws IOException {
            JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));
            List<LineaJSON> listLineasBus = new ArrayList<LineaJSON>();
            reader.beginObject(); //summary y resources
            while (reader.hasNext()){
                    String name = reader.nextName();
                    if(name.equals ("resources")){
                        reader.beginArray(); //cada elemento del array es un object
                        while(reader.hasNext())
                            listLineasBus.add(readLinea(reader));
                    }else{
                        reader.skipValue();
                    }
            }
            return listLineasBus;
    }
    /**
     * Lee una linea
     * @param reader
     * @return
     * @throws IOException
     */
    private static LineaJSON readLinea (JsonReader reader) throws IOException {
        reader.beginObject(); //Leemos un object
        String name ="";
        String numero="";
        int identifier=-1;
        while(reader.hasNext()) {
            String n = reader.nextName();
            if (n.equals("ayto:numero")) {
                numero = reader.nextString();
            } else if (n.equals("dc:identifier")) {
                identifier = reader.nextInt();
            } else if (n.equals("dc:name")) {
                name = reader.nextString();
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return new LineaJSON(name,numero,identifier);
    }

    public static List<ParadaJSON> readArrayParadasBus (InputStream in) throws IOException {
        JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));
        List<ParadaJSON> listParadasJson = new ArrayList<ParadaJSON>();
        reader.beginObject(); //summary y resources
        while (reader.hasNext()){
            String name = reader.nextName();
            if(name.equals ("resources")){
                reader.beginArray(); //cada elemento del array es un object
                while(reader.hasNext())
                    listParadasJson.add(readParada(reader));
            }else{
                reader.skipValue();
            }
        }
        return listParadasJson;
    }

    private static ParadaJSON readParada(JsonReader reader) throws IOException{
        reader.beginObject(); //Leemos un object


        double  posX = 0;
        double posY = 0;
        int linea = 0;
        int nParada = 0;
        String nombreParada = "";

        while(reader.hasNext()) {
            String n = reader.nextName();
            if (n.equals("ayto:PosX")) {
                posX = reader.nextDouble();
            } else if (n.equals("ayto:PosY")) {
                posY = reader.nextDouble();
            } else if (n.equals("ayto:Linea")) {
                linea = reader.nextInt();
            } else if (n.equals("ayto:NParada")) {
                nParada = reader.nextInt();
            } else if (n.equals("ayto:NombreParada")) {
                nombreParada = reader.nextString();
            }else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return new ParadaJSON(posX, posY, linea, nParada,  nombreParada);
    }
}//ParserJSON
