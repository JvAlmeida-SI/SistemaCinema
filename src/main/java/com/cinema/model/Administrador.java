package com.cinema.model;

/**
 * Classe que representa um Administrador, que é um tipo especial de Funcionário.
 */
public class Administrador extends Funcionario {

    /**
     * Construtor para inicializar um Administrador com os detalhes fornecidos.
     *
     * @param nome O nome do administrador.
     * @param sobrenome O sobrenome do administrador.
     * @param cpf O CPF do administrador.
     * @param email O e-mail do administrador.
     * @param telefone O telefone do administrador.
     * @param endereco O endereço do administrador.
     * @param login O login do administrador.
     * @param senha A senha do administrador.
     */
    public Administrador(String nome, String sobrenome, String cpf, String email, String telefone, String endereco, String login, String senha) {
        super(nome, sobrenome, cpf, email, telefone, endereco, login, senha);
    }

    /**
     * Construtor padrão que não recebe parâmetros.
     */
    public Administrador() {

    }

    /**
     * Método para representar o Administrador como uma string formatada.
     *
     * @return Uma string contendo os detalhes do administrador.
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