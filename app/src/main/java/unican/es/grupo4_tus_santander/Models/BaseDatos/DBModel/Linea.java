package unican.es.grupo4_tus_santander.Models.BaseDatos.DBModel;

/**
 * Created by Tiago on 23/10/17.
 */

public class Linea {

    private int id;
    private String numero;
    private String nombre;


    public Linea() {}

    public Linea( String numero, String nombre) {
        this.numero = numero;
        this.nombre = nombre;
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
