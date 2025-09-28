package com.web.java.barriga.repositories;

import com.web.java.barriga.dominio.Usuario;

import java.util.Optional;

public interface UsuarioRepository {

    Usuario salvar(Usuario usuario);

    Optional<Usuario> getUserByEmail(String email);

}
