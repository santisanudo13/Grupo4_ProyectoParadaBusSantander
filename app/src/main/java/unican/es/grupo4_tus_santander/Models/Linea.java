package unican.es.grupo4_tus_santander.Models;

import java.util.List;

/**
 * Created by Tiago on 23/10/17.
 */

public class Linea {

    private long idLinea;
    private int numero;
    private String nombre;
    private List<Parada> paradas;
    private Color color;

    public Linea() {}

    public Linea(long idLinea, int numero, String nombre, List<Parada> paradas, Color color) {
        this.idLinea = idLinea;
        this.numero = numero;
        this.nombre = nombre;
        this.paradas = paradas;
        this.color = color;
    }

    public long getIdLinea() {
        return idLinea;
    }

    public void setIdLinea(long idLinea) {
        this.idLinea = idLinea;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Parada> getParadas() {
        return paradas;
    }

    public void setParadas(List<Parada> paradas) {
        this.paradas = paradas;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
