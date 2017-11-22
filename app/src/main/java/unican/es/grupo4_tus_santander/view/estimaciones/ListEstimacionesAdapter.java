package unican.es.grupo4_tus_santander.view.estimaciones;

import android.content.Context;
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

        linea.setText(estimaciones.get(position).getNombreLinea());
        String e1=estimaciones.get(position).getEstimacionA();
        if(e1.equals("")|| e1==null) {
            est1.setText("En parada");
        }else{
            int k1= Integer.parseInt(e1);
            k1=k1/60;
            est1.setText(k1+"");
        }

        String e2=estimaciones.get(position).getEstimacionB();
        if(e2.equals("")|| e2==null) {
            est2.setText("no hay");
        }else{
            int k1= Integer.parseInt(estimaciones.get(position).getEstimacionB());
            k1=k1/60;
            est2.setText(k1+"");
        }



        return viewRow;
    }
}
