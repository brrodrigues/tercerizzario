package rio.tercerizzario.appsupplier;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import rio.tercerizzario.appsupplier.modelo.Prestador;

public class PerfilPrestadorActivity extends AppCompatActivity {

    //private PerfilPrestadorHelper helper;
    private Prestador prestador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_prestador);
        //helper = new PerfilPrestadorHelper(this);
        prestador = new Prestador();
        //Intent intent = getIntent();
       //prestador = (Prestador) intent.getSerializableExtra("Prestador");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_perfil_prestador, menu);
        setTitle("Perfil do Usuário");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //new BuscaPrestadorTask(this,prestador).execute();
        //helper.setaPrestador(prestador);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_formulario_ok:
                Intent intentVaiParaCadastroPrestador = new Intent(PerfilPrestadorActivity.this, CadastroPrestadorActivity.class);
                intentVaiParaCadastroPrestador.putExtra("Prestador",prestador);
                startActivity(intentVaiParaCadastroPrestador);
                break;
        }

        return  super.onOptionsItemSelected(item);
    }
}
