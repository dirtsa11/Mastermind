package com.example.astrid.mastermind;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

/**
 * Created by Astrid.
 */

// Introduction screen
public class SplashScreen extends Activity {

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run(){
                // Opening the Mastermind activity
                Intent i = new Intent(SplashScreen.this, MastermindActivity.class);
                startActivity(i);
                finish();

            }
        }, 3000); // Display for 3 seconds
    }
}
