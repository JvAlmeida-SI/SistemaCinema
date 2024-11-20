package com.cinema.controller;

import com.cinema.model.Administrador;
import com.cinema.model.Funcionario;
import com.cinema.model.manipularJsonPessoas;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public abstract class ValidaPessoa {

    static manipularJsonPessoas mJson = new manipularJsonPessoas();
    Scanner input = new Scanner(System.in);

    /**
     * Método para validar um número de CPF.
     *
     * @param cpf o número de CPF a ser validado.
     * @return true se o CPF for válido, false caso contrário.
     */
    public static boolean ValidaCPF (String cpf){
        if (cpf.equals("00000000000") ||
                cpf.equals("11111111111") ||
                cpf.equals("22222222222") ||
                cpf.equals("33333333333") ||
                cpf.equals("44444444444") ||
                cpf.equals("55555555555") ||
                cpf.equals("66666666666") ||
                cpf.equals("77777777777") ||
                cpf.equals("88888888888") ||
                cpf.equals("99999999999") ||
                (cpf.length() != 11)) {
            System.out.println("CPF inválido. Verifique e informe novamente.");
            return false;
        }
        // variaveis do décimo e décimo primeiro
        char digito10;
        char digito11;
        int soma;
        int i;
        int r;
        int numero;
        int peso;

        try {
            // para calcular o primeiro digito "verificador"
            soma = 0;
            peso = 10;
            for (i = 0; i < 9; i++) {
                // para converter o caractere do CPF em um número inteiro
                // o 48 representa o zero tabela ASCII
                numero = (int) (cpf.charAt(i) - 48);
                soma = soma + (numero * peso);
                peso = peso - 1;
            }

            r = 11 - (soma % 11);
            if ((r == 10) || (r == 11)) {
                digito10 = '0';
            } else {
                digito10 = (char) (r + 48);
            }

            //para calcular o segundo digito "verificador"
            soma = 0;
            //peso igual a 11, pois, o primeiro digito verificador já foi calculado
            peso = 11;
            for (i = 0; i < 10; i++) {
                // xS é a variavel a qual vai receber os valores das somas
                numero = (int) (cpf.charAt(i) - 48);
                soma = soma + (numero * peso);
                // O xpeso sempre diminui de uma soma para a outra
                peso = peso - 1;
            }

            r = 11 - (soma % 11);

            if ((r == 10) || (r == 11)) {
                digito11 = '0';
            } else {
                digito11 = (char) (r + 48);
            }

            //Valida se os numeros informados batem o valor com os numeros
            if ((digito10 == cpf.charAt(9))
                    && (digito11 == cpf.charAt(10))) {
                return (true);
            } else {
                System.out.println("CPF inválido. Digite novamente.");
                return (false);
            }
        } catch (Exception e) {
            System.out.println("Não foi possivel validar o CPF.");
            return (false);
        }
    }
}
