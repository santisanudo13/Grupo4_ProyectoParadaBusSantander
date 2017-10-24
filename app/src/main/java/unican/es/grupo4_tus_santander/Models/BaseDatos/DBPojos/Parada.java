package unican.es.grupo4_tus_santander.Models.BaseDatos.DBPojos;

import java.util.Date;

/**
 * Created by Tiago on 23/10/17.
 */

public class Parada {

    private int id;
    private int idLinea;
    private int numero;
    private int sentido;
    private String address;
    private String nombre;
    private double wgs84_long;
    private double wgs84_lat;
    private double cordX;
    private double cordY;


    public Parada(){}

    public Parada(int id, int idLinea, int numero, int sentido, String address, String nombre, double wgs84_long, double wgs84_lat, double cordX, double cordY) {
        this.id = id;
        this.idLinea = idLinea;
        this.numero = numero;
        this.sentido = sentido;
        this.address = address;
        this.nombre = nombre;
        this.wgs84_long = wgs84_long;
        this.wgs84_lat = wgs84_lat;
        this.cordX = cordX;
        this.cordY = cordY;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdLinea() {
        return idLinea;
    }

    public void setIdLinea(int idLinea) {
        this.idLinea = idLinea;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getSentido() {
        return sentido;
    }

    public void setSentido(int sentido) {
        this.sentido = sentido;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getWgs84_long() {
        return wgs84_long;
    }

    public void setWgs84_long(double wgs84_long) {
        this.wgs84_long = wgs84_long;
    }

    public double getWgs84_lat() {
        return wgs84_lat;
    }

    public void setWgs84_lat(double wgs84_lat) {
        this.wgs84_lat = wgs84_lat;
    }

    public double getCordX() {
        return cordX;
    }

    public void setCordX(double cordX) {
        this.cordX = cordX;
    }

    public double getCordY() {
        return cordY;
    }

    public void setCordY(double cordY) {
        this.cordY = cordY;
    }
}
