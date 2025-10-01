package com.web.java.barriga.service;

import com.web.java.barriga.dominio.Usuario;
import com.web.java.barriga.dominio.builders.UsuarioBuilder;
import com.web.java.barriga.dominio.exceptions.ValidationException;
import com.web.java.barriga.repositorie.UsuarioRepository;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class UsuarioServiceTest {

    @Mock
    private UsuarioRepository repository;

    @InjectMocks
    private UsuarioService service;

    @Test
    void deve_retornar_empty_quando_usuario_inexistente() {

        when(repository.getUserByEmail("email@email.com"))
                .thenReturn(Optional.empty());

        Optional<Usuario> user = service.getUserByEmail("email@email.com");
        assertTrue(user.isEmpty());
    }

    @Test
    void deve_retornar_usuario_por_email() {

        when(repository.getUserByEmail("email@email.com"))
                .thenReturn(Optional.of(UsuarioBuilder.umUsuario().build()), null);

        Optional<Usuario> user = service.getUserByEmail("email@email.com");
        assertTrue(user.isPresent());
    }

    @Test
    void deve_salvar_usuario_com_sucesso() {

        Usuario userToSave = UsuarioBuilder.umUsuario().comId(null).build();
        when(repository.salvar(userToSave))
                .thenReturn(UsuarioBuilder.umUsuario().build());

        Usuario userSave = service.salvar(userToSave);
        assertNotNull(userSave.getId());
        verify(repository).getUserByEmail(userToSave.getEmail());
    }

    @Test
    void deve_rejeitar_usuario_existente() {
        Usuario userToSave = UsuarioBuilder.umUsuario().comId(null).build();

        when(repository.getUserByEmail(userToSave.getEmail()))
                .thenReturn(Optional.of(UsuarioBuilder.umUsuario().build()));

        ValidationException ex = assertThrows(ValidationException.class, () ->
                service.salvar(userToSave)
        );
        assertTrue(ex.getMessage().endsWith("já existe"));
        verify(repository, never()).salvar(userToSave);
    }
}
