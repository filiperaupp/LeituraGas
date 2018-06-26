package com.example.filip.leituragas.Models;

public class Condominio {
    private int id;
    private String nome;
    private int num_apto;

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public int getNum_aptos() {
        return num_apto;
    }

    public Condominio(int id, String nome, int num_apto) {
        this.id = id;
        this.nome = nome;
        this.num_apto = num_apto;
    }

    public Condominio(String nome, int num_apto) {
        this.nome = nome;
        this.num_apto = num_apto;
    }
}
