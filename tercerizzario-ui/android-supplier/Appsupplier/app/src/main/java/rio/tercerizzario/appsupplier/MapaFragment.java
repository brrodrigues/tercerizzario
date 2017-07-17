package rio.tercerizzario.appsupplier;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

import rio.tercerizzario.appsupplier.modelo.Prestador;

/**
 * Created by admin on 25/06/17.
 */

public class MapaFragment extends SupportMapFragment implements OnMapReadyCallback {

    private Prestador prestador;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        LatLng posicaoEntrada = pegaCoordenadaDoEndereco("Rau Barao do bom retiro 942, engenho novo,rio de janeiro");
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
            Geocoder geocoder = new Geocoder(getContext());
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
