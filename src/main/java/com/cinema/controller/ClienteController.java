package com.cinema.controller;

import com.cinema.model.Cliente;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Controlador responsável pela gestão dos clientes.
 */
public class ClienteController extends ValidaPessoa {
    private List<Cliente> clientes;

    /**
     * Construtor do ClienteController que inicializa a lista de clientes a partir de um arquivo JSON.
     *
     * @throws IOException se ocorrer um erro de entrada/saída durante a leitura do arquivo.
     */
    public ClienteController() throws IOException {
        this.clientes = mJson.buscarCliente();
        if (this.clientes == null){
            this.clientes = new ArrayList<>();
        }
    }

    /**
     * Método para cadastrar um novo cliente, solicitando as informações necessárias ao usuário.
     *
     * @throws IOException se ocorrer um erro de entrada/saída durante a gravação do cliente.
     */
    public void cadastroCliente() throws IOException {
        String nomeCliente, sobrenomeCliente, CPF, telefoneCliente, emailCliente, enderecoCliente, loginCliente, senhaCliente;

        do {
            System.out.printf("Nome: ");
            nomeCliente = input.nextLine();
        } while (nomeCliente.isEmpty());

        do {
            System.out.printf("Sobrenome: ");
            sobrenomeCliente = input.nextLine();
        } while (sobrenomeCliente.isEmpty());

        do {
            System.out.printf("CPF: ");
            CPF = input.nextLine();

            if(ValidaCPF(CPF) == false || consultaCliente(CPF) != null){
                System.out.println("CPF informado já está cadastrado no sistema.");
            }
        } while (!AdministradorController.ValidaCPF(CPF) || consultaCliente(CPF) != null);

        loginCliente = CPF;

        do {
            System.out.printf("E-mail: ");
            emailCliente = input.nextLine();
        } while (emailCliente.isEmpty());

        do {
            System.out.printf("Telefone: ");
            telefoneCliente = input.nextLine();
        } while (telefoneCliente.isEmpty());

        do {
            System.out.printf("Endereço: ");
            enderecoCliente = input.nextLine();
        } while (enderecoCliente.isEmpty());

        do {
            System.out.printf("Senha: ");
            senhaCliente = input.nextLine();
        } while (senhaCliente.isEmpty());

        //Cadastrando novo cliente
        Cliente novoCliente = new Cliente(nomeCliente, sobrenomeCliente, CPF, telefoneCliente, emailCliente, enderecoCliente, loginCliente, senhaCliente);
        clientes.add(novoCliente);
        if (mJson.salvarCliente(clientes))  { System.out.println("Novo cliente cadastrado com sucesso!"); }
        else { System.out.println("Falha ao gravar os dados."); }
    }

    /**
     * Método para consultar um cliente pelo CPF.
     *
     * @param cpf o CPF do cliente a ser consultado.
     * @return o cliente encontrado ou null se não encontrado.
     * @throws IOException se ocorrer um erro de entrada/saída durante a consulta.
     */
    public Cliente consultaCliente(String cpf) throws IOException {
        String cpfCliente = cpf;
        List<Cliente> listaClientes = mJson.buscarCliente();
        Iterator<Cliente> iterator = listaClientes.iterator();

        while (iterator.hasNext()){
            Cliente cliente = iterator.next();
            if (cliente != null && cpfCliente.equals(cliente.getCpf())){
                return cliente;
            }
        }
        return null;
    }

    /**
     * Método para consultar todos os clientes e ordená-los por nome.
     *
     * @return a lista de clientes ordenada.
     * @throws IOException se ocorrer um erro de entrada/saída durante a consulta.
     */
    public List<Cliente> consultaTodosClientes() throws IOException {
        List<Cliente> listaClientes = mJson.buscarCliente();

        Collections.sort(listaClientes, new ClienteComparator());

        return listaClientes;
    }

