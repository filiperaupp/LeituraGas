package com.example.filip.leituragas.Models;

public class Leitura {
    private int id_leitura;
    private int id_tabela_leitura;
    private int numApto;
    private int leituraGas;

    public int getId_leitura() {
        return id_leitura;
    }

    public int getId_tabela_leitura() {
        return id_tabela_leitura;
    }

    public int getNumApto() {
        return numApto;
    }

    public void setId_tabela_leitura(int id_tabela_leitura) {
        this.id_tabela_leitura = id_tabela_leitura;
    }

    public int getLeituraGas() {
        return leituraGas;
    }

    public Leitura(int id_leitura, int id_tabela_leitura, int numApto, int leituraGas) {
        this.id_leitura = id_leitura;
        this.id_tabela_leitura = id_tabela_leitura;
        this.numApto = numApto;
        this.leituraGas = leituraGas;
    }

    public Leitura(int id_tabela_leitura, int numApto, int leituraGas) {
        this.id_tabela_leitura = id_tabela_leitura;
        this.numApto = numApto;
        this.leituraGas = leituraGas;
    }
}
