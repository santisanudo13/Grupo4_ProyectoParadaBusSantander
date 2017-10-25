package unican.es.quiniela.Models.BaseDatos.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import unican.es.quiniela.Models.BaseDatos.DBModel.Color;
import unican.es.quiniela.Models.BaseDatos.DBModel.Linea;
import unican.es.quiniela.Models.BaseDatos.DBModel.Parada;

public interface DatabaseInterface {




	public long createColor(Color color);
	public Color getColor(long color_id);
	public List<Color> getAllColor();


	public long createLinea(Linea linea, long color_id);
	public Linea getLinea(long linea_id);
	public List<Linea> getAllLinea();


	public long createParada(Parada parada, long id_linea);
	public Parada getParada(long parada_id);
	public List<Parada> getAllParada();

	public Color getColorByLinea(long linea_id);

    public Linea getLineaByParada(long parada_id);
    public List<Parada> getParadasByLinea(long linea_id);

	public void closeDB();

}