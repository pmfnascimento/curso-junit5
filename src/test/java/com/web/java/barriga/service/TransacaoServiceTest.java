package com.web.java.barriga.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import com.web.java.barriga.dominio.Conta;
import com.web.java.barriga.dominio.Transacao;
import com.web.java.barriga.dominio.builders.ContaBuilder;
import com.web.java.barriga.dominio.builders.TransacaoBuilder;
import com.web.java.barriga.dominio.exceptions.ValidationException;
import com.web.java.barriga.repositorie.TransacaoDao;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mockConstruction;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
//@EnabledIf(value = "ifHoraValida")
public class TransacaoServiceTest {

    @InjectMocks
    @Spy
    private TransacaoService transacaoService;

    @Mock
    private TransacaoDao transacaoDao;

//    @Mock
//    private LocService locService;

    @BeforeEach
    void checkTime() {
        when(transacaoService.getTime()).thenReturn(LocalDateTime.of(2025, 1, 1, 4, 30, 15));
    }

    @Test
    void deve_salvar_transacao_valida() {

        // assumeTrue(LocalDateTime.now().getHour() < 8);

        Transacao transacaoParaSalvar = TransacaoBuilder.umTransacao()
            .comId(null).build();

        System.out.println(transacaoParaSalvar);

        when(transacaoDao.salvar(transacaoParaSalvar))
            .thenReturn(TransacaoBuilder.umTransacao().build());

        Transacao transacaoSalva = transacaoService.salvar(transacaoParaSalvar);
        assertNotNull(transacaoSalva.getId());
        assertEquals(transacaoParaSalvar, TransacaoBuilder.umTransacao().build());
        assertAll("Transacao",
            () -> assertEquals(1L, transacaoSalva.getId()),
            () ->assertEquals("Transação Válida", transacaoSalva.getDescricao()),
            () -> {
                assertAll("Conta",
                    () -> assertEquals("Conta Válida", transacaoSalva.getConta().getNome()),
                    () -> {
                        assertAll("Usuario",
                            () -> assertEquals("Usuário Válido", transacaoSalva.getConta().getUsuario().getNome()),
                            () -> assertEquals("22334455", transacaoSalva.getConta().getUsuario().getSenha())
                        );
                    }
                );
            }
        );

//        System.out.println(new Date().getHours());

    }

    @ParameterizedTest(name = "{6}")
    @MethodSource({"dataProvider"})
    void deve_rejeitar_conta_dados_vazios(Long id, String descricao, Double valor, Conta conta,
                                            LocalDate data, Boolean status, String mensagem) {

        Transacao transacaoSalvar = TransacaoBuilder.umTransacao().comDescricao(descricao).comValor(valor).comId(id)
            .comConta(conta).comData(data).comStatus(status).build();
        String errorMessage = assertThrows(ValidationException.class, () ->
                transacaoService.salvar(transacaoSalvar)
        ).getMessage();

        assertEquals(mensagem, errorMessage);
    }

    private static Stream<Arguments> dataProvider() {
        return Stream.of(
            Arguments.of(1L, null, 10.0, ContaBuilder.umaConta().build(), LocalDate.now(), false, "Descrição Inexistente"),
            Arguments.of(1L, "Transação Válida", null, ContaBuilder.umaConta().build(), LocalDate.now(), false, "Valor Inexistente"),
            Arguments.of(1L, "Transação Válida", 10.0, null, LocalDate.now(), false, "Conta Inexistente"),
            Arguments.of(1L, "Transação Válida", 10.0, ContaBuilder.umaConta().build(), null, false, "Data Inexistente")
        );
    }

//    public static boolean ifHoraValida() {
//        return LocalDateTime.now().getHour() > 8;
//    }

    @Test
    void deve_rejeitar_transacao_tarde_na_noite() {
        when(transacaoService.getTime()).thenReturn(LocalDateTime.of(2025, 1, 1, 11, 30, 15));
        String errorMessage = assertThrows(RuntimeException.class, () ->
            transacaoService.salvar(TransacaoBuilder.umTransacao().build())
        ).getMessage();

        assertEquals("Tente amanhã novamente", errorMessage);
    }

    @Test
    void deve_rejeitar_transacao_status_nulo() {

      transacaoService.salvar(TransacaoBuilder.umTransacao().comStatus(null).build());


    }
}
