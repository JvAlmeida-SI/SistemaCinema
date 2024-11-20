package com.cinema.model;


/**
 * Representa uma sala de cinema com um número de poltronas e um número de sala.
 */
public class Sala {
    // Atributos da sala
    private int poltrona;
    private int numSala;

    /**
     * Construtor da sala com poltronas e número de sala.
     *
     * @param poltrona O número de poltronas da sala.
     * @param numSala  O número da sala.
     */
    public Sala(int poltrona, int numSala) {
        this.poltrona = poltrona;
        this.numSala = numSala;
    }

    /**
     * Construtor padrão da sala.
     */
    public Sala(){
    }

    //Getters e Setters para os atributos da Sala
    public int getPoltrona() {
        return poltrona;
    }

    public void setPoltrona(int poltrona) {
        this.poltrona = poltrona;
    }

    public int getNumSala() {
        return numSala;
    }

    public void setNumSala(int numSala) {
        this.numSala = numSala;
    }

    @Override
    public String toString() {
        return "\nPoltrona = " + this.poltrona +
                "\nNumero da sala = " + this.numSala;
    }
}
