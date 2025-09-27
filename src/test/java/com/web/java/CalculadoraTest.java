package com.web.java;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class CalculadoraTest {

    private Calculadora calculadora;
    private int contador = 0;

    @BeforeEach
    void setUp() {
        calculadora = new Calculadora();
    }

    @AfterEach
    void tearDown() {
        calculadora = null;
    }

    @Test
    void test_soma_dois_valores_inteiros() {
        int result = calculadora.soma(2,3);

        assertEquals(5, result);

    }

    @Test
    void test_diferentes_assertivas() {
        assertEquals("Casa", "Casa");
        assertNotEquals("Casa", "casa");
        assertTrue("casa".equalsIgnoreCase("CASA"));
        assertTrue("Casa".endsWith("sa"));
        assertTrue("Casa".startsWith("Ca"));

        List<String> s1 = new ArrayList<>();
        List<String> s2 = new ArrayList<>();
        List<String> s3 = null;

        assertEquals(s1, s2);
        assertSame(s1, s1);
        assertNotEquals(s1, s3);
        assertNull(s3);
        assertNotNull(s1);
    }

    @Test
    void test_deve_retornar_numero_inteiro_na_divisao() {
        float result = calculadora.dividir(6,2);

        assertEquals(3, result);
    }

    @Test
    void test_deve_retornar_numero_negativo_na_divisao() {
        float result = calculadora.dividir(6,-2);

        assertEquals(-3, result);
    }

    @Test
    void test_deve_retornar_numero_decimal_na_divisao() {
        float result = calculadora.dividir(10,3);

        assertEquals(3.3333332538604736, result);
        assertEquals(3.33, result, 0.01);
    }

    @Test
    void test_deve_retornar_zero_com_numerador_zero_na_divisao() {

        float result = calculadora.dividir(0,2);

        assertEquals(0, result);
    }

    @Test
    void test_deve_lancar_excepcao_quando_dividir_por_zero_junit4() {
        try {
            float resultado = 10 / 0;
            fail("Deveria ter sido lançado uma exception na divisão");
        } catch (ArithmeticException e) {
            assertEquals("/ by zero", e.getMessage());
        }
    }

    @Test
    void test_deve_lancar_excepcao_quando_dividir_por_zero_junit5() {
        assertThrows(ArithmeticException.class, () -> {
            float resultado = 10 / 0;
        });
    }

    @ParameterizedTest
    @ValueSource(strings = {"Test1", "Test2"})
    void testStrings(String param) {

    }

    @ParameterizedTest
    @CsvSource(value =  {
        "6, 2, 3",
        "6, -2, -3",
        "10, 3, 3.3333332538604736",
        "0, 2, 0"
    })
    void deve_dividir_corretamente(int num, int denom, double res) {
        float result = calculadora.dividir(num,denom);
        assertEquals(res, result);
    }

}
