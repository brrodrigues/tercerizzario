package rio.tercerizzario.appsupplier;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import rio.tercerizzario.appsupplier.converter.PrestadorConverter;
import rio.tercerizzario.appsupplier.modelo.Prestador;

public class CadastroPrestadorActivity extends AppCompatActivity {

    private CadastroPrestadorHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_prestador);
        helper = new CadastroPrestadorHelper(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_cadastro_prestador, menu);
        setTitle("Perfil do Usuário");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_formulario_ok:
                Prestador prestador = helper.pegaPrestador();

                new EnviaPrestadorTask(this, prestador).execute(this);

                finish();
                break;
        }

        return  super.onOptionsItemSelected(item);
    }
}