package com.example.viktor.game1;


import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class PlayGame2Activity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(new GameView(this));


        super.onCreate(savedInstanceState);

        Bundle extras = getIntent().getExtras();
        String name = extras.getString("name");
        int difficulty = extras.getInt("difficulty");


    }


    @Override
    public void onResume() {
        super.onResume();
        System.out.println("V onResume");

    }

    @Override
    public void onPause() {
        super.onPause();
        System.out.println("V onResume");
    }

    @Override
    public void onStop() {
        super.onStop();
        System.out.println("V onResume");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        System.out.println("V onResume");
    }



}
