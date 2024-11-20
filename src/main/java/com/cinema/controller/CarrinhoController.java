package com.cinema.controller;

import com.cinema.model.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Controller responsável por gerenciar as operações de carrinho de compras no cinema.
 */
public class CarrinhoController extends ValidaProduto {
    static int totalIngressos;
    List<Carrinho> vendas = mJsonVendas.buscarVendas();
    Balcao balcao = new Balcao();
    ClienteController controlCliente = new ClienteController();
    static List<Filme> filmesCartaz;

    /**
     * Construtor da classe CarrinhoController.
     * @throws IOException se ocorrer um erro na leitura dos dados das vendas ou dos filmes.
     */
    public CarrinhoController() throws IOException {
        if (filmesCartaz == null) {
            filmesCartaz = new ArrayList<>();
        }
        if (vendas == null) {
            vendas = new ArrayList<>();
        }
    }

    /**
     * Efetua uma venda de ingressos e comidas para um cliente.
     * @param numBalcaoAtendimento o número do balcão de atendimento onde a venda está sendo efetuada.
     * @throws IOException se ocorrer um erro na gravação dos dados da venda.
     */
    public void efetuarVenda(int numBalcaoAtendimento) throws IOException {
        List<Ingresso> ingressos = new ArrayList<>();
        List<Comida> comidasEscolhidas = new ArrayList<>();
        int codVenda = 0;
        Date dataVenda = new Date();
        Double valorTotal = 0.00;
        String cpfCliente;

        Calendar setarHora = Calendar.getInstance();
        setarHora.setTime(dataVenda);
        setarHora.set(Calendar.HOUR_OF_DAY, 0);
        setarHora.set(Calendar.MINUTE, 0);
        setarHora.set(Calendar.SECOND, 0);
        setarHora.set(Calendar.MILLISECOND, 0);

        System.out.println("INFORME O CPF DO CLIENTE: ");
        cpfCliente = input.nextLine();
        Cliente cliente = controlCliente.consultaCliente(cpfCliente);

        ingressos = escolherIngresso();
        comidasEscolhidas = escolherAlimentos();
        if (mJsonVendas.buscarVendas() != null) { codVenda = vendas.get(mJsonVendas.buscarVendas().size() - 1).getCodVenda() + 1; }

        for (Ingresso ingresso : ingressos){
            valorTotal = valorTotal + ingresso.getValor();
        }
        for (Comida comida : comidasEscolhidas){
            if (comida != null){ valorTotal = valorTotal + comida.getPreco(); }
        }

        Carrinho novaVenda = new Carrinho(codVenda, ingressos, cliente, comidasEscolhidas, valorTotal, setarHora.getTime(), numBalcaoAtendimento);
        vendas.add(novaVenda);
        if (mJsonVendas.gravarVenda(vendas)) {
            System.out.println("Venda efetuada com sucesso!");
            System.out.println(novaVenda);
        }
        else { System.out.println("Falha ao efetuar venda."); }
    }

    /**
     * Consulta uma venda pelo seu ID.
     * @param idVenda o ID da venda a ser consultada.
     * @return Carrinho encontrado ou null se não encontrado.
     */
    public Carrinho consultarVenda(int idVenda){
        List<Carrinho> vendas = mJsonVendas.buscarVendas();
        Iterator<Carrinho> iterador = vendas.iterator();

        while (iterador.hasNext()){
            Carrinho venda = iterador.next();
            if (venda != null && venda.getCodVenda() == idVenda){
                return venda;
            }
        }
        return null;
    }

    /**
     * Cancela uma venda pelo seu ID.
     * @param idVenda o ID da venda a ser cancelada.
     * @return true se a venda foi cancelada com sucesso, false caso contrário.
     */
    public boolean cancelarVenda(int idVenda){
        List<Carrinho> vendas = mJsonVendas.buscarVendas();
        Iterator<Carrinho> iterador = vendas.iterator();

        while (iterador.hasNext()){
            Carrinho venda = iterador.next();
            if (venda != null && venda.getCodVenda() == idVenda){
                vendas.get(idVenda).setValorTotal(venda.getValorTotal() * 0.25);
            }
        }

        System.out.println("Venda cancelada com sucesso!");
        return true;
    }

    /**
     * Permite ao usuário escolher ingressos para um filme.
     * @return uma lista de ingressos escolhidos.
     * @throws IOException se ocorrer um erro na leitura dos dados dos filmes.
     */
    private static List<Ingresso> escolherIngresso() throws IOException {
        int codIngresso = 0;
        Filme filmeEscolhido;
        Double valorIngresso;
        List<Ingresso> ingressos = new ArrayList<>();

        System.out.println("INFORME O TOTAL DE INGRESSOS: ");
        totalIngressos = input.nextInt();

        System.out.println("ESCOLHA O CÓDIGO DO FILME: ");
        for (Filme filme : mJsonProdutos.buscarFilmes()) {
            System.out.println(filme.getCodFilme() + " - " + filme.getNome());
        }
        filmeEscolhido = FilmeController.consultaFilme(input.nextInt());

        if (filmeEscolhido.getFaixaEtaria() > 10) {
            valorIngresso = 25.00;
        } else {
            valorIngresso = 15.00;
        }

        System.out.println("Informe a sala de exibição: ");
        int numSala = input.nextInt();

        for (int i = 0; i < totalIngressos; i++) {
            System.out.println("ESCOLHA A POLTRONA (1 a 50): ");
            int numPoltrona = input.nextInt();

            Sala sala = new Sala(numPoltrona, numSala);

            Ingresso novoIngresso = new Ingresso(codIngresso, filmeEscolhido, sala, valorIngresso);
            ingressos.add(novoIngresso);
        }

        return ingressos;
    }

    /**
     * Permite ao usuário escolher alimentos.
     * @return uma lista de alimentos escolhidos.
     * @throws IOException se ocorrer um erro na leitura dos dados dos alimentos.
     */
    public static List<Comida> escolherAlimentos() throws IOException {
        List<Comida> alimentosEscolhidos = new ArrayList<>();
        int codAlimentoEscolhido = -1, xAux = 0;

        System.out.println("INFORME OS CÓDIGOS DOS ALIMENTOS QUE DESEJA INSERIR ( ou digite 0 para sair ): ");
        for (Comida comida : mJsonProdutos.buscarComidas()) {
            System.out.println((comida.getCodComida()+1) + " - " + comida.getNome() + " - R$" + comida.getPreco());
        }
        do {
            codAlimentoEscolhido = input.nextInt();
            if (codAlimentoEscolhido != -1 && codAlimentoEscolhido != 0) {
                alimentosEscolhidos.add(ComidaController.consultaComida(codAlimentoEscolhido - 1));
            }
            xAux = codAlimentoEscolhido;
        } while (xAux != 0);
        return alimentosEscolhidos;
    }
}
