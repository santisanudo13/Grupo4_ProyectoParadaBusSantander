package unican.es.grupo4_tus_santander.Models.BaseDatos;

import java.util.List;

import unican.es.grupo4_tus_santander.Models.Color;
import unican.es.grupo4_tus_santander.Models.Linea;
import unican.es.grupo4_tus_santander.Models.Parada;

/**
 * Created by Tiago on 23/10/2017.
 */

public interface DataBaseInterface {
    public void insertColor(Color input);   //Anhade un nuevo color
    public void deleteColorById(long id);   //Elimina un color
    public Color selectColorByLinea(Linea input);   //Obtiene el color de una linea

    public void insertLinea(Linea input);
    public void deleteLinea(Linea input);
    public List<Linea> selectLineaByNumero(int numero);

    public void insertParada(Parada input);
    public void deleteParada(Parada input);
    public List<Parada> selectParadaByLinea(Linea input);
    public List<Parada> selectParadaByIdentificador(int id);


}
