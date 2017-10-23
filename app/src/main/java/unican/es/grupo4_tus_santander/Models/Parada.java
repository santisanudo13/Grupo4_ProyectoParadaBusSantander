package unican.es.grupo4_tus_santander.Models;

import java.util.Date;

/**
 * Created by Tiago on 23/10/17.
 */

public class Parada {

    private long idParada;
    private String numero;
    private String sentido;
    private String address1;
    private String nombre;
    private Posicion posicion;
    private Date ultimaModificacion;

    public Parada() {
    }

    public Parada(long idParada, String numero, String sentido, String address1, String nombre, Posicion posicion, Date ultimaModificacion) {
        this.idParada = idParada;
        this.numero = numero;
        this.sentido = sentido;
        this.address1 = address1;
        this.nombre = nombre;
        this.posicion = posicion;
        this.ultimaModificacion = ultimaModificacion;
    }

    public long getIdParada() {
        return idParada;
    }

    public void setIdParada(long idParada) {
        this.idParada = idParada;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
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

    public Posicion getPosicion() {
        return posicion;
    }

    public void setPosicion(Posicion posicion) {
        this.posicion = posicion;
    }

    public Date getUltimaModificacion() {
        return ultimaModificacion;
    }

    public void setUltimaModificacion(Date ultimaModificacion) {
        this.ultimaModificacion = ultimaModificacion;
    }
}
