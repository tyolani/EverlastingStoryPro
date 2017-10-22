package com.example.tyolani.everlastingstorypro;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


/**
 * Created by hedholm on 2017-10-22.
 */
public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Thread myThread = new Thread(){
            @Override
            public void run() {
                try {
                    sleep(1500);
                    Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
                    startActivity(intent);
                    finish();
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        };
        myThread.start();
    }
}
