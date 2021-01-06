package com.example.testproiect.oferte;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.testproiect.R;

import java.util.List;

public class OferteAdapter extends ArrayAdapter<Oferta> {
    private Context ctx;
    private List<Oferta> oferta;
    private LayoutInflater inflater;
    private int resource;

    public OferteAdapter(@NonNull Context context, int resource,
                         @NonNull List<Oferta> oferta, LayoutInflater inflater) {
        super(context, resource, oferta);
        this.ctx = context;
        this.oferta = oferta;
        this.inflater = inflater;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = inflater.inflate(resource, parent, false);
        Oferta o = oferta.get(position);
        if (o != null) {
            addDenumire(view, o.getDenumire());
            addText(view, o.getText());
            addValabilitate(view, o.getValabilitate());
        }
        return view;
    }
    private void addDenumire(View view, String denumire) {
        TextView textView = view.findViewById(R.id.titluOferta);
        populateTextViewContent(textView,  denumire  );
    }

    private void addText(View view, String text) {
        TextView textView = view.findViewById(R.id.textOferta);
        populateTextViewContent(textView,  text  );
    }

    private void addValabilitate(View view, int valabilitate) {
        TextView textView = view.findViewById(R.id.tvValabilitateOferta);
        populateTextViewContent(textView, String.valueOf(valabilitate));
    }


    private void populateTextViewContent(TextView textView, String value) {
        if (value != null && !value.trim().isEmpty()) {
            textView.setText(value);
        } else {
            textView.setText("");
        }
    }

}
