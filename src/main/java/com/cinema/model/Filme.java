package com.cinema.model;

import java.util.Date;

/**
 * A classe Filme representa um filme exibido no cinema.
 */
public class Filme {
    private int codFilme;
    private String nome;
    private int faixaEtaria;
    private String genero;
    private int duracao;
    private Date dataEstreia;
    private Date dataSaidaCartaz;

    /**
     * Construtor para inicializar um filme com todas as informações, incluindo o código.
     *
     * @param codFilme O código do filme.
     * @param nome O nome do filme.
     * @param faixaEtaria A faixa etária do filme.
     * @param genero O gênero do filme.
     * @param duracao A duração do filme em minutos.
     * @param dataEstreia A data de estreia do filme em cartaz.
     * @param dataSaidaCartaz A data de saída do filme do cartaz.
     */
    public Filme(int codFilme, String nome, int faixaEtaria, String genero, int duracao, Date dataEstreia, Date dataSaidaCartaz) {
        this.codFilme = codFilme;
        this.nome = nome;
        this.faixaEtaria = faixaEtaria;
        this.genero = genero;
        this.duracao = duracao;
        this.dataEstreia = dataEstreia;
        this.dataSaidaCartaz = dataSaidaCartaz;
    }

    /**
     * Construtor para inicializar um filme com todas as informações, exceto o código.
     *
     * @param nome O nome do filme.
     * @param faixaEtaria A faixa etária do filme.
     * @param genero O gênero do filme.
     * @param duracao A duração do filme em minutos.
     * @param dataEstreia A data de estreia do filme em cartaz.
     * @param dataSaidaCartaz A data de saída do filme do cartaz.
     */
    public Filme(String nome, int faixaEtaria, String genero, int duracao, Date dataEstreia, Date dataSaidaCartaz){
        this.nome = nome;
        this.faixaEtaria = faixaEtaria;
        this.genero = genero;
        this.duracao = duracao;
        this.dataEstreia = dataEstreia;
        this.dataSaidaCartaz = dataSaidaCartaz;
    }

    public int getCodFilme() {
        return codFilme;
    }

    public void setCodFilme(int codFilme) {
        this.codFilme = codFilme;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getFaixaEtaria() {
        return faixaEtaria;
    }

    public void setFaixaEtaria(int faixaEtaria) {
        this.faixaEtaria = faixaEtaria;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    public Date getDataEstreia() { return dataEstreia; }

    public void setDataEstreia(Date dataEstreia) {
        this.dataEstreia = dataEstreia;
    }

    public Date getDataSaidaCartaz() {
        return dataSaidaCartaz;
    }

    public void setDataSaidaCartaz(Date dataSaidaCartaz) {
        this.dataSaidaCartaz = dataSaidaCartaz;
    }

    @Override
    public String toString() {
        return  "\nNome = " + this.nome +
                "\nFaixa etaria = " + this.faixaEtaria + " anos" +
                "\nGenero = " + this.genero +
                "\nData de estreia em cartaz = " + this.dataEstreia +
                "\nData de saída do cartaz = " + this.dataSaidaCartaz +
                "\n-----------------------------------------------------------";
    }
}
