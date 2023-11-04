package com.example.appnews;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class NewsActivity extends AppCompatActivity {
    WebView webTinTuc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        webTinTuc = (WebView) findViewById(R.id.webTinTuc);

        Intent intent = getIntent();

        String link = intent.getStringExtra("linkTinTuc");

        webTinTuc.loadUrl(link);

        webTinTuc.setWebViewClient(new WebViewClient());
    }
}