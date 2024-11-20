package com.cinema.view;

import com.cinema.view.MenuSistema;
import com.cinema.view.TelaLogin.TelaLogin;
import com.cinema.controller.*;
import com.cinema.model.*;

import java.io.IOException;
import java.text.ParseException;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import static com.cinema.controller.ClienteController.findCliente;


/**
 *
 * @autor João Victor Almeida
 */

public class SistemaCinema {

    // INSTANCIAR OS BALCÕES AQUI DE FORMA ESTÁTICA *****************

    public static void main(String[] args) throws IOException, ParseException {

        Scanner scan = new Scanner(System.in);

        System.out.println("\n1 - SISTEMA DE FORMA MANUAL\n" +
                           "\n2 - INTERFACE DO SISTEMA\n");

        int i = scan.nextInt();
        switch (i) {
            case 1:
                MenuSistema menuSistema = new MenuSistema();

                String login, senha, tipoUsuario;
                int balcao;

                System.out.println("\nDIGITE A OPÇÃO DE LOGIN DESEJADA:" +
                        "\n1 - ADMINISTRADOR" +
                        "\n2 - COLABORADOR");
                tipoUsuario = scan.next();


                System.out.println("\nINFORME O BALCÃO DE ATENDIMENTO:" +
                        "\n1 - Balcão 1\n2 - Balcão 2\n3 - Balcão 3\n4 - Balcão 4\n5 - Balcão 5\n6 - Sair.");
                balcao = scan.nextInt();
                while (balcao > 6 || balcao < 1) {
                    System.out.println("NÚMERO INVÁLIDO. INFORME UM NÚMERO ENTRE 1 E 6.");
                    balcao = scan.nextInt();
                }
                if (balcao == 6) {
                    scan.close();
                    return;
                }


                System.out.println("LOGIN: ");
                login = scan.next();

                System.out.println("SENHA: ");
                senha = scan.next();

                menuSistema.FazerLogin(login, senha, tipoUsuario, balcao);
                break;
            case 2:
                manipularJsonPessoas mJsonPessoas = new manipularJsonPessoas();
                manipularJsonProdutos mJsonProdutos = new manipularJsonProdutos();

                AdministradorController controlAdm = new AdministradorController();
                FuncionarioController controlFuncionario = new FuncionarioController();
                ClienteController controlCliente = new ClienteController();
                FilmeController controlFilme = new FilmeController();
                ComidaController controlComida = new ComidaController();
                CarrinhoController controlCarrinho = new CarrinhoController();

                Funcionario funcionario = new Funcionario();
                List<Funcionario> listaFuncionarios;
                List<Administrador> listaAdministradores;
                List<Cliente> listaClientes;
                List<Filme> listaFilmes;
                List<Comida> listaComidas;

                TelaLogin telaLogin = new TelaLogin();
                telaLogin.show();


                //VERIFICANDO VALIDADE NO ESTOQUE:
                /*
                System.out.println("\n----------------------------------------------------------\n");
                System.out.println("VERIFICANDO VALIDADE DE UM PRODUTO\n");
                Comida comida = controlComida.consultaComida(1);
                System.out.println(comida.getNome());
                System.out.println(comida.getDataValidade());
                 */


                // Implementando o binarySearch
                /*
                System.out.println("\n-------------------------QUESTÃO 17---------------------------------\n");

                List<Cliente> clientes = controlCliente.consultaTodosClientes();
                Cliente clienteEncontrado = findCliente(clientes.iterator(), "Malu");
                if (clienteEncontrado != null) {
                    System.out.println("Cliente encontrado: " + clienteEncontrado.getNome());
                } else {
                    System.out.println("Cliente não encontrado.");
                }

                // Faça uma chamada ao binarySearch para comparar
                int indiceNome = Collections.binarySearch(clientes, new Cliente("Malu"), new ClienteComparator());
                if (indiceNome >= 0) {
                    System.out.println("Cliente encontrado (binarySearch): " + clientes.get(indiceNome).getNome());
                } else {
                    System.out.println("Cliente não encontrado (binarySearch).");
                }
                 */
            }
    }
}
