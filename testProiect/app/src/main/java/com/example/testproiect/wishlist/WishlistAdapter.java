package com.example.testproiect.wishlist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.testproiect.R;
import com.example.testproiect.card.DateConverter;

import java.util.List;

import static android.os.Build.VERSION_CODES.R;

public class WishlistAdapter extends ArrayAdapter<Wishlist> {
    private Context context;
    private int resource;
    private List<Wishlist> wishlist;
    private LayoutInflater inflater;

    public WishlistAdapter(@NonNull Context context, int resource, @NonNull List<Wishlist> objects, LayoutInflater inflater) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.wishlist = objects;
        this.inflater = inflater;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = inflater.inflate(resource, parent, false);
        Wishlist wl = wishlist.get(position);
        if (wl != null) {
            addDenumire(view,wl.getDenumire());
            addMagazinProveninenta(view, wl.getMagazinProvenineta());
            addPret(view, wl.getPret());
        }
        return view;
    }

    private void addDenumire(View view, String denumire) {
        TextView textView = view.findViewById(com.example.testproiect.R.id.tvDenumireProdus);
        addTextViewContent(textView, denumire);
    }

    private void addMagazinProveninenta(View view, String magazin) {
        TextView textView = view.findViewById(com.example.testproiect.R.id.tvDenumireMagazin);
        addTextViewContent(textView, magazin);
    }

    private void addPret(View view, Double pret) {
        TextView textView = view.findViewById(com.example.testproiect.R.id.tvPret);

        if (pret != null) {
            addTextViewContent(textView, pret.toString());
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
