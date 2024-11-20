package com.cinema.controller;

import com.cinema.model.Filme;
import com.cinema.model.manipularJsonProdutos;
import com.google.gson.*;

import java.io.IOException;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Classe adaptadora para serializar e desserializar objetos da classe Filme usando Gson.
 * Implementa as interfaces JsonSerializer<Filme> e JsonDeserializer<Filme>.
 */
public class FilmeAdapter implements JsonSerializer<Filme> {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    manipularJsonProdutos mJson = new manipularJsonProdutos();
    List<Filme> filmes = mJson.buscarFilmes();
    /**
     * Construtor da classe FilmeAdapter.
     *
     * @throws IOException se ocorrer um erro ao buscar a lista de filmes.
     */

    public FilmeAdapter() throws IOException {
    }


    /**
     * Serializa um objeto Filme em um JsonElement.
     *
     * @param filme o objeto Filme a ser serializado.
     * @param typeOfSrc o tipo da origem do objeto.
     * @param context o contexto de serialização.
     * @return um JsonElement representando o objeto Filme.
     */
    @Override
    public JsonElement serialize(Filme filme, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject json = new JsonObject();

        json.addProperty("nome", filme.getNome());
        json.addProperty("faixaEtaria", filme.getFaixaEtaria());
        json.addProperty("duracao", filme.getGenero());
        json.addProperty("genero", filme.getGenero());
        json.addProperty("dataEstreia", formatter.format(filme.getDataEstreia().toInstant()));
        json.addProperty("dataSaidaCartaz", formatter.format(filme.getDataSaidaCartaz().toInstant()));

        return json;
    }

    /**
     * Desserializa um JsonElement em uma lista de objetos Filme.
     *
     * @param json o JsonElement a ser desserializado.
     * @param typeOfT o tipo do destino do objeto.
     * @param context o contexto de desserialização.
     * @return uma lista de objetos Filme.
     * @throws JsonParseException se ocorrer um erro durante a desserialização.
     */
    //@Override
    public List<Filme> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        int totalFilmes = filmes.size();
        List<Filme> filmesDesserializados = new ArrayList<>();
        for (int i=0; i < totalFilmes; i++) {
            JsonObject jsonObject = json.getAsJsonObject();
            String titulo = jsonObject.get("titulo").getAsString();
            int faixaEtaria = jsonObject.get("faixaEtaria").getAsInt();
            int duracao = jsonObject.get("duracao").getAsInt();
            String genero = jsonObject.get("genero").getAsString();
            Date dataEstreia = parseDate(jsonObject.get("dataEstreia").getAsString());
            Date dataSaidaCartaz = parseDate(jsonObject.get("dataSaidaCartaz").getAsString());

            Filme filme = new Filme(titulo, faixaEtaria, genero, duracao, dataEstreia, dataSaidaCartaz);
            filmesDesserializados.add(filme);
        }

        return filmesDesserializados;
    }

    private Date parseDate(String dateString) {
        return (Date) formatter.parse(dateString);
    }
}
