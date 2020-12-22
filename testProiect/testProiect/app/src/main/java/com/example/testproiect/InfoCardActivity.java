package com.example.testproiect;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import java.util.ArrayList;
import java.util.List;

public class InfoCardActivity extends AppCompatActivity {
    private static CardDataBase  cardDatabase;
    public CardDAO cardDAO;
    EditText excursieDupaLocatie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vizualizare_info_card);

        cardDatabase= Room.databaseBuilder(this,CardDataBase.class,"carduriDB").allowMainThreadQueries().build();

        cardDAO =cardDatabase.getCardDAO();
    }
    public void adaugaCard(View view){
        Card card1=new Card("Peek","4224 3342 8475 322","10/12/202");
        cardDatabase.getCardDAO().inserareCardInDB(card1);

    }

    public void getAllCards(View view){

        List<Card> lista=new ArrayList<>();
        lista= cardDAO.selectAllCards();
        for(Card c:lista){
            Toast.makeText(getApplicationContext(),c.toString(),Toast.LENGTH_SHORT).show();
        }

    }
    public void getCardDupaNume(View view){
        excursieDupaLocatie=findViewById(R.id.editTextNume);
        String nume=excursieDupaLocatie.getText().toString();
        Card c= cardDAO.getCardDupaNume(nume);
        Toast.makeText(getApplicationContext(),c.toString(),Toast.LENGTH_SHORT).show();

        excursieDupaLocatie.setText(c.getNume());

    }

}
