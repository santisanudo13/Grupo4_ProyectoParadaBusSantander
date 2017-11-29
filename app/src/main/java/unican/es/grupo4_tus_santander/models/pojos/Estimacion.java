package unican.es.grupo4_tus_santander.models.pojos;

import android.support.annotation.NonNull;

/**
 * Created by ssm87 on 21/11/2017.
 */

public class Estimacion implements Comparable<Estimacion>{
    String nombreLinea;
    String parada;
    String estimacionA;
    String estimacionB;
    int iA;

    String lastModified;


    public Estimacion () {}

    public Estimacion(String nombreLinea, String estimacionA, String estimacionB,String parada,String lastModified) {
        this.nombreLinea = nombreLinea;
        this.estimacionA = estimacionA;
        this.iA=Integer.parseInt(estimacionA);
        iA=iA/60;
        this.estimacionB = estimacionB;
        this.parada=parada;
        this.lastModified=lastModified;
    }

    public String getNombreLinea() {
        return nombreLinea;
    }

    public void setNombreLinea(String nombreLinea) {
        this.nombreLinea = nombreLinea;
    }

    public String getEstimacionA() {
        return estimacionA;
    }

    public void setEstimacionA(String estimacionA) {
        this.estimacionA = estimacionA;
    }

    public String getEstimacionB() {
        return estimacionB;
    }

    public void setEstimacionB(String estimacionB) {
        this.estimacionB = estimacionB;
    }

    public int getiA() {
        return iA;
    }

    public String getParada() {
        return parada;
    }

    public String getLastModified() {
        return lastModified;
    }

    @Override
    public int compareTo(@NonNull Estimacion estimacion) {
        return this.iA-estimacion.iA;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!Estimacion.class.isAssignableFrom(obj.getClass())) {
            return false;
        }
        final Estimacion other = (Estimacion) obj;

        if(this.getParada()!=other.getParada()){
            return false;
        }
        return true;
    }
}
