package com.example.mygame;

import android.graphics.Bitmap;
import android.graphics.RectF;

public class myObject {//所有物体的基类
    public RectF r=new RectF();
    protected int width;//宽
    protected int height;//高
    protected Bitmap Image;//图

    public void setX(float x) {
        r.left = x;
        r.right=x+width;
    }

    public void setY(float y) {
        r.top=y;
        r.bottom=y+height;
    }
}
