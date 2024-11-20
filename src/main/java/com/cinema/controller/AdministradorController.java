package com.cinema.controller;

import com.cinema.model.Administrador;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Controller responsável por gerenciar as operações relacionadas aos administradores.
 */
public class AdministradorController extends ValidaPessoa {
    private List<Administrador> administradores;

    /**
     * Construtor da classe AdministradorController.
     * @throws IOException se ocorrer um erro na leitura dos dados dos administradores.
     */
    public AdministradorController() throws IOException {
        this.administradores = mJson.buscarAdministrador();
        if(this.administradores == null){
            this.administradores = new ArrayList<>();
        }
    }

    /**
     * Realiza o cadastro de um novo administrador.
     * @throws IOException se ocorrer um erro na gravação dos dados dos administradores.
     */
    public void cadastroAdministrador() throws IOException {
        String nomeAdministrador, sobrenomeAdministrador, CPF, telefoneAdministrador, emailAdministrador, enderecoAdministrador, loginAdministrador, senhaAdministrador;

        do {
            System.out.printf("Nome: ");
            nomeAdministrador = input.nextLine();
        } while (nomeAdministrador.isEmpty());

        do {
            System.out.printf("Sobrenome: ");
            sobrenomeAdministrador = input.nextLine();
        } while (sobrenomeAdministrador.isEmpty());

        do {
            System.out.printf("CPF: ");
            CPF = input.nextLine();

            if(ValidaCPF(CPF) == false){
                if (consultaAdministrador(CPF) != null) { System.out.println("CPF informado já está cadastrado no sistema."); }
            }
        } while (!AdministradorController.ValidaCPF(CPF) || consultaAdministrador(CPF) != null);

        loginAdministrador = CPF;

        do {
            System.out.printf("E-mail: ");
            emailAdministrador = input.nextLine();
        } while (emailAdministrador.isEmpty());

        do {
            System.out.printf("Telefone: ");
            telefoneAdministrador = input.nextLine();
        } while (telefoneAdministrador.isEmpty());

        do {
            System.out.printf("Endereço: ");
            enderecoAdministrador = input.nextLine();
        } while (enderecoAdministrador.isEmpty());

        do {
            System.out.printf("Senha: ");
            senhaAdministrador = input.nextLine();
        } while (senhaAdministrador.isEmpty());

        //Cadastrando novo administrador
        Administrador novoAdministrador = new Administrador(nomeAdministrador, sobrenomeAdministrador, CPF, emailAdministrador, telefoneAdministrador, enderecoAdministrador, loginAdministrador, senhaAdministrador);
        administradores.add(novoAdministrador);
        if (mJson.salvarAdministrador(administradores))  { System.out.println("Novo administrador cadastrado com sucesso!"); }
        else { System.out.println("Falha ao gravar os dados."); }
    }

    /**
     * Consulta um administrador pelo CPF.
     * @param cpf CPF do administrador a ser consultado.
     * @return Administrador encontrado ou null se não encontrado.
     * @throws IOException se ocorrer um erro na leitura dos dados dos administradores.
     */
    public Administrador consultaAdministrador(String cpf) throws IOException {
        String cpfAdministrador = cpf;
        List<Administrador> listaAdms = mJson.buscarAdministrador();
        Iterator<Administrador> iterator = listaAdms.iterator();

        while (iterator.hasNext()){
            Administrador administrador = iterator.next();
            if (administrador != null && cpfAdministrador.equals(administrador.getCpf())){
                return administrador;
            }
        }
        System.out.println("Administrador não encontrado.");
        return null;
    }

