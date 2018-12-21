package com.example.viktor.game1;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class PlayGame2Activity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(new GameView(this));
        super.onCreate(savedInstanceState);

        Bundle extras = getIntent().getExtras();
        String name = extras.getString("name");
        int difficulty = extras.getInt("difficulty");
        System.out.println("activity2 end");


    }



}
