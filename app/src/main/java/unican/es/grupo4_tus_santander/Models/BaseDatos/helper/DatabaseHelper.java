package unican.es.grupo4_tus_santander.Models.BaseDatos.helper;



import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import unican.es.grupo4_tus_santander.Models.Pojos.*;

public class DatabaseHelper extends SQLiteOpenHelper implements DatabaseInterface {

	

	// Database Name
	private static final String DATABASE_NAME = "TUSSantander";

	// Table Names
	private static final String TABLE_COLOR = "color";
	private static final String TABLE_LINEA = "linea";
	private static final String TABLE_PARADA = "parada";

	// Common column names
	private static final String KEY_ID = "id";
	private static final String KEY_IDENTIFIER = "identifier";

	// COLOR Table - column nmaes
	public static final String KEY_COLOR_ALPHA = "alpha";
	public static final String KEY_COLOR_RED = "red";
	public static final String KEY_COLOR_GREEN = "green";
	public static final String KEY_COLOR_BLUE = "blue";

	// LINEA Table - column names
	int idColor;
	private static final String KEY_LINEA_NAME = "name";
	private static final String KEY_LINEA_NUMERO = "numero";
	private static final String KEY_LINEA_COLORID = "colorID";


	// PARADA Table - column names
	private static final String KEY_PARADA_NUMPARADA = "numeroParada";
	private static final String KEY_PARADA_WGS84LONG = "wgs84Long";
	private static final String KEY_PARADA_WGS84LAT = "wgs84Lat";
	private static final String KEY_PARADA_COORDX = "coordX";
	private static final String KEY_PARADA_COORDY = "coordY";
	private static final String KEY_PARADA_LINEA_IDENTIFIER = "lineaIdentifier";
	private static final String KEY_PARADA_NOMBRE = "nombre";
	private static final String KEY_PARADA_LINEA_ID = "lineaId";
	private static final String KEY_PARADA_FAVORITO = "favorito";


	// Table Create Statements
	// COLOR table create statement
	private static final String CREATE_TABLE_COLOR = "CREATE TABLE " + TABLE_COLOR
			+ "("
			+ KEY_ID + " INTEGER PRIMARY KEY,"
			+ KEY_COLOR_ALPHA + " INTEGER,"
			+ KEY_COLOR_RED + " INTEGER,"
			+ KEY_COLOR_GREEN + " INTEGER,"
			+ KEY_COLOR_BLUE + " INTEGER"
			+ ")";

	// LINEA table create statement
	private static final String CREATE_TABLE_LINEA = "CREATE TABLE "+ TABLE_LINEA
			+ "("
			+ KEY_ID + " INTEGER PRIMARY KEY,"
			+ KEY_IDENTIFIER + " INTEGER,"
			+ KEY_LINEA_NAME+ " TEXT,"
			+ KEY_LINEA_NUMERO + " TEXT,"
			+ KEY_LINEA_COLORID + " INTEGER"
			+ ")";

	// PARADA table create statement
	private static final String CREATE_TABLE_PARADA = "CREATE TABLE " + TABLE_PARADA
			+ "("
			+ KEY_ID + " INTEGER PRIMARY KEY,"
			+ KEY_IDENTIFIER + " INTEGER,"
			+ KEY_PARADA_LINEA_IDENTIFIER + " INTEGER,"
			+ KEY_PARADA_NUMPARADA + " INTEGER,"
			+ KEY_PARADA_WGS84LAT + " REAL,"
			+ KEY_PARADA_WGS84LONG + " REAL,"
			+ KEY_PARADA_COORDX + " REAL,"
			+ KEY_PARADA_COORDY + " REAL,"
			+ KEY_PARADA_NOMBRE + " TEXT,"
			+ KEY_PARADA_LINEA_ID + " INTEGER,"
			+ KEY_PARADA_FAVORITO + " INTEGER"
			+ ")";

	public DatabaseHelper(Context context, int dBVersion) {

		super(context, DATABASE_NAME, null, dBVersion);
	}

