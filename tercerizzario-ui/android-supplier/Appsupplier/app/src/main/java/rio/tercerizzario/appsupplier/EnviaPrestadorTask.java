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

public class EnviaPrestadorTask extends AsyncTask {

    private Context context;
    private Prestador prestador;

    public EnviaPrestadorTask(Context context, Prestador prestador){
        this.context = context;
        this.prestador = prestador;
    }

    @Override
    protected Object doInBackground(Object[] params) {

        PrestadorConverter conversor = new PrestadorConverter();
        String json = conversor.converteParaJson(this.prestador);

        WebClient client = new WebClient();
        String resposta = client.post(json);
        return null;
    }

    @Override
    protected void onPostExecute(Object o){
        //Toast.makeText(this.CadastroPrestadorActivity, o, Toast.LENGTH_SHORT).show();
    }
}
