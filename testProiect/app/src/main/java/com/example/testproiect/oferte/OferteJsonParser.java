package com.example.testproiect.oferte;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class OferteJsonParser {
    public static final String DENUMIRE = "denumire";
    public static final String TEXT = "text";
    public static final String VALABILITATE="valabilitate";

    public static List<Oferta> fromJson(String json) {
        try {
            JSONArray array = new JSONArray(json);
            return readOferte(array);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    private static List<Oferta> readOferte(JSONArray array) throws JSONException {
            List<Oferta> oferte = new ArrayList<>();
            for (int i = 0; i < array.length(); i++) {
                Oferta oferta = readOferte(array.getJSONObject(i));
                oferte.add(oferta);
            }
            return oferte;
        }


     private static Oferta readOferte(JSONObject object) throws JSONException{
        String denumire=object.getString(DENUMIRE);
        String text=object.getString(TEXT);
        int valabilitate=object.getInt(VALABILITATE);
        return new Oferta(denumire,text,valabilitate);
     }
}
