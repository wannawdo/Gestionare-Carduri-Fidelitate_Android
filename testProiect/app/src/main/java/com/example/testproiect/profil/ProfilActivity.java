package com.example.testproiect.profil;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import com.example.testproiect.R;
import com.example.testproiect.logare.LogInActivity;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;

public class ProfilActivity extends AppCompatActivity {
    public static final String PROFIL_SHARED_PREF = "ProfilSharedPref";
    public static final String NUME = "nume";
    public static final String PRENUME = "prenume";
    public static final String ID_SEX_TYPE = "id_sex_type";

    private EditText etNume, etPrenume;
    private RadioGroup rgSextype;
    private Button btnSalvare;
    private Button btnInfoProfil;

    //obiect utilizat pentru a reprezenta un fisier de preferinte incarcat in memorie
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        initializareComponente();
    }

    private void initializareComponente(){
        etNume = findViewById(R.id.etNume);
        etPrenume=findViewById(R.id.etPrenume);
        rgSextype=findViewById(R.id.rgSexType);
        btnSalvare=findViewById(R.id.buttonSalvare);
        btnInfoProfil=findViewById(R.id.buttonVizualizareDateProfil);

        preferences = getSharedPreferences(PROFIL_SHARED_PREF, MODE_PRIVATE);
        btnSalvare.setOnClickListener(saveProfileDetailsEventListener());
        getProfileDetailsFromSharedPreference();
        btnInfoProfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(ProfilActivity.this, LogInActivity.class));
            }
        });
    }

    private void getProfileDetailsFromSharedPreference() {
        //citire informatii din fisierul de preferinte in functie de cheie.
        //cheia este aceeasi ca la operatia put
        String nume = preferences.getString(NUME, "");
        String prenume = preferences.getString(PRENUME, "");
        int idSexType = preferences.getInt(ID_SEX_TYPE, R.id.rbFeminin);
        //incarcare informatii in componente vizuale
        etNume.setText(nume);
        etPrenume.setText(prenume);
        rgSextype.check(idSexType);
    }

    private View.OnClickListener saveProfileDetailsEventListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveProfileDetailsToSharedPreference();
                finish();
            }
        };
    }

    private void saveProfileDetailsToSharedPreference() {
        //preluare informatii din componentele vizuale
        String nume = etNume.getText() != null ? etNume.getText().toString() : "";
        String prenume = etPrenume.getText() != null ? etPrenume.getText().toString() : "";
        int idSexType = rgSextype.getCheckedRadioButtonId();
        //definire editor pentru a putea scrie in fisierul de preferinte
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(NUME, nume);
        editor.putString(PRENUME, prenume);
        editor.putInt(ID_SEX_TYPE, idSexType);
        editor.apply(); // lipsa acestui apel duce la pirderea modificarilor efectuate prin metodele put
    }
}
