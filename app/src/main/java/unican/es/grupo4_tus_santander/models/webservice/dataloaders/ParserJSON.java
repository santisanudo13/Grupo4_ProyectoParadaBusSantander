package unican.es.grupo4_tus_santander.models.webservice.dataloaders;

import android.util.JsonReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import unican.es.grupo4_tus_santander.models.pojos.*;


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
    private static final String UTF8 = "UTF-8";
    private static final String RESOURCES = "resources";
    private static final String DCID = "dc:identifier";

    public static List<Linea> readArrayLineasBus (InputStream in) throws IOException {
            JsonReader reader = new JsonReader(new InputStreamReader(in, UTF8));
            List<Linea> listLineasBus = new ArrayList<>();
            reader.beginObject(); //summary y resources
            while (reader.hasNext()){
                    String name = reader.nextName();
                    if(name.equals (RESOURCES)){
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
    private static Linea readLinea (JsonReader reader) throws IOException {
        reader.beginObject(); //Leemos un object
        String name ="";
        String numero="";
        int identifier=-1;
        while(reader.hasNext()) {
            String n = reader.nextName();
            if (n.equals("ayto:numero")) {
                numero = reader.nextString();
            } else if (n.equals(DCID)) {
                identifier = reader.nextInt();
            } else if (n.equals("dc:name")) {
                name = reader.nextString();
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return new Linea(numero,name,identifier);
    }

    public static List<Parada> readArrayParadas(InputStream in) throws IOException {
        JsonReader reader = new JsonReader(new InputStreamReader(in, UTF8));
        List<Parada> listParadasJson = new ArrayList<>();
        reader.beginObject(); //summary y resources
        while (reader.hasNext()){
            String name = reader.nextName();
            if(name.equals (RESOURCES)){
                reader.beginArray(); //cada elemento del array es un object
                while(reader.hasNext())
                    listParadasJson.add(readParada(reader));
            }else{
                reader.skipValue();
            }
        }
        return listParadasJson;
    }

    private static Parada readParada(JsonReader reader) throws IOException{
        reader.beginObject(); //Leemos un object
        int identifierLinea = 0;
        int identifier = 0;
        double coordX = 0;
        double coordY = 0;
        double wgs64Long = 0;
        double wgs64Lat = 0;
        int numParada = 0;

        while(reader.hasNext()) {
            String n = reader.nextName();
            if (n.equals("wgs84_pos:long")) {
                wgs64Long = reader.nextDouble();
            } else if (n.equals("gn:coordY")) {
                coordY = reader.nextDouble();
            } else if (n.equals("gn:coordX")) {
                coordX = reader.nextDouble();
            }else if (n.equals("ayto:linea")) {
                identifierLinea = reader.nextInt();
            }else if (n.equals("wgs84_pos:lat")) {
                wgs64Lat = reader.nextDouble();
            }else if (n.equals("ayto:parada")) {
                numParada = reader.nextInt();
            }else if (n.equals(DCID)) {
                identifier = reader.nextInt();
            }else {
                reader.skipValue();
            }
        }
        reader.endObject();

            return new Parada(identifierLinea, identifier, coordX, coordY,  wgs64Long, wgs64Lat, numParada);
    }

    public static List<ParadaConNombre> readArrayParadasConNombre (InputStream in) throws IOException {
        JsonReader reader = new JsonReader(new InputStreamReader(in, UTF8));
        List<ParadaConNombre> listParadasJson = new ArrayList<>();
        reader.beginObject(); //summary y resources
        while (reader.hasNext()){
            String name = reader.nextName();
            if(name.equals (RESOURCES)){
                reader.beginArray(); //cada elemento del array es un object
                while(reader.hasNext())
                    listParadasJson.add(readParadaConNombre(reader));
            }else{
                reader.skipValue();
            }
        }
        return listParadasJson;
    }

    private static ParadaConNombre readParadaConNombre(JsonReader reader) throws IOException{
        reader.beginObject(); //Leemos un object

        int identifier = 0;
        String parada = "";
        int numero = 0;

        while(reader.hasNext()) {
            String n = reader.nextName();
            if (n.equals("ayto:parada")) {
                parada = reader.nextString();
            }else if (n.equals(DCID)) {
                identifier = reader.nextInt();
            }else if (n.equals("ayto:numero")) {
                numero = reader.nextInt();
            }else {
                reader.skipValue();
            }
        }
        reader.endObject();

        return new ParadaConNombre(identifier, parada, numero);
    }

    public static List<Estimacion> readArrayEstimaciones (InputStream in) throws IOException, ParseException {
        JsonReader reader = new JsonReader(new InputStreamReader(in, UTF8));
        List<Estimacion> listEstimacionesJson = new ArrayList<>();
        Estimacion a=null;
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SS", Locale.ENGLISH);
        reader.beginObject(); //summary y resources
        while (reader.hasNext()){
            String name = reader.nextName();
            if(name.equals (RESOURCES)){
                reader.beginArray(); //cada elemento del array es un object
                while(reader.hasNext()) {
                    a = readEstimacion(reader);
                    Date date = format.parse(a.getLastModified());
                    Date now = new Date();
                    if (date.getHours() - now.getHours() < 1 && date.getDay() - now.getDay() < 1 && !a.getEstimacionB().equals("")) {
                        listEstimacionesJson.add(a);
                    }
                }
            }else{
                reader.skipValue();
            }
        }
        return listEstimacionesJson;
    }

    private static Estimacion readEstimacion(JsonReader reader) throws IOException{
        reader.beginObject(); //Leemos un object

        String linea = "";
        String tiempo1 = "";
        String tiempo2 = "0";
        String parada = "";
        String fecha = "";

        while(reader.hasNext()) {
            String n = reader.nextName();
            if (n.equals("ayto:etiqLinea")) {
                linea = reader.nextString();
            }else if (n.equals("ayto:tiempo1")) {
                tiempo1 = reader.nextString();
            }else if (n.equals("ayto:tiempo2")) {
                tiempo2 = reader.nextString();
            }else if(n.equals("ayto:paradaId")){
                parada = reader.nextString();
            }else if(n.equals("dc:modified")){
                fecha = reader.nextString();
            }else{
                reader.skipValue();
            }
        }
        reader.endObject();

        if(fecha.length()>3) {
            fecha = fecha.substring(0, fecha.length() - 1);
        }

        return new Estimacion(linea, tiempo1, tiempo2,parada,fecha);
    }



}//ParserJSON
