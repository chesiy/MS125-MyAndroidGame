package com.example.mygame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.Vector;

import static com.example.mygame.global.blocks;
import static com.example.mygame.global.booms;
import static com.example.mygame.global.roses;

public class myView extends View {
    public float touchx,touchy;
    private Paint mPaint=new Paint();
    private Canvas mCanvas=null;
 //   public Vector<Integer> time=new Vector<Integer>();

    private void init(){
        mPaint.setColor(Color.WHITE);
        global.background= BitmapFactory.decodeResource(getResources(),R.mipmap.background);
        global.rose=BitmapFactory.decodeResource(getResources(),R.mipmap.award);
        global.block=BitmapFactory.decodeResource(getResources(),R.mipmap.block);

        global.ex.add(BitmapFactory.decodeResource(getResources(),R.mipmap.e1));
        global.ex.add(BitmapFactory.decodeResource(getResources(),R.mipmap.e2));
        global.ex.add(BitmapFactory.decodeResource(getResources(),R.mipmap.e3));
        global.ex.add(BitmapFactory.decodeResource(getResources(),R.mipmap.e4));
        global.ex.add(BitmapFactory.decodeResource(getResources(),R.mipmap.e5));
        global.ex.add(BitmapFactory.decodeResource(getResources(),R.mipmap.e6));

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
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            touchx = event.getX();
            touchy = event.getY();
            for (int i = 0; i < blocks.size(); i++) {
                Block bl = blocks.get(i);
                if (bl.bang(touchx, touchy)) {
                    global.score += 10;
                    blocks.remove(bl);
                    bl.have_gone = true;
                }
            }
            for (int i = 0; i < roses.size(); i++) {
                Rose rs = roses.get(i);
                if (rs.bang(touchx, touchy)) {
                    global.score += 20;
                    Boom bm = new Boom(rs.r.left, rs.r.top);
                    booms.add(bm);
                    //   bm.run();
                    rs.boom(bm);
                    roses.remove(rs);
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
            for(int i=0;i<roses.size();i++){
                Rose mrs=roses.get(i);
                canvas.drawBitmap(mrs.Image,null,mrs.r,mPaint);
            }
       //     System.err.println(booms.size());
            for(int i=0;i<booms.size();i++) {
                Boom mb=booms.get(i);
                canvas.drawBitmap(mb.Image,null,mb.r,mPaint);
                for(int j=0;j<6;j++) {
                    System.err.println(j + " " + booms.get(i).Image.equals(global.ex.get(j)));
                }
                System.err.println("aaaaa");
            }

            canvas.drawText("SCORE:" + global.score, 0, global.height - 50, mPaint);
            canvas.drawText("LIFE:" + global.life, 0, global.height - 120, mPaint);
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
            int i=0;
            while(true){//每多少ms出现一个白块
                try{Thread.sleep(656);}catch (InterruptedException e){e.printStackTrace();}
                try{
                    new Block();
                    new Rose();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }
}
