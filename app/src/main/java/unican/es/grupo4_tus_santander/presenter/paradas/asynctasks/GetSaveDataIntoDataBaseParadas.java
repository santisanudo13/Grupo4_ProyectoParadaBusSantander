package unican.es.grupo4_tus_santander.presenter.paradas.asynctasks;

import android.os.AsyncTask;

import unican.es.grupo4_tus_santander.presenter.paradas.RecargaBaseDatosParadas;

/**
 * Created by Tiago on 30/10/17.
 */


public class GetSaveDataIntoDataBaseParadas extends AsyncTask<Void, Void, Boolean> {

    private RecargaBaseDatosParadas refresh;

    public GetSaveDataIntoDataBaseParadas(RecargaBaseDatosParadas refresh) {
        this.refresh = refresh;
    }

    @Override
    protected Boolean doInBackground(Void... v) {
        refresh.guardaDataEnBaseDatos();
        return true;
    }

    @Override
    protected void onPostExecute(Boolean result) {
        if(refresh.getActivity() != null) {
            refresh.getPresenter().continua(result);
            refresh.getActivity().showProgress(!result, 1);
        }
        if(refresh.getListener() != null)
            refresh.getListener().onComplete();
    }
}