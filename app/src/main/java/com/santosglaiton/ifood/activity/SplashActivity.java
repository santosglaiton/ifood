package com.santosglaiton.ifood.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.santosglaiton.ifood.R;

public class  SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                openAtuthenticator();
            }
        }, 3000);

    }
    private void openAtuthenticator(){
        Intent i = new Intent(SplashActivity.this, AuthenticationActivity.class);
        startActivity(i);
        finish();
    }
}
