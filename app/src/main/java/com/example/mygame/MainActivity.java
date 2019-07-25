package com.example.mygame;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public MediaPlayer mp=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mp=MediaPlayer.create(this,R.raw.music);
        mp.start();
    /*    Runnable runnable=new Runnable() {
            @Override
            public void run() {
                while(true){
                    try{
                        Thread.sleep(10);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                    mp.start();
                    try{
                        Thread.sleep(10);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        };*/
 //       runnable.run();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mp.stop();
        exit();
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


