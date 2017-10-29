package unican.es.grupo4_tus_santander.View.Lineas;

import android.graphics.Color;
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


import unican.es.grupo4_tus_santander.Models.BaseDatos.helper.DatabaseHelper;
import unican.es.grupo4_tus_santander.Models.Pojos.Linea;
import unican.es.grupo4_tus_santander.R;

/**
 * Created by alejandro on 10/08/17.
 * //http://www.viralandroid.com/2016/04/custom-android-listview-example.html
 */

public class ListLineasAdapter extends ArrayAdapter {
    List<Linea> lineasBus;
    Context context;
    DatabaseHelper ld;

    public ListLineasAdapter (Context context, List<Linea> lineasBus){
        super(context, R.layout.custom_list_lineas_layout,lineasBus);
        this.context = context;
        this.lineasBus = lineasBus;
        this.ld = new DatabaseHelper(this.context,1);
    }// ListLineasAdapter


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View viewRow = layoutInflater.inflate(R.layout.custom_list_lineas_layout,null,true);
        TextView textViewName = (TextView) viewRow.findViewById(R.id.textViewName);
        TextView textViewNumero = (TextView) viewRow.findViewById(R.id.textViewNumero);
        ImageView imageView = (ImageView) viewRow.findViewById(R.id.ImagenLinea);
        textViewName.setText(lineasBus.get(position).getName());
        textViewNumero.setText( lineasBus.get(position).getNumero()+"");
        unican.es.grupo4_tus_santander.Models.Pojos.Color a =ld.getColorByLinea(lineasBus.get(position).getId());
        textViewNumero.setTextColor(Color.argb(a.getAlpha(),a.getRed(),a.getGreen(),a.getBlue()));

        switch (textViewNumero.getText().toString()){
            case "1":
                imageView.setImageResource(R.drawable.linea1_icono);
                break;
            case "2":
                imageView.setImageResource(R.drawable.linea2_icono);
                break;
            case "3":
                imageView.setImageResource(R.drawable.linea3_icono);
                break;
            case "4":
                imageView.setImageResource(R.drawable.linea4_icono);
                break;
            case "5C1":
                imageView.setImageResource(R.drawable.linea5_icono);
                break;
            case "5C2":
                imageView.setImageResource(R.drawable.linea5_icono);
                break;
            case "6C1":
                imageView.setImageResource(R.drawable.linea6_icono);
                break;
            case "6C2":
                imageView.setImageResource(R.drawable.linea6_icono);
                break;
            case "7C1":
                imageView.setImageResource(R.drawable.linea7_icono);
                break;
            case "7C2":
                imageView.setImageResource(R.drawable.linea7_icono);
                break;
            case "11":
                imageView.setImageResource(R.drawable.linea11_icono);
                break;
            case "12":
                imageView.setImageResource(R.drawable.linea12_icono);
                break;
            case "13":
                imageView.setImageResource(R.drawable.linea13_icono);
                break;
            case "14":
                imageView.setImageResource(R.drawable.linea14_icono);
                break;
            case "16":
                imageView.setImageResource(R.drawable.linea16_icono);
                break;
            case "17":
                imageView.setImageResource(R.drawable.linea17_icono);
                break;
            case "18":
                imageView.setImageResource(R.drawable.linea18_icono);
                break;
            case "19":
                imageView.setImageResource(R.drawable.linea19_icono);
                break;
            case "20":
                imageView.setImageResource(R.drawable.linea20_icono);
                break;
            case "21":
                imageView.setImageResource(R.drawable.linea21_icono);
                break;
            case "23":
                imageView.setImageResource(R.drawable.linea23_icono);
                break;
            case "N1":
                imageView.setImageResource(R.drawable.linean1_icono);
                break;
            case "N2":
                imageView.setImageResource(R.drawable.linean2_icono);
                break;
            case "N3":
                imageView.setImageResource(R.drawable.linean3_icono);
                break;
            default:
                break;
        }
        return viewRow;
    }
}// ListLineasAdapter
