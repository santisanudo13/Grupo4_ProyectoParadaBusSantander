package unican.es.grupo4_tus_santander.presenter.lineas.asynctasks;

import android.os.AsyncTask;

import unican.es.grupo4_tus_santander.presenter.lineas.RecargaBaseDatosLineas;

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

        return refresh.guardaDataEnBaseDatos();
    }

    @Override
    protected void onPostExecute(Boolean result) {
        if(result) {
            refresh.getActivity().showProgress(false, 1);
            refresh.getActivity().start();

            if (refresh.getListener() != null)
                refresh.getListener().onComplete();
        }else{
            refresh.getActivity().showProgress(false, -1);


            if (refresh.getListener() != null)
                refresh.getListener().onComplete();
        }
    }
}

