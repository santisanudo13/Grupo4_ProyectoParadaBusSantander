package unican.es.grupo4_tus_santander.presenter.paradas.asynctasks;

import android.os.AsyncTask;

import unican.es.grupo4_tus_santander.presenter.paradas.RecargaBaseDatosParadas;

/**
 * Created by Tiago on 30/10/17.
 */


public class GetDataServicioParadas extends AsyncTask<Void, Void, Boolean> {

    private RecargaBaseDatosParadas refresh;

    public GetDataServicioParadas(RecargaBaseDatosParadas refresh) {
        this.refresh = refresh;
    }

    @Override
    protected Boolean doInBackground(Void... v) {
        return refresh.obtenData();
    }

    @Override
    protected void onPostExecute(Boolean result) {
        if (result) {
            new GetSaveDataIntoDataBaseParadas(refresh).execute();
        } else {
            refresh.getActivity().showProgress(false, -1);
            if(refresh.getListener() != null)
                refresh.getListener().onComplete();
        }
    }


}


