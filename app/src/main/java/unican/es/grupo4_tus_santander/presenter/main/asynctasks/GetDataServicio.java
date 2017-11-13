package unican.es.grupo4_tus_santander.presenter.main.asynctasks;

import android.os.AsyncTask;

import unican.es.grupo4_tus_santander.presenter.main.RecargaBaseDatosMenu;

/**
 * Created by Tiago on 30/10/17.
 */


public class GetDataServicio extends AsyncTask<Void, Void, Boolean> {

    private RecargaBaseDatosMenu refreshMenu;

    public GetDataServicio(RecargaBaseDatosMenu refresh) {
        this.refreshMenu = refresh;
    }

    @Override
    protected Boolean doInBackground(Void... v) {
        return refreshMenu.obtenData();
    }

    @Override
    protected void onPostExecute(Boolean result) {
        if (result) {
            new GetSaveDataIntoDataBase(refreshMenu).execute();
        } else {
            refreshMenu.getActivity().showProgress(false, -1);
            if(refreshMenu.getListener() != null)
                refreshMenu.getListener().onComplete();
        }
    }


}


