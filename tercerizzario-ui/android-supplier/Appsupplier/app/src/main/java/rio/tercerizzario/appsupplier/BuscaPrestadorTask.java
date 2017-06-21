package rio.tercerizzario.appsupplier;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import org.json.JSONException;

import rio.tercerizzario.appsupplier.converter.PrestadorConverter;
import rio.tercerizzario.appsupplier.modelo.Prestador;

/**
 * Created by CsGo on 09/04/2017.
 */

public class BuscaPrestadorTask extends AsyncTask<Object,Object,String> {

    private Context context;
    private Prestador prestador;
    private PerfilPrestadorHelper helper;

    public BuscaPrestadorTask(Context context, Prestador prestador) {
        this.context = context;
        this.prestador = prestador;
    }


    @Override
    protected String doInBackground(Object[] params) {
        WebClient client = new WebClient();
        String resposta = client.get("psymitch@gmail.com");
        return resposta;
    }

    @Override
    protected void onPostExecute(String resposta){
        PrestadorConverter conversor = new PrestadorConverter();
        prestador = conversor.converteParaPrestador(this.prestador,resposta.toString());
        helper = new PerfilPrestadorHelper((PerfilPrestadorActivity) this.context);
        helper.setaPrestador(prestador);
    }

}
