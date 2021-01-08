package com.example.testproiect.coduri.promotionale;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.testproiect.R;

import com.google.android.material.textfield.TextInputEditText;

public class AddCodPromotionalActivity extends AppCompatActivity {
    public static final String KEY_CODE = "promoKey";
    private TextInputEditText tietCodPromo;
    private TextInputEditText tietMagazin;
    private TextInputEditText tietReducere;

    private ImageButton butonAdauga;

    private Intent intent;
    private CodPromotional codPromo = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adaugare_coduri_promotionale);

        initComponents();
        intent = getIntent();
        if (intent.hasExtra(KEY_CODE)) {
            codPromo = (CodPromotional) intent.getSerializableExtra(KEY_CODE);
            buildViewsFromWishlist(codPromo);
        }
    }

    private void buildViewsFromWishlist(CodPromotional cp) {
        if (cp == null) {
            return;
        }
        if (cp.getCodPromotional() != null) {
            tietCodPromo.setText(cp.getCodPromotional());
        }

        if (cp.getMagazin() != null) {
            tietMagazin.setText(cp.getMagazin());
        }
        if(cp.getReducere() != null) {
            tietReducere.setText(String.valueOf(cp.getReducere()));
        }
    }



    private void initComponents() {
        tietCodPromo = findViewById(R.id.tiet_add_cod_promotional);
        tietMagazin = findViewById(R.id.tiet_add_magazin);
        tietReducere = findViewById(R.id.tiet_add_reducere);
        butonAdauga = findViewById(R.id.ibtn_add_cod_promo);
        butonAdauga.setOnClickListener(saveWishlistEventListener());
    }

    private View.OnClickListener saveWishlistEventListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()) {
                    createFromViews();
                    intent.putExtra(KEY_CODE, codPromo);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        };
    }

    private void createFromViews() {
        String cod = tietCodPromo.getText().toString();
        String magazin = tietMagazin.getText().toString();
        Double red = Double.parseDouble(tietReducere.getText().toString());

        if (codPromo == null) {
            codPromo = new CodPromotional(cod, magazin, red);
        } else {
            codPromo.setCodPromotional(cod);
            codPromo.setMagazin(magazin);
            codPromo.setReducere(red);
        }
    }


    private boolean validate() {
        if (tietCodPromo.getText() == null || tietCodPromo.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(),
                    R.string.err_cod_promo,
                    Toast.LENGTH_SHORT)
                    .show();
            return false;
        }
        if (tietReducere.getText() == null || tietReducere.getText().toString().isEmpty()
                || Double.parseDouble(tietReducere.getText().toString()) < 0) {
            Toast.makeText(getApplicationContext(),
                    R.string.err_reducere,
                    Toast.LENGTH_SHORT)
                    .show();
            return false;
        }
        return true;
    }
}
