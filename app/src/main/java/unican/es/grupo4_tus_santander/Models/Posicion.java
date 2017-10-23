package unican.es.grupo4_tus_santander.Models;

/**
 * Created by Tiago on 23/10/17.
 */

public class Posicion {


    private long wgs84_long;
    private long wgs84_lat;
    private long cordX;
    private long cordY;

    public Posicion() {
    }

    public Posicion(long wgs84_long, long wgs84_lat, long cordX, long cordY) {
        this.wgs84_long = wgs84_long;
        this.wgs84_lat = wgs84_lat;
        this.cordX = cordX;
        this.cordY = cordY;
    }

    public long getWgs84_long() {
        return wgs84_long;
    }

    public void setWgs84_long(long wgs84_long) {
        this.wgs84_long = wgs84_long;
    }

    public long getWgs84_lat() {
        return wgs84_lat;
    }

    public void setWgs84_lat(long wgs84_lat) {
        this.wgs84_lat = wgs84_lat;
    }

    public long getCordX() {
        return cordX;
    }

    public void setCordX(long cordX) {
        this.cordX = cordX;
    }

    public long getCordY() {
        return cordY;
    }

    public void setCordY(long cordY) {
        this.cordY = cordY;
    }
}
