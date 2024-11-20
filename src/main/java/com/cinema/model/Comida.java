package com.cinema.model;

import java.util.Date;

/**
 * A classe Comida representa um item de comida vendido no cinema.
 */
public class Comida {
    private int codComida;
    private String nome;
    private double preco;
    private int quantidade;
    private Date dataValidade;

    /**
     * Construtor para inicializar uma comida com todas as informações necessárias.
     *
     * @param nome O nome da comida.
     * @param preco O preço da comida.
     * @param quantidade A quantidade disponível da comida.
     * @param dataValidade A data de validade da comida.
     */
    public Comida(String nome, double preco, int quantidade, Date dataValidade) {
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
        this.dataValidade = dataValidade;
    }

    /**
     * Construtor para inicializar uma comida com todas as informações, incluindo o código.
     *
     * @param codComida O código da comida.
     * @param nome O nome da comida.
     * @param preco O preço da comida.
     * @param quantidade A quantidade disponível da comida.
     * @param dataValidade A data de validade da comida.
     */
    public Comida(int codComida, String nome, double preco, int quantidade, Date dataValidade) {
        this.codComida = codComida;
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
        this.dataValidade = dataValidade;
    }

    public int getCodComida() {
        return codComida;
    }

    public void setCodComida(int codComida) {
        this.codComida = codComida;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Date getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(Date dataValidade) {
        this.dataValidade = dataValidade;
    }

    @Override
    public String toString() {
        return  "\nNome = " + this.nome +
                "\nPreço = R$" + this.preco +
                "\nQuantidade no estoque = " + this.quantidade +
                "\nData de validade = " + this.dataValidade +
                "\n-----------------------------------------------------------";
    }
}
