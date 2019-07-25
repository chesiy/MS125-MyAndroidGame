package com.example.mygame;

import android.graphics.Bitmap;

import java.util.Vector;

public class global {
    public static final int RUNING=0;
    public static final int WIN=1;
    public static final int LOSE=2;

    public static int score=0;
    public static int life=3;
    public static int width,height;//屏幕宽、高
    public static Vector<Block> blocks=new Vector<Block>();
    public static Vector<Rose> roses=new Vector<Rose>();
    public static Vector<Boom> booms=new Vector<Boom>();

    public static Background bkgd;
    public static Bitmap background,rose,block,boom;
    public static Vector<Bitmap> ex=new Vector<Bitmap>();
    public static int GameState=RUNING;
}
