package com.cinema.controller;

import com.cinema.model.Filme;

import java.util.Comparator;

/**
 * Classe que implementa a interface Comparator para comparar objetos da classe Filme.
 * A comparação é feita com base no nome dos filmes, ignorando diferenças entre maiúsculas e minúsculas.
 */
public class FilmeComparator  implements Comparator<Filme> {
    /**
     * Compara dois objetos Filme com base em seus nomes.
     *
     * @param f1 o primeiro objeto Filme a ser comparado.
     * @param f2 o segundo objeto Filme a ser comparado.
     * @return um valor negativo, zero ou positivo se o nome do primeiro filme for, respectivamente,
     * menor, igual ou maior que o nome do segundo filme, ignorando diferenças entre maiúsculas e minúsculas.
     */
    @Override
    public int compare(Filme f1, Filme f2) {
        return f1.getNome().compareToIgnoreCase(f2.getNome());
    }
}