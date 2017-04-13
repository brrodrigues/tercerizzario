package rio.tercerizzario.appsupplier;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import rio.tercerizzario.appsupplier.modelo.Prestador;

public class PerfilPrestadorActivity extends AppCompatActivity {

    private PerfilPrestadorHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_prestador);
        helper = new PerfilPrestadorHelper(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_cadastro_prestador, menu);
        setTitle("Perfil do Usuário");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Prestador prestador = new Prestador();
        new BuscaPrestadorTask(this,prestador,helper).execute();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_formulario_ok:
                Intent intentVaiParaCadastroPrestador = new Intent(PerfilPrestadorActivity.this, CadastroPrestadorActivity.class);
                startActivity(intentVaiParaCadastroPrestador);
                break;
        }

        return  super.onOptionsItemSelected(item);
    }
}
