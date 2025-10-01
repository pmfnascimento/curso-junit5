package com.web.java.barriga.service;

import java.util.Collections;

import com.web.java.barriga.dominio.Conta;
import com.web.java.barriga.dominio.builders.ContaBuilder;
import com.web.java.barriga.dominio.exceptions.ValidationException;
import com.web.java.barriga.repositorie.ContaRepository;
import com.web.java.barriga.service.external.ContaEvent;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class ContaServiceTest {

    @Mock
    private ContaRepository repository;

    @Mock
    private ContaEvent event;

    @InjectMocks
    private ContaService service;

    @Captor
    private ArgumentCaptor<Conta> contaCaptor;

    @Test
    void deve_salvar_primeira_conta_com_sucesso() throws Exception {

        Conta contaToSave = ContaBuilder.umaConta().comId(null).build();

        when(repository.salvar(contaCaptor.capture()))
            .thenReturn(ContaBuilder.umaConta().build());


        doNothing().when(event).dispatch(ContaBuilder.umaConta().build(), ContaEvent.EventType.CREATED);

        Conta contaSalva = service.salvar(contaToSave);

        assertNotNull(contaSalva.getId());
        assertTrue(contaCaptor.getValue().getNome().startsWith("Conta Válida"));
    }

    @Test
    void deve_salvar_segunda_conta_com_sucesso() throws Exception {

        Conta contaToSave = ContaBuilder.umaConta().comId(null).build();

        when(repository.obterContasPorUsuario(contaToSave.getId()))
            .thenReturn(Collections.singletonList(ContaBuilder.umaConta().comNome("Outra Conta").build()));

        when(repository.salvar(contaCaptor.capture())).thenReturn(ContaBuilder.umaConta().build());

        Conta contaSalva = service.salvar(contaToSave);
        assertNotNull(contaSalva.getId());

        verify(repository).salvar(contaCaptor.capture());
    }

    @Test
    void deve_rejeitar_conta_repetida() {

        Conta contaToSave = ContaBuilder.umaConta().comId(null).build();

        when(repository.obterContasPorUsuario(contaToSave.getId()))
            .thenReturn(Collections.singletonList(ContaBuilder.umaConta().build()));


        String message = assertThrows(ValidationException.class, () ->
            service.salvar(contaToSave)).getMessage();

        assertEquals("Usuário já possui uma conta com este nome", message);
    }

    @Test
    void nao_deve_manter_conta_sem_event() throws Exception {

        Conta contaToSave = ContaBuilder.umaConta().comId(null).build();
        Conta contaSalva = ContaBuilder.umaConta().build();

        when(repository.salvar(contaCaptor.capture()))
            .thenReturn(contaSalva);
        doThrow(new Exception("Falha Catastrófica")).when(event).dispatch(ContaBuilder.umaConta().build(), ContaEvent.EventType.CREATED);

        String message = assertThrows(Exception.class, () ->
            service.salvar(contaToSave)).getMessage();

        assertEquals("Falha na criação da conta, tente novamente", message);

        verify(repository).delete(contaCaptor.capture());

        assertNull(contaToSave.getId());
    }

}
