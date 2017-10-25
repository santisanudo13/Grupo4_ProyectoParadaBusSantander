package unican.es.grupo4_tus_santander.Views;

import java.util.List;

import unican.es.grupo4_tus_santander.Models.BaseDatos.DBModel.Parada;


/**
 * Created by alejandro on 11/10/17.
 */

public interface IListParadasView {
    void showList(List<Parada> paradaList);
    void showProgress(boolean state);
    void showToast();
}//IListParadasView