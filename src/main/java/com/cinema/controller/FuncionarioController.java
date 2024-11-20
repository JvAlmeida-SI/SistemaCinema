package com.cinema.controller;

import com.cinema.model.Funcionario;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe controladora para gerenciar operações relacionadas a FUNCIONÁRIOS no sistema.
 */
public class FuncionarioController extends ValidaPessoa {
    private List<Funcionario> funcionarios;

    /**
     * Construtor da classe FuncionarioController.
     * Inicializa a lista de funcionários buscando-os do arquivo JSON.
     *
     * @throws IOException se ocorrer um erro de E/S durante a busca dos funcionários.
     */
    public FuncionarioController() throws IOException {
        this.funcionarios = mJson.buscarFuncionario();
        if (this.funcionarios == null){
            this.funcionarios = new ArrayList<>();
        }
    }

    /**
     * Método para cadastrar um novo funcionário.
     *
     * @throws IOException se ocorrer um erro de E/S durante o cadastro do funcionário.
     */
    public void cadastroFuncionario() throws IOException {
        String nomeFuncionario, sobrenomeFuncionario, CPF, telefoneFuncionario, emailFuncionario, enderecoFuncionario, loginFuncionario, senhaFuncionario;

        do {
            System.out.printf("Nome: ");
            nomeFuncionario = input.nextLine();
        } while (nomeFuncionario.isEmpty());

        do {
            System.out.printf("Sobrenome: ");
            sobrenomeFuncionario = input.nextLine();
        } while (sobrenomeFuncionario.isEmpty());

        do {
            System.out.printf("CPF: ");
            CPF = input.nextLine();

            if(ValidaCPF(CPF) == false){
                if (consultaFuncionario(CPF) != null) { System.out.println("CPF informado já está cadastrado no sistema."); }
            }
        } while (!ValidaPessoa.ValidaCPF(CPF) || consultaFuncionario(CPF) != null);

        loginFuncionario = CPF;

        do {
            System.out.printf("E-mail: ");
            emailFuncionario = input.nextLine();
        } while (emailFuncionario.isEmpty());

        do {
            System.out.printf("Telefone: ");
            telefoneFuncionario = input.nextLine();
        } while (telefoneFuncionario.isEmpty());

        do {
            System.out.printf("Endereço: ");
            enderecoFuncionario = input.nextLine();
        } while (enderecoFuncionario.isEmpty());

        do {
            System.out.printf("Senha: ");
            senhaFuncionario = input.nextLine();
        } while (senhaFuncionario.isEmpty());

        //Cadastrando novo funcionario
        Funcionario novoFuncionario = new Funcionario(nomeFuncionario, sobrenomeFuncionario, CPF, emailFuncionario, telefoneFuncionario, enderecoFuncionario, loginFuncionario, senhaFuncionario);
        funcionarios.add(novoFuncionario);
        if (mJson.salvarFuncionario(funcionarios))  { System.out.println("Novo funcionario cadastrado com sucesso!"); }
        else { System.out.println("Falha ao gravar os dados do colaborador."); }
    }

    /**
     * Método para consultar um funcionário pelo CPF.
     *
     * @param cpf o CPF do funcionário a ser consultado.
     * @return o objeto Funcionario correspondente ao CPF especificado, ou null se não encontrado.
     * @throws IOException se ocorrer um erro de E/S durante a consulta do funcionário.
     */
    public Funcionario consultaFuncionario(String cpf) throws IOException {
        String cpfFuncionario = cpf;
        List<Funcionario> listaFuncionarios = mJson.buscarFuncionario();

        for (Funcionario funcionario : listaFuncionarios){
            if (funcionario != null){
                if (cpfFuncionario.equals(funcionario.getCpf())){
                    return funcionario;
                }
            }
        }
        return null;
    }

