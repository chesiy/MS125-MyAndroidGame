package com.example.mygame;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import static com.example.mygame.global.blocks;

public class myView extends View {
    public float touchx,touchy;
    private Paint mPaint=new Paint();
    private Canvas mCanvas=null;

    private void init(){
        mPaint.setColor(Color.WHITE);
        global.background= BitmapFactory.decodeResource(getResources(),R.mipmap.background);
        global.award=BitmapFactory.decodeResource(getResources(),R.mipmap.award);
        global.block=BitmapFactory.decodeResource(getResources(),R.mipmap.block);

        new Thread(new repaintit()).start();//新建线程，让画布重绘
        new Thread(new loadobj()).start();//新建线程，加载白块和玫瑰
    }
    public myView(Context context) {
        super(context);
        init();
    }

    public myView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public myView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public myView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){//汇报触电
        if(event.getAction()==MotionEvent.ACTION_DOWN){
            touchx=event.getX();
            touchy=event.getY();
            for(int i=0;i< blocks.size();i++){
                Block bl=blocks.get(i);
                Block tmp=blocks.get(blocks.size()-1);
                if(bl.bang(touchx,touchy)){
                    global.score+=10;
                    blocks.remove(bl);
               //     blocks.set(i,tmp);
               //     blocks.set(blocks.size()-1,bl);
               //     blocks.remove(blocks.size()-1);
                }
            }
        }
        invalidate();
        return true;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(global.bkgd.Image,null,global.bkgd.r,mPaint);
    //    System.err.println(blocks.size());
        if(global.life>0){
            for(int i = 0; i< blocks.size(); i++){
                Block mobj= blocks.get(i);
                canvas.drawBitmap(mobj.Image,null,mobj.r,mPaint);
            }
            canvas.drawText("SCORE:"+global.score,0,global.height-50,mPaint);
            canvas.drawText("LIFE:"+global.life,0,global.height-120,mPaint);
        }
        else{
            mPaint.setColor(Color.YELLOW);
            mPaint.setTextSize(70);
            canvas.drawText("What A Pity!",200,global.height/2-80,mPaint);
            canvas.drawText("Your Score:"+global.score,200,global.height/2,mPaint);
        }
    }

    @Override
    protected void onSizeChanged(int w,int h,int oldw,int oldh){
        super.onSizeChanged(w,h,oldw,oldh);
        global.width=w;
        global.height=h;

        mPaint.setColor(Color.WHITE);
        mPaint.setTextSize(50);
        global.life=3;
        global.score=0;

        global.bkgd=new Background();
    }

    private class repaintit implements Runnable{
        @Override
        public void run(){
            while(true){
                try{Thread.sleep(10);}catch (InterruptedException e){e.printStackTrace();}
                postInvalidate();
            }
        }
    }
    private class loadobj implements Runnable{
        @Override
        public void run(){
            while(true){//每1200ms出现一个白块
                try{Thread.sleep(3000);}catch (InterruptedException e){e.printStackTrace();}
                try{
                    new Block();
             //       new Award();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }
}
