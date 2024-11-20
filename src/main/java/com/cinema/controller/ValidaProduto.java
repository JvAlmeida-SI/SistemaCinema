package com.cinema.controller;

import com.cinema.model.manipularJsonProdutos;
import com.cinema.model.manipularJsonVendas;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public abstract class ValidaProduto {
    static manipularJsonProdutos mJsonProdutos = new manipularJsonProdutos();
    static manipularJsonVendas mJsonVendas = new manipularJsonVendas();
    static Scanner input = new Scanner(System.in);
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");


}
