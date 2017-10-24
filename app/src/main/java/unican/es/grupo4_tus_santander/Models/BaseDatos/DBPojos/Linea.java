package unican.es.grupo4_tus_santander.Models.BaseDatos.DBPojos;

import java.util.List;

/**
 * Created by Tiago on 23/10/17.
 */

public class Linea {

    private int id;
    private int idColor;
    private int numero;
    private String nombre;


    public Linea() {}

    public Linea(int id, int idColor, int numero, String nombre) {
        this.id = id;
        this.idColor = idColor;
        this.numero = numero;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdColor() {
        return idColor;
    }

    public void setIdColor(int idColor) {
        this.idColor = idColor;
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
}
