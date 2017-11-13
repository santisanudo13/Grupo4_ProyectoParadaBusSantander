package unican.es.grupo4_tus_santander.models.pojos;

/**
 * Created by Tiago on 26/10/2017.
 */

public class ParadaConNombre {

    int id;
    int identifier;
    String parada;
    int numero;

    public ParadaConNombre(int identifier, String parada, int numero) {
        this.identifier = identifier;
        this.parada = parada;
        this.numero = numero;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdentifier() {
        return identifier;
    }

    public void setIdentifier(int identifier) {
        this.identifier = identifier;
    }

    public String getParada() {
        return parada;
    }

    public void setParada(String parada) {
        this.parada = parada;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }
}
