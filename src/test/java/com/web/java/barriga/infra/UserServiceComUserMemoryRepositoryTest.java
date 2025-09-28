package com.web.java.barriga.infra;

import com.web.java.barriga.dominio.Usuario;
import com.web.java.barriga.dominio.builders.UsuarioBuilder;
import com.web.java.barriga.dominio.exceptions.ValidationException;
import com.web.java.barriga.service.UsuarioService;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class UserServiceComUserMemoryRepositoryTest {

    private static UsuarioService service = new UsuarioService(new UsuarioMemoryRepository());

    @Test
    @Order(1)
    void deve_salvar_usuario_valido() {
        Usuario user = service.salvar(UsuarioBuilder.umUsuario().comId(null).build());
        assertNotNull(user.getId());
        assertEquals(2L, user.getId());
    }

    @Test
    @Order(2)
    void deve_rejeitar_usuario_ja_existente() {


        ValidationException ex = assertThrows(ValidationException.class, () ->
                service.salvar(UsuarioBuilder.umUsuario().comId(null).build())
        );

        assertEquals("Utilizador email@email.com já existe", ex.getMessage());
    }
}
