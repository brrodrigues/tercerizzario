package rio.tercerizzario.appsupplier;

import android.content.Context;
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

    public BuscaPrestadorTask(Context context, Prestador prestador, PerfilPrestadorHelper helper){
        this.context = context;
        this.prestador = prestador;
        this.helper = helper;
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
        Toast.makeText(context, prestador.getNome(),Toast.LENGTH_SHORT).show();
        helper.setaPrestador(prestador);
    }

}
