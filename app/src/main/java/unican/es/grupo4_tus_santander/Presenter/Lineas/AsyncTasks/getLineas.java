package unican.es.grupo4_tus_santander.Presenter.Lineas.AsyncTasks;

import android.os.AsyncTask;

import unican.es.grupo4_tus_santander.Presenter.Lineas.ListLineasPresenter;

/**
 * Created by Luis on 30/10/17.
 */

public class getLineas extends AsyncTask<ListLineasPresenter, Void, Boolean> {
    ListLineasPresenter p;
    @Override
    protected Boolean doInBackground(ListLineasPresenter... v) {
        this.p=v[0];
        return p.obtenLineas();
    }

    @Override
    protected void onPostExecute(Boolean result){
            p.continua(result);

    }
}