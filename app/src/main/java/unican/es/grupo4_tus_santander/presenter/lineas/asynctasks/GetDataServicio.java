package unican.es.grupo4_tus_santander.presenter.lineas.asynctasks;

import android.os.AsyncTask;

import unican.es.grupo4_tus_santander.presenter.lineas.RecargaBaseDatosLineas;

/**
 * Created by Tiago on 30/10/17.
 */


public class GetDataServicio extends AsyncTask<Void, Void, Boolean> {

    private RecargaBaseDatosLineas refreshLineas;

    public GetDataServicio(RecargaBaseDatosLineas refresh) {
        this.refreshLineas = refresh;
    }

    @Override
    protected Boolean doInBackground(Void... v) {
        return refreshLineas.obtenData();
    }

    @Override
    protected void onPostExecute(Boolean result) {
        if (result) {
            new GetSaveDataIntoDataBase(refreshLineas).execute();
        } else {
            refreshLineas.getActivity().showProgress(false, -1);
            if(refreshLineas.getListener() != null)
                refreshLineas.getListener().onComplete();
        }
    }


}


