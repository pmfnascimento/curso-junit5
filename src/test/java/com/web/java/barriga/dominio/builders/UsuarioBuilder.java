package com.web.java.barriga.dominio.builders;

import com.web.java.barriga.dominio.Usuario;

public class UsuarioBuilder {

    private Long id;
    private String nome;
    private String email;
    private String senha;

    private UsuarioBuilder() {
    }

    public static UsuarioBuilder umUsuario() {
        UsuarioBuilder builder = new UsuarioBuilder();
        init(builder);
        return builder;
    }

    public static void init(UsuarioBuilder builder) {
        builder.id = 1L;
        builder.nome = "Pedro";
        builder.email = "email@email.com";
        builder.senha = "22334455";
    }

    public UsuarioBuilder comId(Long id) {
        this.id = id;
        return this;
    }

    public UsuarioBuilder comNome(String nome) {
        this.nome = nome;
        return this;
    }

    public UsuarioBuilder comEmail(String email) {
        this.email = email;
        return this;
    }
    public UsuarioBuilder comSenha(String senha) {
        this.senha = senha;
        return this;
    }

    public Usuario build() {
        return new Usuario(id, nome, email, senha);
    }
}
