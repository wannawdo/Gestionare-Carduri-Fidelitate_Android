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
    private static String URL_JSON="https://pastebin.com/raw/1dRnz8HR";

    private ListView lvOferte;
    private ArrayList<Oferta> oferte=new ArrayList<>();

    private AsyncTaskRunner asyncTaskRunner=new AsyncTaskRunner();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vizualizare_oferte_card);

        oferte.add(new Oferta("Black Friday", "Discount 15% la orice electrocasnice!",3));
        oferte.add(new Oferta("Craciun","30% reducere la cozonac!",7));
        oferte.add(new Oferta("Paste","Oua proaspete la doar 14 lei si 99 de bani.",30));
        oferte.add(new Oferta("1 Decembrie","Reduceri de pana la 70% la articolele cu bulina rosie.",14));
        oferte.add(new Oferta("Ziua sporturilor","50% reducere la toate bicicletele.",5));


        OferteAdapter oferteAdapter=new OferteAdapter(this, oferte);
        lvOferte=findViewById(R.id.listView);
        lvOferte.setAdapter(oferteAdapter);
        addOferteAdapter();
        getOferteJSON();
    }

    private void getOferteJSON(){
        //definim un obiect de tip Callable pe care dorim sa-l procesam pe un alt fir de executie (RunnableTask)
        //HttpManager implementeaza aceasta interfata.
        Callable<String> asyncOperation = new ManagerHttp(URL_JSON);
        //definim Callback-ul, adica zona din activitatea unde dorim sa receptionam rezultatul procesarii paralele
        //realizata de Callable
        Callback<String> mainThreadOperation = receiveCardFromHttp();
        //Apelam asyncTaskRunner cu operation asincrona si zona de cod din activitate unde dorim sa primim raspunsul
        asyncTaskRunner.executeAsync(asyncOperation, mainThreadOperation);
    }

    private Callback<String> receiveCardFromHttp() {
        return new Callback<String>() {
            @Override
            public void runResultOnUiThread(String result) {
                Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
                //apelam parsatorul de json, iar rezultatul obtinut il adaugam in lista de obiecte BankAccount
                //existenta la nivelul activitati
                oferte.addAll(OferteJsonParser.fromJson(result));
                //avand in vedere ca lista de obiecte este modificata la linia de mai sus,
                // este necesar sa notificam adapterul de acest lucru astfel incat obiectele noi
                //sa fie incarcate in ListView
                addOferteAdapter();
            }
        };
    }

    private void addOferteAdapter(){
        OferteAdapter adapter=new OferteAdapter(getApplication(), oferte);
        lvOferte.setAdapter(adapter);
    }

}
