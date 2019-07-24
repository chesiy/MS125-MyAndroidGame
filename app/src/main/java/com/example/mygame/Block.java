package com.example.mygame;

public class Block extends myObject implements Runnable{
    private long sd0=(long)(Math.random()*10)+10;//[10,20),白块速度不同

    public boolean have_gone;
    public Block(){
        have_gone=false;
        Image=global.block;
        width=Image.getWidth();
        height=Image.getHeight();
        setX((float)(Math.random()*(global.width-width)));//x随机
        setY(-height);//屏幕外，刚好看不到
        global.blocks.add(this);
        new Thread(this).start();
    }
    public boolean bang(float x,float y){
        if(r.left<=x&&r.right>=x&&r.top<=y&&r.bottom>=y)return true;
        return false;
    }
    @Override
    public void run(){
        while(global.life>0){
            try{Thread.sleep(sd0);}catch (InterruptedException e){e.printStackTrace();}
            setY(r.top+2);
            if(r.top>=global.height)//block飞出屏幕 跳出循环
                break;
        }
        if(!have_gone){
            global.blocks.remove(this);
            global.life--;
        }
    }

}
