package rio.tercerizzario.appsupplier.modelo;

import java.util.List;

/**
 * Created by admin on 25/06/17.
 */

public class Servico {

    private String nome;
    private String tipo;
    private String id;

    public Servico(String nome, String tipo, String id) {
        this.nome = nome;
        this.tipo = tipo;
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return this.nome;
    }
}
