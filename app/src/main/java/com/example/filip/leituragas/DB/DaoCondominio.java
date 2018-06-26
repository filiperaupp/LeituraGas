package com.example.filip.leituragas.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.filip.leituragas.Models.Condominio;

import java.util.ArrayList;

public class DaoCondominio {
    private SQLiteDatabase db;
    private Banco banco;

    public DaoCondominio(Context context) {
        banco = new Banco(context);
    }

    public String save(Condominio condominio) {
        String msg="";

        ContentValues values;
        db = banco.getWritableDatabase();
        values = new ContentValues();
        values.put(Banco.NOME,condominio.getNome());
        values.put(Banco.NUMERO_APTOS,condominio.getNum_aptos());

        long result = db.insert(Banco.TABELA,null,values);
        if(result==-1){
            msg="Erro";
        }else{
            msg= "Sucesso ";
        }

        return msg;
    }

    public ArrayList<Condominio> getAll(){
        ArrayList<Condominio> lista = new ArrayList<>();
        Cursor cursor;
        String[] campos = {banco.ID,banco.NOME,banco.NUMERO_APTOS};
        db = banco.getReadableDatabase();
        cursor = db.query(banco.TABELA, campos,null,null,null,null,null,null);

        while (cursor.moveToNext()) {
            int id= cursor.getInt(0);
            String nome = cursor.getString(1);
            int num_apto = cursor.getInt(2);


            Condominio newCond = new Condominio(id,nome,num_apto);
            lista.add(newCond);
        }
        db.close();
        return lista;
    }

    public Condominio getById(Condominio cond){
        //String where = banco.ID+"="+cond.getId();
        String[] colunas = {"id_tabela","data","id_condominio"};
        String sql = "SELECT * FROM condominios WHERE _id="+cond.getId()+";";
        Cursor cursor;
        cursor = db.query("tabelas",colunas,null,null,null,null,"DESC","1");

        int id= cursor.getInt(0);
        String nome = cursor.getString(1);
        int num_apto = cursor.getInt(2);


        Condominio consultCond = new Condominio(id,nome,num_apto);

        return consultCond;
    }

    public String remove(Condominio cond){
        String where = banco.ID+"="+cond.getId();
        db = banco.getWritableDatabase();
        db.delete(Banco.TABELA,where,null);
        db.close();
        return cond.getNome()+ "REMOVIDO";
    }
}
