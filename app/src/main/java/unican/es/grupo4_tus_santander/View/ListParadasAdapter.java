package unican.es.grupo4_tus_santander.View;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import unican.es.grupo4_tus_santander.Models.BaseDatos.DBModel.Parada;
import unican.es.grupo4_tus_santander.R;

/**
 * Created by Asier on 25/10/17.
 */

public class ListParadasAdapter extends ArrayAdapter {
    private List<Parada> paradasBus;
    private Context context;

    public ListParadasAdapter (Context context, List<Parada> paradasBus){
        super(context, R.layout.custom_list_paradas_layout,paradasBus);
        this.context = context;
        this.paradasBus = paradasBus;
    }// ListLineasAdapter


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View viewRow = layoutInflater.inflate(R.layout.custom_list_paradas_layout,null,true);
        TextView textViewName = (TextView) viewRow.findViewById(R.id.textViewName);
        TextView textViewNumero = (TextView) viewRow.findViewById(R.id.textViewNumero);
        textViewName.setText(paradasBus.get(position).getNombreParada().trim());
        textViewNumero.setText( paradasBus.get(position).getNumeroParada()+"");

        return viewRow;
    }
}// ListParadasAdapter
