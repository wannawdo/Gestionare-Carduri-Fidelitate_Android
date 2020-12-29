package com.example.testproiect;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class AdaugareCardActivity extends AppCompatActivity {
    private static final String TAG = "AdaugareCardNou";

    //pentru buton adauga
    private EditText etNume, etIDCard;
    //final buton adauga



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adaugare_card_nou);
        //pentru buton adauga
        etNume = findViewById(R.id.etNume);
        etIDCard = findViewById(R.id.etIDCard);


    }
}
