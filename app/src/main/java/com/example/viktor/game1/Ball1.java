package com.example.viktor.game1;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import java.util.Random;

public class Ball1 {

    private Bitmap image;
    private int x, y;
    private int xVelocity;
    private int yVelocity;
    private int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
    private int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;
    int tenCycle = 0;
    Random rand = new Random();


    public Ball1(Bitmap bmp) {
        image = bmp;
        x = rand.nextInt(100)+10;
        y = rand.nextInt(100)+10;
        xVelocity = rand.nextInt(10) - 5;
        yVelocity = rand.nextInt(10) - 5;


    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(image, x,y, null);
    }

    public void update(float[] orientation) {
        if(tenCycle < 10) tenCycle++;
        else {
            xVelocity -= (orientation[0]);
            yVelocity += (orientation[1]);
            tenCycle = 0;
        }

        x +=xVelocity;
        y +=yVelocity;
        if(x > screenWidth - image.getWidth() || x < 0) {
            xVelocity = xVelocity * -1;
        }
        if(y > screenHeight - (image.getHeight()) || y < 0) {
            yVelocity = yVelocity * -1;
        }
    }

    public void setVelocity(float v, float v1) {
        xVelocity += v*100;
        yVelocity += v1*100;
    }
}
