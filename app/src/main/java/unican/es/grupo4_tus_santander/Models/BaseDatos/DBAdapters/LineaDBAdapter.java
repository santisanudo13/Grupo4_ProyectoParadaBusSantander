package unican.es.grupo4_tus_santander.Models.BaseDatos.DBAdapters;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import unican.es.grupo4_tus_santander.Models.BaseDatos.DBHelpers.ColorDbHelper;
import unican.es.grupo4_tus_santander.Models.BaseDatos.DBHelpers.LineaDbHelper;
import unican.es.grupo4_tus_santander.Models.BaseDatos.DBPojos.Linea;

/**
 * Created by Tiago on 23/10/2017.
 */

public class LineaDBAdapter 
{
    // Database fields
    private LineaDbHelper dbHelper;
    private String[] Linea_TABLE_COLUMNS = {LineaDbHelper.TABLE_ID, LineaDbHelper.COLUMN_1, LineaDbHelper.COLUMN_2, LineaDbHelper.COLUMN_3};
    private SQLiteDatabase database;

    public LineaDBAdapter(Context context) {
        dbHelper = new LineaDbHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Linea addLinea(Linea c) {

        ContentValues values = new ContentValues();

        values.put(LineaDbHelper.COLUMN_1, c.getIdColor());
        values.put(LineaDbHelper.COLUMN_2, c.getNumero());
        values.put(LineaDbHelper.COLUMN_3, c.getNombre());
        
        long studId = database.insert(LineaDbHelper.TABLE_NAME, null, values);

        // now that the Linea is created return it ...
        Cursor cursor = database.query(LineaDbHelper.TABLE_NAME,
                Linea_TABLE_COLUMNS, LineaDbHelper.TABLE_ID + " = "
                        + studId, null, null, null, null);

        cursor.moveToFirst();

        Linea newComment = parseLinea(cursor);
        cursor.close();
        return newComment;
    }

    public void deleteLinea(Linea comment) {
        long id = comment.getId();
        System.out.println("Comment deleted with id: " + id);
        int delete = database.delete(ColorDbHelper.TABLE_NAME, ColorDbHelper.TABLE_ID
                + " = " + id, null);
    }

    public List getAllLineas() {
        List Lineaes = new ArrayList();

        Cursor cursor = database.query(LineaDbHelper.TABLE_NAME,
                Linea_TABLE_COLUMNS, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Linea Linea = parseLinea(cursor);
            Lineaes.add(Linea);
            cursor.moveToNext();
        }

        cursor.close();
        return Lineaes;
    }

    private Linea parseLinea(Cursor cursor) {
        Linea linea = new Linea();
        linea.setId((cursor.getInt(0)));
        linea.setIdColor(cursor.getInt(1));
        linea.setNumero(cursor.getInt(2));
        linea.setNombre(cursor.getString(3));


        return linea;
    }
}