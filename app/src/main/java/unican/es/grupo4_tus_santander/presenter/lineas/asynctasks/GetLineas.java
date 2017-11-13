package unican.es.grupo4_tus_santander.presenter.lineas.asynctasks;

import android.os.AsyncTask;

import unican.es.grupo4_tus_santander.presenter.lineas.ListLineasPresenter;

/**
 * Created by Luis on 30/10/17.
 */

public class GetLineas extends AsyncTask<ListLineasPresenter, Void, Boolean> {
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