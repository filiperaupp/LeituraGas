package com.example.filip.leituragas.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.filip.leituragas.Models.Tabela;
import com.example.filip.leituragas.R;

import java.util.List;

public class TabelaAdapter extends ArrayAdapter<Tabela> {
    public TabelaAdapter(@NonNull Context context, @NonNull List<Tabela> objects){
        super(context,0,objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listTabelaView = convertView;

        if (listTabelaView == null) {

            listTabelaView = LayoutInflater.from(getContext()).inflate(R.layout.item_tabela, parent, false);
        }
        Tabela atual = getItem(position);

        TextView txtData = listTabelaView.findViewById(R.id.txtData);
        TextView txtNum = listTabelaView.findViewById(R.id.txtNum);

        txtNum.setText(String.valueOf(position+1));
        txtData.setText(atual.getData());

        return listTabelaView;
    }
}
