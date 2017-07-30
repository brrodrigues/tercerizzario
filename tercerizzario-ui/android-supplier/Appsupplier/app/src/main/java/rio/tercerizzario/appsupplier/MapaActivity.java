package rio.tercerizzario.appsupplier;

import android.location.Address;
import android.location.Geocoder;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class MapaActivity extends AppCompatActivity implements MapaFragment.OnItemSelectedListener {

    private GoogleMap googleMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);
        setTitle("Digite seu endereço");
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction tx = manager.beginTransaction();
        tx.replace(R.id.frame_mapa, new MapaFragment());
        tx.commit();

    }

    @Override
    public void onItemSelected(String link) {
        // Código que interague com outros componente, inclusive Fragments
        LatLng posicaoEntrada = pegaCoordenadaDoEndereco("Rua Barao do bom retiro 942, engenho novo,rio de janeiro//");
        if (posicaoEntrada != null){
            MarkerOptions marcador = new MarkerOptions();
            marcador.position(posicaoEntrada);
            marcador.title("teste");
            CameraUpdate update = CameraUpdateFactory.newLatLngZoom(posicaoEntrada,17);
            googleMap.moveCamera(update);
        }
    }

    private LatLng pegaCoordenadaDoEndereco(String endereco) {
        try {
            Geocoder geocoder = new Geocoder(getBaseContext());
            List<Address> resultados = geocoder.getFromLocationName(endereco,1);
            if (!resultados.isEmpty()){
                LatLng posicao = new LatLng(resultados.get(0).getLatitude(),resultados.get(0).getLongitude());
                return posicao;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}