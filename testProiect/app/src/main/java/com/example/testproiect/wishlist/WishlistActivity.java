package com.example.testproiect.wishlist;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.testproiect.R;
import com.example.testproiect.asyncTask.Callback;
import com.example.testproiect.grafic.GraficActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class WishlistActivity extends AppCompatActivity {
    private static final int ADD_WISHLIST_REQUEST_CODE = 201;
    private static final int UPDATE_WISHLIST_REQUEST_CODE = 202;

    private ListView lvWishlist;
    private FloatingActionButton fabAddWishlist;
    private Button btnGrafic;

    private List<Wishlist> wishlist = new ArrayList<>();
    private WishlistOperations wishlistOperations;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_dorinte);
        initComponents();
        wishlistOperations = new WishlistOperations(getApplicationContext());
        wishlistOperations.getAll(getAllFromDbCallback());
        btnGrafic.setOnClickListener(veziGrafic());
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null) {
            Wishlist expense = (Wishlist) data.getSerializableExtra(AddWishlistActivity.WISHLIST_KEY);
            if (requestCode == ADD_WISHLIST_REQUEST_CODE) {
                wishlistOperations.insert(insertIntoDbCallback(), expense);
            } else if (requestCode == UPDATE_WISHLIST_REQUEST_CODE) {
                wishlistOperations.update(updateToDbCallback(), expense);
            }
        }
    }



    private Callback<List<Wishlist>> getAllFromDbCallback() {
        return new Callback<List<Wishlist>>() {
            @Override
            public void runResultOnUiThread(List<Wishlist> result) {
                if (result != null) {
                    wishlist.clear();
                    wishlist.addAll(result);
                    notifyAdapter();
                }
            }
        };
    }

    private Callback<Wishlist> insertIntoDbCallback() {
        return new Callback<Wishlist>() {
            @Override
            public void runResultOnUiThread(Wishlist result) {
                if (result != null) {
                    wishlist.add(result);
                    notifyAdapter();
                }
            }
        };
    }

    private Callback<Wishlist> updateToDbCallback() {
        return new Callback<Wishlist>() {
            @Override
            public void runResultOnUiThread(Wishlist result) {
                if (result != null) {
                    for (Wishlist wishlist : wishlist) {
                        if (wishlist.getId() == result.getId()) {
                            wishlist.setDenumire(result.getDenumire());
                            wishlist.setMagazinProvenineta(result.getMagazinProvenineta());
                            wishlist.setPret(result.getPret());
                            break;
                        }
                    }
                    notifyAdapter();
                }
            }
        };
    }

    private Callback<Integer> deleteToDbCallback(final int position) {
        return new Callback<Integer>() {
            @Override
            public void runResultOnUiThread(Integer result) {
                if (result != -1) {
                    wishlist.remove(position);
                    notifyAdapter();
                }
            }
        };
    }

    private void initComponents() {
        lvWishlist = findViewById(R.id.lv_wishlist);
        fabAddWishlist = findViewById(R.id.fab_add_wishlist);
        btnGrafic=findViewById(R.id.buttonGrafic);

        addAdapter();
        fabAddWishlist.setOnClickListener(addExpenseEventListener());
        lvWishlist.setOnItemClickListener(updateEventListener());
        lvWishlist.setOnItemLongClickListener(deleteEventListener());
    }

    private AdapterView.OnItemClickListener updateEventListener() {
        return new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), AddWishlistActivity.class);
                intent.putExtra(AddWishlistActivity.WISHLIST_KEY, wishlist.get(position));
                startActivityForResult(intent, UPDATE_WISHLIST_REQUEST_CODE);
            }
        };
    }

    private AdapterView.OnItemLongClickListener deleteEventListener() {
        return new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                wishlistOperations.delete(deleteToDbCallback(position), wishlist.get(position));
                return true;
            }
        };
    }

    private View.OnClickListener addExpenseEventListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddWishlistActivity.class);
                startActivityForResult(intent, ADD_WISHLIST_REQUEST_CODE);
            }
        };
    }

    private void addAdapter() {
        WishlistAdapter adapter = new WishlistAdapter(getApplicationContext(), R.layout.listview_wishlist_items,
                wishlist, getLayoutInflater());
        lvWishlist.setAdapter(adapter);
    }

    private void notifyAdapter() {
        WishlistAdapter adapter = (WishlistAdapter) lvWishlist.getAdapter();
        adapter.notifyDataSetChanged();
    }

    private View.OnClickListener veziGrafic(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Wishlist> list = new ArrayList<>();
                for (Wishlist w : wishlist)
                    list.add(w);
                Intent intent = new Intent(WishlistActivity.this, GraficActivity.class);
                intent.putExtra("list", list);
                startActivity(intent);
            }
        };
    }
}
