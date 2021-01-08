package com.example.testproiect.coduri.promotionale;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.testproiect.R;
import com.example.testproiect.asyncTask.Callback;


import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class CodPromotionalActivity extends AppCompatActivity {
    private static final int ADD_COD_PROMO_REQUEST_CODE = 201;
    private static final int UPDATE_COD_PROMO_REQUEST_CODE = 202;

    private ListView lvCodPromo;
    private FloatingActionButton fabAddCodPromo;


    private List<CodPromotional> codPromo = new ArrayList<>();
    private CodPromotionalOperations cpOperations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coduri_promotionale);
//        codPromo.add(new CodPromotional("test","lidl", (double) 12));
//        codPromo.add(new CodPromotional("testtt","lidl", (double) 50));
        initComponents();
        cpOperations = new CodPromotionalOperations(getApplicationContext());
        cpOperations.getAll(getAllFromDbCallback());

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null) {
            CodPromotional expense = (CodPromotional) data.getSerializableExtra(AddCodPromotionalActivity.KEY_CODE);
            if (requestCode == ADD_COD_PROMO_REQUEST_CODE) {
                cpOperations.insert(insertIntoDbCallback(), expense);
            } else if (requestCode == UPDATE_COD_PROMO_REQUEST_CODE) {
                cpOperations.update(updateToDbCallback(), expense);
            }
        }
    }



    private Callback<List<CodPromotional>> getAllFromDbCallback() {
        return new Callback<List<CodPromotional>>() {
            @Override
            public void runResultOnUiThread(List<CodPromotional> result) {
                if (result != null) {
                    codPromo.clear();
                    codPromo.addAll(result);
                    notifyAdapter();
                }
            }
        };
    }

    private Callback<CodPromotional> insertIntoDbCallback() {
        return new Callback<CodPromotional>() {
            @Override
            public void runResultOnUiThread(CodPromotional result) {
                if (result != null) {
                    codPromo.add(result);
                    notifyAdapter();
                }
            }
        };
    }

    private Callback<CodPromotional> updateToDbCallback() {
        return new Callback<CodPromotional>() {
            @Override
            public void runResultOnUiThread(CodPromotional result) {
                if (result != null) {
                    for (CodPromotional cPromo : codPromo) {
                        if (cPromo.getId() == result.getId()) {
                            cPromo.setCodPromotional(result.getCodPromotional());
                            cPromo.setMagazin(result.getMagazin());
                            cPromo.setReducere(result.getReducere());

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
                    codPromo.remove(position);
                    notifyAdapter();
                }
            }
        };
    }

    private void initComponents() {
        lvCodPromo = findViewById(R.id.lv_coduri_promotionale);
        fabAddCodPromo = findViewById(R.id.fab_add_cod_promotional);


        addAdapter();
        fabAddCodPromo.setOnClickListener(addExpenseEventListener());
        lvCodPromo.setOnItemClickListener(updateEventListener());
        lvCodPromo.setOnItemLongClickListener(deleteEventListener());
    }

    private AdapterView.OnItemClickListener updateEventListener() {
        return new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), AddCodPromotionalActivity.class);
                intent.putExtra(AddCodPromotionalActivity.KEY_CODE, codPromo.get(position));
                startActivityForResult(intent, UPDATE_COD_PROMO_REQUEST_CODE);
            }
        };
    }

    private AdapterView.OnItemLongClickListener deleteEventListener() {
        return new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                cpOperations.delete(deleteToDbCallback(position), codPromo.get(position));
                return true;
            }
        };
    }

    private View.OnClickListener addExpenseEventListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddCodPromotionalActivity.class);
                startActivityForResult(intent, ADD_COD_PROMO_REQUEST_CODE);
            }
        };
    }

    private void addAdapter() {
        CoduriPromotionaleAdapter adapter = new CoduriPromotionaleAdapter(getApplicationContext(), R.layout.custom_adapter_coduri_promotionale,
                codPromo, getLayoutInflater());
        lvCodPromo.setAdapter(adapter);
    }

    private void notifyAdapter() {
        CoduriPromotionaleAdapter adapter = (CoduriPromotionaleAdapter) lvCodPromo.getAdapter();
        adapter.notifyDataSetChanged();
    }


}
