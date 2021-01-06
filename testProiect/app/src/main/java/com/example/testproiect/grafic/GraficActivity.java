package com.example.testproiect.grafic;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.testproiect.R;
import com.example.testproiect.wishlist.Wishlist;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GraficActivity extends AppCompatActivity {

    ArrayList<Wishlist> list;
    LinearLayout layout;
    Map<String, Integer> source;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grafic);

        Intent intent = getIntent();

        list = (ArrayList<Wishlist>) intent.getSerializableExtra("list");

        source = getSource(list);

        layout = findViewById(R.id.layoutBar);
        layout.addView(new GraficView(getApplicationContext(), source));

    }

    private Map<String, Integer> getSource(List<Wishlist> wishlists)
    {
        if(wishlists==null || wishlists.isEmpty())
            return new HashMap<>();
        else
        {
            Map<String, Integer> results = new HashMap<>();
            for(Wishlist wishlist: wishlists)
                if(results.containsKey(wishlist.getMagazinProvenineta()))
                    results.put(wishlist.getMagazinProvenineta(), results.get(wishlist.getMagazinProvenineta())+1);
                else
                    results.put(wishlist.getMagazinProvenineta(), 1);
            return results;
        }
    }
}
