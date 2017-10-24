package unican.es.grupo4_tus_santander.Model;

import java.util.Date;

/**
 * Created by guill on 23/10/2017.
 */

public class Parada {
    private String parada;
    private int numero;
    private String sentido;
    private double coordY;
    private double lon;
    private double coordX;
    private String address;
    private double lat;
    private int id;
    private Date modified;

    public Parada(String parada, int numero, String sentido, double coordY, double lon, double coordX, String address, double lat, int id, Date modified){
        this.parada = parada;
        this.numero = numero;
        this.sentido = sentido;
        this.coordY = coordY;
        this.lon = lon;
        this.coordX = coordX;
        this.address = address;
        this.lat = lat;
        this.id = id;
        this.modified = modified;
    }

    public String getParada(){
        return parada;
    }

    public int getNumero(){
        return numero;
    }

    public String getSentido(){
        return sentido;
    }

    public double getCoordY(){
        return coordY;
    }

    public double getLon(){
        return lon;
    }

    public double getCoordX(){
        return coordX;
    }

    public String getAddress(){
        return address;
    }

    public double getLat(){
        return lat;
    }

    public int getId(){
        return id;
    }

    public Date getModified(){
        return modified;
    }
}
