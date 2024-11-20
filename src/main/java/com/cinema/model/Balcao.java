package com.cinema.model;

import java.util.Date;
import java.util.List;

/**
 * A classe Balcao representa o balcão de vendas de um cinema.
 */
public class Balcao {
    private List<Carrinho> vendas;
    private manipularJsonVendas mJson = new manipularJsonVendas();

    /**
     * Construtor para inicializar um balcão com as vendas registradas.
     */
    public Balcao() {
        this.vendas = mJson.buscarVendas();
    }


    /**
     * Obtém o balanço financeiro diário para um determinado dia e balcão de atendimento.
     *
     * @param date A data para a qual o balanço diário deve ser calculado.
     * @param numBalcaoAtendimento O número do balcão de atendimento para o qual o balanço deve ser calculado.
     * @return O balanço financeiro diário para o dia e balcão de atendimento especificados.
     */
    public double obterBalancoDiario(Date date, int numBalcaoAtendimento) {
        return vendas.stream()
                .filter(venda -> venda.getDataVenda().equals(date) && venda.getNumBalcao() == numBalcaoAtendimento)
                .mapToDouble(Carrinho::getValorTotal)
                .sum();
    }

    /**
     * Obtém o balanço financeiro mensal para um período específico e balcão de atendimento.
     *
     * @param dataInicio A data de início do período para o qual o balanço mensal deve ser calculado.
     * @param dataFinal A data final do período para o qual o balanço mensal deve ser calculado.
     * @param numBalcaoAtendimento O número do balcão de atendimento para o qual o balanço deve ser calculado.
     * @return O balanço financeiro mensal para o período e balcão de atendimento especificados.
     */
    public double obterBalancoMensal(Date dataInicio, Date dataFinal, int numBalcaoAtendimento) {
        vendas = mJson.buscarVendas();
        return vendas.stream()
                .filter(venda -> venda.getDataVenda().before(dataInicio) && !(venda.getDataVenda().before(dataFinal)) && venda.getNumBalcao() == numBalcaoAtendimento)
                .mapToDouble(Carrinho::getValorTotal)
                .sum();
    }
}

