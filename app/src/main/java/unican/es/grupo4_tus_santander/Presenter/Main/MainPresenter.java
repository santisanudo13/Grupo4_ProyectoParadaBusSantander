package unican.es.grupo4_tus_santander.Presenter.Main;

import android.content.Context;

import unican.es.grupo4_tus_santander.View.Main.MainActivity;


public class MainPresenter {
    private MainActivity activity;
    private Context context;
    private RecargaBaseDatosMenu refresh;


    public MainPresenter(Context context, MainActivity activity){
        this.activity = activity;
        this.context = context;

    }// MainPresenter

    public void start(){
        activity.showList();
    }// start


    public void recargarDatos() {
        refresh = new RecargaBaseDatosMenu(context, activity);
        refresh.start();
    }
}