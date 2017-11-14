package unican.es.grupo4_tus_santander.models.basedatos.helper;


import java.util.List;

import unican.es.grupo4_tus_santander.models.pojos.*;

public interface DatabaseInterface {




	public long createColor(Color color);
	public Color getColor(long colorID);
	public List<Color> getAllColor();


	public long createLinea(Linea linea, long colorID);
	public Linea getLinea(long lineaID);
	public List<Linea> getAllLinea();


	public long createParada(Parada parada, long lineaID);
	public Parada getParada(long paradaID);
	public List<Parada> getAllParada();
	public List<Parada> getAllParadasFavoritos();


	public Color getColorByLinea(long lineaID);

    public Linea getLineaByParada(long paradaID);
    public List<Parada> getParadasByLinea(long lineaID);

	public void closeDB();

}
