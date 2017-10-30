package unican.es.grupo4_tus_santander.View.Main;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import unican.es.grupo4_tus_santander.R;


public class ListFuncionesMainAdapter extends ArrayAdapter {
    static final String[] funcionesMain = {"Mostrar Favoritos", "Mostrar Líneas", "Mostrar Paradas", "Mostrar Tarifas",  "Mostrar Restricciones","Mostrar Transportes Alternativos" ,"Ajustes" };
    Context context;

    public ListFuncionesMainAdapter(Context context){
        super(context, R.layout.custom_list_funciones_main_layout,funcionesMain);
        this.context = context;
    }// ListFuncionesMainAdapter


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View viewRow = layoutInflater.inflate(R.layout.custom_list_funciones_main_layout,null,true);

        TextView textViewNameFuncion = (TextView) viewRow.findViewById(R.id.textViewNameFuncion);
        ImageView imagenViewFuncion = (ImageView) viewRow.findViewById(R.id.ImagenFuncion);

        textViewNameFuncion.setText(funcionesMain[position]);

        switch(position){
            case 0:
                imagenViewFuncion.setImageResource(R.drawable.ic_favoritos);
                break;
            case 1:
                imagenViewFuncion.setImageResource(R.drawable.ic_lineas);
                break;
            case 2:
                imagenViewFuncion.setImageResource(R.drawable.ic_paradas);
                break;
            case 3:
                imagenViewFuncion.setImageResource(R.drawable.ic_tarifas);
                break;
            case 4:
                imagenViewFuncion.setImageResource(R.drawable.ic_restricciones);
                break;
            case 5:
                imagenViewFuncion.setImageResource(R.drawable.ic_servcicios_alternativos);
                break;
            case 6:
                imagenViewFuncion.setImageResource(R.drawable.ic_ajustes);
                break;
            default:
                break;
        }

        return viewRow;
    }
}// ListFuncionesMainAdapter
