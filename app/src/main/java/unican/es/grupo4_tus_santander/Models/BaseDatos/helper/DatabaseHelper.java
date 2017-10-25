package unican.es.grupo4_tus_santander.Models.BaseDatos.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import unican.es.grupo4_tus_santander.Models.BaseDatos.DBModel.Color;
import unican.es.grupo4_tus_santander.Models.BaseDatos.DBModel.Linea;
import unican.es.grupo4_tus_santander.Models.BaseDatos.DBModel.Parada;

public class DatabaseHelper extends SQLiteOpenHelper implements DatabaseInterface {

	// Logcat Linea
	private static final String LOG = "DatabaseHelper";


	// Database Name
	private static final String DATABASE_NAME = "TUSSantander";

	// Table Names
	private static final String TABLE_COLOR = "color";
	private static final String TABLE_LINEA = "linea";
	private static final String TABLE_PARADA = "parada";
	private static final String TABLE_LINEA_COLOR = "linea_color";
	private static final String TABLE_LINEA_PARADA = "linea_parada";




	// Common column names
	private static final String KEY_ID = "id";

	// COLOR Table - column nmaes
	public static final String KEY_ALPHA = "alpha";
	public static final String KEY_RED = "red";
	public static final String KEY_GREEN = "green";
	public static final String KEY_BLUE = "blue";

	// LINEA Table - column names

	private static final String KEY_NOMBRE = "nombre";
	private static final String KEY_NUMERO = "numero";

	// PARADA Table - column names
	private static final String KEY_NOMBREPARADA = "nombreParada";
	private static final String KEY_NUMEROPARADA = "numeroParada";
	private static final String KEY_DIRECCIONPARADA = "direccionParada";
	private static final String KEY_WGS84LONG = "wgs84Long";
	private static final String KEY_WGS84LAT = "wgs84Lat";
	private static final String KEY_COORDX = "coordX";
	private static final String KEY_COORDY = "coordY";

	// LINEA_COLOR Table - column names
	private static final String KEY_LINEA_ID = "linea_id";
	private static final String KEY_COLOR_ID = "color_id";

	// LINEA_PARADA Table - column names

	private static final String KEY_PARADA_ID = "parada_id";

	// Table Create Statements
	// LINEA table create statement
	private static final String CREATE_TABLE_LINEA = "CREATE TABLE "+ TABLE_LINEA
			+ "("
			+ KEY_ID + " INTEGER PRIMARY KEY,"
			+ KEY_NOMBRE+ " TEXT,"
			+ KEY_NUMERO + " INTEGER"
			+ ")";

	// COLOR table create statement
	private static final String CREATE_TABLE_COLOR = "CREATE TABLE " + TABLE_COLOR
			+ "("
			+ KEY_ID + " INTEGER PRIMARY KEY,"
			+ KEY_ALPHA + " INTEGER,"
			+ KEY_RED + " INTEGER,"
			+ KEY_GREEN + " INTEGER,"
			+ KEY_BLUE + " INTEGER"
			+ ")";

	// PARADA table create statement
	private static final String CREATE_TABLE_PARADA = "CREATE TABLE " + TABLE_PARADA
			+ "("
			+ KEY_ID + " INTEGER PRIMARY KEY,"
			+ KEY_NOMBREPARADA + " TEXT,"
			+ KEY_NUMEROPARADA + " INTEGER,"
			+ KEY_DIRECCIONPARADA + " TEXT,"
			+ KEY_WGS84LAT + " REAL,"
			+ KEY_WGS84LONG + " REAL,"
			+ KEY_COORDX + " REAL,"
			+ KEY_COORDY + " REAL"
			+ ")";

	// LINEA_PARADA table create statement
	private static final String CREATE_TABLE_LINEA_COLOR = "CREATE TABLE "+ TABLE_LINEA_COLOR
			+ "("
			+ KEY_LINEA_ID + " INTEGER,"
			+ KEY_COLOR_ID+ " INTEGER,"
			+ "PRIMARY KEY ("+KEY_LINEA_ID+", "+KEY_COLOR_ID+"), "
			+ "FOREIGN KEY ("+KEY_LINEA_ID+") REFERENCES "+TABLE_LINEA+" ("+KEY_ID+"), "
			+ "FOREIGN KEY ("+KEY_COLOR_ID+") REFERENCES "+TABLE_COLOR+" ("+KEY_ID+")"
			+ ")";

