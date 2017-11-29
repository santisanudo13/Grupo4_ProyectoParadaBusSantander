package unican.es.grupo4_tus_santander.presenter.estimaciones.asynctasks;

import android.os.AsyncTask;

import unican.es.grupo4_tus_santander.presenter.estimaciones.EstimacionesPresenter;

/**
 * Created by Luis on 21/11/17.
 */

public class GetEstimaciones extends AsyncTask<EstimacionesPresenter, Void, Boolean> {

    private EstimacionesPresenter presenter;

    @Override
    protected Boolean doInBackground(EstimacionesPresenter... in) {
        presenter = in[0];
        return in[0].obtenData();
    }

    @Override
    protected void onPostExecute(Boolean result){
            presenter.ordena(result);
    }
}
