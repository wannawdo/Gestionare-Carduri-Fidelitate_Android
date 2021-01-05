package com.example.testproiect.magazine.partenere;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

import com.example.testproiect.R;

public class InfoMagazinePartenereActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_magazine);
    }
    public void accesareSiteIKEA(View view) {
        WebView wb=findViewById(R.id.webView);
        wb.loadUrl("https://www.ikea.com/");
        wb.setWebViewClient(new WebViewClient());
    }

    public void accesareSiteCarrefour(View view) {
        WebView wb=findViewById(R.id.webView);
        wb.loadUrl("https://carrefour.ro/");
        wb.setWebViewClient(new WebViewClient());
    }

    public void accesareSiteLIDL(View view) {
        WebView wb=findViewById(R.id.webView);
        wb.loadUrl("https://www.lidl.ro/");
        wb.setWebViewClient(new WebViewClient());
    }
}
