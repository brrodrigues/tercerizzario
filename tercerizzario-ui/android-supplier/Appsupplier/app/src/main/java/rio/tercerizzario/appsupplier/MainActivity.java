package rio.tercerizzario.appsupplier;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import rio.tercerizzario.appsupplier.modelo.Prestador;

public class MainActivity extends AppCompatActivity {

    private Prestador prestador;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Prestador");
        //prestador = new Prestador();
        //new BuscaPrestadorTask(this,prestador).execute();
        Toast.makeText(this,"Login efetuado com sucesso",Toast.LENGTH_SHORT).show();

        Button botaoPerfil = (Button) findViewById(R.id.main_botao_perfil);
        botaoPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentVaiParaPerfilPrestador = new Intent(MainActivity.this, PerfilPrestadorActivity.class);
                //intentVaiParaPerfilPrestador.putExtra("Prestador",prestador);
                startActivity(intentVaiParaPerfilPrestador);
            }
        });

    }

}
