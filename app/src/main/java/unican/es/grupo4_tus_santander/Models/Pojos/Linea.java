package unican.es.grupo4_tus_santander.Models.Pojos;

import java.util.Date;

/**
 * Created by Tiago on 26/10/2017.
 */

public class Linea {
    int id;
    String numero;
    String name;
    int identifier;

    int idColor;

    public Linea(){}

    public Linea( String numero, String name,  int identifier) {
        this.numero = numero;
        this.name = name;
        this.identifier = identifier;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIdentifier() {
        return identifier;
    }

    public void setIdentifier(int identifier) {
        this.identifier = identifier;
    }

    public int getIdColor() {
        return idColor;
    }

    public void setIdColor(int idColor) {
        this.idColor = idColor;
    }
}
