package unican.es.grupo4_tus_santander.Models.BaseDatos.DBPojos;

/**
 * Created by Tiago on 23/10/17.
 */

public class Color {

    private int id;
    private int idLinea;
    private int alpha;
    private int red;
    private int green;
    private int blue;

    public Color() {
    }

    public Color(int id, int idLinea, int alpha, int red, int green, int blue) {
        this.id = id;
        this.idLinea = idLinea;
        this.alpha = alpha;
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdLinea() {
        return idLinea;
    }

    public void setIdLinea(int idLinea) {
        this.idLinea = idLinea;
    }

    public int getAlpha() {
        return alpha;
    }

    public void setAlpha(int alpha) {
        this.alpha = alpha;
    }

    public int getRed() {
        return red;
    }

    public void setRed(int red) {
        this.red = red;
    }

    public int getGreen() {
        return green;
    }

    public void setGreen(int green) {
        this.green = green;
    }

    public int getBlue() {
        return blue;
    }

    public void setBlue(int blue) {
        this.blue = blue;
    }
}
