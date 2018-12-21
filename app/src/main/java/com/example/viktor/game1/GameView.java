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
    ArrayList<Rect> badRectanglesList = new ArrayList<>();
    Rect rect;
    //Time startTime;
    private OrientationData orientationData;
    Drawable d = getResources().getDrawable(R.drawable.ball_shape);
    Drawable f = getResources().getDrawable(R.drawable.rect_shape);
    Drawable g = getResources().getDrawable(R.drawable.rect2_shape);
    int points = 0;



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

        ball1 = new Ball1(drawableToBitmap(d));
        rect = new Rect(drawableToBitmap(f));
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
                //System.out.println("ukoncujem vlakno");
                thread.setRunning(false);
                thread.join();
                try {
                    return;
                } catch(Exception s) {
                    s.printStackTrace();
                    System.out.println(s);
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            retry = false;
        }
    }

    public void update() {


        ball1.update(orientationData.getOrientation());
        int ballX = ball1.getX();
        int ballY = ball1.getY();
        int rectX = rect.getX();
        int rectY = rect.getY();
        //System.out.println("balX = " + ballX+" balY = " + ballY + " rectX = " + rectX + " rectY = " + rectY);
        for(Rect badRect : badRectanglesList) {
            if((ballX<badRect.getX()+5) && (ballX+110>badRect.getX())) {
                if((ballY<badRect.getY()+5) && ballY+110>badRect.getY()) {
                    endGame();
                }
            }
        }

        if((ballX<rectX+5) && (ballX+110>rectX))  {
            if((ballY<rectY+5) && (ballY+110>rectY)) {
                rect.newUpdate();
                points++;
                System.out.println("Pocet bodov = " + points);
                //if(points%2==0) {
                    //System.out.println("New bad rectangle");
                    badRectanglesList.add(new Rect(drawableToBitmap(g)));
                    System.out.println(badRectanglesList.size());
                    for(Rect badRectangle : badRectanglesList) {
                        System.out.println("coordinate of rec: " + badRectangle.getX() + " " + badRectangle.getY());
                        badRectangle.update();
                    }
                //}
            }
        }



    }

    private void endGame() {
        System.out.println("KONIEC HRY");
        surfaceDestroyed(getHolder());
    }

    public void draw(Canvas canvas) {
        super.draw(canvas);
        if(canvas!=null) {
            rect.draw(canvas);
            ball1.draw(canvas);
            for(Rect badRectangle : badRectanglesList) {
                badRectangle.draw(canvas);
            }

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
