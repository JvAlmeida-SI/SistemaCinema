package com.cinema.controller;

import com.cinema.model.Comida;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Classe controladora para gerenciar operações relacionadas a alimentos (Comida) no sistema.
 */
public class ComidaController extends ValidaProduto{
    private static List<Comida> listaComidas;

    /**
     * Construtor da classe ComidaController. Inicializa a lista de comidas se estiver nula.
     */
    public ComidaController() {
        if ( this.listaComidas == null){
            this.listaComidas = new ArrayList<>();
        }
    }

    /**
     * Método para cadastrar uma nova comida.
     * @throws IOException se ocorrer um erro de entrada/saída.
     */
    public void cadastroComida () throws IOException {
        String nome, dataValidade;
        int quantidade = 0;
        Double preco = 0.00;
        Date dataValidadeFormatada;
        int novoCodComida;

        do {
            System.out.print("Nome: ");
            nome = input.nextLine();
        } while (nome.isEmpty());

        do {
            System.out.print("Preço: ");
            preco = input.nextDouble();
        } while (preco.equals(0.00));

        do {
            System.out.print("Quantidade: ");
            quantidade = input.nextInt();
        } while (quantidade == 0);

        do {
            System.out.print("Data de validade: ");
            dataValidade = input.nextLine();
        } while (dataValidade.isEmpty());
        try {
            dataValidadeFormatada = dateFormat.parse(dataValidade);
        } catch (ParseException e) {
            throw new RuntimeException("Falha ao converter a data de validade. Controller" + e.getMessage());
        }

        if (mJsonProdutos.buscarComidas() != null) {
            listaComidas = mJsonProdutos.buscarComidas();
            novoCodComida = listaComidas.get(listaComidas.size() - 1).getCodComida() + 1;
        } else { novoCodComida = 0; }

        Comida novaComida = new Comida(novoCodComida, nome, preco, quantidade, dataValidadeFormatada);
        listaComidas.add(novaComida);
        if (mJsonProdutos.salvarComida(listaComidas)) { System.out.println("Alimento cadastrado com sucesso!"); }
        else { System.out.println("Erro ao cadastrar alimento."); }
    }

    /**
     * Consulta uma comida pelo seu código.
     * @param id o código da comida.
     * @return a comida encontrada ou null se não encontrada.
     * @throws IOException se ocorrer um erro de entrada/saída.
     */
    public static Comida consultaComida(int id) throws IOException{
        listaComidas = mJsonProdutos.buscarComidas();

        for (Comida comida : listaComidas){
            if (id == comida.getCodComida()){
                return comida;
            }
        }
        return null;
    }

    /**
     * Deleta uma comida pelo seu código.
     * @param id o código da comida.
     * @throws IOException se ocorrer um erro de entrada/saída.
     */
    public void deletaComida (int id) throws IOException {
        listaComidas = mJsonProdutos.buscarComidas();
        Comida comida = consultaComida(id);
        listaComidas.remove(comida);

        if (mJsonProdutos.salvarComida(listaComidas)){ System.out.println("Comida excluída com sucesso!");}
        else { System.out.println("Falha ao excluir comida."); }
    }

