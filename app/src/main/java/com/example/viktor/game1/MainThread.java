package com.example.viktor.game1;

import android.app.Activity;
import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class MainThread extends Thread {

    private SurfaceHolder surfaceHolder;
    private GameView gameView;
    private boolean running;
    public static Canvas canvas;
    private boolean endgame;


    public MainThread(SurfaceHolder surfaceHolder, GameView gameView) {
        super();
        this.surfaceHolder = surfaceHolder;
        this.gameView = gameView;
        endgame = false;
    }



    public void run() {
        while(running) {
            canvas = null;

            try {
                canvas = this.surfaceHolder.lockCanvas();
                synchronized (surfaceHolder) {
                    if(endgame) {
                        this.gameView.showScore(canvas);
                    } else {
                        this.gameView.update();
                        this.gameView.draw(canvas);
                    }
                }
            } catch (Exception e) {

            } finally {
                if(canvas != null) {
                    try {
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        System.out.println("thread ends");
    }

    /*public void run() {
        while(running) {
            if(!surfaceHolder.getSurface().isValid()) continue;
            Canvas canvas = surfaceHolder.lockCanvas();

            this.gameView.draw(canvas);
            this.gameView.update();
            surfaceHolder.unlockCanvasAndPost(canvas);
        }
        System.out.println("end run");
    }*/


    public void setRunning(boolean isRunning) {

        running = isRunning;
        if(isRunning==false) {
            System.out.println("running false");
            synchronized (surfaceHolder) {
                ((Activity) gameView.getContext()).finish();
            }
        }
    }

    public void setEndgame(boolean b) {
        endgame = b;
    }
}
