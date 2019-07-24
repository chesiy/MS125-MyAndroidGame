package com.example.mygame;

public class Block extends myObject implements Runnable {
    private long sd0 = (long) (Math.random() * 10) + 10;//[10,20),白块速度不同
    private float[] randx = new float[]{50f, 150f, 250f, 350f, 450f, 550f, 650f, 750f};
    public boolean havedisappeared;
    private volatile Thread coreThread = null;

    public Thread getCoreThread() {
        return coreThread;
    }

    public void setCoreThread(Thread coreThread) {
        throw new RuntimeException("Internal Thread cannot be set");
    }

    public Block() {
        havedisappeared = false;
        Image = global.block;
        width = Image.getWidth();
        height = Image.getHeight();
        int select = (int) (Math.random() * 9);
        System.err.println(select);
        setX(randx[select]);//x在8个里随机
        setY(-height);//屏幕外，刚好看不到
        global.blocks.add(this);
//        new Thread(this).start();
        coreThread = new Thread(this);
        coreThread.start();
    }

    public boolean bang(float x, float y) {
        if (r.left <= x && r.right >= x && r.top <= y && r.bottom >= y) return true;
        else return false;
    }

    @Override
    public void run() {
  //      System.err.println("run Block" + this.toString() + "  ");
        while (global.life > 0) {
            try {
                Thread.sleep(sd0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            setY(r.top + 2);
            if (r.top > global.height) {
                break;
            }
        }
        global.life--;
        global.blocks.remove(this); //从集合中删除
    }

    public void stop() {
        this.coreThread = null;
    }
}
