package com.example.viktor.game1;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import java.util.ArrayList;




class GameView extends SurfaceView  implements SurfaceHolder.Callback {

    private MainThread thread;
    Activity activity;
    Ball1 ball1;
    ArrayList<Rect> badRectanglesList = new ArrayList<>();
    Rect rect;
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

        //activity = (Activity) holder;
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
                thread.join();

                try {
                    return;

                } catch(Exception s) {
                    System.out.println("Exception s: " + s);

                }

            } catch (InterruptedException e) {
                System.out.println("Exception e:" + e);
            }
            System.out.println("retry false");
            retry = false;
        }
    }

    public void update() {


        ball1.update(orientationData.getOrientation());
        int ballX = ball1.getX();
        int ballY = ball1.getY();
        int rectX = rect.getX();
        int rectY = rect.getY();

        for(Rect badRect : badRectanglesList) {
            if((ballX<badRect.getX()+5) && (ballX+110>badRect.getX())) {
                if((ballY<badRect.getY()+5) && ballY+110>badRect.getY()) {
                    thread.setEndgame(true);

                }
            }
        }

        if((ballX<rectX+5) && (ballX+110>rectX))  {
            if((ballY<rectY+5) && (ballY+110>rectY)) {
                rect.newUpdate();
                points++;
                //System.out.println("Pocet bodov = " + points);

                    badRectanglesList.add(new Rect(drawableToBitmap(g)));
                    //System.out.println(badRectanglesList.size());
                    for(Rect badRectangle : badRectanglesList) {
                        //System.out.println("coordinate of rec: " + badRectangle.getX() + " " + badRectangle.getY());
                        badRectangle.update();
                    }
                //}
            }
        }



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


    public void showScore(Canvas canvas) {

        for(int a = 0; a < 100000; a++) {

        }
        thread.setRunning(false);
        surfaceDestroyed(getHolder());
    }
}