    /**
     * Edita os dados de uma comida pelo seu código.
     * @param id o código da comida.
     * @throws IOException se ocorrer um erro de entrada/saída.
     */
    public void editarComida(int id) throws IOException{
        Integer i;
        Comida comida;
        List<Comida> listaComidas = mJsonProdutos.buscarComidas();
        List<Comida> listaComidasAtualizada = new ArrayList<>();

        if (consultaComida(id) != null) {
            comida = consultaComida(id);
            System.out.println("INFORME A INFORMAÇÃO QUE DESEJA ALTERAR: " +
                    "\n1 - Nome" +
                    "\n2 - Preço" +
                    "\n3 - Quantidade" +
                    "\n4 - Data de Validade");

            i = input.nextInt();
            input.nextLine();

            switch (i) {
                case 1:
                    System.out.println("Infome o novo nome que deseja salvar: ");
                    String nomeComida = input.nextLine();
                    while (nomeComida.isEmpty()){
                        System.out.println("Esta informação não pode ficar em branco.");
                        System.out.println("Infome o novo nome que deseja salvar: ");
                        nomeComida = input.nextLine();
                    }
                    comida.setNome(nomeComida);
                    break;
                case 2:
                    Double preco = 0.00;
                    System.out.println("Infome o novo preço que deseja salvar: ");
                    preco = input.nextDouble();
                    while (preco.equals(0.00)){
                        System.out.println("Esta informação não pode ficar em branco.");
                        System.out.println("Infome o novo preço que deseja salvar: ");
                        preco = input.nextDouble();
                    }
                    comida.setPreco(preco);
                    break;
                case 3:
                    int quantidade = 0;
                    System.out.println("Infome a nova quantidade que deseja salvar: ");
                    quantidade = input.nextInt();
                    while (quantidade == 0){
                        System.out.println("Esta informação não pode ficar em branco.");
                        System.out.println("Infome a nova quantidade que deseja salvar: ");
                        quantidade = input.nextInt();
                    }
                    comida.setQuantidade(quantidade);
                    input.nextLine();
                    break;
                case 4:
                    System.out.println("Infome a nova data de validade que deseja salvar: ");
                    String dataValidade = input.nextLine();
                    while (dataValidade.isEmpty()){
                        System.out.println("Esta informação não pode ficar em branco.");
                        System.out.println("Infome a nova data de validade que deseja salvar: ");
                        dataValidade = input.nextLine();
                    }
                    try {
                        comida.setDataValidade(dateFormat.parse(dataValidade));
                    } catch (ParseException e) {
                        throw new RuntimeException("Erro ao alterar a data de validade. " +e.getMessage());
                    }
                    break;
                default:
                    System.out.println("Opção inválida.");
                    break;
            }

            for (Comida c : listaComidas){
                if (comida.getCodComida() == c.getCodComida()){
                    switch (i){
                        case 1:
                            c.setNome(comida.getNome());
                            break;
                        case 2:
                            c.setPreco(comida.getPreco());
                            break;
                        case 3:
                            c.setQuantidade(comida.getQuantidade());
                            break;
                        case 4:
                            c.setDataValidade(comida.getDataValidade());
                            break;
                    }
                }
                listaComidasAtualizada.add(c);
            }

            if (mJsonProdutos.salvarComida(listaComidasAtualizada)){ System.out.println("Comida editada com sucesso!");}
            else { System.out.println("Erro ao salvar as alterações da comida."); }
        }
    }

    /**
     * Obtém a lista de alimentos vencidos.
     * @return a lista de alimentos vencidos.
     * @throws IOException se ocorrer um erro de entrada/saída.
     */
    public static List<Comida> alimentosVencidos () throws IOException {
        Date dataAtual = new Date();
        List<Comida> Comidas = mJsonProdutos.buscarComidas();
        List<Comida> ComidasVencidas = new ArrayList<>();

        for (Comida comida : Comidas){
            if (!dataAtual.before(comida.getDataValidade())){
                ComidasVencidas.add(comida);
            }
        }
        return  ComidasVencidas;
    }

    /**
     * Alerta sobre alimentos vencidos e exibe a lista de alimentos vencidos.
     */
    public static void alertaAlimentosVencidos(){
        List<Comida> comidasVencidas = new ArrayList<>();
        try {
            comidasVencidas = alimentosVencidos();
            System.out.println("\nLISTA DE ALIMENTOS VENCIDOS: \n");
            System.out.println(comidasVencidas);
        } catch (IOException e) {
            System.out.println("Fala ao buscar alimentos vencidos. Controller\n" + e.getMessage());
        }
    }
}
