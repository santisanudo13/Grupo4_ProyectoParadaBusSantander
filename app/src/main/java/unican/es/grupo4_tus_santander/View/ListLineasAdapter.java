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

import unican.es.grupo4_tus_santander.Model.*;
import unican.es.grupo4_tus_santander.R;

/**
 * Created by alejandro on 10/08/17.
 * //http://www.viralandroid.com/2016/04/custom-android-listview-example.html
 */

public class ListLineasAdapter extends ArrayAdapter {
    List<Linea> lineasBus;
    Context context;

    public ListLineasAdapter (Context context, List<Linea> lineasBus){
        super(context, R.layout.custom_list_lineas_layout,lineasBus);
        this.context = context;
        this.lineasBus = lineasBus;
    }// ListLineasAdapter


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View viewRow = layoutInflater.inflate(R.layout.custom_list_lineas_layout,null,true);
        TextView textViewName = (TextView) viewRow.findViewById(R.id.textViewName);
        TextView textViewNumero = (TextView) viewRow.findViewById(R.id.textViewNumero);
        textViewName.setText(lineasBus.get(position).getName().trim());
        textViewNumero.setText(lineasBus.get(position).getNumero().trim());
        switch (textViewNumero.getText().toString()){
            case "1":
                textViewNumero.setTextColor(Color.rgb(255,0,0));
                break;
            case "2":
                textViewNumero.setTextColor(Color.rgb(171,68,206));
                break;
            case "3":
                textViewNumero.setTextColor(Color.rgb(253,205,47));
                break;
            case "4":
                textViewNumero.setTextColor(Color.rgb(48,180,214));
                break;
            case "5C1":
                textViewNumero.setTextColor(Color.rgb(150,150,150));
                break;
            case "5C2":
                textViewNumero.setTextColor(Color.rgb(150,150,150));
                break;
            case "6C1":
                textViewNumero.setTextColor(Color.rgb(15,127,52));
                break;
            case "6C2":
                textViewNumero.setTextColor(Color.rgb(15,127,52));
                break;
            case "7C1":
                textViewNumero.setTextColor(Color.rgb(244,98,38));
                break;
            case "7C2":
                textViewNumero.setTextColor(Color.rgb(244,98,38));
                break;
            case "11":
                textViewNumero.setTextColor(Color.rgb(2,23,91));
                break;
            case "12":
                textViewNumero.setTextColor(Color.rgb(164,211,99));
                break;
            case "13":
                textViewNumero.setTextColor(Color.rgb(144,129,176));
                break;
            case "14":
                textViewNumero.setTextColor(Color.rgb(14,105,175));
                break;
            case "16":
                textViewNumero.setTextColor(Color.rgb(98,24,54));
                break;
            case "17":
                textViewNumero.setTextColor(Color.rgb(246,128,132));
                break;
            case "18":
                textViewNumero.setTextColor(Color.rgb(177,232,224));
                break;
            case "19":
                textViewNumero.setTextColor(Color.rgb(18,132,147));
                break;
            case "20":
                textViewNumero.setTextColor(Color.rgb(136,248,170));
                break;
            case "21":
                textViewNumero.setTextColor(Color.rgb(163,211,98));
                break;
            case "23":
                textViewNumero.setTextColor(Color.rgb(202,202,202));
                break;
            default:
                textViewNumero.setTextColor(Color.rgb(0,0,0));
                break
        }
        return viewRow;
    }
}// ListLineasAdapter
