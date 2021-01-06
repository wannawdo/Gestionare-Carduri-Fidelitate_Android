package com.example.testproiect.magazine.partenere;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.testproiect.MainActivity;
import com.example.testproiect.R;
import com.example.testproiect.oferte.Oferta;


import java.util.ArrayList;

public class MagazinePartenereActivity extends AppCompatActivity {

    private ListView lvMagazin;
    private ArrayList<Magazin> magazin=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_magazine);

        magazin.add(new Magazin("IKEA",R.drawable.ikea));
        magazin.add(new Magazin("Carrefour",R.drawable.carrefour));
        magazin.add(new Magazin("LIDL",R.drawable.lidl));


        MagazinAdapter oferteAdapter=new MagazinAdapter(this, magazin);
        lvMagazin=findViewById(R.id.listViewSiteMagazine);
        lvMagazin.setAdapter(oferteAdapter);
        addMagazineAdapter();
    }
    private void addMagazineAdapter(){
        MagazinAdapter adapter=new MagazinAdapter(getApplication(), magazin);
        lvMagazin.setAdapter(adapter);
    }


    public void ActivitateSiteuri(View view) {
        startActivity(new Intent(MagazinePartenereActivity.this, InfoMagazinePartenereActivity.class));
    }
}
