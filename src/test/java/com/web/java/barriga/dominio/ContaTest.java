package com.web.java.barriga.dominio;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ContaTest {

    @Test
    void deve_criar_conta_valida() {

        // criar uma conta
        Conta cont = ContaBuilder.umaConta().build();

        // Criar Assertivas
        assertAll("Conta",
            () -> assertEquals(1L, conta.getId()),
            () -> assertEquals("Conta Válida", conta.getNome()),
            () -> assertEquals("Conta Válida", conta.getNome()),
            );
    }
}
