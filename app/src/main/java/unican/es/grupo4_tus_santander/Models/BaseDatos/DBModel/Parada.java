package unican.es.grupo4_tus_santander.Models.BaseDatos.DBModel;

/**
 * Created by Tiago on 23/10/17.
 */

public class Parada {

    private int id;
    private String nombreParada;
    private int numeroParada;
    private String direccionParada;
    private double wgs84Long;
    private double wgs84Lat;
    private double coordX;
    private double coordY;


    public Parada(){}

    public Parada(String nombreParada, int numeroParada, String direccionParada, double wgs84Long, double wgs84Lat, double coordX, double coordY) {
        this.nombreParada = nombreParada;
        this.numeroParada = numeroParada;
        this.direccionParada = direccionParada;
        this.wgs84Long = wgs84Long;
        this.wgs84Lat = wgs84Lat;
        this.coordX = coordX;
        this.coordY = coordY;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreParada() {
        return nombreParada;
    }

    public void setNombreParada(String nombreParada) {
        this.nombreParada = nombreParada;
    }

    public int getNumeroParada() {
        return numeroParada;
    }

    public void setNumeroParada(int numeroParada) {
        this.numeroParada = numeroParada;
    }

    public String getDireccionParada() {
        return direccionParada;
    }

    public void setDireccionParada(String direccionParada) {
        this.direccionParada = direccionParada;
    }

    public double getWgs84Long() {
        return wgs84Long;
    }

    public void setWgs84Long(double wgs84Long) {
        this.wgs84Long = wgs84Long;
    }

    public double getWgs84Lat() {
        return wgs84Lat;
    }

    public void setWgs84Lat(double wgs84Lat) {
        this.wgs84Lat = wgs84Lat;
    }

    public double getCoordX() {
        return coordX;
    }

    public void setCoordX(double coordX) {
        this.coordX = coordX;
    }

    public double getCoordY() {
        return coordY;
    }

    public void setCoordY(double coordY) {
        this.coordY = coordY;
    }
}
