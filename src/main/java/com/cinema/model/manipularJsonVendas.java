package com.cinema.model;

import com.cinema.controller.DateTimeAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsável por manipular os dados das vendas em um arquivo JSON.
 */
public class manipularJsonVendas {
    // Caminho JSON das Vendas
    private String caminhoJsonVendas = "D:\\Documentos\\Desktop\\SistemaCinema\\src\\main\\java\\com\\cinema\\arquivosJson\\Vendas.json";

    /**
     * Construtor padrão da classe.
     */
    public manipularJsonVendas() {
    }

    /**
     * Grava a lista de carrinhos de compra em um arquivo JSON.
     *
     * @param carrinho A lista de carrinhos de compra a ser gravada.
     * @return true se os dados foram gravados com sucesso, false caso contrário.
     * @throws RuntimeException Se ocorrer uma falha ao gravar os dados.
     */
    public boolean gravarVenda (List<Carrinho> carrinho){
        Gson objectJson = new GsonBuilder()
                .setDateFormat("dd-MM-yyyy HH:mm:ss")
                .setPrettyPrinting()
                .create();

        String dadosVendas = objectJson.toJson(carrinho);

        try {
            FileWriter writer = new FileWriter(caminhoJsonVendas);
            writer.write(dadosVendas);
            writer.flush();
            return true;
        } catch (IOException e) {
            throw new RuntimeException("Falha ao salvar venda. ManipularJson\n" + e.getMessage());
        }
    }

    /**
     * Busca e retorna a lista de vendas a partir do arquivo JSON.
     *
     * @return A lista de vendas encontrada no arquivo JSON, ou uma lista vazia se não encontrar nada.
     */
    public List<Carrinho> buscarVendas(){
        Gson objectJson = new GsonBuilder()
                .setDateFormat("dd-MM-yyyy HH:mm:ss")
                .setPrettyPrinting()
                .create();

        File vendasFile = new File (caminhoJsonVendas);

        try (FileReader reader = new FileReader(vendasFile)) {
            Type listType = new TypeToken<List<Carrinho>>() {}.getType();
            return objectJson.fromJson(reader, listType);
        } catch (IOException e){
            System.out.println("Falha ao buscar vendas. ManipularJson\n" + e.getMessage());
            return new ArrayList<>();
        }
    }
}
