package com.web.java.barriga.dominio;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

import com.web.java.barriga.dominio.builders.UsuarioBuilder;
import com.web.java.barriga.dominio.exceptions.ValidationException;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class UsuarioTest {

    @Test
    void deve_criar_um_usuario_valido(){

        Usuario usuario = UsuarioBuilder.umUsuario().build();
        System.out.println(usuario);
        assertAll("Usuario",
            () -> assertEquals(1L, usuario.getId()),
            () -> assertEquals("Usuário Válido", usuario.getNome()),
            () -> assertEquals("email@email.com", usuario.getEmail()),
            () -> assertEquals("22334455", usuario.getSenha())
        );
    }

    @Test
    void deve_rejeitar_usuario_sem_nome() {

        ValidationException ex = assertThrows(ValidationException.class, () -> {
            UsuarioBuilder.umUsuario().comNome(null).build();
        });
        assertEquals("Nome é Obrigatório", ex.getMessage());
    }

    @Test
    void deve_rejeitar_usuario_sem_email() {

        ValidationException ex = assertThrows(ValidationException.class, () -> {
            UsuarioBuilder.umUsuario().comEmail(null).build();
        });
        assertEquals("Email é Obrigatório", ex.getMessage());
    }

    @Test
    void deve_rejeitar_usuario_sem_senha() {

        ValidationException ex = assertThrows(ValidationException.class, () -> {
            UsuarioBuilder.umUsuario().comSenha(null).build();
        });
        assertEquals("Senha é Obrigatória", ex.getMessage());
    }

//    @ParameterizedTest(name =  "{4}")
//    @CsvFileSource(
//        files = "src\\test\\resources\\composObrigatoriosUsuario.csv",
//        nullValues = {"null"},
//        numLinesToSkip = 1
//    )
    @ParameterizedTest
    @CsvFileSource(
               files = "src\\test\\resources\\camposObrigatoriosUsuario.csv",
                nullValues = {"null"},
    useHeadersInDisplayName = false,
    numLinesToSkip = 1)
    void deve_rejeitar_usuario(Long id, String nome, String email, String senha, String mensagem) {

        ValidationException ex = assertThrows(ValidationException.class, () -> {
            UsuarioBuilder.umUsuario().comId(id).comNome(nome).comEmail(email).comSenha(senha).build();
        });
        assertEquals(mensagem, ex.getMessage());
    }


}