package com.web.java.barriga.repositorie;

import com.web.java.barriga.dominio.Usuario;

import java.util.Optional;

public interface UsuarioRepository {

    Usuario salvar(Usuario usuario);

    Optional<Usuario> getUserByEmail(String email);

}
