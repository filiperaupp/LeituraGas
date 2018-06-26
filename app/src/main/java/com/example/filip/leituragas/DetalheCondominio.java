package com.example.filip.leituragas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.filip.leituragas.Adapter.TabelaAdapter;
import com.example.filip.leituragas.DB.DaoTabela;
import com.example.filip.leituragas.Models.Tabela;
import com.example.filip.leituragas.R;

import java.util.ArrayList;

public class DetalheCondominio extends AppCompatActivity {

    TextView txtNomeCond;
    Button btnAddLeitura;
    TabelaAdapter adapter;
    ListView listTabelas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_condominio);
        btnAddLeitura = findViewById(R.id.btnAddLeitura);
        final int idCondAtivo;
        final int numAptosCondAtivo;

        refresh();
        listTabelas = findViewById(R.id.listTabelas);
        listTabelas.setAdapter(adapter);

        txtNomeCond = findViewById(R.id.txtNomeDoCondominio);
        idCondAtivo = (Integer) getIntent().getSerializableExtra("idCondAtivo");
        numAptosCondAtivo = (Integer) getIntent().getSerializableExtra("numAptosCondAtivo");
        String nomeAtivo = getIntent().getSerializableExtra("nomeCondAtivo").toString();
        txtNomeCond.setText(nomeAtivo);

        listTabelas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Tabela tab = (Tabela) adapterView.getItemAtPosition(i);
                Intent intent = new Intent(getApplicationContext(),DetalheLeitura.class);
                intent.putExtra("idTabAtiva",tab.getId_tabela());
                intent.putExtra("numAptosCondAtivo",numAptosCondAtivo);
                intent.putExtra("dataTabela",tab.getData());
                startActivity(intent);
            }
        });

        listTabelas.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int i, long l) {
                Tabela tab = (Tabela) parent.getItemAtPosition(i);
                DaoTabela daoTabela = new DaoTabela(getApplicationContext());
                String msg = daoTabela.remove(tab);
                refresh();
                Toast.makeText(getApplicationContext(),msg, Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        btnAddLeitura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),AddLeitura.class);
                intent.putExtra("idCondAtivo",idCondAtivo);
                intent.putExtra("numAptosCondAtivo",numAptosCondAtivo);
                startActivity(intent);
            }
        });
    }

    private void refresh(){
        final int idCondAtivo = (Integer) getIntent().getSerializableExtra("idCondAtivo");
        DaoTabela daoTabela= new DaoTabela(getApplicationContext());
        ArrayList<Tabela> lista = daoTabela.getAllFromCondominio(idCondAtivo);
        adapter = new TabelaAdapter(getApplicationContext(),lista);
        ListView list=  findViewById(R.id.listTabelas);
        list.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    protected void onRestart(){
        final DaoTabela daoTabela = new DaoTabela(getApplicationContext());
        refresh();
        super.onRestart();
    }
}
