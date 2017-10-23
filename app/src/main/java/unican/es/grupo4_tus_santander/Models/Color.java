package unican.es.grupo4_tus_santander.Models;

/**
 * Created by Tiago on 23/10/17.
 */

public class Color {

    private long id;
    private int alpha, red, green , blue;

    public Color() {
    }

    public Color(long id, int alpha, int red, int green, int blue) {
        this.id = id;
        this.alpha = alpha;
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
