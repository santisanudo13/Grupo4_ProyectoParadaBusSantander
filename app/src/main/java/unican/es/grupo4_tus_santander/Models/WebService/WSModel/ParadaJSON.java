package unican.es.grupo4_tus_santander.Models.WebService.WSModel;

/**
 * Clase que almacena la información referente a una línea de TUS
 * Created by alejandro on 4/08/17.
 */

public class ParadaJSON {


    private double  posX;
    private double posY;
    private int linea;
    private int nParada;
    private String nombreParada;


    public ParadaJSON(){}

    public ParadaJSON(double posX, double posY, int linea, int nParada, String nombreParada) {
        this.posX = posX;
        this.posY = posY;
        this.linea = linea;
        this.nParada = nParada;
        this.nombreParada = nombreParada;
    }

    public double getPosX() {
        return posX;
    }

    public void setPosX(double posX) {
        this.posX = posX;
    }

    public double getPosY() {
        return posY;
    }

    public void setPosY(double posY) {
        this.posY = posY;
    }

    public int getLinea() {
        return linea;
    }

    public void setLinea(int linea) {
        this.linea = linea;
    }

    public int getnParada() {
        return nParada;
    }

    public void setnParada(int nParada) {
        this.nParada = nParada;
    }



    public String getNombreParada() {
        return nombreParada;
    }

    public void setNombreParada(String nombreParada) {
        this.nombreParada = nombreParada;
    }
}
