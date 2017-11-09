package unican.es.grupo4_tus_santander.Presenter.Lineas.AsyncTasks;

import android.os.AsyncTask;

import unican.es.grupo4_tus_santander.Presenter.Lineas.RecargaBaseDatosLineas;
import unican.es.grupo4_tus_santander.Presenter.Main.RecargaBaseDatosMenu;

/**
 * Created by Tiago on 30/10/17.
 */


public class GetSaveDataIntoDataBase extends AsyncTask<Void, Void, Boolean> {

    private RecargaBaseDatosLineas refresh;

    public GetSaveDataIntoDataBase(RecargaBaseDatosLineas refresh) {
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
        refresh.getActivity().start();

        if(refresh.getListener() != null)
            refresh.getListener().onComplete();
    }
}

