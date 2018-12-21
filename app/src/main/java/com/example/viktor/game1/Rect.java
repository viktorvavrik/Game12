package com.example.viktor.game1;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import java.util.Random;

public class Rect {
    private Bitmap image;
    private int x, y;
    private int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
    private int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;
    Random rand = new Random();


    public Rect(Bitmap bmp) {
        image = bmp;
        x = rand.nextInt(screenWidth-100)+50;
        y = rand.nextInt(screenHeight-100)+50;


    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(image, x,y, null);
    }

    public void update() {
        x=x;
        y=y;

    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void newUpdate() {
        x = rand.nextInt(screenWidth-100)+50;
        y = rand.nextInt(screenHeight-100)+50;
    }
}
