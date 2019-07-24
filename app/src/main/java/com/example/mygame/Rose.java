package com.example.mygame;

public class Rose extends myObject implements Runnable{
    private long sd0=(long)(Math.random()*10)+5;//[5,15),白块速度不同
    public Rose(){
        this.Image=global.rose;
        width=Image.getWidth();
        height=Image.getHeight();
        setX((float)(Math.random()*(global.width-width)));//x随机
        setY(-height);//屏幕外，刚好看不到
        global.roses.add(this);
        new Thread(this).start();
    }

    public boolean bang(float x,float y){
        if(r.left<=x&&r.right>=x&&r.top<=y&&r.bottom>=y)return true;
        return false;
    }

    @Override
    public void run(){
        while(global.life>0){
            try{
                Thread.sleep(sd0);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            setY(r.top+3);
            if(r.top>=global.height)//block飞出屏幕 跳出循环
                break;
        }
        global.roses.remove(this);
    }
}
