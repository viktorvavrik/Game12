package com.example.viktor.game1;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.sql.Time;
import java.util.ArrayList;


class GameView extends SurfaceView  implements SurfaceHolder.Callback {

    private MainThread thread;

    Ball1 ball1;
    ArrayList<Rect> badRectanglesList;
    Rect rect;
    Time startTime, newBadRectTime;
    private OrientationData orientationData;



    public GameView(Context context) {
        super(context);

        getHolder().addCallback(this);
        orientationData = new OrientationData(context);
        orientationData.register();


        thread = new MainThread(getHolder(), this);
        setFocusable(true);

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Drawable d = getResources().getDrawable(R.drawable.ball_shape);
        Drawable e = getResources().getDrawable(R.drawable.rect_shape);
        ball1 = new Ball1(drawableToBitmap(d));
        startTime = new Time(0);
        newBadRectTime = new Time(0);
        orientationData.newGame();


        thread.setRunning(true);
        thread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        while(retry) {
            try {
                thread.setRunning(false);
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            retry = false;
        }
    }

    public void update() {

        ball1.update(orientationData.getOrientation());
        if(time > 10000) badRectanglesList.add(new Rect());
    }

    public void draw(Canvas canvas) {
        super.draw(canvas);
        if(canvas!=null) {
            ball1.draw(canvas);
        }
    }

    public static Bitmap drawableToBitmap (Drawable drawable) {

        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable)drawable).getBitmap();
        }

        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);

        return bitmap;
    }


}