	// LINEA_PARADA table create statement
	private static final String CREATE_TABLE_LINEA_PARADA = "CREATE TABLE "+ TABLE_LINEA_PARADA
			+ "("
			+ KEY_LINEA_ID + " INTEGER,"
			+ KEY_PARADA_ID+ " INTEGER,"
			+ "PRIMARY KEY ("+KEY_LINEA_ID+", "+KEY_PARADA_ID+"), "
			+ "FOREIGN KEY ("+KEY_LINEA_ID+") REFERENCES "+TABLE_LINEA+" ("+KEY_ID+"), "
			+ "FOREIGN KEY ("+KEY_PARADA_ID+") REFERENCES "+TABLE_PARADA+" ("+KEY_ID+")"
			+ ")";


	public DatabaseHelper(Context context, int dBVersion) {

		super(context, DATABASE_NAME, null, dBVersion);
	}


	public void reiniciarTablas(){

		// on upgrade drop older tables
		SQLiteDatabase db = this.getWritableDatabase();

		db.execSQL("DROP TABLE IF EXISTS " + TABLE_COLOR);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_LINEA);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_LINEA_COLOR);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_PARADA);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_LINEA_PARADA);

		// create new tables
		onCreate(db);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// creating required tables
		db.execSQL(CREATE_TABLE_LINEA);
		db.execSQL(CREATE_TABLE_COLOR);
		db.execSQL(CREATE_TABLE_LINEA_COLOR);
		db.execSQL(CREATE_TABLE_PARADA);
		db.execSQL(CREATE_TABLE_LINEA_PARADA);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// on upgrade drop older tables
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_COLOR);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_LINEA);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_LINEA_COLOR);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_PARADA);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_LINEA_PARADA);


		// create new tables
		onCreate(db);
	}

	// ------------------------ "color" table methods ----------------//

	/*
	 * Creating a color
	 */
	public long createColor(Color color) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_ALPHA, color.getAlpha());
		values.put(KEY_RED, color.getRed());
		values.put(KEY_GREEN, color.getGreen());
		values.put(KEY_BLUE, color.getBlue());

		// insert row
		long color_id = db.insert(TABLE_COLOR, null, values);



		return color_id;
	}

	/*
	 * get single color
	 */
	public Color getColor(long color_id) {
		SQLiteDatabase db = this.getReadableDatabase();

		String selectQuery = "SELECT  * FROM " + TABLE_COLOR + " WHERE "
				+ KEY_ID + " = " + color_id;

		Log.e(LOG, selectQuery);

		Cursor c = db.rawQuery(selectQuery, null);

		if(c == null){
			System.out.println("Cursor nulo al obtener color");
			return null;
		}

		c.moveToFirst();
		Color color = new Color();
		color.setId(c.getInt(c.getColumnIndex(KEY_ID)));
		color.setAlpha((c.getInt(c.getColumnIndex(KEY_ALPHA))));
		color.setRed((c.getInt(c.getColumnIndex(KEY_RED))));
		color.setGreen((c.getInt(c.getColumnIndex(KEY_GREEN))));
		color.setBlue((c.getInt(c.getColumnIndex(KEY_BLUE))));

		return color;
	}

	/**
	 * getting all color
	 * */
	public List<Color> getAllColor() {
		List<Color> colores = new ArrayList<Color>();
		String selectQuery = "SELECT  * FROM " + TABLE_COLOR;

		Log.e(LOG, selectQuery);

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (c.moveToFirst()) {
			do {
				Color color = new Color();
				color.setId(c.getInt(c.getColumnIndex(KEY_ID)));
				color.setAlpha((c.getInt(c.getColumnIndex(KEY_ALPHA))));
				color.setRed((c.getInt(c.getColumnIndex(KEY_RED))));
				color.setGreen((c.getInt(c.getColumnIndex(KEY_GREEN))));
				color.setBlue((c.getInt(c.getColumnIndex(KEY_BLUE))));

				// adding to todo list
				colores.add(color);
			} while (c.moveToNext());
		}

		return colores;
	}



	// ------------------------ "Linea" table methods ----------------//

	/*
	 * Creating linea
	 */
	public long createLinea(Linea linea, long color_id) {
		SQLiteDatabase db = this.getWritableDatabase();

		String selectQuery = "SELECT  * FROM " + TABLE_COLOR + " WHERE "
				+ KEY_ID + " = " + color_id;

		Log.e(LOG, selectQuery);

		Cursor c = db.rawQuery(selectQuery, null);

		if (c == null){
			return -2;
		} else{

		ContentValues valuesLinea = new ContentValues();
		valuesLinea.put(KEY_NOMBRE, linea.getNombre());
		valuesLinea.put(KEY_NUMERO, linea.getNumero());


		// insert row en lineas
		long linea_id = db.insert(TABLE_LINEA, null, valuesLinea);

		ContentValues valuesLineaColor = new ContentValues();
		valuesLineaColor.put(KEY_LINEA_ID, linea_id);
		valuesLineaColor.put(KEY_COLOR_ID, color_id);

		long linea_color_id = db.insert(TABLE_LINEA_COLOR, null, valuesLineaColor);

		return linea_id;
	}
	}
	/*
	 * get single color
	 */
	public Linea getLinea(long linea_id) {
		SQLiteDatabase db = this.getReadableDatabase();

		String selectQuery = "SELECT  * FROM " + TABLE_LINEA + " WHERE "
				+ KEY_ID + " = " + linea_id;

		Log.e(LOG, selectQuery);

		Cursor c = db.rawQuery(selectQuery, null);

		if(c == null){
			System.out.println("Cursor nulo al obtener linea");
			return null;
		}

		c.moveToFirst();

		Linea linea = new Linea();
		linea.setId(c.getInt((c.getColumnIndex(KEY_ID))));
		linea.setNombre(c.getString(c.getColumnIndex(KEY_NOMBRE)));
		linea.setNumero(c.getInt(c.getColumnIndex(KEY_NUMERO)));

		return linea;
	}
	/**
	 * getting all linea
	 * */
	public List<Linea> getAllLinea() {
		List<Linea> lineas = new ArrayList<Linea>();
		String selectQuery = "SELECT  * FROM " + TABLE_LINEA;

		Log.e(LOG, selectQuery);

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (c.moveToFirst()) {
			do {
				Linea t = new Linea();
				t.setId(c.getInt((c.getColumnIndex(KEY_ID))));
				t.setNombre(c.getString(c.getColumnIndex(KEY_NOMBRE)));
				t.setNumero(c.getInt(c.getColumnIndex(KEY_NUMERO)));

				// adding to Lineas list
				lineas.add(t);
			} while (c.moveToNext());
		}
		return lineas;
	}



	// ------------------------ "parada" table methods ----------------//

	/*
	 * Creating a parada
	 */
	public long createParada(Parada parada, long id_linea) {
		SQLiteDatabase db = this.getWritableDatabase();

		String selectQuery = "SELECT  * FROM " + TABLE_LINEA + " WHERE "
				+ KEY_ID + " = " + id_linea;

		Log.e(LOG, selectQuery);

		Cursor c = db.rawQuery(selectQuery, null);

		if (c == null){
			return -2;
		}else{

			ContentValues values = new ContentValues();
			values.put(KEY_NOMBREPARADA, parada.getNombreParada());
			values.put(KEY_NUMEROPARADA, parada.getNumeroParada());
			values.put(KEY_DIRECCIONPARADA, parada.getDireccionParada());
			values.put(KEY_WGS84LONG, parada.getWgs84Long());
			values.put(KEY_WGS84LAT, parada.getWgs84Lat());
			values.put(KEY_COORDX, parada.getCoordX());
			values.put(KEY_COORDY, parada.getCoordY());

			// insert row
			long parada_id = db.insert(TABLE_PARADA, null, values);

			ContentValues valuesLineaParada = new ContentValues();
			valuesLineaParada.put(KEY_LINEA_ID, id_linea);
			valuesLineaParada.put(KEY_PARADA_ID, parada_id);

			long linea_parada_id = db.insert(TABLE_LINEA_PARADA, null, valuesLineaParada);


			return parada_id;
		}
	}

	/*
	 * get single parada
	 */
	public Parada getParada(long parada_id) {
		SQLiteDatabase db = this.getReadableDatabase();

		String selectQuery = "SELECT  * FROM " + TABLE_PARADA + " WHERE "
				+ KEY_ID + " = " + parada_id;

		Log.e(LOG, selectQuery);

		Cursor c = db.rawQuery(selectQuery, null);

		if (c == null){
			System.out.println("Cursor nulo al obtener parada");
			return null;
		}

		c.moveToFirst();

		Parada parada = new Parada();
		parada.setId(c.getInt(c.getColumnIndex(KEY_ID)));
		parada.setNombreParada((c.getString(c.getColumnIndex(KEY_NOMBREPARADA))));
		parada.setNumeroParada((c.getInt(c.getColumnIndex(KEY_NUMEROPARADA))));
		parada.setDireccionParada((c.getString(c.getColumnIndex(KEY_DIRECCIONPARADA))));
		parada.setWgs84Long((c.getFloat(c.getColumnIndex(KEY_WGS84LONG))));
		parada.setWgs84Lat((c.getFloat(c.getColumnIndex(KEY_WGS84LAT))));
		parada.setCoordX((c.getFloat(c.getColumnIndex(KEY_COORDX))));
		parada.setCoordY((c.getFloat(c.getColumnIndex(KEY_COORDY))));

		return parada;
	}

	/**
	 * getting all paradas
	 * */
	public List<Parada> getAllParada() {
		List<Parada> paradas = new ArrayList<Parada>();
		String selectQuery = "SELECT  * FROM " + TABLE_PARADA;

		Log.e(LOG, selectQuery);

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (c.moveToFirst()) {
			do {
				Parada parada = new Parada();
				parada.setId(c.getInt(c.getColumnIndex(KEY_ID)));
				parada.setNombreParada((c.getString(c.getColumnIndex(KEY_NOMBREPARADA))));
				parada.setNumeroParada((c.getInt(c.getColumnIndex(KEY_NUMEROPARADA))));
				parada.setDireccionParada((c.getString(c.getColumnIndex(KEY_DIRECCIONPARADA))));
				parada.setWgs84Long((c.getFloat(c.getColumnIndex(KEY_WGS84LONG))));
				parada.setWgs84Lat((c.getFloat(c.getColumnIndex(KEY_WGS84LAT))));
				parada.setCoordX((c.getFloat(c.getColumnIndex(KEY_COORDX))));
				parada.setCoordY((c.getFloat(c.getColumnIndex(KEY_COORDY))));

				// adding to todo list
				paradas.add(parada);
			} while (c.moveToNext());
		}

		return paradas;
	}


	public Color getColorByLinea(long linea_id){
		String selectQuery = "SELECT  * FROM " + TABLE_LINEA_COLOR + " WHERE "
				+ KEY_LINEA_ID + " = " + linea_id;

		Log.e(LOG, selectQuery);

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.rawQuery(selectQuery, null);

		long color_id = -1;
		// looping through all rows and adding to list
		if (c.moveToFirst()) {
			do {

				if(c.getInt(c.getColumnIndex(KEY_LINEA_ID)) == linea_id){
					color_id = c.getInt(c.getColumnIndex(KEY_COLOR_ID));
					break;
				}



			} while (c.moveToNext());
		}
		Color col = null;
		col = getColor(color_id);

		return col;
	}

	public Linea getLineaByParada(long parada_id){
		String selectQuery = "SELECT  * FROM " + TABLE_LINEA_PARADA + " WHERE "
				+ KEY_PARADA_ID + " = " + parada_id;

		Log.e(LOG, selectQuery);

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.rawQuery(selectQuery, null);

		long linea_id = -1;
		// looping through all rows and adding to list
		if (c.moveToFirst()) {
			do {

				if(c.getInt(c.getColumnIndex(KEY_PARADA_ID)) == parada_id){
					linea_id = c.getInt(c.getColumnIndex(KEY_LINEA_ID));
					break;
				}



			} while (c.moveToNext());
		}
		Linea lin = null;
		lin = getLinea(linea_id);

		return lin;
	}

	public List<Parada> getParadasByLinea(long linea_id){
		String selectQuery = "SELECT * FROM " + TABLE_LINEA_PARADA + " WHERE "
				+ KEY_LINEA_ID + " = " + linea_id;

		Log.e(LOG, selectQuery);

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.rawQuery(selectQuery, null);

		long parada_id = -1;
		// looping through all rows and adding to list
		List<Long> idParadas = new ArrayList<Long>();
		if (c.moveToFirst()) {
			do {

				if(c.getInt(c.getColumnIndex(KEY_LINEA_ID)) == linea_id){
					parada_id = c.getInt(c.getColumnIndex(KEY_PARADA_ID));
					idParadas.add(parada_id);
				}
			} while (c.moveToNext());
		}

		ArrayList listParadas = new ArrayList();
		for(long id_parada : idParadas){
			selectQuery = "SELECT * FROM " + TABLE_PARADA + " WHERE "
					+ KEY_ID + " = " + id_parada ;

			Log.e(LOG, selectQuery);

			c = db.rawQuery(selectQuery, null);


			// looping through all rows and adding to list

			if (c.moveToFirst()) {
				do {

					Parada parada = new Parada();
					parada.setId(c.getInt(c.getColumnIndex(KEY_ID)));
					parada.setNombreParada((c.getString(c.getColumnIndex(KEY_NOMBREPARADA))));
					parada.setNumeroParada((c.getInt(c.getColumnIndex(KEY_NUMEROPARADA))));
					parada.setDireccionParada((c.getString(c.getColumnIndex(KEY_DIRECCIONPARADA))));
					parada.setWgs84Long((c.getFloat(c.getColumnIndex(KEY_WGS84LONG))));
					parada.setWgs84Lat((c.getFloat(c.getColumnIndex(KEY_WGS84LAT))));
					parada.setCoordX((c.getFloat(c.getColumnIndex(KEY_COORDX))));
					parada.setCoordY((c.getFloat(c.getColumnIndex(KEY_COORDY))));

					listParadas.add(parada);
				} while (c.moveToNext());
			}
		}
		return listParadas;
	}



	// closing database
	public void closeDB() {
		SQLiteDatabase db = this.getReadableDatabase();
		if (db != null && db.isOpen())
			db.close();
	}



}
