package com.example.mygame;

public class Background extends myObject implements Runnable{
    public Background(){
        this.width=global.width;
        this.height=global.height*2;
        this.Image=global.background;
        setX(0);
        setY(-global.height);
        new Thread(this).start();
    }
    @Override
    public void run(){
        while(true){
            try{Thread.sleep(10);}catch (InterruptedException e){e.printStackTrace();}
            if(r.top+2<=0){
                setY(r.top+2);
            }
            else{
                setY(-global.height);
            }
        }
    }
}
