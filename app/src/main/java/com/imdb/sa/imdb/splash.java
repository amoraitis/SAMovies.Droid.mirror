package com.imdb.sa.imdb;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.imdb.sa.helpers.SaveSharedPreference;

public class splash extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {

                       /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                if(SaveSharedPreference.getUserName(getApplicationContext()).isEmpty()){
                    Intent i = new Intent(splash.this, Login.class);
                    startActivity(i);
                }
                else{
                    Intent i = new Intent(splash.this, Home.class);
                    startActivity(i);
                }




                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}
