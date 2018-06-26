package com.example.filip.leituragas.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.filip.leituragas.Models.Leitura;

import java.util.ArrayList;

public class DaoLeitura {
    private SQLiteDatabase db;
    private Banco banco;

    public DaoLeitura(Context context) {
        banco = new Banco(context);
    }

    public String save(Leitura leitura) {
        String msg="";

        ContentValues values;
        db = banco.getWritableDatabase();
        values = new ContentValues();
        values.put(Banco.ID_TABELA_LEITURA,leitura.getId_tabela_leitura());
        values.put(Banco.NUM_APTO,leitura.getNumApto());
        values.put(Banco.LEITURA_GAS,leitura.getLeituraGas());

        long result = db.insert(Banco.TABELA_3,null,values);
        if(result==-1){
            msg="Erro";
        }else{
            msg= "Sucesso ";
        }

        return msg;
    }

    public ArrayList<Leitura> getLeiturarPorTabela(int idTabela, String dataTabela){
            ArrayList<Leitura> lista = new ArrayList<>();
            Cursor cursor;
            db = banco.getReadableDatabase();
            cursor = db.query("leituras", null,"id_tabela_leitura="+idTabela,null,null,null,null);
            while (cursor.moveToNext()) {
                int id_leitura = cursor.getInt(0);
                int id_tabela_leitura= cursor.getInt(1);
                int apto = cursor.getInt(2);
                int leitura_gas = cursor.getInt(3);


                Leitura newLeitura = new Leitura(id_leitura,id_tabela_leitura,apto,leitura_gas);
                lista.add(newLeitura);
            }
            db.close();
            return lista;

    }
}
