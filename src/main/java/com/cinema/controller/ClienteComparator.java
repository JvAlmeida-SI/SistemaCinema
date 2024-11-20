package com.cinema.controller;

import com.cinema.model.Cliente;

import java.util.Comparator;

/**
 * Comparator para ordenar objetos da classe Cliente pelo nome em ordem alfabética.
 */
public class ClienteComparator implements Comparator<Cliente> {

    /**
     * Compara dois objetos Cliente pelo nome, ignorando diferenças entre maiúsculas e minúsculas.
     *
     * @param c1 o primeiro cliente a ser comparado.
     * @param c2 o segundo cliente a ser comparado.
     * @return um valor negativo, zero ou positivo conforme o nome do primeiro cliente seja
     *         menor, igual ou maior que o nome do segundo cliente, respectivamente.
     */
    @Override
    public int compare(Cliente c1, Cliente c2) {
        return c1.getNome().compareToIgnoreCase(c2.getNome());
    }
}
