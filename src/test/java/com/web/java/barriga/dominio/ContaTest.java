package com.web.java.barriga.dominio;

import com.web.java.barriga.dominio.builders.ContaBuilder;
import com.web.java.barriga.dominio.builders.UsuarioBuilder;
import com.web.java.barriga.dominio.exceptions.ValidationException;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class ContaTest {

    @Test
    void deve_criar_conta_valida() {

        // criar uma conta
        Conta conta = ContaBuilder.umaConta().build();

        // Criar Assertivas
        assertAll("Conta",
            () -> assertEquals(1L, conta.getId()),
            () -> assertEquals("Conta Válida", conta.getNome()),
            () -> assertEquals(ContaBuilder.umaConta().build().getUsuario(), conta.getUsuario())
        );
    }

    @ParameterizedTest(name =  "{3}")
    @MethodSource({"dataProvider"})
    void deve_rejeitar_conta_invalida(Long id, String nome, Usuario usuario, String menssagem) {

        String errorMessage = assertThrows(ValidationException.class, () ->
         ContaBuilder.umaConta().comId(id).comNome(nome).comUsuario(usuario).build()
        ).getMessage();

        assertEquals(menssagem, errorMessage);
    }

    private static Stream<Arguments> dataProvider() {
        return Stream.of(
                Arguments.of(1L, null, UsuarioBuilder.umUsuario().build(), "Nome é Obrigatório"),
                Arguments.of(1L, "Conta Válida", null, "Usuário é Obrigatório")
        );
    }
}
