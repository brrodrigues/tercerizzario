package rio.tercerizzario.appsupplier;

import android.content.Context;
import android.content.res.ObbInfo;
import android.os.AsyncTask;
import android.widget.Toast;

import rio.tercerizzario.appsupplier.converter.PrestadorConverter;
import rio.tercerizzario.appsupplier.modelo.Prestador;


/**
 * Created by CsGo on 06/04/2017.
 */

public class EnviaPrestadorTask extends AsyncTask<Object,Object,String> {

    private Context context;
    private Prestador prestador;
    private CadastroPrestadorHelper helper;

    public  EnviaPrestadorTask(Context context, Prestador prestador, CadastroPrestadorHelper helper){
        this.context = context;
        this.prestador = prestador;
        this.helper = helper;
    }

    @Override
    protected String doInBackground(Object[] params) {

        PrestadorConverter conversor = new PrestadorConverter();
        String json = conversor.converteParaJson(this.prestador);

        WebClient client = new WebClient();
        String resposta = client.post(json);
        return resposta;
    }

    @Override
    protected void onPostExecute(String resposta){
    }
}
