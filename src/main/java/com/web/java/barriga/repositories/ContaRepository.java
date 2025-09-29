package com.web.java.barriga.repositories;

import com.web.java.barriga.dominio.Conta;
import java.util.*;

public interface ContaRepository {

    Conta salvar(Conta conta);

    List<Conta> obterContasPorUsuario(Long usuarioId);

}
