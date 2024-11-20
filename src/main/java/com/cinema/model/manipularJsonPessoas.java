package com.cinema.model;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * A classe manipularJsonPessoas é responsável por salvar e buscar informações de funcionários, administradores e clientes em arquivos JSON.
 */
public class manipularJsonPessoas {
    // Caminho JSON dos Funcionários
    private String caminhoJsonFuncionario = "D:\\Documentos\\Desktop\\SistemaCinema\\src\\main\\java\\com\\cinema\\arquivosJson\\Funcionario.json";
    // Caminho JSON dos Administradores
    private String caminhoJsonAdministrador = "D:\\Documentos\\Desktop\\SistemaCinema\\src\\main\\java\\com\\cinema\\arquivosJson\\Administrador.json";
    // Caminho JSON dos Clientes
    private String caminhoJsonCliente = "D:\\Documentos\\Desktop\\SistemaCinema\\src\\main\\java\\com\\cinema\\arquivosJson\\Cliente.json";

    /**
     * Construtor padrão.
     */
    public manipularJsonPessoas() {
    }

    /**
     * Salva a lista de funcionários em um arquivo JSON.
     *
     * @param funcionario A lista de funcionários a ser salva.
     * @return true se os dados foram salvos com sucesso, false caso contrário.
     * @throws IOException Se ocorrer um erro de I/O ao salvar os dados.
     */
    public boolean salvarFuncionario (List<Funcionario> funcionario) throws IOException {
        Gson objectJson = new GsonBuilder().setPrettyPrinting().create();
        String caminhoJson = caminhoJsonFuncionario;

        String dadosFuncionarios = objectJson.toJson(funcionario);
        
        try {
            FileWriter writer = new FileWriter(caminhoJson);
            writer.write(dadosFuncionarios);
            writer.flush();
            return true;
        } catch (IOException e) {
            System.out.println("ERRO AO SALVAR OS DADOS. manipularJson" + e.getMessage());
            return false;
        }
    }

    /**
     * Busca e retorna a lista de funcionários a partir do arquivo JSON.
     *
     * @return A lista de funcionários encontrada no arquivo JSON, ou uma lista vazia se não encontrar nada.
     * @throws IOException Se ocorrer um erro de I/O ao ler os dados.
     */
    public List<Funcionario> buscarFuncionario() throws IOException{
        Gson objectJson = new Gson();
        File funcionarioFile = new File (caminhoJsonFuncionario);
        
        try {
            String dadosFuncuncionario = new String(Files.readAllBytes(Paths.get(funcionarioFile.toURI())));
            Type listType = new TypeToken<List<Funcionario>>() {}.getType();
            return objectJson.fromJson(dadosFuncuncionario, listType);
        } catch (IOException e){
            return new ArrayList<>();
        }
    }

    /**
     * Salva a lista de administradores em um arquivo JSON.
     *
     * @param adm A lista de administradores a ser salva.
     * @return true se os dados foram salvos com sucesso, false caso contrário.
     * @throws IOException Se ocorrer um erro de I/O ao salvar os dados.
     */
    public boolean salvarAdministrador (List<Administrador> adm) throws IOException {
        Gson objectJson = new GsonBuilder().setPrettyPrinting().create();
        String caminhoJson = caminhoJsonAdministrador;

        String dadosAdministrador = objectJson.toJson(adm);

        try {
            FileWriter writer = new FileWriter(caminhoJson);
            writer.write(dadosAdministrador);
            writer.flush();
            return true;
        } catch (IOException e) {
            System.out.println("ERRO AO SALVAR OS DADOS. manipularJson" + e.getMessage());
            return false;
        }
    }

    /**
     * Busca e retorna a lista de administradores a partir do arquivo JSON.
     *
     * @return A lista de administradores encontrada no arquivo JSON, ou uma lista vazia se não encontrar nada.
     * @throws IOException Se ocorrer um erro de I/O ao ler os dados.
     */
    public List<Administrador> buscarAdministrador() throws IOException{
        Gson objectJson = new Gson();
        File administradorFile = new File (caminhoJsonAdministrador);

        try {
            String dadosAdministrador = new String(Files.readAllBytes(Paths.get(administradorFile.toURI())));
            Type listType = new TypeToken<List<Administrador>>() {}.getType();
            return objectJson.fromJson(dadosAdministrador, listType);
        } catch (IOException e){
            return new ArrayList<>();
        }
    }

    /**
     * Salva a lista de clientes em um arquivo JSON.
     *
     * @param clientes A lista de clientes a ser salva.
     * @return true se os dados foram salvos com sucesso, false caso contrário.
     * @throws IOException Se ocorrer um erro de I/O ao salvar os dados.
     */
    public boolean salvarCliente (List<Cliente> clientes) throws IOException {
        Gson objectJson = new GsonBuilder().setPrettyPrinting().create();
        String caminhoJson = caminhoJsonCliente;

        String dadosCliente = objectJson.toJson(clientes);

        try {
            FileWriter writer = new FileWriter(caminhoJson);
            writer.write(dadosCliente);
            writer.flush();
            return true;
        } catch (IOException e) {
            System.out.println("ERRO AO SALVAR OS DADOS. manipularJson" + e.getMessage());
            return false;
        }
    }

    /**
     * Busca e retorna a lista de clientes a partir do arquivo JSON.
     *
     * @return A lista de clientes encontrada no arquivo JSON, ou uma lista vazia se não encontrar nada.
     * @throws IOException Se ocorrer um erro de I/O ao ler os dados.
     */
    public List<Cliente> buscarCliente() throws IOException{
        Gson objectJson = new Gson();
        File clienteFile = new File (caminhoJsonCliente);

        try {
            String dadosCliente = new String(Files.readAllBytes(Paths.get(clienteFile.toURI())));
            Type listType = new TypeToken<List<Cliente>>() {}.getType();
            return objectJson.fromJson(dadosCliente, listType);
        } catch (IOException e){
            return new ArrayList<>();
        }
    }

    @Override
    public String toString(){
        return "Manipulando Arquivos JSON";
    }
}
