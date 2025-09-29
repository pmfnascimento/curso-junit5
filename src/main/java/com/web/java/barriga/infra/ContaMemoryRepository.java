package com.web.java.barriga.infra;

import com.web.java.barriga.dominio.Conta;
import com.web.java.barriga.dominio.Usuario;
import com.web.java.barriga.repositories.ContaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ContaMemoryRepository implements ContaRepository {

    private List<Conta> contas;
    private Long currentId;

    public ContaMemoryRepository() {
        this.contas = new ArrayList<>();
        this.currentId = 0L;
        salvar(new Conta(null, "Conta 1",
                new Usuario(null, "Usuário","email1@email.com","123456")));
    }

    @Override
    public Conta salvar(Conta conta) {
        Conta newUser = new Conta(nextId(), conta.getNome(), conta.getUsuario());
        contas.add(newUser);
        return newUser;
    }

    @Override
    public List<Conta> obterContasPorUsuario(Long usuarioId) {
        return null;
    }


    private Long nextId() {
        return ++currentId;
    }
}
