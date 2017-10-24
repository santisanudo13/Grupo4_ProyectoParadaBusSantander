package unican.es.grupo4_tus_santander.Models.BaseDatos.DBHelpers;

/**
 * Created by Tiago on 23/10/2017.
 */






import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class ColorDbHelper extends SQLiteOpenHelper {

    public static final String TABLE_NAME = "Color";
    public static final String TABLE_ID = "_id";
    public static final String COLUMN_1 = "_alpha";
    public static final String COLUMN_2 = "_red";
    public static final String COLUMN_3 = "_green";
    public static final String COLUMN_4 = "_blue";
    public static final String COLUMN_5 = "_idLinea";



    private static final String DATABASE_NAME = "TUS.db";
    private static final int DATABASE_VERSION = 1;

    // creation SQLite statement
    private static final String DATABASE_CREATE = "create table " + TABLE_NAME
            + "(" + TABLE_ID + " integer primary key autoincrement, "
            + "(" + COLUMN_1 + " integer not null, "
            + "(" + COLUMN_2 + " integer not null, "
            + "(" + COLUMN_3 + " integer not null, "
            + "(" + COLUMN_4 + " integer not null, "
            + COLUMN_5 + " integer not null);";

    public ColorDbHelper(Context context) {
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

