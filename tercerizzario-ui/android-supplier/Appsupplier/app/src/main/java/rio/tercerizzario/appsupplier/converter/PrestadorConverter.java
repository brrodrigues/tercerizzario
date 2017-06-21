package rio.tercerizzario.appsupplier.converter;

import android.content.Context;
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
            js.key("id").value(prestador.getId());
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
        try {
            js = new JSONObject(json);
            prestador.setId(js.getString("id"));
            prestador.setNome(js.getString("name"));
            prestador.setTelefone(js.getString("cellPhone"));
            prestador.setEndereco(js.getString("address"));
            prestador.setEmail(js.getString("email"));
            prestador.setRg(js.getString("document"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return prestador;
    }

}
