package lam.fpoly.myrelaxstar.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;

import lam.fpoly.myrelaxstar.Object.Object_Photo;
import lam.fpoly.myrelaxstar.Object.TinTuc;
import lam.fpoly.myrelaxstar.R;
import lam.fpoly.myrelaxstar.Service.WebView_News;

public class MainActivity2 extends AppCompatActivity {
    public static WebView view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        WebView view = findViewById(R.id.idWeb);
        ActionBar actionBar = getSupportActionBar();

        WebView_News webView_news = new WebView_News();
        Intent intentweb = new Intent(MainActivity2.this, WebView_News.class);
        bindService(intentweb,sv_conn, Context.BIND_AUTO_CREATE);

        Bundle bundle = getIntent().getExtras();
        if (bundle == null){
            return;
        }
        TinTuc tinTuc = (TinTuc) bundle.get("key");
        Object_Photo photo = (Object_Photo) bundle.get("photo");

        WebSettings webSettings = view.getSettings();
        webSettings.setJavaScriptEnabled(true);

        if (photo != null){
            String title1 = photo.getTitle();
            String link1 = photo.getLink();
            webView_news.loadWebNews(link1,view,this);
            actionBar.setTitle(title1);

        }
        if(tinTuc != null){
            String title = tinTuc.getTitle();
            String link = tinTuc.getLink();
            webView_news.loadWebNews(link,view,this);
            actionBar.setTitle(title);
        }
    }


    ServiceConnection sv_conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            WebView_News.LocalBinder2 localBinder2 = (WebView_News.LocalBinder2) iBinder;
        }
        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
                super.onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

}
