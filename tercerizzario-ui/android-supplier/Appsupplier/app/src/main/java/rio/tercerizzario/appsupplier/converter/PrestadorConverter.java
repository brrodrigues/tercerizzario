package rio.tercerizzario.appsupplier.converter;

import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONStringer;
import org.json.JSONObject;

import rio.tercerizzario.appsupplier.modelo.Prestador;

/**
 * Created by CsGo on 06/04/2017.
 */
public class PrestadorConverter {
    public String converteParaJson(Prestador prestador) {
        JSONStringer js = new JSONStringer();
        try {
            js.object();
            js.key("name").value(prestador.getNome());
            js.key("cellPhone").value(prestador.getTelefone());
            js.key("address").value(prestador.getEndereco());
            js.key("email").value(prestador.getEmail());
            js.key("document").value(prestador.getRg());
            js.endObject();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return js.toString();
    }

    public Prestador converteParaPrestador(Prestador prestador, String json) {
        JSONObject js = null;
        String nome = null;
        //json = "{'id':'58e673a81d28ad2fb62a71e2','name':'Mitchell'}";
        //json.replace('"', '\'');
        try {
            js = new JSONObject(json);
            nome =  js.getString("name");
            prestador.setNome(nome);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return prestador;
    }

}
