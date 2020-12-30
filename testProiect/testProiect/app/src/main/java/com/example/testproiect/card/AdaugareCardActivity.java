package com.example.testproiect.card;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.testproiect.R;

import java.util.Date;

public class AdaugareCardActivity extends AppCompatActivity {
    public static final String CARD_KEY = "card_key";
    private final DateConverter dateConverter = new DateConverter();

    private EditText nume;
    private EditText IDCard;
    private EditText dataEliberare;
    private Spinner numeMagazin;
    private RadioGroup sexType;
    private Button butonAdaugaCard;

    private Intent intentAdaugaCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adaugare_card_nou);

        initializareComponente();
        populareSpinnerMagazine();
        butonAdaugaCard.setOnClickListener(adaugareCardEvent());
        intentAdaugaCard=getIntent();

    }

    private void initializareComponente(){
        nume=findViewById(R.id.etNume);
        sexType=findViewById(R.id.rbSexType);
        IDCard=findViewById(R.id.etIDCard);
        numeMagazin=findViewById(R.id.spinnerNumeMagazin);
        dataEliberare=findViewById(R.id.etDataEliberare);

        butonAdaugaCard=findViewById(R.id.buttonAdaugareCard);
    }

    private void populareSpinnerMagazine(){
        ArrayAdapter<CharSequence> adapterPopSpinnerMagazin=ArrayAdapter.createFromResource(getApplicationContext(), R.array.SpinnerNumemagazin, android.R.layout.simple_dropdown_item_1line);
        numeMagazin.setAdapter(adapterPopSpinnerMagazin);
    }

    private View.OnClickListener adaugareCardEvent(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validare()){
                    Card card=buildCard();
                    intentAdaugaCard.putExtra(CARD_KEY,card);
                    setResult(RESULT_OK,intentAdaugaCard);
                    Toast.makeText(getApplicationContext(), card.toString(), Toast.LENGTH_LONG).show();
                    finish();

                }
            }
        };
    }

    private boolean validare(){
        //validare pentru nume
        if(nume.getText().length()<3) {
            Toast.makeText(getApplicationContext(), R.string.validare_nume, Toast.LENGTH_SHORT).show();
            return false;
        }
        //validare pentru nr card
//        if(Integer.parseInt(IDCard.getText().toString().trim())>0) {
//            Toast.makeText(getApplicationContext(), R.string.validare_nrCard, Toast.LENGTH_SHORT).show();
//
//            return false;
//        }
        if(dataEliberare.getText()==null || dateConverter.fromString(dataEliberare.getText().toString().trim()) == null) {
            Toast.makeText(getApplicationContext(),
                    R.string.format_data,
                    Toast.LENGTH_LONG)
                    .show();
            return false;
        }

        return true;
    }

    private Card buildCard(){
        String numeDetinator=nume.getText().toString();
        SexType sexTip=SexType.FEMININ;
        if (sexType.getCheckedRadioButtonId() == R.id.rbFeminin) {
            sexTip = SexType.FEMININ;
        }
        int idCard=Integer.parseInt(IDCard.getText().toString().trim());
        String numeMagazine=numeMagazin.getSelectedItem().toString();
        Date dataEliberareCard=dateConverter.fromString(dataEliberare.getText().toString().trim());

    return new Card(numeDetinator,sexTip,idCard,numeMagazine,dataEliberareCard);

    }
}
