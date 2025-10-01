package com.web.java.barriga.service.external;

import com.web.java.barriga.dominio.Conta;

public interface ContaEvent {

    enum EventType {CREATED, UPDATED, DELETED}

    void dispatch(Conta conta, EventType eventType) throws Exception;
}
