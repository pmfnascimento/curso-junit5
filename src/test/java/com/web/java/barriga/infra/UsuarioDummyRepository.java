package com.web.java.barriga.infra;

import com.web.java.barriga.dominio.Usuario;
import com.web.java.barriga.dominio.builders.UsuarioBuilder;
import com.web.java.barriga.repositories.UsuarioRepository;

import java.util.Optional;

public class UsuarioDummyRepository implements UsuarioRepository {
    @Override
    public Usuario salvar(Usuario usuario) {
        return UsuarioBuilder.umUsuario()
                .comNome(usuario.getNome())
                .comEmail(usuario.getEmail())
                .comSenha(usuario.getSenha())
                .build();
    }

    @Override
    public Optional<Usuario> getUserByEmail(String email) {
        if("email@email.com".equalsIgnoreCase(email))
            return Optional.of(UsuarioBuilder.umUsuario().comEmail(email).build());
        return Optional.empty();
    }

}
