package com.example.filip.leituragas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.example.filip.leituragas.Adapter.CondominioAdapater;
import com.example.filip.leituragas.DB.DaoCondominio;
import com.example.filip.leituragas.Models.Condominio;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listCond;
    Button btnAddCond;
    CondominioAdapater adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listCond = findViewById(R.id.listCond);
        btnAddCond = findViewById(R.id.btnAddCond);


        refresh();
        listCond = findViewById(R.id.listCond);
        listCond.setAdapter(adapter);
        listCond.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                Condominio condominio = (Condominio) parent.getItemAtPosition(i);
                Intent intent = new Intent(getApplicationContext(), DetalheCondominio.class);
                intent.putExtra("idCondAtivo",condominio.getId());
                intent.putExtra("nomeCondAtivo",condominio.getNome().toString());
                intent.putExtra("numAptosCondAtivo",condominio.getNum_aptos());
                startActivity(intent);
            }
        });

        listCond.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int i, long l) {
                Condominio condominio = (Condominio) parent.getItemAtPosition(i);
                DaoCondominio daoCondominio = new DaoCondominio(getApplicationContext());
                String msg = daoCondominio.remove(condominio);
                refresh();
                Toast.makeText(getApplicationContext(),msg, Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        btnAddCond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),AddCondominio.class);
                startActivity(intent);
            }
        });


    }

    private void refresh(){
        DaoCondominio daoCondominio= new DaoCondominio(getApplicationContext());
        ArrayList<Condominio> lista = daoCondominio.getAll();
        adapter = new CondominioAdapater(getApplicationContext(),lista);
        ListView list=  findViewById(R.id.listCond);
        list.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    protected void onRestart(){
        final DaoCondominio daoCondominio = new DaoCondominio(getApplicationContext());
        refresh();
        super.onRestart();
    }
}
