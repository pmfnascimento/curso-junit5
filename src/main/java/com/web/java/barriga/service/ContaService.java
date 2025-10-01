package com.web.java.barriga.service;

import com.web.java.barriga.dominio.Conta;
import com.web.java.barriga.dominio.exceptions.ValidationException;
import com.web.java.barriga.repositorie.ContaRepository;
import com.web.java.barriga.service.external.ContaEvent;

import java.time.LocalDateTime;
import java.util.List;

public class ContaService{

    private final ContaRepository repository;
    private final ContaEvent event;

    public ContaService(ContaRepository repository, ContaEvent event) {
        this.repository = repository;
        this.event = event;
    }

    public Conta salvar(Conta conta) throws Exception {

        List<Conta> contas = obterContasPorUsuario(conta.getId());

        contas.forEach(contaExistente -> {
            if(conta.getNome().equalsIgnoreCase(contaExistente.getNome()))
                throw new ValidationException("Usuário já possui uma conta com este nome");
        });

        Conta contaPersistida = repository.salvar(new Conta(conta.getId(), conta.getNome() + LocalDateTime.now(), conta.getUsuario()));

        try {
            event.dispatch(contaPersistida, ContaEvent.EventType.CREATED);
        } catch (Exception e) {
            repository.delete(contaPersistida);
            throw new RuntimeException("Falha na criação da conta, tente novamente");
        }

        return contaPersistida;
    }

    public List<Conta> obterContasPorUsuario(Long usuarioId) {

        return repository.obterContasPorUsuario(usuarioId);
    }
}
