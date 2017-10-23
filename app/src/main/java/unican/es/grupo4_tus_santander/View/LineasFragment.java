package unican.es.grupo4_tus_santander.View;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import unican.es.grupo4_tus_santander.Presenter.ListLineasPresenter;
import unican.es.grupo4_tus_santander.R;
/**
 * A fragment representing a list of Items.
 */
public class LineasFragment extends ListFragment implements IListLineasView {

    private DataCommunication dataCommunication;
    private ProgressDialog dialog;
    private ListLineasPresenter listLineasPresenter;
    private ProgressDialog progress;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lineas_list, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.listLineasPresenter = new ListLineasPresenter(getContext(),this);
        this.dialog = new ProgressDialog(getContext());
        this.listLineasPresenter.start();
    }

    @Override
    public void onListItemClick(ListView listView, View view, int position, long id) {
        Log.d("pulsado", ""+position);
        //Haciendo uso de la interfaz DataCommunication podemos enviar los datos entre fragmentos
        //Ejemplo:
        //dataCommunication = (DataCommunication) getContext();
        //dataCommunication.setLineaIdentifier(datosBuses.getListaLineasBus().get(position).getIdentifier());
    }

    @Override
    public void showList(List<Linea> lineaList) {
        ListLineasAdapter listLineasAdapter = new ListLineasAdapter(getContext(), lineaList);
        getListView().setAdapter(listLineasAdapter);
    }

    /**
     * Este método cuando es llamado se encarga de mostrar un progressDialog
     * @param state si es true pone el progressDialog en la interfaz, si es false lo cancela
     */
    @Override
    public void showProgress (boolean state){
        if(state){
            progress = new ProgressDialog(this.getContext());
            progress.setMessage("Descargando datos");
            progress.show();
        }else{
            progress.dismiss();
        }
    }
    
    public void showToast(){
        Toast.makeText(this.getContext(), "Descargado correctamente", Toast.LENGTH_SHORT).show();
    }
}