	public void reiniciarTablas(){

		// on upgrade drop older tables
		SQLiteDatabase db = this.getWritableDatabase();

		db.execSQL("DROP TABLE IF EXISTS " + TABLE_COLOR);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_LINEA);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_PARADA);

		// create new tables
		onCreate(db);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// creating required tables
		db.execSQL(CREATE_TABLE_LINEA);
		db.execSQL(CREATE_TABLE_COLOR);
		db.execSQL(CREATE_TABLE_PARADA);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// on upgrade drop older tables
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_COLOR);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_LINEA);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_PARADA);
		// create new tables
		onCreate(db);
	}

	// ------------------------ "Color" table methods ----------------//

	/*
	 * Creating a color
	 */
	public long createColor(Color color) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_COLOR_ALPHA, color.getAlpha());
		values.put(KEY_COLOR_RED, color.getRed());
		values.put(KEY_COLOR_GREEN, color.getGreen());
		values.put(KEY_COLOR_BLUE, color.getBlue());
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

		Cursor c = db.rawQuery(selectQuery, null);
		if (c != null)
			c.moveToFirst();

		Color color = new Color();
		color.setId(c.getInt(c.getColumnIndex(KEY_ID)));
		color.setAlpha((c.getInt(c.getColumnIndex(KEY_COLOR_ALPHA))));
		color.setRed((c.getInt(c.getColumnIndex(KEY_COLOR_RED))));
		color.setGreen((c.getInt(c.getColumnIndex(KEY_COLOR_GREEN))));
		color.setBlue((c.getInt(c.getColumnIndex(KEY_COLOR_BLUE))));

		return color;
	}

	/**
	 * getting all color
	 * */
	public List<Color> getAllColor() {
		List<Color> colores = new ArrayList<Color>();
		String selectQuery = "SELECT  * FROM " + TABLE_COLOR;

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (c.moveToFirst()) {
			do {
				Color color = new Color();
				color.setId(c.getInt(c.getColumnIndex(KEY_ID)));
				color.setAlpha((c.getInt(c.getColumnIndex(KEY_COLOR_ALPHA))));
				color.setRed((c.getInt(c.getColumnIndex(KEY_COLOR_RED))));
				color.setGreen((c.getInt(c.getColumnIndex(KEY_COLOR_GREEN))));
				color.setBlue((c.getInt(c.getColumnIndex(KEY_COLOR_BLUE))));

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
		List<Color> colores = getAllColor();

		Color color = null;
		for(Color c: colores){
			if(c.getId() == color_id){
				color = c;
				break;
			}
		}
		if(color == null){
			return -2;
		}else {
			SQLiteDatabase db = this.getWritableDatabase();

			ContentValues values = new ContentValues();
			values.put(KEY_IDENTIFIER, linea.getIdentifier());
			values.put(KEY_LINEA_NAME, linea.getName());
			values.put(KEY_LINEA_NUMERO, linea.getNumero());
			values.put(KEY_LINEA_COLORID, color_id);
			// insert row
			long linea_id = db.insert(TABLE_LINEA, null, values);
			return  linea_id;
		}
	}
	/*
	 * get single color
	 */
	public Linea getLinea(long linea_id) {
		SQLiteDatabase db = this.getReadableDatabase();

		String selectQuery = "SELECT  * FROM " + TABLE_LINEA + " WHERE "
				+ KEY_ID + " = " + linea_id;
		Cursor c;
		try{
			c = db.rawQuery(selectQuery, null);
		}catch(Exception e){
			return null;
		}


		if (c != null)
			c.moveToFirst();

		Linea linea = new Linea();
		linea.setId(c.getInt((c.getColumnIndex(KEY_ID))));
		linea.setIdentifier(c.getInt((c.getColumnIndex(KEY_IDENTIFIER))));
		linea.setName(c.getString(c.getColumnIndex(KEY_LINEA_NAME)));
		linea.setNumero(c.getString(c.getColumnIndex(KEY_LINEA_NUMERO)));
		linea.setIdColor(c.getInt((c.getColumnIndex(KEY_LINEA_COLORID))));
		return linea;
	}
	/**
	 * getting all linea
	 * */
	public List<Linea> getAllLinea() {
		List<Linea> lineas = new ArrayList<Linea>();
		String selectQuery = "SELECT  * FROM " + TABLE_LINEA;


		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c;
		try{
			c = db.rawQuery(selectQuery, null);
		}catch(Exception e){
			return null;
		}

		// looping through all rows and adding to list
		if (c.moveToFirst()) {
			do {
				Linea linea = new Linea();
				linea.setId(c.getInt((c.getColumnIndex(KEY_ID))));
				linea.setIdentifier(c.getInt((c.getColumnIndex(KEY_IDENTIFIER))));
				linea.setName(c.getString(c.getColumnIndex(KEY_LINEA_NAME)));
				linea.setNumero(c.getString(c.getColumnIndex(KEY_LINEA_NUMERO)));
				linea.setIdColor(c.getInt((c.getColumnIndex(KEY_LINEA_COLORID))));

				// adding to Lineas list
				lineas.add(linea);
			} while (c.moveToNext());
		}
		return lineas;
	}



	// ------------------------ "parada" table methods ----------------//

	/*
	 * Creating a parada
	 */
	public long createParada(Parada parada, long id_linea) {
		List<Linea> lineas = getAllLinea();

		Linea linea = null;

		for(Linea l: lineas){
			if(l.getId() == id_linea){
				linea = l;
				break;
			}
		}

		if (linea == null){
			return -2;
		}else{
			SQLiteDatabase db = this.getWritableDatabase();

			ContentValues values = new ContentValues();
			values.put(KEY_IDENTIFIER, parada.getIdentifier());
			values.put(KEY_PARADA_LINEA_IDENTIFIER, parada.getIdentifierLinea());
			values.put(KEY_PARADA_NUMPARADA, parada.getNumParada());
			values.put(KEY_PARADA_COORDX, parada.getCoordX());
			values.put(KEY_PARADA_COORDY, parada.getCoordY());
			values.put(KEY_PARADA_WGS84LAT, parada.getWgs64Lat());
			values.put(KEY_PARADA_WGS84LONG, parada.getWgs64Long());
			values.put(KEY_PARADA_NOMBRE, parada.getNombre());
			values.put(KEY_PARADA_LINEA_ID, id_linea);
			values.put(KEY_PARADA_FAVORITO, 0);


			// insert row
			long linea_id = db.insert(TABLE_PARADA, null, values);
			return  linea_id;
		}
	}

	/*
	 * get single parada
	 */
	public Parada getParada(long parada_id) {
		SQLiteDatabase db = this.getReadableDatabase();
		String selectQuery = "SELECT  * FROM " + TABLE_PARADA + " WHERE "
				+ KEY_ID + " = " + parada_id;

		Cursor c = db.rawQuery(selectQuery, null);

		if (c != null)
			c.moveToFirst();

		Parada parada = new Parada();
		parada.setId(c.getInt(c.getColumnIndex(KEY_ID)));
		parada.setIdentifier(c.getInt(c.getColumnIndex(KEY_IDENTIFIER)));
		parada.setIdentifierLinea(c.getInt(c.getColumnIndex(KEY_PARADA_LINEA_IDENTIFIER)));
		parada.setNumParada(c.getInt(c.getColumnIndex(KEY_PARADA_NUMPARADA)));
		parada.setWgs64Lat((c.getDouble(c.getColumnIndex(KEY_PARADA_WGS84LAT))));
		parada.setWgs64Long((c.getDouble(c.getColumnIndex(KEY_PARADA_WGS84LONG))));
		parada.setCoordX((c.getDouble(c.getColumnIndex(KEY_PARADA_COORDX))));
		parada.setCoordY((c.getDouble(c.getColumnIndex(KEY_PARADA_COORDY))));
		parada.setNombre((c.getString(c.getColumnIndex(KEY_PARADA_NOMBRE))));
		parada.setIdLinea((c.getInt(c.getColumnIndex(KEY_PARADA_LINEA_ID))));
		parada.setFavorito((c.getInt(c.getColumnIndex(KEY_PARADA_FAVORITO))));


		return parada;
	}

	/**
	 * getting all paradas
	 * */
	public List<Parada> getAllParada() {
		List<Parada> paradas = new ArrayList<Parada>();
		String selectQuery = "SELECT  * FROM " + TABLE_PARADA;

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (c.moveToFirst()) {
			do {
				Parada parada = new Parada();
				parada.setId(c.getInt(c.getColumnIndex(KEY_ID)));
				parada.setIdentifier(c.getInt(c.getColumnIndex(KEY_IDENTIFIER)));
				parada.setIdentifierLinea(c.getInt(c.getColumnIndex(KEY_PARADA_LINEA_IDENTIFIER)));
				parada.setNumParada(c.getInt(c.getColumnIndex(KEY_PARADA_NUMPARADA)));
				parada.setWgs64Lat((c.getDouble(c.getColumnIndex(KEY_PARADA_WGS84LAT))));
				parada.setWgs64Long((c.getDouble(c.getColumnIndex(KEY_PARADA_WGS84LONG))));
				parada.setCoordX((c.getDouble(c.getColumnIndex(KEY_PARADA_COORDX))));
				parada.setCoordY((c.getDouble(c.getColumnIndex(KEY_PARADA_COORDY))));
				parada.setNombre((c.getString(c.getColumnIndex(KEY_PARADA_NOMBRE))));
				parada.setIdLinea((c.getInt(c.getColumnIndex(KEY_PARADA_LINEA_ID))));
				parada.setFavorito((c.getInt(c.getColumnIndex(KEY_PARADA_FAVORITO))));


				paradas.add(parada);
			} while (c.moveToNext());
		}

		return paradas;
	}

	/**
	 * getting all paradas
	 * */
	public List<Parada> getAllParadasFavoritos() {
		List<Parada> paradas = new ArrayList<Parada>();
		String selectQuery = "SELECT  * FROM " + TABLE_PARADA + " WHERE favorito == 1";

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (c.moveToFirst()) {
			do {
				Parada parada = new Parada();
				parada.setId(c.getInt(c.getColumnIndex(KEY_ID)));
				parada.setIdentifier(c.getInt(c.getColumnIndex(KEY_IDENTIFIER)));
				parada.setIdentifierLinea(c.getInt(c.getColumnIndex(KEY_PARADA_LINEA_IDENTIFIER)));
				parada.setNumParada(c.getInt(c.getColumnIndex(KEY_PARADA_NUMPARADA)));
				parada.setWgs64Lat((c.getDouble(c.getColumnIndex(KEY_PARADA_WGS84LAT))));
				parada.setWgs64Long((c.getDouble(c.getColumnIndex(KEY_PARADA_WGS84LONG))));
				parada.setCoordX((c.getDouble(c.getColumnIndex(KEY_PARADA_COORDX))));
				parada.setCoordY((c.getDouble(c.getColumnIndex(KEY_PARADA_COORDY))));
				parada.setNombre((c.getString(c.getColumnIndex(KEY_PARADA_NOMBRE))));
				parada.setIdLinea((c.getInt(c.getColumnIndex(KEY_PARADA_LINEA_ID))));
				parada.setFavorito((c.getInt(c.getColumnIndex(KEY_PARADA_FAVORITO))));


				paradas.add(parada);
			} while (c.moveToNext());
		}

		return paradas;
	}

	public Color getColorByLinea(long linea_id){
		Linea linea = null;
		linea = getLinea(linea_id);
		if(linea == null){
			return null;
		}

		Color c = null;
		c = getColor(linea.getIdColor());

		return c;
	}

	public Linea getLineaByParada(long parada_id){
		Parada parada = null;
		parada = getParada(parada_id);
		if(parada == null)
			return null;
		Linea linea = null;
		linea = getLinea(parada.getIdLinea());

		return linea;

	}

	public List<Parada> getParadasByLinea(long linea_id){
		Linea linea = null;
		linea = getLinea(linea_id);
		if(linea == null)
			return null;

		List<Parada> allParadas = getAllParada();
		List<Parada> paradasLinea = new ArrayList<Parada>();

		for(Parada parada : allParadas){
			if(parada.getIdentifierLinea() == linea.getIdentifier())
				paradasLinea.add(parada);
		}
		return paradasLinea;
	}



	// closing database
	public void closeDB() {
		SQLiteDatabase db = this.getReadableDatabase();
		if (db != null && db.isOpen())
			db.close();
	}



}
