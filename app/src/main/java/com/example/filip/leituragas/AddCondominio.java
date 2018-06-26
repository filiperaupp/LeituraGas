package com.example.filip.leituragas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.filip.leituragas.DB.DaoCondominio;
import com.example.filip.leituragas.Models.Condominio;

public class AddCondominio extends AppCompatActivity {

    EditText edNome;
    EditText edNumAptos;
    Button btnAdicionar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_condominio);

        btnAdicionar = findViewById(R.id.btnCadastrar);

        btnAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    edNome = findViewById(R.id.edNome);
                    String nome = edNome.getText().toString();
                    edNumAptos = findViewById(R.id.edNumAptos);
                    int numAptos = Integer.parseInt(edNumAptos.getText().toString());
                    Condominio novoCondominio = new Condominio(nome, numAptos);

                    DaoCondominio daoCondominio = new DaoCondominio(getApplicationContext());
                    daoCondominio.save(novoCondominio);
                    finish();
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(),"Preencha os campos corretamente", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}
