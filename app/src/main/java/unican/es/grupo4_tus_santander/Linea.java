package unican.es.grupo4_tus_santander;

import java.util.Date;

/**
 * Created by guill on 23/10/2017.
 */

public class Linea {
    private String nombre;
    private String numero;
    private int id;
    private Date modified;

    public Linea(String nombre, String numero, int id, Date modified){
        this.nombre = nombre;
        this.numero = numero;
        this.id = id;
        this.modified = modified;
    }

    public String getNombre(){
        return nombre;
    }

    public String getNumero(){
        return numero;
    }

    public int getId(){
        return id;
    }

    public Date getModified(){
        return modified;
    }
}
