package com.cinema.controller;

import com.cinema.model.*;

import java.io.IOException;
import java.text.ParseException;
import java.util.*;


public class FilmeController extends ValidaProduto{

    private static List<Filme> filmesCartaz;

    static {
        try {
            filmesCartaz = mJsonProdutos.buscarFilmes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Construtor da classe FilmeController.
     * Inicializa a lista de filmes em cartaz.
     *
     * @throws IOException se ocorrer um erro de E/S durante a busca dos filmes em cartaz.
     */
    public FilmeController() throws IOException{
        if (this.filmesCartaz == null){
            this.filmesCartaz = new ArrayList<>();
        }
    }

    /**
     * Método para cadastrar um novo filme.
     *
     * @throws IOException se ocorrer um erro de E/S durante o cadastro do filme.
     */
    public void cadastroFilme () throws IOException {
        String nome, faixaEtaria, genero, duracao, dataEstreia, dataSaidaCartaz;
        Date dataEstreiaFilme = null, dataSaidaFilme = null;

        do {
            System.out.print("Nome do filme: ");
            nome = input.nextLine();
        } while (nome.isEmpty());

        do {
            System.out.print("Faixa etária: ");
            faixaEtaria = input.next();
        } while (faixaEtaria.isEmpty());

        do {
            System.out.print("Genero: ");
            genero = input.nextLine();
        } while (genero.isEmpty());

        do {
            System.out.print("Duração em minutos: ");
            duracao = input.next();
        } while (duracao.isEmpty());

        do {
            System.out.print("Data de estreia(dd/MM/yyyy): ");
            dataEstreia = input.nextLine();
        } while (dataEstreia.isEmpty());
        try{
            dataEstreiaFilme = dateFormat.parse(dataEstreia);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        do {
            System.out.print("Data de saída do cartaz(dd/MM/yyyy): ");
            dataSaidaCartaz = input.nextLine();
        } while (dataSaidaCartaz.isEmpty());
        try {
            dataSaidaFilme = dateFormat.parse(dataSaidaCartaz);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        if (mJsonProdutos.buscarFilmes() != null){ filmesCartaz = mJsonProdutos.buscarFilmes(); }
        int novoCodFilme = filmesCartaz.get(filmesCartaz.size() - 1).getCodFilme() + 1;

        Filme novoFilme = new Filme(novoCodFilme, nome, Integer.parseInt(faixaEtaria), genero, Integer.parseInt(duracao), dataEstreiaFilme, dataSaidaFilme);
        filmesCartaz.add(novoFilme);
        if (mJsonProdutos.salvarFilme(filmesCartaz)) { System.out.println("Filme cadastrado com sucesso!"); }
        else { System.out.println("Erro ao cadastrar filme."); }
    }

    /**
     * Método para consultar um filme pelo seu ID.
     *
     * @param id o ID do filme a ser consultado.
     * @return o objeto Filme correspondente ao ID especificado, ou null se não encontrado.
     * @throws IOException se ocorrer um erro de E/S durante a consulta do filme.
     */
    public static Filme consultaFilme(int id) throws IOException {
        Iterator<Filme> iterator = filmesCartaz.iterator();

        while (iterator.hasNext()){
            Filme filme = iterator.next();
            if (id == filme.getCodFilme()) {
                return filme;
            }
        }
        return null;
    }

    /**
     * Método para consultar todos os filmes em cartaz.
     *
     * @return uma lista de todos os filmes em cartaz, ordenados pelo nome.
     */
    public List<Filme> consultaTodosFilmes(){

        filmesCartaz.sort(new FilmeComparator());

        return filmesCartaz;
    }

    /**
     * Método para excluir um filme pelo seu ID.
     *
     * @param id o ID do filme a ser excluído.
     * @throws IOException se ocorrer um erro de E/S durante a exclusão do filme.
     */
    public void deletaFilme (int id) throws IOException {
        Filme filme = consultaFilme(id);
        filmesCartaz.remove(filme);

        if (mJsonProdutos.salvarFilme(filmesCartaz)){ System.out.println("Filme excluído com sucesso!");}
        else { System.out.println("Falha ao excluir filme."); }
    }

    /**
     * Método para editar as informações de um filme pelo seu ID.
     *
     * @param id o ID do filme a ser editado.
     * @throws IOException se ocorrer um erro de E/S durante a edição do filme.
     */
    public void editarFilme(int id) throws IOException{
        Integer i;
        Filme filme;
        List<Filme> listaFilmes = mJsonProdutos.buscarFilmes();
        List<Filme> listaFilmesAtualizado = new ArrayList<>();

        if (consultaFilme(id) != null) {
            filme = consultaFilme(id);
            System.out.println("INFORME A INFORMAÇÃO QUE DESEJA ALTERAR: " +
                    "\n1 - Nome" +
                    "\n2 - Faixa etária" +
                    "\n3 - Genero" +
                    "\n4 - Duração" +
                    "\n5 - Data de estréia" +
                    "\n6 - Data de saída do cartaz");

            i = input.nextInt();
            input.nextLine();

            switch (i) {
                case 1:
                    System.out.println("Infome o novo nome que deseja salvar: ");
                    String nomeFilme = input.nextLine();
                    while (nomeFilme.isEmpty()){
                        System.out.println("Esta informação não pode ficar em branco.");
                        System.out.println("Infome o novo nome que deseja salvar: ");
                        nomeFilme = input.nextLine();
                    }
                    filme.setNome(nomeFilme);
                    break;
                case 2:
                    System.out.println("Infome o novo sobrenome que deseja salvar: ");
                    String faixaEtaria = input.nextLine();
                    while (faixaEtaria.isEmpty()){
                        System.out.println("Esta informação não pode ficar em branco.");
                        System.out.println("Infome o novo sobrenome que deseja salvar: ");
                        faixaEtaria = input.nextLine();
                    }
                    filme.setFaixaEtaria(Integer.parseInt(faixaEtaria));
                    break;
                case 3:
                    System.out.println("Infome o novo endereço que deseja salvar: ");
                    String genero = input.nextLine();
                    while (genero.isEmpty()){
                        System.out.println("Esta informação não pode ficar em branco.");
                        System.out.println("Infome o novo endereço que deseja salvar: ");
                        genero = input.nextLine();
                    }
                    filme.setGenero(genero);
                    break;
                case 4:
                    System.out.println("Infome o novo telefone que deseja salvar: ");
                    String duracao = input.nextLine();
                    while (duracao.isEmpty()){
                        System.out.println("Esta informação não pode ficar em branco.");
                        System.out.println("Infome o novo telefone que deseja salvar: ");
                        duracao = input.nextLine();
                    }
                    filme.setDuracao(Integer.parseInt(duracao));
                    break;
                case 5:
                    System.out.println("Infome o novo e-mail que deseja salvar: ");
                    String dataEstreia = input.nextLine();
                    while (dataEstreia.isEmpty()){
                        System.out.println("Esta informação não pode ficar em branco.");
                        System.out.println("Infome o novo e-mail que deseja salvar: ");
                        dataEstreia = input.nextLine();
                    }
                    try {
                        filme.setDataEstreia(dateFormat.parse(dataEstreia));
                    } catch (ParseException e) {
                        throw new RuntimeException("Erro ao alterar a data de estreia. " +e.getMessage());
                    }
                    break;
                case 6:
                    System.out.println("Infome o novo login que deseja salvar: ");
                    String dataSaidaCartaz = input.next();
                    while (dataSaidaCartaz.isEmpty()){
                        System.out.println("Esta informação não pode ficar em branco.");
                        System.out.println("Infome o novo login que deseja salvar: ");
                        dataSaidaCartaz = input.nextLine();
                    }
                    try {
                        filme.setDataSaidaCartaz(dateFormat.parse(dataSaidaCartaz));
                    } catch (ParseException e) {
                        throw new RuntimeException("Erro ao alterar a data de saída. " +  e.getMessage());
                    }
                    break;
                default:
                    System.out.println("Opção inválida.");
                    break;
            }

            for (Filme f : listaFilmes){
                if (filme.getCodFilme() == f.getCodFilme()){
                    switch (i){
                        case 1:
                            f.setNome(filme.getNome());
                            break;
                        case 2:
                            f.setFaixaEtaria(filme.getFaixaEtaria());
                            break;
                        case 3:
                            f.setDuracao(filme.getDuracao());
                            break;
                        case 4:
                            f.setGenero(filme.getGenero());
                            break;
                        case 5:
                            f.setDataEstreia(filme.getDataEstreia());
                            break;
                        case 6:
                            f.setDataSaidaCartaz(filme.getDataSaidaCartaz());
                            break;
                    }
                }
                listaFilmesAtualizado.add(f);
            }

            if (mJsonProdutos.salvarFilme(listaFilmesAtualizado)){
                System.out.println("Filme editado com sucesso!");
            } else {
                System.out.println("Erro ao salvar as alterações do filme.");
            }
        }
    }
}
