package com.web.java.barriga.dominio;

import com.web.java.barriga.dominio.exceptions.ValidationException;

public class Usuario {

    private Long id;
    private String nome;
    private String email;
    private String senha;

    public Usuario(Long id, String nome, String email, String senha) {

        if(nome == null) throw new ValidationException("Nome é Obrigatório");
        if(email == null) throw new ValidationException("Email é Obrigatório");
        if(senha == null) throw new ValidationException("Senha é Obrigatória");

        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    @Override public String toString() {
        return "Usuario{" +
            "id=" + id +
            ", nome='" + nome + '\'' +
            ", email='" + email + '\'' +
            ", senha='" + senha + '\'' +
            '}';
    }
}
