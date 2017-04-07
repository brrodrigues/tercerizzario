package rio.tercerizzario.appsupplier.converter;

import org.json.JSONException;
import org.json.JSONStringer;

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
            js.endObject();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return js.toString();
    }
}
