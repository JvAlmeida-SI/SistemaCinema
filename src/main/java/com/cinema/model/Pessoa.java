package com.cinema.model;

/**
 * Classe abstrata que representa uma pessoa genérica.
 */

public abstract class Pessoa {
    // Atributos da pessoa
    private String login;

    private String senha;

    private String nome;

    private String sobrenome;

    private String cpf;

    private String email;

    private String telefone;

    private String endereco;

    /**
     * Construtor padrão da classe.
     */
    public Pessoa() {
        
    }

    /**
     * Construtor da classe com todos os atributos.
     *
     * @param nome      O nome da pessoa.
     * @param sobrenome O sobrenome da pessoa.
     * @param cpf       O CPF da pessoa.
     * @param email     O email da pessoa.
     * @param telefone  O telefone da pessoa.
     * @param endereco  O endereço da pessoa.
     * @param login     O login da pessoa.
     * @param senha     A senha da pessoa.
     */
    public Pessoa(String nome, String sobrenome, String cpf, String email, String telefone, String endereco, String login, String senha) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
        this.endereco = endereco;
        this.login = login;
        this.senha = senha;
    }

    /**
     * Construtor da classe apenas com o nome.
     *
     * @param nome O nome da pessoa.
     */
    public Pessoa(String nome) {
        this.nome = nome;
    }

    // Getters e setters para os atributos da pessoa
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

}
