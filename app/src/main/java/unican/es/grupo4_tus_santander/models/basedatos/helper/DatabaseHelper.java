package unican.es.grupo4_tus_santander.models.basedatos.helper;



import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import unican.es.grupo4_tus_santander.models.pojos.*;

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
	private static final String KEY_LINEA_NAME = "name";
	private static final String KEY_LINEA_NUMERO = "numero";
	private static final String KEY_LINEA_COLORID = "colorID";


	// PARADA Table - column names
	private static final String KEY_PARADA_NUMPARADA = "numeroParada";
	private static final String KEY_PARADA_WGS64LONG = "wgs84Long";
	private static final String KEY_PARADA_WGS64LAT = "wgs84Lat";
	private static final String KEY_PARADA_COORDX = "coordX";
	private static final String KEY_PARADA_COORDY = "coordY";
	private static final String KEY_PARADA_LINEA_IDENTIFIER = "lineaIdentifier";
	private static final String KEY_PARADA_NOMBRE = "nombre";
	private static final String KEY_PARADA_LINEA_ID = "lineaId";
	private static final String KEY_PARADA_FAVORITO = "favorito";

	private static final String INTEGER1 = " INTEGER,";
	private static final String INTEGER2 = " INTEGER";
	private static final String INTEGER_PRIMARY_KEY = " INTEGER PRIMARY KEY,";
	private static final String TEXT = " TEXT,";
	private static final String REAL = " REAL,";
	private static final String CREATE_TABLE = "CREATE TABLE ";
	private static final String WHERE = " WHERE ";
	private static final String SELECT = "SELECT * FROM ";
	private static final String DROP = "DROP TABLE IF EXISTS ";


	// Table Create Statements
	// COLOR table create statement
	private static final String CREATE_TABLE_COLOR = CREATE_TABLE + TABLE_COLOR
			+ "("
			+ KEY_ID + INTEGER_PRIMARY_KEY
			+ KEY_COLOR_ALPHA + INTEGER1
			+ KEY_COLOR_RED + INTEGER1
			+ KEY_COLOR_GREEN + INTEGER1
			+ KEY_COLOR_BLUE + INTEGER2
			+ ")";

	// LINEA table create statement
	private static final String CREATE_TABLE_LINEA = CREATE_TABLE + TABLE_LINEA
			+ "("
			+ KEY_ID + INTEGER_PRIMARY_KEY
			+ KEY_IDENTIFIER + INTEGER1
			+ KEY_LINEA_NAME+ TEXT
			+ KEY_LINEA_NUMERO + TEXT
			+ KEY_LINEA_COLORID + INTEGER2
			+ ")";

	// PARADA table create statement
	private static final String CREATE_TABLE_PARADA = CREATE_TABLE + TABLE_PARADA
			+ "("
			+ KEY_ID + INTEGER_PRIMARY_KEY
			+ KEY_IDENTIFIER + INTEGER1
			+ KEY_PARADA_LINEA_IDENTIFIER + INTEGER1
			+ KEY_PARADA_NUMPARADA + INTEGER1
			+ KEY_PARADA_WGS64LAT + REAL
			+ KEY_PARADA_WGS64LONG + REAL
			+ KEY_PARADA_COORDX + REAL
			+ KEY_PARADA_COORDY + REAL
			+ KEY_PARADA_NOMBRE + TEXT
			+ KEY_PARADA_LINEA_ID + INTEGER1
			+ KEY_PARADA_FAVORITO + INTEGER2
			+ ")";

	public DatabaseHelper(Context context, int dBVersion) {

		super(context, DATABASE_NAME, null, dBVersion);
	}

	public void reiniciarTablas(){

		// on upgrade drop older tables
		SQLiteDatabase db = this.getWritableDatabase();

		db.execSQL(DROP + TABLE_COLOR);
		db.execSQL(DROP + TABLE_LINEA);
		db.execSQL(DROP + TABLE_PARADA);

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
		db.execSQL(DROP + TABLE_COLOR);
		db.execSQL(DROP + TABLE_LINEA);
		db.execSQL(DROP + TABLE_PARADA);
		// create new tables
		onCreate(db);
	}

	// ------------------------ "Color" table methods ----------------//

	/*
	 * Creating a color
	 */
	public long createColor(Color color) {
		if(color.getAlpha()<0||color.getAlpha()>255 ||
				color.getRed()<0||color.getRed()>255 ||
				color.getGreen()<0||color.getGreen()>255 ||
				color.getBlue()<0||color.getBlue()>255){
			return -1;
		}

		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_COLOR_ALPHA, color.getAlpha());
		values.put(KEY_COLOR_RED, color.getRed());
		values.put(KEY_COLOR_GREEN, color.getGreen());
		values.put(KEY_COLOR_BLUE, color.getBlue());
		// insert row
		return db.insert(TABLE_COLOR, null, values);
	}

	/*
	 * get single color
	 */
	public Color getColor(long colorID) {
		SQLiteDatabase db = this.getReadableDatabase();
		String selectQuery = SELECT + TABLE_COLOR + WHERE
				+ KEY_ID + " = " + colorID;

		Cursor c;
		try{
			c = db.rawQuery(selectQuery, null);

			c.moveToFirst();

			Color color = new Color();
			color.setId(c.getInt(c.getColumnIndex(KEY_ID)));
			color.setAlpha((c.getInt(c.getColumnIndex(KEY_COLOR_ALPHA))));
			color.setRed((c.getInt(c.getColumnIndex(KEY_COLOR_RED))));
			color.setGreen((c.getInt(c.getColumnIndex(KEY_COLOR_GREEN))));
			color.setBlue((c.getInt(c.getColumnIndex(KEY_COLOR_BLUE))));
			c.close();
			return color;
		}catch(Exception e){
			return null;
		}
	}

	/**
	 * getting all color
	 * */
	public List<Color> getAllColor() {
		List<Color> colores = new ArrayList<>();
		String selectQuery = SELECT + TABLE_COLOR;

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = null;
		try{
			c = db.rawQuery(selectQuery, null);
		}catch(Exception e){
			return new ArrayList<>();
		}

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
		c.close();

		return colores;
	}

	// ------------------------ "Linea" table methods ----------------//

	/*
	 * Creating linea
	 */
	public long createLinea(Linea linea, long colorID) {
		List<Color> colores = getAllColor();

		Color color = null;
		for(Color c: colores){
			if(c.getId() == colorID){
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
			values.put(KEY_LINEA_COLORID, colorID);
			// insert row
			return db.insert(TABLE_LINEA, null, values);
		}
	}
	/*
	 * get single color
	 */
	public Linea getLinea(long lineaID) {
		SQLiteDatabase db = this.getReadableDatabase();

		String selectQuery = SELECT + TABLE_LINEA + WHERE
				+ KEY_ID + " = " + lineaID;
		Cursor c;
		try{
			c = db.rawQuery(selectQuery, null);
			c.moveToFirst();

			Linea linea = new Linea();

			linea.setId(c.getInt((c.getColumnIndex(KEY_ID))));
			linea.setIdentifier(c.getInt((c.getColumnIndex(KEY_IDENTIFIER))));
			linea.setName(c.getString(c.getColumnIndex(KEY_LINEA_NAME)));
			linea.setNumero(c.getString(c.getColumnIndex(KEY_LINEA_NUMERO)));
			linea.setIdColor(c.getInt((c.getColumnIndex(KEY_LINEA_COLORID))));
			c.close();
			return linea;
		}catch(Exception e){
			return null;
		}
	}
	/**
	 * getting all linea
	 * */
	public List<Linea> getAllLinea() {
		List<Linea> lineas = new ArrayList<>();
		String selectQuery = SELECT + TABLE_LINEA;


		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = null;
		try{
			c = db.rawQuery(selectQuery, null);
		}catch(Exception e){
			return new ArrayList<>();
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
		c.close();
		Collections.sort(lineas);
		return lineas;
	}



	// ------------------------ "parada" table methods ----------------//

	/*
	 * Creating a parada
	 */
	public long createParada(Parada parada, long lineaID) {
		List<Linea> lineas = getAllLinea();

		Linea linea = null;

		for(Linea l: lineas){
			if(l.getId() == lineaID){
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
			values.put(KEY_PARADA_WGS64LAT, parada.getWgs64Lat());
			values.put(KEY_PARADA_WGS64LONG, parada.getWgs64Long());
			values.put(KEY_PARADA_NOMBRE, parada.getNombre());
			values.put(KEY_PARADA_LINEA_ID, lineaID);
			values.put(KEY_PARADA_FAVORITO, 0);


			// insert row
			return  db.insert(TABLE_PARADA, null, values);

		}
	}

	/*
	 * get single parada
	 */
	public Parada getParada(long paradaID) {
		SQLiteDatabase db = this.getReadableDatabase();
		String selectQuery = SELECT + TABLE_PARADA + WHERE
				+ KEY_ID + " = " + paradaID;

		Cursor c = null;
		try{
			c = db.rawQuery(selectQuery, null);


			c.moveToFirst();

			Parada parada = setDatosParada(c);

			c.close();
			return parada;
		}catch(Exception e){
			return null;
		}

	}

	/**
	 * getting all paradas
	 * */
	public List<Parada> getAllParada() {
		List<Parada> paradas = new ArrayList<>();
		String selectQuery = SELECT + TABLE_PARADA;

		return setListaParadas(selectQuery, paradas);
	}

	/**
	 * getting all paradas
	 * */
	public List<Parada> getAllParadasFavoritos() {
		List<Parada> paradas = new ArrayList<>();

		String selectQuery = SELECT + TABLE_PARADA + " WHERE "+KEY_PARADA_FAVORITO+" == 1";

		return setListaParadas(selectQuery, paradas);
	}

	public Parada setDatosParada(Cursor c){
		Parada parada = new Parada();
		parada.setId(c.getInt(c.getColumnIndex(KEY_ID)));
		parada.setIdentifier(c.getInt(c.getColumnIndex(KEY_IDENTIFIER)));
		parada.setIdentifierLinea(c.getInt(c.getColumnIndex(KEY_PARADA_LINEA_IDENTIFIER)));
		parada.setNumParada(c.getInt(c.getColumnIndex(KEY_PARADA_NUMPARADA)));
		parada.setWgs64Lat((c.getDouble(c.getColumnIndex(KEY_PARADA_WGS64LAT))));
		parada.setWgs64Long((c.getDouble(c.getColumnIndex(KEY_PARADA_WGS64LONG))));
		parada.setCoordX((c.getDouble(c.getColumnIndex(KEY_PARADA_COORDX))));
		parada.setCoordY((c.getDouble(c.getColumnIndex(KEY_PARADA_COORDY))));
		parada.setNombre((c.getString(c.getColumnIndex(KEY_PARADA_NOMBRE))));
		parada.setIdLinea((c.getInt(c.getColumnIndex(KEY_PARADA_LINEA_ID))));
		parada.setFavorito((c.getInt(c.getColumnIndex(KEY_PARADA_FAVORITO))));
		return parada;
	}

	public List<Parada> setListaParadas(String selectQuery, List<Parada> paradas){
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = null;
		try{
			c = db.rawQuery(selectQuery, null);
		}catch(Exception e){
			return new ArrayList<>();
		}

		// looping through all rows and adding to list
		if (c.moveToFirst()) {
			do {
				paradas.add(setDatosParada(c));
			} while (c.moveToNext());
		}
		c.close();
		Collections.sort(paradas);
		return paradas;
	}

	public Color getColorByLinea(long lineaID){
		Linea linea = null;
		linea = getLinea(lineaID);
		if(linea == null){
			return null;
		}

		Color c = null;
		c = getColor(linea.getIdColor());

		return c;
	}

	public Linea getLineaByParada(long paradaID){
		Parada parada = null;
		parada = getParada(paradaID);
		if(parada == null)
			return null;
		Linea linea = null;
		linea = getLinea(parada.getIdLinea());

		return linea;

	}

	public List<Parada> getParadasByLinea(long lineaID){

		List<Parada> paradas = new ArrayList<>();
		String selectQuery = SELECT + TABLE_PARADA + WHERE + KEY_PARADA_LINEA_ID + " == " +lineaID;

		return setListaParadas(selectQuery, paradas);

	}



	// closing database
	public void closeDB() {
		SQLiteDatabase db = this.getReadableDatabase();
		if (db != null && db.isOpen())
			db.close();
	}



}
