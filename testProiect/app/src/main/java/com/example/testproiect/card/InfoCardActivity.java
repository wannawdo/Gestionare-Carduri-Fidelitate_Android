package com.example.testproiect.card;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.testproiect.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class InfoCardActivity extends AppCompatActivity {
    public static final int ADD_CARD_REQUEST_CODE=210;

    private ListView lvCarduri;
    private List<Card> carduri=new ArrayList<>();
    private FloatingActionButton fabAddCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vizualizare_info_card);
        initializareComponente();
        adaugaLVCard();
        fabAddCard.setOnClickListener(addCardEvent());

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==ADD_CARD_REQUEST_CODE && resultCode==RESULT_OK && data!=null){
            Card card=(Card)data.getSerializableExtra(AdaugareCardActivity.CARD_KEY);
            if(card!=null){
                Toast.makeText(getApplicationContext(), card.toString(), Toast.LENGTH_LONG).show();
                carduri.add(card);
                ArrayAdapter adaptor=(ArrayAdapter)lvCarduri.getAdapter();
                adaptor.notifyDataSetChanged();
            }
        }
    }

    private void initializareComponente(){
        fabAddCard=findViewById(R.id.floatingActionButton);
        lvCarduri=findViewById(R.id.lvInfoCard);
    }

    private View.OnClickListener addCardEvent(){
        return new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intentAddCard=new Intent(getApplicationContext(),AdaugareCardActivity.class);
                startActivityForResult(intentAddCard, ADD_CARD_REQUEST_CODE);
            }
        };
    }

    private void adaugaLVCard(){
        ArrayAdapter<Card> adapterCarduri=new ArrayAdapter<>(getApplication(), android.R.layout.simple_list_item_1, carduri);
        lvCarduri.setAdapter(adapterCarduri);
    }
}
