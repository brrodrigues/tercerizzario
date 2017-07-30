package rio.tercerizzario.appsupplier;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import rio.tercerizzario.appsupplier.converter.PrestadorConverter;
import rio.tercerizzario.appsupplier.modelo.Prestador;

public class CadastroPrestadorActivity extends AppCompatActivity {

    private CadastroPrestadorHelper helper;
    private Prestador prestador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_prestador);
        helper = new CadastroPrestadorHelper(this);
        Intent intent = getIntent();
        prestador = (Prestador) intent.getSerializableExtra("Prestador");
        helper.setaPrestador(prestador);

        Button botaoEndereco = (Button) findViewById(R.id.cadastro_prestador_botao_endereco);
        botaoEndereco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentionVaiParaOMapa = new Intent(CadastroPrestadorActivity.this,MapaActivity.class);
                startActivity(intentionVaiParaOMapa);
            }
        });

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
                prestador = helper.pegaPrestador(prestador);
                //new EnviaPrestadorTask(this, prestador,helper).execute(this);
                finish();
                break;
        }

        return  super.onOptionsItemSelected(item);
    }
}