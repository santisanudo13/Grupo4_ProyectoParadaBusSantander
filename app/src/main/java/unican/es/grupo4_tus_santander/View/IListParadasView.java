package unican.es.grupo4_tus_santander.View;

import java.util.List;

import unican.es.grupo4_tus_santander.Models.Pojos.Linea;
import unican.es.grupo4_tus_santander.Models.Pojos.Parada;


/**
 * Created by Asier on 25/10/17.
 */

public interface IListParadasView {
    void showList(List<Parada> paradaList, List<Linea> lineasBus);
    void showProgress(boolean state, int tipo);
}//IListParadasView