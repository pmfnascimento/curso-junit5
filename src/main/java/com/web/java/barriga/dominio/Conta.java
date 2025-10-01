package com.web.java.barriga.dominio;

import com.web.java.barriga.dominio.exceptions.ValidationException;

import java.util.Objects;

public class Conta {

    private Long id;
    private String nome;
    private Usuario usuario;

    public Conta(Long id, String nome, Usuario usuario) {
        if(nome == null) throw new ValidationException("Nome é Obrigatório");
        if(usuario == null) throw new ValidationException("Usuário é Obrigatório");
        this.id = id;
        this.nome = nome;
        this.usuario = usuario;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Conta conta = (Conta) o;
        return Objects.equals(nome, conta.nome) && Objects.equals(usuario, conta.usuario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, usuario);
    }

    @Override public String toString() {
        return "Conta{" +
            "id=" + id +
            ", nome='" + nome + '\'' +
            ", usuario=" + usuario +
            '}';
    }
}
