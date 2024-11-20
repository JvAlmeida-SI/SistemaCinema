package com.cinema.model;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * A classe Carrinho representa um carrinho de compras em um sistema de venda de ingressos e alimentos.
 */
public class Carrinho {
    private int codVenda;
    private List<Ingresso> ingresso;
    private Cliente cliente;
    private List<Comida> comidas;
    private Double valorTotal;
    private Date dataVenda;
    int numBalcao;

    /**
     * Construtor para inicializar um carrinho com todas as informações necessárias.
     *
     * @param codVenda O código de venda do carrinho.
     * @param ingresso A lista de ingressos contidos no carrinho.
     * @param cliente O cliente associado ao carrinho.
     * @param comidas A lista de comidas contidas no carrinho.
     * @param valorTotal O valor total da compra.
     * @param dataVenda A data da realização da venda.
     * @param numBalcao O número do balcão associado à venda.
     */
    public Carrinho(int codVenda, List<Ingresso> ingresso, Cliente cliente, List<Comida> comidas, Double valorTotal, Date dataVenda, int numBalcao) {
        this.codVenda = codVenda;
        this.ingresso = ingresso;
        this.cliente = cliente;
        this.comidas = comidas;
        this.valorTotal = valorTotal;
        this.dataVenda = dataVenda;
        this.numBalcao = numBalcao;
    }

    /**
     * Construtor para inicializar um carrinho com todas as informações necessárias, exceto o código de venda e o número do balcão.
     *
     * @param ingresso A lista de ingressos contidos no carrinho.
     * @param cliente O cliente associado ao carrinho.
     * @param comidas A lista de comidas contidas no carrinho.
     * @param valorTotal O valor total da compra.
     * @param dataVenda A data da realização da venda.
     */
    public Carrinho (List<Ingresso> ingresso, Cliente cliente, List<Comida> comidas, Double valorTotal, Date dataVenda) {
        this.ingresso = ingresso;
        this.cliente = cliente;
        this.comidas = comidas;
        this.valorTotal = valorTotal;
        this.dataVenda = dataVenda;
    }

    /**
     * Construtor para inicializar um carrinho com apenas o valor total da compra e a data da venda.
     *
     * @param valorTotal O valor total da compra.
     * @param dataVenda A data da realização da venda.
     */
    public Carrinho (Double valorTotal, Date dataVenda){
        this.valorTotal = valorTotal;
        this.dataVenda = dataVenda;
    }

    public Carrinho() {
    }

    public Carrinho(double valor, Date dataVenda) {
        this.valorTotal = valor;
        this.dataVenda = dataVenda;
    }

    // Métodos getters e setters
    public int getCodVenda() {
        return codVenda;
    }

    public void setCodVenda(int codVenda) {
        this.codVenda = codVenda;
    }

    public List<Ingresso> getIngresso() { return ingresso; }

    public void setIngresso(List<Ingresso> ingresso) { this.ingresso = ingresso; }

    public Cliente getCliente() { return cliente; }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Comida> getComidas() {
        return comidas;
    }

    public void setComidas(List<Comida> comidas) {
        this.comidas = comidas;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Date getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(Date dataVenda) {
        this.dataVenda = dataVenda;
    }

    public int getNumBalcao() {
        return numBalcao;
    }

    public void setNumBalcao(int numBalcao) {
        this.numBalcao = numBalcao;
    }

    @Override
    public String toString() {
        return "\nIngresso = " + this.ingresso +
                "\nCliente = " + this.cliente +
                "\nComidas = " + this.comidas +
                "\nValor Total = " + this.valorTotal +
                "\nData da Venda = " + this.dataVenda +
                "\nNúmero do Balcao = " + this.numBalcao +
                "\n--------------------------------------------";
    }
}
