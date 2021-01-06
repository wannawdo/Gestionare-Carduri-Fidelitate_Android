package com.example.testproiect.oferte;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.testproiect.HTTP.ManagerHttp;
import com.example.testproiect.R;
import com.example.testproiect.asyncTask.AsyncTaskRunner;
import com.example.testproiect.asyncTask.Callback;

import java.nio.channels.AsynchronousByteChannel;
import java.util.ArrayList;
import java.util.concurrent.Callable;


public class OferteCardActivity extends AppCompatActivity {
    private static String URL_JSON="https://jsonkeeper.com/b/9O6D";

    private ListView lvOferte;
    private ArrayList<Oferta> oferte=new ArrayList<>();

    private AsyncTaskRunner asyncTaskRunner=new AsyncTaskRunner();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vizualizare_oferte_card);
        lvOferte=findViewById(R.id.listView);
//        oferte.add(new Oferta("Black Friday", "Discount 15% la orice electrocasnice!",3));
//        oferte.add(new Oferta("Craciun","30% reducere la cozonac!",7));
//        oferte.add(new Oferta("Paste","Oua proaspete la doar 14 lei si 99 de bani.",30));
//        oferte.add(new Oferta("1 Decembrie","Reduceri de pana la 70% la articolele cu bulina rosie.",14));
//        oferte.add(new Oferta("Ziua sporturilor","50% reducere la toate bicicletele.",5));


        addOferteAdapter();
        getOferteJSON();
    }

    private void getOferteJSON(){
        Callable<String> asyncOperation = new ManagerHttp(URL_JSON);
        Callback<String> mainThreadOperation = receiveCardFromHttp();

        asyncTaskRunner.executeAsync(asyncOperation, mainThreadOperation);
    }

    private Callback<String> receiveCardFromHttp() {
        return new Callback<String>() {
            @Override
            public void runResultOnUiThread(String result) {
                Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
                oferte.addAll(OferteJsonParser.fromJson(result));

                notifyAdapter();
            }
        };
    }

    private void addOferteAdapter(){
        OferteAdapter adapter=new OferteAdapter(getApplicationContext(),R.layout.custom_adapter_oferte, oferte, getLayoutInflater());
        lvOferte.setAdapter(adapter);
    }
    private void notifyAdapter(){
        ArrayAdapter adapter=(ArrayAdapter)lvOferte.getAdapter();
        adapter.notifyDataSetChanged();
    }

}
