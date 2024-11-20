package com.cinema.model;

/**
 * A classe Funcionario representa um funcionário do cinema.
 */
public class Funcionario extends Pessoa {

    /**
     * Construtor para inicializar um funcionário com todas as informações.
     *
     * @param nome O nome do funcionário.
     * @param sobrenome O sobrenome do funcionário.
     * @param cpf O CPF do funcionário.
     * @param email O email do funcionário.
     * @param telefone O telefone do funcionário.
     * @param endereco O endereço do funcionário.
     * @param login O login do funcionário.
     * @param senha A senha do funcionário.
     */
    public Funcionario(String nome, String sobrenome, String cpf, String email, String telefone, String endereco, String login, String senha) {
        super(nome, sobrenome, cpf, email, telefone, endereco, login, senha);
    }

    /**
     * Construtor vazio.
     */
    public Funcionario(){
        super();
    }

    /**
     * Sobrescrita do método getCpf() para acessar o CPF do funcionário.
     *
     * @return O CPF do funcionário.
     */
    @Override
    public String getCpf() {
        return super.getCpf();
    }

    /**
     * Retorna uma representação em forma de String do funcionário.
     *
     * @return Uma String contendo informações do funcionário.
     */
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
