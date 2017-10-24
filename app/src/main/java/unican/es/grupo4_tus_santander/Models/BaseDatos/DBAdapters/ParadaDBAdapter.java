package unican.es.grupo4_tus_santander.Models.BaseDatos.DBAdapters;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import unican.es.grupo4_tus_santander.Models.BaseDatos.DBHelpers.ColorDbHelper;
import unican.es.grupo4_tus_santander.Models.BaseDatos.DBHelpers.ParadaDbHelper;
import unican.es.grupo4_tus_santander.Models.BaseDatos.DBPojos.Parada;

/**
 * Created by Tiago on 23/10/2017.
 */

public class ParadaDBAdapter {
    // Database fields
    private ParadaDbHelper dbHelper;
    private String[] Parada_TABLE_COLUMNS = {ParadaDbHelper.TABLE_ID, ParadaDbHelper.COLUMN_1, ParadaDbHelper.COLUMN_2, ParadaDbHelper.COLUMN_3, ParadaDbHelper.COLUMN_4, ParadaDbHelper.COLUMN_5, ParadaDbHelper.COLUMN_6, ParadaDbHelper.COLUMN_7, ParadaDbHelper.COLUMN_8, ParadaDbHelper.COLUMN_9};
    private SQLiteDatabase database;

    public ParadaDBAdapter(Context context) {
        dbHelper = new ParadaDbHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Parada addParada(Parada c) {

        ContentValues values = new ContentValues();

        values.put(ParadaDbHelper.COLUMN_1, c.getIdLinea());
        values.put(ParadaDbHelper.COLUMN_2, c.getNumero());
        values.put(ParadaDbHelper.COLUMN_3, c.getSentido());
        values.put(ParadaDbHelper.COLUMN_4, c.getAddress());
        values.put(ParadaDbHelper.COLUMN_5, c.getNombre());
        values.put(ParadaDbHelper.COLUMN_6, c.getWgs84_long());
        values.put(ParadaDbHelper.COLUMN_7, c.getWgs84_lat());
        values.put(ParadaDbHelper.COLUMN_8, c.getCordX());
        values.put(ParadaDbHelper.COLUMN_9, c.getCordY());


        long studId = database.insert(ParadaDbHelper.TABLE_NAME, null, values);

        // now that the Parada is created return it ...
        Cursor cursor = database.query(ParadaDbHelper.TABLE_NAME,
                Parada_TABLE_COLUMNS, ParadaDbHelper.TABLE_ID + " = "
                        + studId, null, null, null, null);

        cursor.moveToFirst();

        Parada newComment = parseParada(cursor);
        cursor.close();
        return newComment;
    }

    public void deleteParada(Parada comment) {
        long id = comment.getId();
        System.out.println("Comment deleted with id: " + id);
        int delete = database.delete(ColorDbHelper.TABLE_NAME, ColorDbHelper.TABLE_ID
                + " = " + id, null);
    }

    public List getAllParadas() {
        List Paradaes = new ArrayList();

        Cursor cursor = database.query(ParadaDbHelper.TABLE_NAME,
                Parada_TABLE_COLUMNS, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Parada Parada = parseParada(cursor);
            Paradaes.add(Parada);
            cursor.moveToNext();
        }

        cursor.close();
        return Paradaes;
    }

    private Parada parseParada(Cursor cursor) {
        Parada Parada = new Parada();
        Parada.setId((cursor.getInt(0)));
        Parada.setIdLinea(cursor.getInt(1));
        Parada.setNumero(cursor.getInt(2));
        Parada.setSentido(cursor.getInt(3));
        Parada.setAddress(cursor.getString(4));
        Parada.setNombre(cursor.getString(5));
        Parada.setWgs84_long(cursor.getDouble(6));
        Parada.setWgs84_lat(cursor.getDouble(7));
        Parada.setCordX(cursor.getDouble(8));
        Parada.setCordY(cursor.getDouble(9));

        return Parada;
    }
}