package com.appdet.quizhub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class Splash_Screen extends AppCompatActivity {

    Intent intentSplash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        intentSplash = new Intent(this, Login.class); //eto rin need for splash
        splashScreen();
    }

    public void splashScreen(){
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Do something after 5s = 5000ms
                startActivity(intentSplash); //bubuksan yung splashscreen
                finish();         // eto yung destroy yung splashscreen para d makabalik

                // meron pa sa manifest yung ililipat ang intent sa splashscreen
            }
        }, 5000);
    }
}