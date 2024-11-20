package com.cinema.model;

import com.cinema.controller.FilmeAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Classe responsável por manipular os dados dos produtos (filmes e comidas) em arquivos JSON.
 */
public class manipularJsonProdutos {
    // Caminho JSON dos Filmes
    private String caminhoJsonFilmes = "D:\\Documentos\\Desktop\\SistemaCinema\\src\\main\\java\\com\\cinema\\arquivosJson\\Filmes.json";
    // Caminho JSON dos Alimentos
    private String caminhoJsonComidas = "D:\\Documentos\\Desktop\\SistemaCinema\\src\\main\\java\\com\\cinema\\arquivosJson\\Comidas.json";

    /**
     * Construtor padrão da classe.
     */
    public manipularJsonProdutos(){
    }

    /**
     * Salva a lista de filmes em um arquivo JSON.
     *
     * @param filmes A lista de filmes a ser salva.
     * @return true se os dados foram salvos com sucesso, false caso contrário.
     * @throws RuntimeException Se ocorrer uma falha ao salvar os dados.
     */
    public boolean salvarFilme(List<Filme> filmes){
        Gson objectJson = new GsonBuilder()
                                .setDateFormat("dd-MM-yyyy HH:mm:ss")
                                .create();

        String dadosFilmes = objectJson.toJson(filmes);

        try {
            FileWriter writer = new FileWriter(caminhoJsonFilmes);
            writer.write(dadosFilmes);
            writer.flush();
            return true;
        } catch (IOException e) {
            throw new RuntimeException("Falha ao salvar filme. ManipularJson\n" + e.getMessage());
        }
    }

    /**
     * Busca e retorna a lista de filmes a partir do arquivo JSON.
     *
     * @return A lista de filmes encontrada no arquivo JSON, ou uma lista vazia se não encontrar nada.
     * @throws IOException Se ocorrer uma falha ao buscar os dados.
     */
    public List<Filme> buscarFilmes() throws IOException{
        Gson objectJson = new GsonBuilder()
                .setDateFormat("dd/MM/yyyy HH:mm:ss")
                .setPrettyPrinting()
                .create();
        File filmesFile = new File (caminhoJsonFilmes);

        try (FileReader reader = new FileReader(filmesFile)) {
            Type listType = new TypeToken<List<Filme>>() {}.getType();
            return objectJson.fromJson(reader, listType);
        } catch (IOException e){
            System.out.println("Falha ao buscar filmes. ManipularJson\n" + e.getMessage());
            return new ArrayList<>();
        }
    }

    /**
     * Salva a lista de comidas em um arquivo JSON.
     *
     * @param comidas A lista de comidas a ser salva.
     * @return true se os dados foram salvos com sucesso, false caso contrário.
     */
    public boolean salvarComida (List<Comida> comidas){
        Gson objectJson = new GsonBuilder().setDateFormat("dd/MM/yyyy HH:mm:ss").setPrettyPrinting().create();

        String dadosComidas = objectJson.toJson(comidas);

        try {
            FileWriter writer = new FileWriter(caminhoJsonComidas);
            writer.write(dadosComidas);
            writer.flush();
            return true;
        } catch (IOException e) {
            System.out.println("Falha ao salvar comida. ManipularJson\n" + e.getMessage());
            return false;
        }
    }

    /**
     * Busca e retorna a lista de comidas a partir do arquivo JSON.
     *
     * @return A lista de comidas encontrada no arquivo JSON, ou uma lista vazia se não encontrar nada.
     * @throws IOException Se ocorrer uma falha ao buscar os dados.
     */
    public List<Comida> buscarComidas() throws IOException {
        Gson objectJson = new GsonBuilder().setDateFormat("dd/MM/yyyy HH:mm:ss").create();
        File comidasFile = new File(caminhoJsonComidas);

        try (FileReader reader = new FileReader(comidasFile)) {
            Type listType = new TypeToken<List<Comida>>() {}.getType();
            return objectJson.fromJson(reader, listType);
        } catch (IOException e){
            System.out.println("Falha ao buscar comidas. ManipularJson\n" + e.getMessage());
            return new ArrayList<>();
        }
    }

}
