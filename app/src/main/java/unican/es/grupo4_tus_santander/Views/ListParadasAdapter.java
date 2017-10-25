package unican.es.grupo4_tus_santander.Views;

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
 * Created by alejandro on 10/08/17.
 * //http://www.viralandroid.com/2016/04/custom-android-listview-example.html
 */

public class ListParadasAdapter extends ArrayAdapter {
    List<Parada> paradasBus;
    Context context;

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
        paradasBus.get(position);
        //int a=lineasBus.get(position).
        //int r=lineasBus.get(position).getColor().getRed();
        //int g=lineasBus.get(position).getColor().getGreen();
        //int b=lineasBus.get(position).getColor().getBlue();
        //textViewNumero.setTextColor(Color.argb(a,r,g,b));
        return viewRow;
    }
}// ListParadasAdapter
