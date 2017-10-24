package unican.es.grupo4_tus_santander.Models.BaseDatos.DBAdapters;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import unican.es.grupo4_tus_santander.Models.BaseDatos.DBHelpers.ColorDbHelper;
import unican.es.grupo4_tus_santander.Models.BaseDatos.DBPojos.Color;

/**
 * Created by Tiago on 23/10/2017.
 */

public class ColorDBAdapter
{
    // Database fields
    private ColorDbHelper dbHelper;
    private String[] COLOR_TABLE_COLUMNS = { ColorDbHelper.TABLE_ID, ColorDbHelper.COLUMN_1, ColorDbHelper.COLUMN_2, ColorDbHelper.COLUMN_3, ColorDbHelper.COLUMN_4, ColorDbHelper.COLUMN_5 };
    private SQLiteDatabase database;

    public ColorDBAdapter(Context context) {
        dbHelper = new ColorDbHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Color addColor(Color c) {

        ContentValues values = new ContentValues();

        values.put(ColorDbHelper.COLUMN_1, c.getAlpha());
        values.put(ColorDbHelper.COLUMN_2, c.getRed());
        values.put(ColorDbHelper.COLUMN_3, c.getGreen());
        values.put(ColorDbHelper.COLUMN_4, c.getBlue());
        values.put(ColorDbHelper.COLUMN_5, c.getIdLinea());


        long studId = database.insert(ColorDbHelper.TABLE_NAME, null, values);

        // now that the Color is created return it ...
        Cursor cursor = database.query(ColorDbHelper.TABLE_NAME,
                COLOR_TABLE_COLUMNS, ColorDbHelper.TABLE_ID + " = "
                        + studId, null, null, null, null);

        cursor.moveToFirst();

        Color newComment = parseColor(cursor);
        cursor.close();
        return newComment;
    }

    public void deleteColor(Color comment) {
        long id = comment.getId();
        System.out.println("Comment deleted with id: " + id);
        int delete = database.delete(ColorDbHelper.TABLE_NAME, ColorDbHelper.TABLE_ID
                + " = " + id, null);
    }

    public List getAllColors() {
        List colores = new ArrayList();

        Cursor cursor = database.query(ColorDbHelper.TABLE_NAME,
                COLOR_TABLE_COLUMNS, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Color color = parseColor(cursor);
            colores.add(color);
            cursor.moveToNext();
        }

        cursor.close();
        return colores;
    }

    private Color parseColor(Cursor cursor) {
        Color col = new Color();
        col.setId((cursor.getInt(0)));
        col.setAlpha(cursor.getInt(1));
        col.setRed(cursor.getInt(2));
        col.setGreen(cursor.getInt(3));
        col.setBlue(cursor.getInt(4));
        col.setIdLinea(cursor.getInt(5));

        return col;
    }
}