    /**
     * Exclui um administrador pelo CPF.
     * @param CPF CPF do administrador a ser excluído.
     * @throws IOException se ocorrer um erro na leitura ou gravação dos dados dos administradores.
     */
    public void excluirAdministrador(String CPF) throws IOException {
        String cpf = CPF;
        List<Administrador> administradores = null;

        try {
            administradores = mJson.buscarAdministrador();

            for (Administrador i : administradores) {
                if (cpf.equals(i.getCpf())) {
                    administradores.remove(i);
                    if (mJson.salvarAdministrador(administradores)) {
                        System.out.println("Administrador excluído com sucesso!");
                    }
                    break;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Edita as informações de um administrador pelo CPF.
     * @param cpf CPF do administrador a ser editado.
     * @throws IOException se ocorrer um erro na leitura ou gravação dos dados dos administradores.
     */
    public void editarAdministrador(String cpf) throws IOException{
        Integer i;
        Administrador administrador;
        List<Administrador> listaAdministradores = mJson.buscarAdministrador();
        List<Administrador> listaAdministradoresAtualizado = new ArrayList<>();

        if (ValidaCPF(cpf)) {
            administrador = consultaAdministrador(cpf);
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
                    String nomeAdministrador = input.nextLine();
                    while (nomeAdministrador.isEmpty()){
                        System.out.println("Esta informação não pode ficar em branco.");
                        System.out.println("Infome o novo nome que deseja salvar: ");
                        nomeAdministrador = input.nextLine();
                    }
                    administrador.setNome(nomeAdministrador);
                    break;
                case 2:
                    System.out.println("Infome o novo sobrenome que deseja salvar: ");
                    String sobrenomenomeAdministrador = input.nextLine();
                    while (sobrenomenomeAdministrador.isEmpty()){
                        System.out.println("Esta informação não pode ficar em branco.");
                        System.out.println("Infome o novo sobrenome que deseja salvar: ");
                        sobrenomenomeAdministrador = input.nextLine();
                    }
                    administrador.setSobrenome(sobrenomenomeAdministrador);
                    break;
                case 3:
                    System.out.println("Infome o novo endereço que deseja salvar: ");
                    String enderecoAdministrador = input.nextLine();
                    while (enderecoAdministrador.isEmpty()){
                        System.out.println("Esta informação não pode ficar em branco.");
                        System.out.println("Infome o novo endereço que deseja salvar: ");
                        enderecoAdministrador = input.nextLine();
                    }
                    administrador.setEndereco(enderecoAdministrador);
                    break;
                case 4:
                    System.out.println("Infome o novo telefone que deseja salvar: ");
                    String telefoneAdministrador = input.next();
                    while (telefoneAdministrador.isEmpty()){
                        System.out.println("Esta informação não pode ficar em branco.");
                        System.out.println("Infome o novo telefone que deseja salvar: ");
                        telefoneAdministrador = input.nextLine();
                    }
                    administrador.setTelefone(telefoneAdministrador);
                    break;
                case 5:
                    System.out.println("Infome o novo e-mail que deseja salvar: ");
                    String emailAdministrador = input.next();
                    while (emailAdministrador.isEmpty()){
                        System.out.println("Esta informação não pode ficar em branco.");
                        System.out.println("Infome o novo e-mail que deseja salvar: ");
                        emailAdministrador = input.nextLine();
                    }
                    administrador.setEmail(emailAdministrador);
                    break;
                case 6:
                    System.out.println("Infome o novo login que deseja salvar: ");
                    String loginAdministrador = input.next();
                    while (loginAdministrador.isEmpty()){
                        System.out.println("Esta informação não pode ficar em branco.");
                        System.out.println("Infome o novo login que deseja salvar: ");
                        loginAdministrador = input.nextLine();
                    }
                    administrador.setLogin(loginAdministrador);
                    break;
                case 7:
                    System.out.println("Infome a nova senha que deseja salvar: ");
                    String senhaAdministrador = input.next();
                    while (senhaAdministrador.isEmpty()){
                        System.out.println("Esta informação não pode ficar em branco.");
                        System.out.println("Infome a nova senha que deseja salvar: ");
                        senhaAdministrador = input.nextLine();
                    }
                    administrador.setSenha(senhaAdministrador);
                    break;
                default:
                    System.out.println("Opção inválida.");
                    break;
            }

            for (Administrador adm : listaAdministradores){
                if (administrador.getCpf().equals(adm.getCpf())){
                    switch (i){
                        case 1:
                            adm.setNome(administrador.getNome());
                            break;
                        case 2:
                            adm.setSobrenome(administrador.getSobrenome());
                            break;
                        case 3:
                            adm.setEndereco(administrador.getEndereco());
                            break;
                        case 4:
                            adm.setTelefone(administrador.getTelefone());
                            break;
                        case 5:
                            adm.setEmail(administrador.getEmail());
                            break;
                        case 6:
                            adm.setLogin(administrador.getLogin());
                            break;
                        case 7:
                            adm.setSenha(administrador.getSenha());
                            break;
                    }
                }
                listaAdministradoresAtualizado.add(adm);
            }

            if (mJson.salvarAdministrador(listaAdministradoresAtualizado)){
                System.out.println("Funcionário editado com sucesso!");
            } else {
                System.out.println("Erro ao salvar as alterações do funcionário.");
            }
        }
    }
}
