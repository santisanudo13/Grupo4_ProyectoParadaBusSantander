package unican.es.grupo4_tus_santander.Models.BaseDatos.helper;


import java.util.List;

import unican.es.grupo4_tus_santander.Models.Pojos.*;

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
	public List<Parada> getAllParadasFavoritos();


	public Color getColorByLinea(long linea_id);

    public Linea getLineaByParada(long parada_id);
    public List<Parada> getParadasByLinea(long linea_id);

	public void closeDB();

}
