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
    private final EditText campoEndRua;
    private final EditText campoEndNumero;
    private final EditText campoEndBairro;
    private final EditText campoRg;
    private final EditText campoCpf;


    public CadastroPrestadorHelper(CadastroPrestadorActivity activity) {
        campoNome = (EditText) activity.findViewById(R.id.cadastro_prestador_nome);
        campoTelefone = (EditText) activity.findViewById(R.id.cadastro_prestador_telefone);
        campoEmail = (EditText) activity.findViewById(R.id.cadastro_prestador_email);
        campoEndRua = (EditText) activity.findViewById(R.id.cadastro_prestador_end_rua);
        campoEndNumero = (EditText) activity.findViewById(R.id.cadastro_prestador_complemento_end_numero);
        campoEndBairro = (EditText) activity.findViewById(R.id.cadastro_prestador_complemento_end_bairro);
        campoRg = (EditText) activity.findViewById(R.id.cadastro_prestador_rg);
        campoCpf = (EditText) activity.findViewById(R.id.cadastro_prestador_cpf);

        //campoNota = (RatingBar) activity.findViewById(R.id.cadastro_prestador_nota);
    }

    public Prestador pegaPrestador(Prestador prestador) {
        //Prestador prestador = new Prestador();
        prestador.setNome(campoNome.getText().toString());
        prestador.setTelefone(campoTelefone.getText().toString());
        prestador.setEmail(campoEmail.getText().toString());
        prestador.setRg(campoRg.getText().toString());
        prestador.setEndRua(campoEndRua.getText().toString());
        prestador.setEndNumero(campoEndNumero.getText().toString());
        prestador.setEndBairro(campoEndBairro.getText().toString());
        //prestador.setNota(Double.valueOf(campoNota.getProgress()));
        return prestador;
    }

    public void setaPrestador(Prestador prestador) {
        campoNome.setText(prestador.getNome());
        campoEmail.setText(prestador.getEmail());
        campoTelefone.setText(prestador.getTelefone());
        campoEndRua.setText(prestador.getEndRua());
        campoEndNumero.setText(prestador.getEndNumero());
        campoEndBairro.setText(prestador.getEndBairro());
        campoRg.setText(prestador.getRg());
    }


}