    /**
     * Método para excluir um funcionário pelo CPF.
     *
     * @param CPF o CPF do funcionário a ser excluído.
     * @throws IOException se ocorrer um erro de E/S durante a exclusão do funcionário.
     */
    public void excluirFuncionario(String CPF) throws IOException {
        String cpf = CPF;
        List<Funcionario> funcionarios = null;

        try {
            funcionarios = mJson.buscarFuncionario();

            for (Funcionario i : funcionarios) {
                if (cpf.equals(i.getCpf())) {
                    funcionarios.remove(i);
                    if (mJson.salvarFuncionario(funcionarios)) {
                        System.out.println("Funcionário excluído com sucesso!");
                    }
                    break;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Método para editar as informações de um funcionário pelo CPF.
     *
     * @param cpf o CPF do funcionário a ser editado.
     * @throws IOException se ocorrer um erro de E/S durante a edição do funcionário.
     */
    public void editarFuncionario(String cpf) throws IOException{
        Integer i;
        Funcionario funcionario;
        List<Funcionario> listaFuncionarios = mJson.buscarFuncionario();
        List<Funcionario> listaFuncionariosAtualizado = new ArrayList<>();

        if (ValidaPessoa.ValidaCPF(cpf)) {
            funcionario = consultaFuncionario(cpf);
            System.out.println("INFORME A INFORMAÇÃO QUE DESEJA ALTERAR: " +
                    "\n1 - Nome" +
                    "\n2 - Sobrenome" +
                    "\n3 - Endereço" +
                    "\n4 - Telefone" +
                    "\n5 - E-mail" +
                    "\n6 - Login" +
                    "\n7 - Senha");
            i = input.nextInt();
            input.nextLine();

            switch (i) {
                case 1:
                    System.out.println("Infome o novo nome que deseja salvar: ");
                    String nomeFuncionario = input.nextLine();
                    while (nomeFuncionario.isEmpty()){
                        System.out.println("Esta informação não pode ficar em branco.");
                        System.out.println("Infome o novo nome que deseja salvar: ");
                        nomeFuncionario = input.nextLine();
                    }
                    funcionario.setNome(nomeFuncionario);
                    break;
                case 2:
                    System.out.println("Infome o novo sobrenome que deseja salvar: ");
                    String sobrenomenomeFuncionario = input.nextLine();
                    while (sobrenomenomeFuncionario.isEmpty()){
                        System.out.println("Esta informação não pode ficar em branco.");
                        System.out.println("Infome o novo sobrenome que deseja salvar: ");
                        sobrenomenomeFuncionario = input.nextLine();
                    }
                    funcionario.setSobrenome(sobrenomenomeFuncionario);
                    break;
                case 3:
                    System.out.println("Infome o novo endereço que deseja salvar: ");
                    String enderecoFuncionario = input.nextLine();
                    while (enderecoFuncionario.isEmpty()){
                        System.out.println("Esta informação não pode ficar em branco.");
                        System.out.println("Infome o novo endereço que deseja salvar: ");
                        enderecoFuncionario = input.nextLine();
                    }
                    funcionario.setEndereco(enderecoFuncionario);
                    break;
                case 4:
                    System.out.println("Infome o novo telefone que deseja salvar: ");
                    String telefoneFuncionario = input.next();
                    while (telefoneFuncionario.isEmpty()){
                        System.out.println("Esta informação não pode ficar em branco.");
                        System.out.println("Infome o novo telefone que deseja salvar: ");
                        telefoneFuncionario = input.nextLine();
                    }
                    funcionario.setTelefone(telefoneFuncionario);
                    break;
                case 5:
                    System.out.println("Infome o novo e-mail que deseja salvar: ");
                    String emailFuncionario = input.next();
                    while (emailFuncionario.isEmpty()){
                        System.out.println("Esta informação não pode ficar em branco.");
                        System.out.println("Infome o novo e-mail que deseja salvar: ");
                        emailFuncionario = input.nextLine();
                    }
                    funcionario.setEmail(emailFuncionario);
                    break;
                case 6:
                    System.out.println("Infome o novo login que deseja salvar: ");
                    String loginFuncionario = input.next();
                    while (loginFuncionario.isEmpty()){
                        System.out.println("Esta informação não pode ficar em branco.");
                        System.out.println("Infome o novo login que deseja salvar: ");
                        loginFuncionario = input.nextLine();
                    }
                    funcionario.setLogin(loginFuncionario);
                    break;
                case 7:
                    System.out.println("Infome a nova senha que deseja salvar: ");
                    String senhaFuncionario = input.next();
                    while (senhaFuncionario.isEmpty()){
                        System.out.println("Esta informação não pode ficar em branco.");
                        System.out.println("Infome a nova senha que deseja salvar: ");
                        senhaFuncionario = input.nextLine();
                    }
                    funcionario.setSenha(senhaFuncionario);
                    break;
                default:
                    System.out.println("Opção inválida.");
                    break;
            }

            for (Funcionario f : listaFuncionarios){
                if (funcionario.getCpf().equals(f.getCpf())){
                    switch (i){
                        case 1:
                            f.setNome(funcionario.getNome());
                            break;
                        case 2:
                            f.setSobrenome(funcionario.getSobrenome());
                            break;
                        case 3:
                            f.setEndereco(funcionario.getEndereco());
                            break;
                        case 4:
                            f.setTelefone(funcionario.getTelefone());
                            break;
                        case 5:
                            f.setEmail(funcionario.getEmail());
                            break;
                        case 6:
                            f.setLogin(funcionario.getLogin());
                            break;
                        case 7:
                            f.setSenha(funcionario.getSenha());
                            break;
                    }
                }
                listaFuncionariosAtualizado.add(f);
            }

            if (mJson.salvarFuncionario(listaFuncionariosAtualizado)){
                System.out.println("Funcionário editado com sucesso!");
            } else {
                System.out.println("Erro ao salvar as alterações do funcionário.");
            }
        }
    }
}
