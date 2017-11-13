package unican.es.grupo4_tus_santander.presenter.paradas.asynctasks;

import android.os.AsyncTask;

import unican.es.grupo4_tus_santander.presenter.paradas.ListParadasPresenter;

/**
 * Created by Luis on 30/10/17.
 */

public class GetParadas extends AsyncTask<ListParadasPresenter, Void, Boolean> {
    ListParadasPresenter presenter;
    @Override
    protected Boolean doInBackground(ListParadasPresenter... v) {
        this.presenter=v[0];
        return presenter.obtenParadas();
    }

    @Override
    protected void onPostExecute(Boolean result){
        presenter.continua(result);

    }
}