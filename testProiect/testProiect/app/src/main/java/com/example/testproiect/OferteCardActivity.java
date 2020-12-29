package com.example.testproiect;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;


public class OferteCardActivity extends AppCompatActivity {

    private ListView lvOferte;
    private ArrayList<Oferta> oferte=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vizualizare_oferte_card);

        oferte.add(new Oferta("Black Friday", "Discount 15% la orice electrocasnice!",R.drawable.ic_baseline_local_offer_24));
        oferte.add(new Oferta("Craciun","30% reducere la cozonac!",R.drawable.ic_baseline_local_offer_24));
        oferte.add(new Oferta("Paste","Oua proaspete la doar 14 lei si 99 de bani.",R.drawable.ic_baseline_local_offer_24));
        oferte.add(new Oferta("1 Decembrie","Reduceri de pana la 70% la articolele cu bulina rosie.",R.drawable.ic_baseline_local_offer_24));
        oferte.add(new Oferta("Ziua sporturilor","50% reducere la toate bicicletele.",R.drawable.ic_baseline_local_offer_24));


        OferteAdapter oferteAdapter=new OferteAdapter(this, oferte);
        lvOferte=findViewById(R.id.listView);
        lvOferte.setAdapter(oferteAdapter);
        addOferteAdapter();
    }

    private void addOferteAdapter(){
        OferteAdapter adapter=new OferteAdapter(getApplication(), oferte);
        lvOferte.setAdapter(adapter);
    }

}
