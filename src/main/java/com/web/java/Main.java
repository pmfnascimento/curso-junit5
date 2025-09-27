package com.web.java;

import java.util.logging.Logger;

public class Main {

    public static void main(String[] args) {
        
        Calculadora operacao = new Calculadora();

        System.out.println("soma = " + operacao.soma(2, 5));
    }
}