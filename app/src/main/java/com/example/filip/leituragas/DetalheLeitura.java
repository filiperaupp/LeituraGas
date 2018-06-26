package com.example.filip.leituragas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.filip.leituragas.DB.DaoLeitura;
import com.example.filip.leituragas.DB.DaoTabela;
import com.example.filip.leituragas.Models.Leitura;
import com.example.filip.leituragas.R;

import java.util.ArrayList;

public class DetalheLeitura extends AppCompatActivity {

    LinearLayout llDetalheLeituras, llTableAptos, llTableLeituraGas;
    TextView txtLeituraTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_leitura);

        llDetalheLeituras = findViewById(R.id.llDetalheLeituras);
        llTableAptos = findViewById(R.id.llTableAptos);
        llTableLeituraGas = findViewById(R.id.llTableLeituraGas);
        txtLeituraTitle = findViewById(R.id.txtLeituraTitle);

        int idTabAtiva= (Integer) getIntent().getSerializableExtra("idTabAtiva");
        int numAptosCondAtivo = (Integer) getIntent().getSerializableExtra("numAptosCondAtivo");
        String dataTabela = getIntent().getSerializableExtra("dataTabela").toString();
        txtLeituraTitle.setText("Leitura feita em "+ dataTabela);

        DaoLeitura daoLeitura = new DaoLeitura(getApplicationContext());
        ArrayList<Leitura> leituras = daoLeitura.getLeiturarPorTabela(idTabAtiva,dataTabela);
        for (int i=0; i<leituras.size(); i++){
            TextView txtApto = new TextView(getApplicationContext());
            txtApto.setText(String.valueOf(leituras.get(i).getNumApto()));
            txtApto.setTextSize(TypedValue.COMPLEX_UNIT_DIP,20);
            llTableAptos.addView(txtApto);

            TextView txtLeituraGas = new TextView(getApplicationContext());
            txtLeituraGas.setText(String.valueOf(leituras.get(i).getLeituraGas()));
            txtLeituraGas.setTextSize(TypedValue.COMPLEX_UNIT_DIP,20);
            llTableLeituraGas.addView(txtLeituraGas);
        }
    }
}
