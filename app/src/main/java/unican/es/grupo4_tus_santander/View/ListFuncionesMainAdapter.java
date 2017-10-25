package unican.es.grupo4_tus_santander.View;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import unican.es.grupo4_tus_santander.R;


public class ListFuncionesMainAdapter extends ArrayAdapter {
    static final String[] funcionesMain = {"Mostrar Favoritos", "Mostrar Líneas", "Mostrar Paradas", "Mostrar Tarifas",  "Mostrar Restricciones", "Ajustes" };
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

        textViewNameFuncion.setText( funcionesMain[position]);

        return viewRow;
    }
}// ListFuncionesMainAdapter
