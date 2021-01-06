package com.example.testproiect.magazine.partenere;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.testproiect.R;
import com.example.testproiect.oferte.Oferta;

import java.util.List;

public class MagazinAdapter extends BaseAdapter {
    private Context context;
    private List<Magazin> magazin;

    public MagazinAdapter(Context context, List<Magazin> magazin) {
        this.context = context;
        this.magazin = magazin;
    }

    @Override
    public int getCount() {
        return this.magazin.size();
    }

    @Override
    public Object getItem(int position) {
        return this.magazin.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

       LayoutInflater inflater = LayoutInflater.from(context);
       View v = inflater.inflate(R.layout.custom_adapter_magazine, parent,false);
       ImageView imgMagazin=v.findViewById(R.id.imageViewAdapterMagazine);
       TextView textNume=v.findViewById(R.id.numeMagazin);

       Magazin magazin=(Magazin)getItem(position);
       imgMagazin.setImageResource(magazin.getImgMagazin());
       textNume.setText(""+magazin.getNumeMagazin());

       return v;

    }
}
