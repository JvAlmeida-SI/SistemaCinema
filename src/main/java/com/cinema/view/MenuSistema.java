package com.cinema.view;



import com.cinema.controller.*;
import com.cinema.model.*;

import java.io.IOException;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class MenuSistema {
    static manipularJsonPessoas mJsonPessoas = new manipularJsonPessoas();
    static manipularJsonProdutos mJsonProdutos = new manipularJsonProdutos();

    static Scanner scan = new Scanner(System.in);

    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    AdministradorController controlAdm = new AdministradorController();
    FuncionarioController controlFuncionario = new FuncionarioController();
    ClienteController controlCliente = new ClienteController();
    FilmeController controlFilme = new FilmeController();
    ComidaController controlComida = new ComidaController();
    CarrinhoController controlCarrinho = new CarrinhoController();

    Balcao balcao = new Balcao();
    static Funcionario funcionario = new Funcionario();
    static Administrador administrador = new Administrador();
    static List<Funcionario> listaFuncionarios;
    static List<Administrador> listaAdministradores;
    static List<Cliente> listaClientes;
    static List<Filme> listaFilmes;
    static List<Comida> listaComidas;

    static Carrinho venda;

    static int id;
    private int numBalcao;
    static int i = 0;
    public static String cpf;
    public MenuSistema() throws IOException {
    }


    public void FazerLogin (String login, String senha, String tipoUsuario, int numBalcaoAtendimento) throws IOException, ParseException {
        numBalcao = numBalcaoAtendimento;
        if (tipoUsuario.equals("1")){
            List<Administrador> administradores = mJsonPessoas.buscarAdministrador();
            for(Administrador administrador : administradores){
                if (login.equals(administrador.getLogin()) && senha.equals(administrador.getSenha())){
                    exibirMenu(tipoUsuario);
                }
            }
        } else if (tipoUsuario.equals("2")){
            List<Funcionario> funcionarios = mJsonPessoas.buscarFuncionario();
            for (Funcionario funcionario : funcionarios){
                if(login.equals(funcionario.getLogin()) && senha.equals(funcionario.getSenha())){
                    exibirMenu(tipoUsuario);
                }
            }
        }
    }

    private void exibirMenu(String codVerificador) throws IOException, ParseException {
        if (codVerificador.equals("1")) {
            menuAdministrador();
        } else if (codVerificador.equals("2")) {
            menuFuncionario();
        }
    }

    private void menuAdministrador() throws IOException, ParseException {
        ComidaController.alertaAlimentosVencidos();

        while (i != 8) {
            System.out.println("INFORME A OPÇÃO QUE DESEJA: " +
                    "\n1 - Gerenciamento de clientes" +
                    "\n2 - Gerenciamento de funcionários" +
                    "\n3 - Gerenciamento de administradores" +
                    "\n4 - Gerenciamento de filmes" +
                    "\n5 - Gerenciamento de alimentos" +
                    "\n6 - Balanços de vendas" +
                    "\n7 - Gerenciamento de venda" +
                    "\n8 - Sair"
            );
            i = scan.nextInt();

            switch (i) {
                case 1:
                    System.out.println("INFORME A OPÇÃO QUE DESEJA: " +
                            "\n1 - Cadastrar um cliente" +
                            "\n2 - Consultar um cliente" +
                            "\n3 - Listar todos os clientes" +
                            "\n4 - Excluir cliente" +
                            "\n5 - Editar cliente" +
                            "\n6 - Sair");
                    i = scan.nextInt();

                    switch (i) {
                        case 1:
                            controlCliente.cadastroCliente();
                            break;
                        case 2:
                            System.out.print("Informe o CPF do cliente: ");
                            cpf = scan.next();
                            Cliente cliente = controlCliente.consultaCliente(cpf);
                            System.out.println(cliente);
                            break;
                        case 3:
                            listaClientes = controlCliente.consultaTodosClientes();
                            System.out.println(listaClientes);
                            break;
                        case 4:
                            System.out.print("Informe o CPF do cliente que deseja excluir: ");
                            cpf = scan.next();
                            controlCliente.excluirCliente(cpf);
                            break;
                        case 5:
                            System.out.println("Informe o CPF do cliente que deseja editar: ");
                            cpf = scan.next();
                            controlCliente.editarCliente(cpf);
                            break;
                        case 6:
                            System.out.println("Saindo...");
                            scan.close();
                            return;
                        default:
                            System.out.println("Opção inválida.");
                    }
                    break;

                case 2:
                    System.out.println("INFORME A OPÇÃO QUE DESEJA: " +
                            "\n1 - Cadastrar um funcionário" +
                            "\n2 - Consultar um funcionário" +
                            "\n3 - Listar todos os funcionários" +
                            "\n4 - Excluir funcionário" +
                            "\n5 - Editar funcionário" +
                            "\n6 - Sair");
                    i = scan.nextInt();

                    switch (i) {
                        case 1:
                            controlFuncionario.cadastroFuncionario();
                            break;
                        case 2:
                            System.out.print("Informe o CPF do funcionário: ");
                            cpf = scan.next();
                            funcionario = controlFuncionario.consultaFuncionario(cpf);
                            System.out.println(funcionario);
                            break;
                        case 3:
                            listaFuncionarios = mJsonPessoas.buscarFuncionario();
                            System.out.println(listaFuncionarios);
                            break;
                        case 4:
                            System.out.print("Informe o CPF do funcionário que deseja excluir: ");
                            cpf = scan.next();
                            controlFuncionario.excluirFuncionario(cpf);
                            break;
                        case 5:
                            System.out.println("Informe o CPF do funcionário que deseja editar: ");
                            cpf = scan.next();
                            controlFuncionario.editarFuncionario(cpf);
                            break;
                        case 6:
                            System.out.println("Saindo...");
                            scan.close();
                            return;
                        default:
                            System.out.println("Opção inválida.");
                    }
                    break;

                case 3:
                    System.out.println("INFORME A OPÇÃO QUE DESEJA: " +
                            "\n1 - Cadastrar um administrador" +
                            "\n2 - Consultar um administrador" +
                            "\n3 - Listar todos os administradores" +
                            "\n4 - Excluir administrador" +
                            "\n5 - Editar administrador" +
                            "\n6 - Sair");
                    i = scan.nextInt();

                    switch (i) {
                        case 1:
                            controlAdm.cadastroAdministrador();
                            break;
                        case 2:
                            System.out.print("Informe o CPF do administrador: ");
                            cpf = scan.next();
                            Administrador adm = controlAdm.consultaAdministrador(cpf);
                            System.out.println(adm);
                            break;
                        case 3:
                            listaAdministradores = mJsonPessoas.buscarAdministrador();
                            System.out.println(listaAdministradores);
                            break;
                        case 4:
                            System.out.print("Informe o CPF do administrador que deseja excluir: ");
                            cpf = scan.next();
                            controlAdm.excluirAdministrador(cpf);
                            break;
                        case 5:
                            System.out.println("Informe o CPF do funcionário que deseja editar: ");
                            cpf = scan.next();
                            controlAdm.editarAdministrador(cpf);
                            break;
                        case 6:
                            System.out.println("Saindo...");
                            scan.close();
                            return;
                        default:
                            System.out.println("Opção inválida.");
                    }
                    break;

                case 4:
                    System.out.println("INFORME A OPÇÃO QUE DESEJA: " +
                            "\n1 - Cadastrar um filme" +
                            "\n2 - Consultar um filme" +
                            "\n3 - Listar todos os filmes em cartaz" +
                            "\n4 - Excluir um filme" +
                            "\n5 - Editar um filme" +
                            "\n6 - Sair");
                    i = scan.nextInt();

                    switch (i) {
                        case 1:
                            controlFilme.cadastroFilme();
                            break;
                        case 2:
                            System.out.println("Informe o código do filme: ");
                            id = scan.nextInt();
                            System.out.println(controlFilme.consultaFilme(id));
                            break;
                        case 3:
                            listaFilmes = controlFilme.consultaTodosFilmes();
                            System.out.println(listaFilmes);
                            break;
                        case 4:
                            System.out.println("Informe o código do filme que deseja excluir: ");
                            id = scan.nextInt();
                            controlFilme.deletaFilme(id);
                            break;
                        case 5:
                            System.out.println("Informe o código do filme que deseja editar: ");
                            id = scan.nextInt();
                            controlFilme.editarFilme(id);
                            break;
                        case 6:
                            System.out.println("Saindo...");
                            scan.close();
                            return;
                        default:
                            System.out.println("Opção Inválida.");
                    }
                    break;
                case 5:
                    System.out.println("INFORME A OPÇÃO QUE DESEJA: " +
                            "\n1 - Cadastrar um alimento" +
                            "\n2 - Consultar um alimento" +
                            "\n3 - Listar todos os alimentos no estoque" +
                            "\n4 - Excluir um alimento" +
                            "\n5 - Editar um alimento" +
                            "\n6 - Sair");
                    i = scan.nextInt();

                    switch (i) {
                        case 1:
                            controlComida.cadastroComida();
                            break;
                        case 2:
                            System.out.println("Informe o código do alimento: ");
                            id = scan.nextInt();
                            System.out.println(controlComida.consultaComida(id));
                            break;
                        case 3:
                            listaComidas = mJsonProdutos.buscarComidas();
                            System.out.println(listaComidas);
                            break;
                        case 4:
                            System.out.println("Informe o código do alimento que deseja excluir: ");
                            id = scan.nextInt();
                            controlComida.deletaComida(id);
                            break;
                        case 5:
                            System.out.println("Informe o código do alimento que deseja editar: ");
                            id = scan.nextInt();
                            controlComida.editarComida(id);
                            break;
                        case 6:
                            System.out.println("Saindo...");
                            scan.close();
                            return;
                        default:
                            System.out.println("Opção Inválida.");
                    }
                    break;
                case 6:
                    System.out.println("INFORME A OPÇÃO DESEJADA:" +
                                       "\n1 - Gerar balanço das vendas de um dia" +
                                       "\n2 - Gerar balanço das vendas de um mês");
                    i = scan.nextInt();
                    if (i == 1) {
                        Date dataPesquisa = new Date();
                        Calendar setarHora = Calendar.getInstance();
                        setarHora.setTime(dataPesquisa);
                        setarHora.set(Calendar.HOUR_OF_DAY, 0);
                        setarHora.set(Calendar.MINUTE, 0);
                        setarHora.set(Calendar.SECOND, 0);
                        setarHora.set(Calendar.MILLISECOND, 0);
                        System.out.println(balcao.obterBalancoDiario(setarHora.getTime(), numBalcao));
                    } else if (i == 2) {
                        String dataInicio;
                        do {
                            System.out.print("Data de inicio(dd/MM/yyyy): ");
                            dataInicio = scan.next();
                        } while (dataInicio.isEmpty());

                        String dataFinal;
                        do {
                            System.out.println("Informe a data final: ");
                            dataFinal = scan.next();
                        } while (dataFinal.isEmpty());

                        System.out.println(balcao.obterBalancoMensal(dateFormat.parse(dataInicio), dateFormat.parse(dataFinal), numBalcao));
                    }
                    break;
                case 7:
                    System.out.println("INFORME A OPÇÃO QUE DESEJA: " +
                            "\n1 - Efetuar venda" +
                            "\n2 - Consultar venda" +
                            "\n3 - Cancelar venda" +
                            "\n4 - Sair");
                    i = scan.nextInt();
                    switch (i) {
                        case 1:
                            controlCarrinho.efetuarVenda(numBalcao);
                            break;
                        case 2:
                            System.out.println("Informe o código da venda: ");
                            Carrinho vendaPesquisada = controlCarrinho.consultarVenda(scan.nextInt());
                            System.out.println(vendaPesquisada);
                            break;
                        case 3:
                            System.out.println("Informe o código da venda: ");
                            vendaPesquisada = controlCarrinho.consultarVenda(scan.nextInt());
                            System.out.println("Deseja excluir a seguinte venda? Se sim, digite s.\n");
                            System.out.println(vendaPesquisada);
                            String decisao = scan.next();
                            if (decisao.equals("s")){ controlCarrinho.cancelarVenda(scan.nextInt()); }
                        case 4:
                            scan.close();
                            return;
                        default:
                            System.out.println("Código inválido!");
                    }
                case 8:
                    System.out.println("Saindo...");
                    scan.close();
                    return;
                default:
                    System.out.println("Opção Inválida.");
            }
        }
    }

    private void menuFuncionario() throws IOException {


        while (i != 6 ) {
            System.out.println("INFORME A OPÇÃO QUE DESEJA: " +
                    "\n1 - Gerenciamento de clientes" +
                    "\n2 - Gerenciamento de funcionários" +
                    "\n3 - Gerenciamento de filmes" +
                    "\n4 - Gerenciamento de alimentos" +
                    "\n5 - Gerenciamento de venda" +
                    "\n6 - Sair"
            );
            i = scan.nextInt();

            switch (i) {
                case 1:
                    System.out.println("INFORME A OPÇÃO QUE DESEJA: " +
                            "\n1 - Cadastrar um cliente" +
                            "\n2 - Consultar um cliente" +
                            "\n3 - Listar todos os clientes" +
                            "\n4 - Excluir cliente" +
                            "\n5 - Editar cliente" +
                            "\n6 - Sair");
                    i = scan.nextInt();

                    switch (i) {
                        case 1:
                            controlCliente.cadastroCliente();
                            break;
                        case 2:
                            System.out.print("Informe o CPF do cliente: ");
                            cpf = scan.next();
                            Cliente cliente = controlCliente.consultaCliente(cpf);
                            System.out.println(cliente);
                            break;
                        case 3:
                            listaClientes = mJsonPessoas.buscarCliente();
                            System.out.println(listaClientes);
                            break;
                        case 4:
                            System.out.print("Informe o CPF do cliente que deseja excluir: ");
                            cpf = scan.next();
                            controlCliente.excluirCliente(cpf);
                            break;
                        case 5:
                            System.out.println("Informe o CPF do cliente que deseja editar: ");
                            cpf = scan.next();
                            controlCliente.editarCliente(cpf);
                            break;
                        case 6:
                            System.out.println("Saindo...");
                            scan.close();
                            return;
                        default:
                            System.out.println("Opção inválida.");
                    }
                    break;

                case 2:
                    System.out.println("INFORME A OPÇÃO QUE DESEJA: " +
                            "\n1 - Cadastrar um funcionário" +
                            "\n2 - Consultar um funcionário" +
                            "\n3 - Listar todos os funcionários" +
                            "\n4 - Excluir funcionário" +
                            "\n5 - Editar funcionário" +
                            "\n6 - Sair");
                    i = scan.nextInt();

                    switch (i) {
                        case 1:
                            controlFuncionario.cadastroFuncionario();
                            break;
                        case 2:
                            System.out.print("Informe o CPF do funcionário: ");
                            cpf = scan.next();
                            funcionario = controlFuncionario.consultaFuncionario(cpf);
                            System.out.println(funcionario);
                            break;
                        case 3:
                            listaFuncionarios = mJsonPessoas.buscarFuncionario();
                            System.out.println(listaFuncionarios);
                            break;
                        case 4:
                            System.out.print("Informe o CPF do funcionário que deseja excluir: ");
                            cpf = scan.next();
                            controlFuncionario.excluirFuncionario(cpf);
                            break;
                        case 5:
                            System.out.println("Informe o CPF do funcionário que deseja editar: ");
                            cpf = scan.next();
                            controlFuncionario.editarFuncionario(cpf);
                            break;
                        case 6:
                            System.out.println("Saindo...");
                            scan.close();
                            return;
                        default:
                            System.out.println("Opção inválida.");
                    }
                    break;

                case 3:
                    System.out.println("INFORME A OPÇÃO QUE DESEJA: " +
                            "\n1 - Cadastrar um filme" +
                            "\n2 - Consultar um filme" +
                            "\n3 - Listar todos os filmes em cartaz" +
                            "\n4 - Excluir um filme" +
                            "\n5 - Editar um filme" +
                            "\n6 - Sair");
                    i = scan.nextInt();

                    switch (i) {
                        case 1:
                            controlFilme.cadastroFilme();
                            break;
                        case 2:
                            System.out.println("Informe o código do filme: ");
                            id = scan.nextInt();
                            System.out.println(controlFilme.consultaFilme(id));
                            break;
                        case 3:
                            listaFilmes = mJsonProdutos.buscarFilmes();
                            System.out.println(listaFilmes);
                            break;
                        case 4:
                            System.out.println("Informe o código do filme que deseja excluir: ");
                            id = scan.nextInt();
                            controlFilme.deletaFilme(id);
                            break;
                        case 5:
                            System.out.println("Informe o código do filme que deseja editar: ");
                            id = scan.nextInt();
                            controlFilme.editarFilme(id);
                            break;
                        case 6:
                            System.out.println("Saindo...");
                            scan.close();
                            return;
                        default:
                            System.out.println("Opção Inválida.");
                    }
                    break;
                case 4:
                    System.out.println("INFORME A OPÇÃO QUE DESEJA: " +
                            "\n1 - Cadastrar um alimento" +
                            "\n2 - Consultar um alimento" +
                            "\n3 - Listar todos os alimentos no estoque" +
                            "\n4 - Excluir um alimento" +
                            "\n5 - Editar um alimento" +
                            "\n6 - Sair");
                    i = scan.nextInt();

                    switch (i) {
                        case 1:
                            controlComida.cadastroComida();
                            break;
                        case 2:
                            System.out.println("Informe o código do alimento: ");
                            id = scan.nextInt();
                            System.out.println(controlComida.consultaComida(id));
                            break;
                        case 3:
                            listaComidas = mJsonProdutos.buscarComidas();
                            System.out.println(listaComidas);
                            break;
                        case 4:
                            System.out.println("Informe o código do alimento que deseja excluir: ");
                            id = scan.nextInt();
                            controlComida.deletaComida(id);
                            break;
                        case 5:
                            System.out.println("Informe o código do alimento que deseja editar: ");
                            id = scan.nextInt();
                            controlComida.editarComida(id);
                            break;
                        case 6:
                            System.out.println("Saindo...");
                            scan.close();
                            return;
                        default:
                            System.out.println("Opção Inválida.");
                    }
                    break;
                case 5:
                    System.out.println("INFORME A OPÇÃO QUE DESEJA: " +
                            "\n1 - Efetuar venda" +
                            "\n2 - Consultar venda" +
                            "\n3 - Cancelar venda" +
                            "\n4 - Sair");
                    i = scan.nextInt();
                    switch (i) {
                        case 1:
                            controlCarrinho.efetuarVenda(numBalcao);
                            break;
                        case 2:
                            System.out.println("Informe o código da venda: ");
                            System.out.println(controlCarrinho.consultarVenda(scan.nextInt()));
                            break;
                        case 3:
                            System.out.println("Informe o código da venda: ");
                            controlCarrinho.cancelarVenda(scan.nextInt());
                        case 4:
                            scan.close();
                            return;
                        default:
                            System.out.println("Código inválido!");
                    }
                case 6:
                    System.out.println("Saindo...");
                    scan.close();
                    return;
                default:
                    System.out.println("Opção Inválida.");
            }
        }
    }
}
