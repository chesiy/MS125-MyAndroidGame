package com.example.mygame;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setContentView(new myView(this));
    }

    public void exit(){
        MainActivity.this.finish();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{Thread.sleep(500);}catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.exit(0);
            }
        }).start();
    }

}


