package rio.tercerizzario.appsupplier;

import android.app.Activity;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;

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

    private OnItemSelectedListener listener;
    private Button botaoBuscaEnd;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        getMapAsync(this);

        botaoBuscaEnd.findViewById(R.id.mapa_botao_busca_endereco);
        botaoBuscaEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
            }
        });
    }

    public interface OnItemSelectedListener {
        public void onItemSelected(String link);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnItemSelectedListener) {
            listener = (OnItemSelectedListener) context;
        } else {
            throw new ClassCastException();
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        LatLng posicaoEntrada = pegaCoordenadaDoEndereco("Rua Barao do bom retiro 942, engenho novo,rio de janeiro");
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
