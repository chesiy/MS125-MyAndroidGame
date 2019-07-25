package com.example.mygame;

import android.provider.ContactsContract;

public class Boom extends myObject implements Runnable{

    public Boom(float x,float y){
        Image=global.ex.get(0);
        height=Image.getHeight();
        width= Image.getWidth();
        setX(x);
        setY(y);
    }

    @Override
    public void run() {
  //      System.err.println("boom");
        int i=0;
        while(global.life>0&&i<6){
            try{
                Thread.sleep(10);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            setY(r.top+3);
            System.err.println(i);
            Image=global.ex.get(i);
            i++;
            if(r.top>=global.height)//block飞出屏幕 跳出循环
                break;
        }
        global.booms.remove(this);
    }
}
