package unican.es.grupo4_tus_santander.View;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;





public class ListFuncionesMainAdapter extends ArrayAdapter {
    static final String[] funcionesMain = {"asdasd" };
    Context context;

    public ListFuncionesMainAdapter(Context context){
        super(context, R.layout.custom_list_lineas_layout,funcionesMain);
        this.context = context;
    }// ListFuncionesMainAdapter


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View viewRow = layoutInflater.inflate(R.layout.custom_list_lineas_layout,null,true);
        TextView textViewName = (TextView) viewRow.findViewById(R.id.textViewName);
        TextView textViewNumero = (TextView) viewRow.findViewById(R.id.textViewNumero);
        textViewName.setText(lineasBus.get(position).getNombre().trim());
        textViewNumero.setText( lineasBus.get(position).getNumero()+"");
        lineasBus.get(position);
        //int a=lineasBus.get(position).
        //int r=lineasBus.get(position).getColor().getRed();
        //int g=lineasBus.get(position).getColor().getGreen();
        //int b=lineasBus.get(position).getColor().getBlue();
        //textViewNumero.setTextColor(Color.argb(a,r,g,b));
        return viewRow;
    }
}// ListFuncionesMainAdapter
