package com.example.testproiect.coduri.promotionale;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.testproiect.R;


import java.util.List;

public class CoduriPromotionaleAdapter extends ArrayAdapter<CodPromotional> {
    private Context context;
    private int resource;
    private List<CodPromotional> coduriPromo;
    private LayoutInflater inflater;

    public CoduriPromotionaleAdapter(@NonNull Context context, int resource,  @NonNull List<CodPromotional> coduriP, LayoutInflater inflater) {
        super(context, resource, coduriP);
        this.context = context;
        this.resource = resource;
        this.coduriPromo = coduriP;
        this.inflater = inflater;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = inflater.inflate(resource, parent, false);
        CodPromotional cp = coduriPromo.get(position);
        if (cp != null) {
            addCodReducere(view,cp.getCodPromotional());
            addMagazin(view, cp.getMagazin());
            addReducere(view, cp.getReducere());
        }
        return view;
    }

    private void addCodReducere(View view, String cod) {
        TextView textView = view.findViewById(R.id.tvCodreducere);
        addTextViewContent(textView, cod);
    }

    private void addMagazin(View view, String magazin) {
        TextView textView = view.findViewById(R.id.tvMagazin);
        addTextViewContent(textView, magazin);
    }

    private void addReducere(View view, Double reducere) {
        TextView textView = view.findViewById(R.id.tvReducere);

        if (reducere != null) {
            addTextViewContent(textView, reducere.toString() + " %");
        }

    }

    private void addTextViewContent(TextView textView, String value) {
        if (value != null && !value.isEmpty()) {
            textView.setText(value);
        } else {
            textView.setText(com.example.testproiect.R.string.default_pret_value);
        }
    }
}
