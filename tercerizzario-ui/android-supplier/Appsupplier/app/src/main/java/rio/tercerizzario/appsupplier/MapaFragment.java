package rio.tercerizzario.appsupplier;

import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentActivity;
import android.app.Activity;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
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

    MapView mapView;
    private View mView;
    private EditText edttxt_entrada_busca_end;
    private Button botaoBuscaEnd;
    private GoogleMap mMap;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        getMapAsync(this);
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        mView = layoutInflater.inflate(R.layout.activity_mapa, viewGroup,false);
        edttxt_entrada_busca_end = (EditText) mView.findViewById(R.id.mapa_entrada_busca_endereco);

        mapView = (MapView) mView.findViewById(R.id.mapaView);
        mapView.onCreate(bundle);
        mapView.onResume();

        try {
            MapsInitializer.initialize(getActivity().getApplication());
        } catch (Exception e) {
            e.printStackTrace();
        }

        botaoBuscaEnd = (Button) mView.findViewById(R.id.mapa_botao_busca_endereco);
        botaoBuscaEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LatLng posicaoEntrada = pegaCoordenadaDoEndereco("Rua Barao do bom retiro 942, engenho novo,rio de janeiro");
                atualizaMapa(mMap, posicaoEntrada);
            }
        });

        return super.onCreateView(layoutInflater, viewGroup, bundle);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {

        LatLng posicaoEntrada = pegaCoordenadaDoEndereco("Rua Barao do bom retiro 942, engenho novo,rio de janeiro");
        atualizaMapa(googleMap, posicaoEntrada);

    }

    private void atualizaMapa(GoogleMap googleMap, LatLng posicaoEntrada) {
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
