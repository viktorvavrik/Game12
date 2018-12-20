package com.example.viktor.game1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements RadioGroup.OnCheckedChangeListener {

    Button nameButton, goButton;
    EditText nameEdit;
    TextView nameView;
    RadioButton radioBeginner, radioAdvanced, radioExpert;
    RadioGroup radioGroup;
    String namePlayer;
    Boolean nameDisplayed = false;
    int difficulty = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        nameEdit = (EditText) findViewById(R.id.editName);
        nameView = (TextView) findViewById(R.id.viewName);

        nameButton = (Button) findViewById(R.id.buttonName);
        nameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(nameEdit.getText().toString().length() != 0) {
                    namePlayer = nameEdit.getText().toString();
                    nameView.setText("Welcome " + namePlayer);
                    nameDisplayed = true;
                } else {
                    nameView.setText("");
                    nameDisplayed = false;
                    Toast.makeText(getApplicationContext(), "Enter your name!",
                            Toast.LENGTH_LONG).show();
                }
            }
        });

        radioGroup = (RadioGroup) findViewById(R.id.radioGroup1);
        radioBeginner = (RadioButton) findViewById(R.id.difficultyButton1);
        radioAdvanced = (RadioButton) findViewById(R.id.difficultyButton2);
        radioExpert = (RadioButton) findViewById(R.id.difficultyButton3);

        radioGroup.setOnCheckedChangeListener(this);




        goButton = (Button) findViewById(R.id.buttonStart);
        goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(nameDisplayed == true && difficulty > 0) {
                    Intent intent = new Intent(getApplicationContext(), PlayGame2Activity.class);
                    intent.putExtra("name", namePlayer);
                    intent.putExtra("difficulty", difficulty);
                    startActivity(intent);

                } else if(nameDisplayed == false) {
                    Toast.makeText(getApplicationContext(), "Enter your name first!",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch(checkedId) {
            case R.id.difficultyButton1:
                difficulty = 1;
                break;
            case R.id.difficultyButton2:
                difficulty = 2;
                break;
            case R.id.difficultyButton3:
                difficulty = 3;
                break;
        }
    }

}
