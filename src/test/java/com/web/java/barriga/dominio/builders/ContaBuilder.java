package com.web.java.barriga.dominio.builders;
import com.web.java.barriga.dominio.Conta;
import com.web.java.barriga.dominio.Usuario;

public class ContaBuilder {
    private Long id;
    private String nome;
    private Usuario usuario;

    private ContaBuilder(){}

    public static ContaBuilder umaConta() {
        ContaBuilder builder = new ContaBuilder();
        init(builder);
        return builder;
    }

    public static void init(ContaBuilder builder) {
        builder.id = 1L;
        builder.nome = "Conta Válida";
        builder.usuario = UsuarioBuilder.umUsuario().build();
    }

    public ContaBuilder comId(Long id) {
        this.id = id;
        return this;
    }

    public ContaBuilder comNome(String nome) {
        this.nome = nome;
        return this;
    }

    public ContaBuilder comUsuario(Usuario usuario) {
        this.usuario = usuario;
        return this;
    }

    public Conta build() {
        return new Conta(id, nome, usuario);
    }
}