package rio.tercerizzario.appsupplier;

import android.widget.EditText;
import android.widget.TextView;

import rio.tercerizzario.appsupplier.modelo.Prestador;

/**
 * Created by CsGo on 08/04/2017.
 */

public class PerfilPrestadorHelper {
    private final TextView campoNome;
    private final TextView campoTelefone;
    private final TextView campoEmail;
    private final TextView campoEndereco;
    private final TextView campoRg;
    private final TextView campoEndRua;
    private final TextView campoEndNumero;
    private final TextView campoEndBairro;
    //private final TextView campoComplEndereco;
    //private final TextView campoCpf;


    public PerfilPrestadorHelper(PerfilPrestadorActivity activity) {
        campoNome = (TextView) activity.findViewById(R.id.perfil_prestador_nome);
        campoTelefone = (TextView) activity.findViewById(R.id.perfil_prestador_telefone);
        campoEmail = (TextView) activity.findViewById(R.id.perfil_prestador_email);
        campoEndereco = (TextView) activity.findViewById(R.id.perfil_prestador_endereco);
        campoRg = (TextView) activity.findViewById(R.id.perfil_prestador_rg);
        campoEndRua = (TextView) activity.findViewById(R.id.cadastro_prestador_end_rua);
        campoEndNumero = (TextView) activity.findViewById(R.id.cadastro_prestador_complemento_end_numero);
        campoEndBairro = (TextView) activity.findViewById(R.id.cadastro_prestador_complemento_end_bairro);
        //campoCpf = (TextView) activity.findViewById(R.id.perfil_prestador_cpf);
        //campoNota = (RatingBar) activity.findViewById(R.id.cadastro_prestador_nota);
    }

    public void setaPrestador(Prestador prestador) {
        campoNome.setText(prestador.getNome());
        campoEmail.setText(prestador.getEmail());
        campoTelefone.setText(prestador.getTelefone());
        campoEndereco.setText(prestador.getEndereco());
        campoRg.setText(prestador.getRg());
        campoEndRua.setText(prestador.getEndRua());
        campoEndNumero.setText(prestador.getEndNumero());
        campoEndBairro.setText(prestador.getEndBairro());
    }
}
