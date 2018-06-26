package com.example.filip.leituragas.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.filip.leituragas.Models.Tabela;

import java.util.ArrayList;

public class DaoTabela {
    private SQLiteDatabase db;
    private Banco banco;

    public DaoTabela(Context context) {
        banco = new Banco(context);
    }

    public String save(Tabela tabela) {
        String msg="";

        ContentValues values;
        db = banco.getWritableDatabase();
        values = new ContentValues();
        values.put(Banco.DATA,tabela.getData());
        values.put(Banco.ID_CONDOMINIO,tabela.getId_condominio());

        long result = db.insert(Banco.TABELA_2,null,values);
        if(result==-1){
            msg="Erro";
        }else{
            msg= "Sucesso ";
        }

        return msg;
    }

    public ArrayList<Tabela> getAllFromCondominio(int idCond){
        ArrayList<Tabela> lista = new ArrayList<>();
        Cursor cursor;
        db = banco.getReadableDatabase();
        cursor = db.query("tabelas", null,"id_condominio="+idCond,null,null,null,null);
        while (cursor.moveToNext()) {
            int id_tabela= cursor.getInt(0);
            String data = cursor.getString(1);
            int id_condominio = cursor.getInt(2);


            Tabela newTabela = new Tabela(id_tabela,data,id_condominio);
            lista.add(newTabela);
        }
        db.close();
        return lista;
    }

    public Tabela getTheLast(){
        String sql = "SELECT * FROM tabelas ORDER BY id_tabela DESC;";
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToNext();
        int id= cursor.getInt(0);
        String data = cursor.getString(1);
        int id_condominio = cursor.getInt(2);


        Tabela consultTabela= new Tabela(id,data,id_condominio);

        return consultTabela;
    }

    public String remove(Tabela tabela){
        String where = banco.ID_TABELA+"="+tabela.getId_tabela();
        db = banco.getWritableDatabase();
        db.delete(Banco.TABELA_2,where,null);
        db.close();
        return tabela.getData()+ " DATA REMOVIDO";
    }
}
