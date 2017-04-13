package rio.tercerizzario.appsupplier;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Prestador");

        Button botaoPerfil = (Button) findViewById(R.id.main_botao_perfil);
        botaoPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentVaiParaPerfilPrestador = new Intent(MainActivity.this, PerfilPrestadorActivity.class);
                startActivity(intentVaiParaPerfilPrestador);
            }
        });

    }

}
