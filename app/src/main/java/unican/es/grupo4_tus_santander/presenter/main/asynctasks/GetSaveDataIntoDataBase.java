package unican.es.grupo4_tus_santander.presenter.main.asynctasks;

import android.os.AsyncTask;

import unican.es.grupo4_tus_santander.presenter.main.RecargaBaseDatosMenu;

/**
 * Created by Tiago on 30/10/17.
 */


public class GetSaveDataIntoDataBase extends AsyncTask<Void, Void, Boolean> {

    private RecargaBaseDatosMenu refresh;

    public GetSaveDataIntoDataBase(RecargaBaseDatosMenu refresh) {
        this.refresh = refresh;
    }

    @Override
    protected Boolean doInBackground(Void... v) {
        refresh.guardaDataEnBaseDatos();
        return true;
    }

    @Override
    protected void onPostExecute(Boolean result) {
        refresh.getActivity().showProgress(false, 1);
        if(refresh.getListener() != null)
            refresh.getListener().onComplete();
    }
}

