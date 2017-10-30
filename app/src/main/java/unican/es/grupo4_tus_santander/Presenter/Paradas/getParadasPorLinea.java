package unican.es.grupo4_tus_santander.Presenter.Paradas;

import android.os.AsyncTask;

/**
 * Created by ASIER on 30/10/2017.
 */


public class getParadasPorLinea extends AsyncTask<ListParadasPresenter, Void, Boolean> {
    private ListParadasPresenter p;
    @Override
    protected Boolean doInBackground(ListParadasPresenter... v) {
        this.p=v[0];
        return p.obtenParadasPorLinea();
    }

    @Override
    protected void onPostExecute(Boolean result){
        if(result){
            p.continua(result);
        }else{
            p.showToastEmptyParadas();
        }
    }
}
