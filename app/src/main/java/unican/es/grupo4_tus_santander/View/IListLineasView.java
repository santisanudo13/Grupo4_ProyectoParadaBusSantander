package unican.es.grupo4_tus_santander.View;

import java.util.List;

/**
 * Created by alejandro on 11/10/17.
 */

public interface IListLineasView {
    void showList(List<Linea> lineaList);
    void showProgress(boolean state);
    void showToast();
}//IListLineasView
