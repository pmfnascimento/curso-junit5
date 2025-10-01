package com.web.java.barriga.service;

import java.time.LocalDateTime;

import com.web.java.barriga.dominio.Transacao;
import com.web.java.barriga.dominio.exceptions.ValidationException;
import com.web.java.barriga.repositorie.TransacaoDao;

public class TransacaoService {

    private TransacaoDao transacaoDao;

    public Transacao salvar(Transacao transacao) {

        if(getTime().getHour() > 8) throw new RuntimeException("Tente amanhã novamente");
        if(transacao.getDescricao() == null) throw new ValidationException("Descrição Inexistente");
        if(transacao.getValor() == null) throw new ValidationException("Valor Inexistente");
        if(transacao.getData() == null) throw new ValidationException("Data Inexistente");
        if(transacao.getConta() == null) throw new ValidationException("Conta Inexistente");
        if(transacao.getStatus() == null) transacao.setStatus(false);

        return transacaoDao.salvar(transacao);
    }

    protected LocalDateTime getTime() {
        return LocalDateTime.now();
    }
}
