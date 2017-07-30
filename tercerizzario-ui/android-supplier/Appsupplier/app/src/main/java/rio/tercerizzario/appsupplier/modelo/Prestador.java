package rio.tercerizzario.appsupplier.modelo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by CsGo on 31/03/2017.
 */

public class Prestador implements Serializable{
    private String id;
    private String nome;
    private String telefone;
    private String endereco;
    private String email;
    private String rg;
    private String senha;
    private Double nota;
    private List<String> comentario;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }

    public void setComentario(List<String> comentario) {
        this.comentario = comentario;
    }
}
