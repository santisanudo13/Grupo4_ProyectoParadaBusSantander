package unican.es.grupo4_tus_santander.Models.BaseDatos;

import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import unican.es.grupo4_tus_santander.Models.Color;
import unican.es.grupo4_tus_santander.Models.Linea;
import unican.es.grupo4_tus_santander.Models.Parada;

/**
 * Created by Tiago on 23/10/2017.
 */

public class DataBaseAdapter implements  DataBaseInterface{
    SQLiteDatabase db;

    public DataBaseAdapter(SQLiteDatabase db) {
        this.db = db;
    }

    @Override
    public void  insertColor(Color input){
        if(db != null) {
                //Insertamos los datos en la tabla Usuarios
                db.execSQL("INSERT INTO color (alpha, red, gree, blue) " +
                        "VALUES (" + input.getAlpha() + ", " + input.getRed() + ", " + input.getGreen() + ", " + input.getBlue() + ")");
            //Cerramos la base de datos
            db.close();
        }
    }

    @Override
    public void deleteColorById(long id) {
        if(db != null) {
            //Insertamos los datos en la tabla Usuarios
            db.execSQL("DELETE FROM color WHERE id = " + id + " )");
            //Cerramos la base de datos
            db.close();
        }
    }

    @Override
    public Color selectColorByLinea(Linea input) {
        //TODO
        return null;
    }

    @Override
    public void insertLinea(Linea input) {
        //TODO

    }

    @Override
    public void deleteLinea(Linea input) {
        //TODO

    }

    @Override
    public List<Linea> selectLineaByNumero(int numero) {
        //TODO
        return null;
    }

    @Override
    public void insertParada(Parada input) {
        //TODO

    }

    @Override
    public void deleteParada(Parada input) {
        //TODO

    }

    @Override
    public List<Parada> selectParadaByLinea(Linea input) {
        //TODO
        return null;
    }

    @Override
    public List<Parada> selectParadaByIdentificador(int id) {
        //TODO
        return null;
    }
}
