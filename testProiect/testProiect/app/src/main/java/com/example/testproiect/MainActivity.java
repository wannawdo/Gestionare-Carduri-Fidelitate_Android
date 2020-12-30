package com.example.testproiect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.testproiect.card.AdaugareCardActivity;
import com.example.testproiect.card.InfoCardActivity;
import com.example.testproiect.magazine.partenere.InfoMagazinePartenereActivity;
import com.example.testproiect.magazine.partenere.MagazinePartenereActivity;
import com.example.testproiect.oferte.OferteCardActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout llMagazinePartenere=(LinearLayout)findViewById(R.id.layoutMagazine);
        llMagazinePartenere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MagazinePartenereActivity.class));
            }
        });

        LinearLayout llWishlist=(LinearLayout)findViewById(R.id.layoutWishlist);
        llWishlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, InfoMagazinePartenereActivity.class));
            }
        });

        LinearLayout llInfoCard=(LinearLayout)findViewById(R.id.layoutInfo);
        llInfoCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, InfoCardActivity.class));
            }
        });

        LinearLayout llOferteCard=(LinearLayout)findViewById(R.id.layoutOferte);
        llOferteCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, OferteCardActivity.class));
            }
        });

        LinearLayout llProfil=(LinearLayout)findViewById(R.id.layoutProfil);
        llProfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,ProfilActivity.class));
            }
        });
    }


}