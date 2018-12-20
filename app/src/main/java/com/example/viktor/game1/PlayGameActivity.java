package com.example.viktor.game1;

import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.view.animation.Animation;
import android.view.animation.BounceInterpolator;
import android.view.animation.Animation.AnimationListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Random;


public class PlayGameActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_game);
        Bundle extras = getIntent().getExtras();
        String name = extras.getString("name");
        int difficulty = extras.getInt("difficulty");

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        final int width = size.x;
        final int height = size.y;
        Log.i("Velikost obrazovky je: ", "Velikost obrazovky je: ");
        final Random rand = new Random();

        final ImageView ball1 = (ImageView) findViewById(R.id.theBall1);

        Button start = (Button) findViewById(R.id.buttonStart);
        start.setOnClickListener(new View.OnClickListener() {
           @Override
            public void onClick(View v) {
                //Ball1.clearAnimation();
                int r = rand.nextInt(200) - 100;
                TranslateAnimation transAnim = new TranslateAnimation(0,r,0,height/2);
                transAnim.setStartOffset(100);
                transAnim.setDuration(3000);
                transAnim.setFillAfter(false);
                transAnim.setInterpolator(new BounceInterpolator());
                transAnim.setAnimationListener(new AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        final int left = ball1.getLeft();
                        final int right = ball1.getRight();
                        final int top = ball1.getTop();
                        final int bottom = ball1.getBottom();
                        ball1.layout(left, top, right, bottom);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                ball1.startAnimation(transAnim);
            }
        });
    }
}
