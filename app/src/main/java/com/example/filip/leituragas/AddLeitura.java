package com.example.filip.leituragas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.example.filip.leituragas.DB.DaoLeitura;
import com.example.filip.leituragas.DB.DaoTabela;
import com.example.filip.leituragas.Models.Leitura;
import com.example.filip.leituragas.Models.Tabela;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class AddLeitura extends AppCompatActivity {

    LinearLayout llLeituras, llAptos, llLeituraGas;
    ArrayList<EditText> listEditAptos = new ArrayList<>();
    ArrayList<EditText> listEditLeiturasGas= new ArrayList<>();
    Button btnEnviarLeitura;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_leitura);
        llLeituras = findViewById(R.id.llLeituras);
        llAptos = findViewById(R.id.llAptos);
        llLeituraGas = findViewById(R.id.llLeituraGas);
        btnEnviarLeitura = findViewById(R.id.btnEnviarLeitura);

        final int numAptosCondAtivo = (Integer) getIntent().getSerializableExtra("numAptosCondAtivo");
        final int idCondAtivo = (Integer) getIntent().getSerializableExtra("idCondAtivo");
//pega data atual
        SimpleDateFormat formataData = new SimpleDateFormat("dd-MM-yyyy");
        Date data = new Date();
        String dataFormatada = formataData.format(data);
//----------------------
        final EditText dataLeitura = new EditText(this);
        dataLeitura.setInputType(InputType.TYPE_CLASS_DATETIME);
        dataLeitura.setText(dataFormatada);
        dataLeitura.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        llLeituras.addView(dataLeitura);
        for (int i=0; i<numAptosCondAtivo; i++) {
            listEditAptos.add(new EditText(this));
            listEditAptos.get(i).setHint("Apartamento");
            listEditAptos.get(i).setInputType(InputType.TYPE_CLASS_NUMBER);
            llAptos.addView(listEditAptos.get(i));

            listEditLeiturasGas.add(new EditText(this));
            listEditLeiturasGas.get(i).setHint("Leitura gás");
            listEditLeiturasGas.get(i).setInputType(InputType.TYPE_CLASS_NUMBER);
            llLeituraGas.addView(listEditLeiturasGas.get(i));
        }

        btnEnviarLeitura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {

                    ArrayList<Leitura> leituras = new ArrayList<>();
                    DaoLeitura daoLeitura = new DaoLeitura(getApplicationContext());
                    for (int i=0; i<numAptosCondAtivo; i++){
                        int apto = Integer.parseInt(listEditAptos.get(i).getText().toString());
                        int leituraGas =  Integer.parseInt(listEditLeiturasGas.get(i).getText().toString());
                        Leitura leitura = new Leitura(0,apto,leituraGas);
                        leituras.add(leitura);
                        //daoLeitura.save(leitura);
                    }
                    if (dataLeitura.getText().toString()==" "){
                        Toast.makeText(getApplicationContext(),"Data vazia", Toast.LENGTH_LONG).show();

                    }
                    Tabela tabela = new Tabela(dataLeitura.getText().toString(),idCondAtivo);
                    DaoTabela daoTabela = new DaoTabela(getApplicationContext());
                    daoTabela.save(tabela);
                    Tabela tabelaAtual = daoTabela.getTheLast();
                    for (int i=0; i<numAptosCondAtivo; i++){
                        leituras.get(i).setId_tabela_leitura(tabelaAtual.getId_tabela());
                        daoLeitura.save(leituras.get(i));
                    }
                    finish();
                } catch (Exception e) {

                    Toast.makeText(getApplicationContext(),"Preencha os campos corretamente.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
