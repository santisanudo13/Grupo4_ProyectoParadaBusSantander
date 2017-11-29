package unican.es.grupo4_tus_santander.view.estimaciones;

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

import unican.es.grupo4_tus_santander.R;
import unican.es.grupo4_tus_santander.models.pojos.Estimacion;

/**
 * Created by Luis on 21/11/17.
 */

public class ListEstimacionesAdapter extends ArrayAdapter {

    private List<Estimacion> estimaciones;
    private Context context;

    public ListEstimacionesAdapter(@NonNull Context context, List<Estimacion> estimaciones) {
        super(context, R.layout.custom_list_estimaciones_layout,estimaciones);
        this.estimaciones=estimaciones;
        this.context=context;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View viewRow = layoutInflater.inflate(R.layout.custom_list_estimaciones_layout,null,true);

        TextView linea = viewRow.findViewById(R.id.textViewNameLineaEstimada);
        TextView est1 = viewRow.findViewById(R.id.textViewNameEstimacion1);
        TextView est2 = viewRow.findViewById(R.id.textViewNameEstimacion2);

        String line = estimaciones.get(position).getNombreLinea();
        linea.setText("Línea "+ line);
        switch (line){
            case "1":
                linea.setTextColor(Color.rgb(255,0,0));
                break;
            case "2":
                linea.setTextColor(Color.rgb(171,68,206));
                break;
            case "3":
                linea.setTextColor(Color.rgb(253,205,47));
                break;
            case "4":
                linea.setTextColor(Color.rgb(48,180,214));
                break;
            case "5C1":
                linea.setTextColor(Color.rgb(150,150,150));
                break;
            case "5C2":
                linea.setTextColor(Color.rgb(150,150,150));
                break;
            case "6C1":
                linea.setTextColor(Color.rgb(15,127,52));
                break;
            case "6C2":
                linea.setTextColor(Color.rgb(15,127,52));
                break;
            case "7C1":
                linea.setTextColor(Color.rgb(244,98,38));
                break;
            case "7C2":
                linea.setTextColor(Color.rgb(244,98,38));
                break;
            case "11":
                linea.setTextColor(Color.rgb(2,23,91));
                break;
            case "12":
                linea.setTextColor(Color.rgb(164,211,99));
                break;
            case "13":
                linea.setTextColor(Color.rgb(144,129,176));
                break;
            case "14":
                linea.setTextColor(Color.rgb(14,105,175));
                break;
            case "16":
                linea.setTextColor(Color.rgb(98,24,54));
                break;
            case "17":
                linea.setTextColor(Color.rgb(246,128,132));
                break;
            case "18":
                linea.setTextColor(Color.rgb(177,232,224));
                break;
            case "19":
                linea.setTextColor(Color.rgb(18,132,147));
                break;
            case "20":
                linea.setTextColor(Color.rgb(136,248,170));
                break;
            case "21":
                linea.setTextColor(Color.rgb(163,211,98));
                break;
            case "23":
                linea.setTextColor(Color.rgb(202,202,202));
                break;
            default:
                linea.setTextColor(Color.rgb(0,0,0));
                break;
        }


        String e1=estimaciones.get(position).getEstimacionA();
        int k1= Integer.parseInt(e1);
        k1=k1/60;
        if(k1==0){
                est1.setText("En parada");
        }else{
                est1.setText(k1+"");
        }

        String e2=estimaciones.get(position).getEstimacionB();

        int k2= Integer.parseInt(estimaciones.get(position).getEstimacionB());
        k2=k2/60;
        est2.setText(k2+"");

        return viewRow;
    }
}
