package com.example.mygame;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

public class PlayMusic extends Service {

    private MediaPlayer mediaPlayer;

    @Override
    public IBinder onBind(Intent intent){
        return null;
    }

    @Override
    public void onStart(Intent intent,int startId){
        if(mediaPlayer==null){
     //       mediaPlayer=MediaPlayer.create(this,R.raw.BBC);
            mediaPlayer.setLooping(true);
            mediaPlayer.start();
        }
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        mediaPlayer.stop();
    }
}

