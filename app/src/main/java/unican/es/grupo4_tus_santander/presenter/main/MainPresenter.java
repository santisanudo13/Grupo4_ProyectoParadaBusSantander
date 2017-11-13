package unican.es.grupo4_tus_santander.presenter.main;

import android.content.Context;

import unican.es.grupo4_tus_santander.view.main.MainActivity;


public class MainPresenter {
    private MainActivity activity;
    private Context context;


    public MainPresenter(Context context, MainActivity activity){
        this.activity = activity;
        this.context = context;

    }// MainPresenter

    public void start(){
        activity.showList();
    }// start


    public void recargarDatos() {
        RecargaBaseDatosMenu refresh;
        refresh = new RecargaBaseDatosMenu(context, activity);
        refresh.start();
    }
}