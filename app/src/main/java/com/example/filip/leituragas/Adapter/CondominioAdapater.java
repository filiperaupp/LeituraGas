package com.example.filip.leituragas.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.filip.leituragas.Models.Condominio;
import com.example.filip.leituragas.R;

import java.util.List;

public class CondominioAdapater extends ArrayAdapter<Condominio> {
    public CondominioAdapater(@NonNull Context context, @NonNull List<Condominio> objects){
        super(context,0,objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listCondominioView = convertView;

        if (listCondominioView == null) {
            listCondominioView = LayoutInflater.from(getContext()).inflate(R.layout.item_condominio, parent, false);

        }
        Condominio atual = getItem(position);

        TextView txtId = listCondominioView.findViewById(R.id.txtId);
        TextView txtNome = listCondominioView.findViewById(R.id.txtNomeCond);

        txtId.setText(String.valueOf(atual.getId()));
        txtNome.setText(atual.getNome());

        return listCondominioView;
    }
}
