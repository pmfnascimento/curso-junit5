package com.web.java.barriga.service;

import com.web.java.barriga.dominio.Usuario;
import com.web.java.barriga.dominio.exceptions.ValidationException;
import com.web.java.barriga.repositories.UsuarioRepository;

import java.util.Optional;

public class UsuarioService {

    private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }


    public Usuario salvar(Usuario usuario) {

        repository.getUserByEmail(usuario.getEmail()).ifPresent(user -> {
            throw new ValidationException(String.format("Utilizador %s já existe", usuario.getEmail()));
        });

        return repository.salvar(usuario);
    }

    public Optional<Usuario> getUserByEmail(String email) {
        return repository.getUserByEmail(email);
    }

}
