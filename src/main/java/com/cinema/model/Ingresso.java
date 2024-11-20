package com.cinema.model;

import com.cinema.model.Filme;

import java.util.Date;

/**
 * A classe Ingresso representa um ingresso para um filme em uma sala de cinema.
 */
public class Ingresso {
    private int codIngresso;
    private Filme filme;
    private Sala sala;
    private Double valor;

    /**
     * Construtor para inicializar um ingresso com todas as informações.
     *
     * @param codIngresso O código do ingresso.
     * @param filme O filme associado ao ingresso.
     * @param sala A sala onde o filme será exibido.
     * @param valor O valor do ingresso.
     */
    public Ingresso(int codIngresso, Filme filme, Sala sala, Double valor) {
        this.codIngresso = codIngresso;
        this.filme = filme;
        this.sala = sala;
        this.valor = valor;
    }

    /**
     * Construtor para inicializar um ingresso com todas as informações, exceto o código.
     *
     * @param filme O filme associado ao ingresso.
     * @param sala A sala onde o filme será exibido.
     * @param valor O valor do ingresso.
     */
    public Ingresso(Filme filme, Sala sala, Double valor) {
        this.filme = filme;
        this.sala = sala;
        this.valor = valor;
    }

    /**
     * Construtor vazio.
     */
    public Ingresso() {
    }

    public int getCodIngresso() {
        return codIngresso;
    }

    public void setCodIngresso(int codIngresso) {
        this.codIngresso = codIngresso;
    }

    public Filme getFilme() {
        return filme;
    }

    public void setFilme(Filme filme) {
        this.filme = filme;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    /**
     * Retorna uma representação em forma de String do ingresso.
     *
     * @return Uma String contendo informações do ingresso.
     */
    @Override
    public String toString() {
        return  "\nFilme = " + this.filme +
                "\nSala = " + this.sala +
                "\nPreço = R$" + this.valor +
                "\n-----------------------------------------------------------";
    }
}
