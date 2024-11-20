package com.cinema.controller;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Classe adaptadora para serialização e desserialização de objetos LocalDateTime com Gson.
 */
public class DateTimeAdapter extends TypeAdapter<LocalDateTime> {
    // Define o formato da data e hora
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    /**
     * Serializa um objeto LocalDateTime para JSON.
     * @param out o JsonWriter usado para escrever a saída JSON.
     * @param value o objeto LocalDateTime a ser serializado.
     * @throws IOException se ocorrer um erro de I/O durante a escrita.
     */
    @Override
    public void write(JsonWriter out, LocalDateTime value) throws IOException {
        out.value(value.format(formatter));
    }

    /**
     * Desserializa uma string JSON para um objeto LocalDateTime.
     * @param in o JsonReader usado para ler a entrada JSON.
     * @return o objeto LocalDateTime desserializado.
     * @throws IOException se ocorrer um erro de I/O durante a leitura.
     */
    @Override
    public LocalDateTime read(JsonReader in) throws IOException {
        return LocalDateTime.parse(in.nextString(), formatter);
    }
}
