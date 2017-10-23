package unican.es.grupo4_tus_santander.Models;

import java.util.Date;

/**
 * Created by Tiago on 23/10/17.
 */

public class Parada {

    private long idParada;
    private int numero;
    private String sentido;
    private String address1;
    private String nombre;
    private double wgs84_long;
    private double wgs84_lat;
    private double cordX;
    private double cordY;


    public Parada(){}

    public Parada(long idParada, int numero, String sentido, String address1, String nombre, double wgs84_long, double wgs84_lat, double cordX, double cordY) {
        this.idParada = idParada;
        this.numero = numero;
        this.sentido = sentido;
        this.address1 = address1;
        this.nombre = nombre;
        this.wgs84_long = wgs84_long;
        this.wgs84_lat = wgs84_lat;
        this.cordX = cordX;
        this.cordY = cordY;
    }

    public long getIdParada() {
        return idParada;
    }

    public void setIdParada(long idParada) {
        this.idParada = idParada;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getSentido() {
        return sentido;
    }

    public void setSentido(String sentido) {
        this.sentido = sentido;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
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
