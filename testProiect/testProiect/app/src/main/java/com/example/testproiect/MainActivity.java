package com.example.testproiect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout llAdaugareCard=(LinearLayout)findViewById(R.id.layoutAdaugare);
        llAdaugareCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,AdaugareCardActivity.class));
            }
        });

        LinearLayout llInfoCard=(LinearLayout)findViewById(R.id.layoutInfo);
        llInfoCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,InfoCardActivity.class));
            }
        });

        LinearLayout llOferteCard=(LinearLayout)findViewById(R.id.layoutOferte);
        llOferteCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,OferteCardActivity.class));
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