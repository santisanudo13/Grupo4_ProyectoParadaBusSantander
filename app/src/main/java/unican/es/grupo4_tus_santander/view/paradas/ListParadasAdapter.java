package unican.es.grupo4_tus_santander.view.paradas;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


import unican.es.grupo4_tus_santander.models.pojos.Parada;
import unican.es.grupo4_tus_santander.R;

/**
 * Created by Asier on 25/10/17.
 */

public class ListParadasAdapter extends ArrayAdapter {
    private List<Parada> paradasBus;
    private Context context;

    public ListParadasAdapter(Context context, List<Parada> paradasBus){
        super(context, R.layout.custom_list_paradas_layout,paradasBus);
        this.context = context;
        this.paradasBus = paradasBus;

    }// ListLineasAdapter


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View viewRow = layoutInflater.inflate(R.layout.custom_list_paradas_layout,null,true);

        TextView textViewName = viewRow.findViewById(R.id.textViewName);
        TextView textViewNumero = viewRow.findViewById(R.id.textViewNumero);
        ImageView imagenViewFavorito = viewRow.findViewById(R.id.ImagenFavorito);

        String nombre = paradasBus.get(position).getNombre();
        String numero = "" + Integer.toString(paradasBus.get(position).getNumParada());
        int favorito = paradasBus.get(position).getFavorito();
        if(favorito == 0) {
            imagenViewFavorito.setImageResource(R.drawable.ic_star_empty);
        }
        if(favorito == 1) {
            imagenViewFavorito.setImageResource(R.drawable.ic_star_full);
        }
        textViewName.setText(nombre);
        textViewNumero.setText(numero);

        return viewRow;
    }
}// ListParadasAdapter