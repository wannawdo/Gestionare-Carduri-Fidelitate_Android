package com.example.testproiect.oferte;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.testproiect.R;

import java.util.List;

public class OferteAdapter extends BaseAdapter {
    private Context ctx;
    private List<Oferta> oferta;

    public OferteAdapter(Context ctx, List<Oferta> oferta) {
        this.ctx = ctx;
        this.oferta = oferta;
    }

    @Override
    public int getCount() {
        return this.oferta.size();
    }

    @Override
    public Object getItem(int position) {
        return this.oferta.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(ctx);
        View v = inflater.inflate(R.layout.custom_adapter_oferte, parent,false);
        ImageView img=v.findViewById(R.id.imageViewAdapterOferte);
        TextView textTitlu=v.findViewById(R.id.titluOferta);
        TextView textOf=v.findViewById(R.id.textOferta);
        Oferta oferta=(Oferta)getItem(position);
        img.setImageResource(oferta.getImage());
        textTitlu.setText(""+oferta.getDenumire());
        textOf.setText(""+oferta.getText());
        return v;

    }
}
