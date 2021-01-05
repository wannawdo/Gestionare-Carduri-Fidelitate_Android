package com.example.testproiect.wishlist;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.testproiect.R;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Date;

public class AddWishlistActivity extends AppCompatActivity {
    public static final String WISHLIST_KEY = "expenseKey";
    private TextInputEditText tietNumeProdus;
    private TextInputEditText tietNumeMagazin;
    private TextInputEditText tietPret;

    private ImageButton butonAdauga;

    private Intent intent;
    private Wishlist wishlist = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adaugare_lista_dorinte);

        initComponents();
        intent = getIntent();
        if (intent.hasExtra(WISHLIST_KEY)) {
            wishlist = (Wishlist) intent.getSerializableExtra(WISHLIST_KEY);
            buildViewsFromWishlist(wishlist);
        }
    }

    private void buildViewsFromWishlist(Wishlist wishlist) {
        if (wishlist == null) {
            return;
        }
        if (wishlist.getDenumire() != null) {
            tietNumeProdus.setText(wishlist.getDenumire());
        }

        if (wishlist.getMagazinProvenineta() != null) {
            tietNumeMagazin.setText(wishlist.getMagazinProvenineta());
        }
        if(wishlist.getPret() != null) {
            tietPret.setText(String.valueOf(wishlist.getPret()));
        }
    }



    private void initComponents() {
        tietNumeProdus = findViewById(R.id.tiet_add_denumire_produs);
        tietNumeMagazin = findViewById(R.id.tiet_add_denumire_magazin);
        tietPret = findViewById(R.id.tiet_add_pret_produs);
        butonAdauga = findViewById(R.id.ibtn_add_produs_dorit);
        butonAdauga.setOnClickListener(saveWishlistEventListener());
    }

    private View.OnClickListener saveWishlistEventListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()) {
                    createFromViews();
                    intent.putExtra(WISHLIST_KEY, wishlist);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        };
    }

    private void createFromViews() {
        String numep = tietNumeProdus.getText().toString();
        String numem = tietNumeMagazin.getText().toString();
        Double pret = Double.parseDouble(tietPret.getText().toString());

        if (wishlist == null) {
            wishlist = new Wishlist(numep, numem, pret);
        } else {
            wishlist.setDenumire(numep);
            wishlist.setMagazinProvenineta(numem);
            wishlist.setPret(pret);
        }
    }


    private boolean validate() {
        if (tietNumeProdus.getText() == null || tietNumeProdus.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(),
                    R.string.adauga_produs_eroare,
                    Toast.LENGTH_SHORT)
                    .show();
            return false;
        }
        if (tietPret.getText() == null || tietPret.getText().toString().isEmpty()
                || Double.parseDouble(tietPret.getText().toString()) < 0) {
            Toast.makeText(getApplicationContext(),
                    R.string.adauga_pret_produs_eroare,
                    Toast.LENGTH_SHORT)
                    .show();
            return false;
        }
        return true;
    }
}
