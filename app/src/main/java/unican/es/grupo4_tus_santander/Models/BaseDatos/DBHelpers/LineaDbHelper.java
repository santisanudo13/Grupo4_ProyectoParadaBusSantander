package unican.es.grupo4_tus_santander.Models.BaseDatos.DBHelpers;

/**
 * Created by Tiago on 23/10/2017.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class LineaDbHelper extends SQLiteOpenHelper {

    public static final String TABLE_NAME = "Linea";
    public static final String TABLE_ID = "_id";
    public static final String COLUMN_1 = "_idColor";
    public static final String COLUMN_2 = "_numero";
    public static final String COLUMN_3 = "_nombre";




    private static final String DATABASE_NAME = "TUS.db";
    private static final int DATABASE_VERSION = 1;

    // creation SQLite statement
    private static final String DATABASE_CREATE = "create table " + TABLE_NAME
            + "(" + TABLE_ID + " integer primary key autoincrement, "
            + "(" + COLUMN_1 + " integer not null, "
            + "(" + COLUMN_2 + " integer not null, "
            + COLUMN_3 + " text not null);";

    public LineaDbHelper(Context context) {
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

