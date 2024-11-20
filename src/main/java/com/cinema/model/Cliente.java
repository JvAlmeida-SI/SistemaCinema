package com.cinema.model;

import java.util.Set;

/**
 * A classe Cliente representa um cliente no sistema de cinema.
 */
public class Cliente extends Pessoa {
    // Encapsulamento e segurança
    private static int numClientes = 0;
    protected int numClientes2 = 0;
    private Set<Filme> preferenciasFilme;

    /**
     * Construtor padrão para inicializar um cliente.
     */
    public Cliente(){
        super();
        numClientes2++;
    }

    /**
     * Construtor para inicializar um cliente com todas as informações necessárias.
     *
     * @param nome O nome do cliente.
     * @param sobrenome O sobrenome do cliente.
     * @param cpf O CPF do cliente.
     * @param telefone O telefone do cliente.
     * @param email O email do cliente.
     * @param endereco O endereço do cliente.
     * @param login O login do cliente.
     * @param senha A senha do cliente.
     */
    public Cliente(String nome, String sobrenome, String cpf, String telefone, String email, String endereco, String login, String senha) {
        super(nome, sobrenome, cpf, email, telefone, endereco, login, senha);
        numClientes2++;
    }

    /**
     * Construtor para inicializar um cliente com apenas o nome.
     *
     * @param nome O nome do cliente.
     */
    public Cliente(String nome) {
        super(nome);
    }


    public static int getNumClientes() {
        return numClientes;
    }

    public int getNumClientes2() {
        return getNumClientes();
    }

    public Set<Filme> getPreferenciasFilme() {
        return preferenciasFilme;
    }

    public void setPreferenciasFilme(Set<Filme> preferenciasFilme) {
        this.preferenciasFilme = preferenciasFilme;
    }

    @Override
    public String toString() {
        return    "\nNOME = " + this.getNome()
                + "\nSOBRENOME = " + this.getSobrenome()
                + "\nTELEFONE = " + this.getTelefone()
                + "\nCPF = " + this.getCpf()
                + "\nENDEREÇO = " + this.getEndereco()
                + "\nE-MAIL = " + this.getEmail()
                + "\nSENHA = " + this.getSenha()
                + "\n------------------------------------------------------------------------";
    }
}