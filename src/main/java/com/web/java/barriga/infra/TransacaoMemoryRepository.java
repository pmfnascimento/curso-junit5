package com.web.java.barriga.infra;

import java.util.ArrayList;
import java.util.List;

import com.web.java.barriga.dominio.Transacao;
import com.web.java.barriga.repositorie.TransacaoDao;

public class TransacaoMemoryRepository implements TransacaoDao {

    private List<Transacao> transacoes;

    public TransacaoMemoryRepository() {
        transacoes = new ArrayList<>();
    }

    @Override
    public Transacao salvar(Transacao transacao) {
        transacoes.add(transacao);
        return transacao;
    }
}
