package unican.es.grupo4_tus_santander.Models.Pojos;

/**
 * Created by Tiago on 26/10/2017.
 */

public class Parada {

    int id;
    int identifierLinea;
    int identifier;
    double coordX;
    double coordY;
    double wgs64Long;
    double wgs64Lat;
    int numParada;
    int favorito;

    String nombre;

    int idLinea;

    public Parada(){}

    public Parada(int identifierLinea, int identifier, double coordX, double coordY, double wgs64Long, double wgs64Lat, int numParada) {
        this.identifierLinea = identifierLinea;
        this.identifier = identifier;
        this.coordX = coordX;
        this.coordY = coordY;
        this.wgs64Long = wgs64Long;
        this.wgs64Lat = wgs64Lat;
        this.numParada = numParada;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdentifierLinea() {
        return identifierLinea;
    }

    public void setIdentifierLinea(int identifierLinea) {
        this.identifierLinea = identifierLinea;
    }

    public int getIdentifier() {
        return identifier;
    }

    public void setIdentifier(int identifier) {
        this.identifier = identifier;
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

    public double getWgs64Long() {
        return wgs64Long;
    }

    public void setWgs64Long(double wgs64Long) {
        this.wgs64Long = wgs64Long;
    }

    public double getWgs64Lat() {
        return wgs64Lat;
    }

    public void setWgs64Lat(double wgs64Lat) {
        this.wgs64Lat = wgs64Lat;
    }

    public int getNumParada() {
        return numParada;
    }

    public void setNumParada(int numParada) {
        this.numParada = numParada;
    }

    public int getIdLinea() {
        return idLinea;
    }

    public void setIdLinea(int idLinea) {
        this.idLinea = idLinea;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getFavorito() {
        return favorito;
    }
    public void setFavorito(int favorito) {
        this.favorito = favorito;
    }
}
