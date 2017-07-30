package rio.tercerizzario.appsupplier;

import android.widget.EditText;
import android.widget.RatingBar;

import rio.tercerizzario.appsupplier.modelo.Prestador;

/**
 * Created by CsGo on 31/03/2017.
 */

public class CadastroPrestadorHelper {
    private final EditText campoNome;
    private final EditText campoTelefone;
    private final EditText campoEmail;
    private final EditText campoEndereco;
    private final EditText campoRg;


    public CadastroPrestadorHelper(CadastroPrestadorActivity activity) {
        campoNome = (EditText) activity.findViewById(R.id.cadastro_prestador_nome);
        campoTelefone = (EditText) activity.findViewById(R.id.cadastro_prestador_telefone);
        campoEmail = (EditText) activity.findViewById(R.id.cadastro_prestador_email);
        campoEndereco = (EditText) activity.findViewById(R.id.cadastro_prestador_endereco);
        campoRg = (EditText) activity.findViewById(R.id.cadastro_prestador_rg);
        //campoNota = (RatingBar) activity.findViewById(R.id.cadastro_prestador_nota);
    }

    public Prestador pegaPrestador(Prestador prestador) {
        //Prestador prestador = new Prestador();
        prestador.setNome(campoNome.getText().toString());
        prestador.setTelefone(campoTelefone.getText().toString());
        prestador.setEmail(campoEmail.getText().toString());
        prestador.setRg(campoRg.getText().toString());
        prestador.setEndereco(campoEndereco.getText().toString());
        //prestador.setNota(Double.valueOf(campoNota.getProgress()));
        return prestador;
    }

    public void setaPrestador(Prestador prestador) {
        campoNome.setText(prestador.getNome());
        campoEmail.setText(prestador.getEmail());
        campoTelefone.setText(prestador.getTelefone());
        campoEndereco.setText(prestador.getEndereco());
        campoRg.setText(prestador.getRg());
    }


}
