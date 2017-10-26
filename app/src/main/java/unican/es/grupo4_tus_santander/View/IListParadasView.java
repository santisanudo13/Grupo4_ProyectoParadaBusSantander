package unican.es.grupo4_tus_santander.View;

import java.util.List;

import unican.es.grupo4_tus_santander.Models.BaseDatos.DBModel.Parada;


/**
 * Created by Asier on 25/10/17.
 */

public interface IListParadasView {
    void showList(List<Parada> paradaList);
    void showProgress(boolean state);
    void showToast();
}//IListParadasView