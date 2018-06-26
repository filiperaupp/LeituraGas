package com.example.filip.leituragas.Models;

public class Tabela {
    private int id_tabela;
    private String data;
    private int id_condominio;

    public int getId_tabela() {
        return id_tabela;
    }

    public String getData() {
        return data;
    }

    public int getId_condominio() {
        return id_condominio;
    }

    public Tabela(int id_tabela, String data, int id_condominio) {
        this.id_tabela = id_tabela;
        this.data = data;
        this.id_condominio = id_condominio;
    }

    public Tabela(String data, int id_condominio) {
        this.data = data;
        this.id_condominio = id_condominio;
    }
}
