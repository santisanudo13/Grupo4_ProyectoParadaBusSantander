package unican.es.grupo4_tus_santander.View;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;


import unican.es.grupo4_tus_santander.Models.BaseDatos.helper.DatabaseHelper;
import unican.es.grupo4_tus_santander.Models.Pojos.Linea;
import unican.es.grupo4_tus_santander.Models.Pojos.Parada;
import unican.es.grupo4_tus_santander.R;

/**
 * Created by Asier on 25/10/17.
 */

public class ListParadasAdapter extends ArrayAdapter {
    private List<Parada> paradasBus;
    private List<Linea> lineasBus;
    private Context context;
    private DatabaseHelper ld;

    public ListParadasAdapter (Context context, List<Parada> paradasBus, List<Linea> lineasBus){
        super(context, R.layout.custom_list_paradas_layout,paradasBus);
        this.context = context;
        this.paradasBus = paradasBus;
        this.lineasBus = lineasBus;
        this.ld = new DatabaseHelper(this.context,1);
    }// ListLineasAdapter


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View viewRow = layoutInflater.inflate(R.layout.custom_list_paradas_layout,null,true);
        TextView textViewName = (TextView) viewRow.findViewById(R.id.textViewName);
        TextView textViewNumero = (TextView) viewRow.findViewById(R.id.textViewNumero);
        textViewName.setText(paradasBus.get(position).getNombre().trim());
        textViewNumero.setText(lineasBus.get(position).getNumero()+lineasBus.get(position).getName()+"");

        unican.es.grupo4_tus_santander.Models.Pojos.Color a =ld.getColorByLinea(paradasBus.get(position).getIdentifierLinea());
        textViewNumero.setTextColor(Color.argb(a.getAlpha(),a.getRed(),a.getGreen(),a.getBlue()));

        return viewRow;
    }
}// ListParadasAdapter