package com.example.filip.leituragas.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Banco extends SQLiteOpenHelper {

    public static final String NOME_BANCO = "leiturasGas.db";
    //condominio
    public static final String TABELA = "condominios";
    public static final String ID = "_id";
    public static final String NOME = "nome";
    public static final String NUMERO_APTOS = "numero_aptos";

    //tabela
    public static final String TABELA_2 = "tabelas";
    public static final String ID_TABELA= "id_tabela";
    public static final String DATA = "data";
    public static final String ID_CONDOMINIO = "id_condominio";

    //leitura
    public static final String TABELA_3 = "leituras";
    public static final String ID_LEITURA = "id_leitura";
    public static final String ID_TABELA_LEITURA = "id_tabela_leitura";
    public static final String NUM_APTO = "num_apto";
    public static final String LEITURA_GAS = "leitura_gas";


    public static final int VERSAO = 1;

    public  Banco(Context context){
        super(context,NOME_BANCO,null,VERSAO);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "CREATE TABLE "+TABELA+"("
                + ID + " integer primary key autoincrement,"
                + NOME + " text,"
                + NUMERO_APTOS + " integer"
                +")";

        String sql2 = "CREATE TABLE "+ TABELA_2+"("
                + ID_TABELA+ " integer primary key autoincrement,"
                + DATA + " text,"
                + ID_CONDOMINIO + " integer"
                +")";

        String sql3 = "CREATE TABLE "+ TABELA_3+"("
                + ID_LEITURA+ " integer primary key autoincrement,"
                + ID_TABELA_LEITURA + " integer,"
                + NUM_APTO + " integer,"
                + LEITURA_GAS + " integer"
                +")";

        db.execSQL(sql);
        db.execSQL(sql2);
        db.execSQL(sql3);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "  + TABELA);
        onCreate(db);
    }
}
