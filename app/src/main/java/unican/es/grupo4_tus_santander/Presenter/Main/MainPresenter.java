package unican.es.grupo4_tus_santander.Presenter.Main;

import android.content.Context;

import unican.es.grupo4_tus_santander.Models.WebService.DataLoaders.RemoteFetch;
import unican.es.grupo4_tus_santander.View.Interfaz.ActivityInterface;


public class MainPresenter {
    private ActivityInterface activity;
    private Context context;
    private  RemoteFetch remoteFetch = new RemoteFetch();


    public MainPresenter(Context context, ActivityInterface activity){
        this.activity = activity;
        this.context = context;

    }// MainPresenter

    public void start(){
        activity.showList();
    }// start


}