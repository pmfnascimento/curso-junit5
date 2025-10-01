package com.web.java.barriga.dominio.builders;

import java.time.LocalDate;
import com.web.java.barriga.dominio.Conta;
import com.web.java.barriga.dominio.Transacao;


public class TransacaoBuilder {
    private Transacao elemento;
    private TransacaoBuilder(){}

    public static TransacaoBuilder umTransacao() {
        TransacaoBuilder builder = new TransacaoBuilder();
        init(builder);
        return builder;
    }

    public static void init(TransacaoBuilder builder) {
        builder.elemento = new Transacao();
        Transacao elemento = builder.elemento;


        elemento.setId(1L);
        elemento.setDescricao("Transação Válida");
        elemento.setValor(10.0);
        elemento.setConta(ContaBuilder.umaConta().build());
        elemento.setData(LocalDate.now());
        elemento.setStatus(false);
    }

    public TransacaoBuilder comId(Long param) {
        elemento.setId(param);
        return this;
    }

    public TransacaoBuilder comDescricao(String param) {
        elemento.setDescricao(param);
        return this;
    }

    public TransacaoBuilder comValor(Double param) {
        elemento.setValor(param);
        return this;
    }

    public TransacaoBuilder comConta(Conta param) {
        elemento.setConta(param);
        return this;
    }

    public TransacaoBuilder comData(LocalDate param) {
        elemento.setData(param);
        return this;
    }

    public TransacaoBuilder comStatus(Boolean param) {
        elemento.setStatus(param);
        return this;
    }

    public Transacao build() {
        return elemento;
    }
}

