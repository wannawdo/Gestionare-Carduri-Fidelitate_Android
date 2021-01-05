package com.example.testproiect;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.testproiect.card.InfoCardActivity;
import com.example.testproiect.profil.ProfilActivity;
import com.example.testproiect.magazine.partenere.InfoMagazinePartenereActivity;
import com.example.testproiect.magazine.partenere.MagazinePartenereActivity;
import com.example.testproiect.oferte.OferteCardActivity;
import com.example.testproiect.wishlist.WishlistActivity;

import static com.example.testproiect.R.string.buna;

public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_CODE_PROFIL = 222;
    private TextView tvHello;


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_PROFIL) {
            afisareMesaj();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvHello=findViewById(R.id.textHello);


        ImageView img=findViewById(R.id.imageMenu);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AboutActivity.class));
            }
        });

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
                startActivity(new Intent(MainActivity.this, WishlistActivity.class));
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
                Intent intent=new Intent(MainActivity.this, ProfilActivity.class);
                startActivityForResult(intent,REQUEST_CODE_PROFIL);
            }
        });

        ConstraintLayout llInfoMagazine=(ConstraintLayout) findViewById(R.id.layoutInfoParteneri);
        llInfoMagazine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,InfoMagazinePartenereActivity.class));
            }
        });

        TextView tvSiteMagazine=findViewById(R.id.tvSiteMagazinePartenere);
        tvSiteMagazine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,MagazinePartenereActivity.class));
            }
        });
    }

    private void afisareMesaj(){
        SharedPreferences preferences=getSharedPreferences(ProfilActivity.PROFIL_SHARED_PREF,MODE_PRIVATE);

        String nume=preferences.getString(ProfilActivity.NUME,"");
        String prenume=preferences.getString(ProfilActivity.PRENUME,"");
        if (nume != null && !nume.isEmpty()&&prenume != null && !prenume.isEmpty()) {
            tvHello.setText(getString(buna,nume,prenume));
        }
    }


}