package unican.es.grupo4_tus_santander.Models.BaseDatos.DBHelpers;

/**
 * Created by Tiago on 23/10/2017.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class ParadaDbHelper extends SQLiteOpenHelper {

    public static final String TABLE_NAME = "Parada";
    public static final String TABLE_ID = "_id";
    public static final String COLUMN_1 = "_idLinea";
    public static final String COLUMN_2 = "_numero";
    public static final String COLUMN_3 = "_sentido";
    public static final String COLUMN_4 = "_address";
    public static final String COLUMN_5 = "_nombre";
    public static final String COLUMN_6 = "_wgs84Long";
    public static final String COLUMN_7 = "_wgs84Lat";
    public static final String COLUMN_8 = "_coordX";
    public static final String COLUMN_9 = "_coordY";



    private static final String DATABASE_NAME = "TUS.db";
    private static final int DATABASE_VERSION = 1;

    // creation SQLite statement
    private static final String DATABASE_CREATE = "create table " + TABLE_NAME
            + "(" + TABLE_ID + " integer primary key autoincrement, "
            + "(" + COLUMN_1 + " integer not null, "
            + "(" + COLUMN_2 + " integer not null, "
            + "(" + COLUMN_3 + " intenger not null, "
            + "(" + COLUMN_4 + " text not null, "
            + "(" + COLUMN_5 + " text not null, "
            + "(" + COLUMN_6 + " real not null, "
            + "(" + COLUMN_7 + " real not null, "
            + "(" + COLUMN_8 + " real not null, "
            + COLUMN_9 + " real not null);";

    public ParadaDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // you should do some logging in here
        // ..

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

}
