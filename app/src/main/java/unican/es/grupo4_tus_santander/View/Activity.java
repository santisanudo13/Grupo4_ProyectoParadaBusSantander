package unican.es.grupo4_tus_santander.View;


import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import unican.es.grupo4_tus_santander.Presenter.MainPresenter;
import unican.es.grupo4_tus_santander.R;
import unican.es.grupo4_tus_santander.View.Main.ListFuncionesMainAdapter;

public interface Activity {
    void showProgress(boolean b, int i);

    void showList();
}