package com.web.java.barriga.service;

import com.web.java.barriga.dominio.Conta;
import com.web.java.barriga.dominio.exceptions.ValidationException;
import com.web.java.barriga.repositories.ContaRepository;

import java.util.List;
import java.util.Optional;

public class ContaService{

    private final ContaRepository repository;

    public ContaService(ContaRepository repository) {
        this.repository = repository;
    }

    public Conta salvar(Conta conta) {
        return repository.salvar(conta);
    }

    public List<Conta> obterContasPorUsuario(Long usuarioId) {

        return repository.obterContasPorUsuario(usuarioId);
    }
}