    /**
     * Método para excluir um cliente pelo CPF.
     *
     * @param CPF o CPF do cliente a ser excluído.
     * @throws IOException se ocorrer um erro de entrada/saída durante a exclusão.
     */
    public void excluirCliente(String CPF) throws IOException {
        String cpf = CPF;
        List<Cliente> listaClientes = mJson.buscarCliente();
        Iterator<Cliente> iterator = listaClientes.iterator();

        try {
            while (iterator.hasNext()) {
                Cliente cliente = iterator.next();
                if (cpf.equals(cliente.getCpf())) {
                    clientes.remove(cliente);
                    if (mJson.salvarCliente(listaClientes)) {
                        System.out.println("Cliente excluído com sucesso!");
                    }
                    break;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Método para editar as informações de um cliente pelo CPF.
     *
     * @param cpf o CPF do cliente a ser editado.
     * @throws IOException se ocorrer um erro de entrada/saída durante a edição.
     */
    public void editarCliente(String cpf) throws IOException {
        Integer i;
        Cliente cliente;
        List<Cliente> listaClientes = mJson.buscarCliente();
        List<Cliente> listaClientesAtualizado = new ArrayList<>();

        if (ValidaCPF(cpf)) {
            cliente = consultaCliente(cpf);
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
                    String nomeCliente = input.nextLine();
                    while (nomeCliente.isEmpty()) {
                        System.out.println("Esta informação não pode ficar em branco.");
                        System.out.println("Infome o novo nome que deseja salvar: ");
                        nomeCliente = input.nextLine();
                    }
                    cliente.setNome(nomeCliente);
                    break;
                case 2:
                    System.out.println("Infome o novo sobrenome que deseja salvar: ");
                    String sobrenomenomeCliente = input.nextLine();
                    while (sobrenomenomeCliente.isEmpty()) {
                        System.out.println("Esta informação não pode ficar em branco.");
                        System.out.println("Infome o novo sobrenome que deseja salvar: ");
                        sobrenomenomeCliente = input.nextLine();
                    }
                    cliente.setSobrenome(sobrenomenomeCliente);
                    break;
                case 3:
                    System.out.println("Infome o novo endereço que deseja salvar: ");
                    String enderecoCliente = input.nextLine();
                    while (enderecoCliente.isEmpty()) {
                        System.out.println("Esta informação não pode ficar em branco.");
                        System.out.println("Infome o novo endereço que deseja salvar: ");
                        enderecoCliente = input.nextLine();
                    }
                    cliente.setEndereco(enderecoCliente);
                    break;
                case 4:
                    System.out.println("Infome o novo telefone que deseja salvar: ");
                    String telefoneCliente = input.next();
                    while (telefoneCliente.isEmpty()) {
                        System.out.println("Esta informação não pode ficar em branco.");
                        System.out.println("Infome o novo telefone que deseja salvar: ");
                        telefoneCliente = input.nextLine();
                    }
                    cliente.setTelefone(telefoneCliente);
                    break;
                case 5:
                    System.out.println("Infome o novo e-mail que deseja salvar: ");
                    String emailCliente = input.next();
                    while (emailCliente.isEmpty()) {
                        System.out.println("Esta informação não pode ficar em branco.");
                        System.out.println("Infome o novo e-mail que deseja salvar: ");
                        emailCliente = input.nextLine();
                    }
                    cliente.setEmail(emailCliente);
                    break;
                case 6:
                    System.out.println("Infome o novo login que deseja salvar: ");
                    String loginCliente = input.next();
                    while (loginCliente.isEmpty()) {
                        System.out.println("Esta informação não pode ficar em branco.");
                        System.out.println("Infome o novo login que deseja salvar: ");
                        loginCliente = input.nextLine();
                    }
                    cliente.setLogin(loginCliente);
                    break;
                case 7:
                    System.out.println("Infome a nova senha que deseja salvar: ");
                    String senhaCliente = input.next();
                    while (senhaCliente.isEmpty()) {
                        System.out.println("Esta informação não pode ficar em branco.");
                        System.out.println("Infome a nova senha que deseja salvar: ");
                        senhaCliente = input.nextLine();
                    }
                    cliente.setSenha(senhaCliente);
                    break;
                default:
                    System.out.println("Opção inválida.");
                    break;
            }

            for (Cliente clt : listaClientes) {
                if (cliente.getCpf().equals(clt.getCpf())) {
                    switch (i) {
                        case 1:
                            clt.setNome(cliente.getNome());
                            break;
                        case 2:
                            clt.setSobrenome(cliente.getSobrenome());
                            break;
                        case 3:
                            clt.setEndereco(cliente.getEndereco());
                            break;
                        case 4:
                            clt.setTelefone(cliente.getTelefone());
                            break;
                        case 5:
                            clt.setEmail(cliente.getEmail());
                            break;
                        case 6:
                            clt.setLogin(cliente.getLogin());
                            break;
                        case 7:
                            clt.setSenha(cliente.getSenha());
                            break;
                    }
                }
                listaClientesAtualizado.add(clt);
            }

            if (mJson.salvarCliente(listaClientesAtualizado)) {
                System.out.println("Cliente editado com sucesso!");
            } else {
                System.out.println("Erro ao salvar as alterações do cliente.");
            }
        }
    }


    /**
     * Método estático para encontrar um cliente pelo nome em um Iterator de clientes.
     *
     * @param iterator o Iterator de clientes.
     * @param nome o nome do cliente a ser encontrado.
     * @return o cliente encontrado ou null se não encontrado.
     */
    public static Cliente findCliente(Iterator<Cliente> iterator, String nome) {
        while (iterator.hasNext()) {
            Cliente cliente = iterator.next();
            if (cliente.getNome().equals(nome)) {
                return cliente;
            }
        }
        return null;
    }
}
