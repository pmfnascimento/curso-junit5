package com.web.java.barriga.services;

import com.web.java.barriga.dominio.Conta;
import com.web.java.barriga.dominio.builders.ContaBuilder;
import com.web.java.barriga.repositories.ContaRepository;
import com.web.java.barriga.service.ContaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class ContaServiceTest {

    @Mock
    private ContaRepository repository;

    @InjectMocks
    private ContaService service;

    @Test
    void deve_salvar_com_sucesso() {

        Conta contaToSave = ContaBuilder.umaConta().comId(null).build();

        when(repository.salvar(contaToSave)).thenReturn(ContaBuilder.umaConta().build());

        Conta contaSalva = service.salvar(contaToSave);
        assertNotNull(contaSalva.getId());
        verify(repository).getContaByName(contaSalva.getNome());
    }

    @Test
    void deve_rejeitar_conta_repetida() {

       
    }
}
