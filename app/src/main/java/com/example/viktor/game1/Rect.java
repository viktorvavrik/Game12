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
        x = rand.nextInt(100)+10;
        y = rand.nextInt(100)+10;


    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(image, x,y, null);
    }

    public void update(float[] orientation) {

    }
}
