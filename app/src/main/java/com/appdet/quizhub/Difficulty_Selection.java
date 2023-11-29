package com.appdet.quizhub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Difficulty_Selection extends AppCompatActivity {

    Button btnEasy, btnMedium, btnHard;
    private SystemBars systemBars;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_difficulty_selection);

        // to call the SystemBars class that immerse the screen and reveal if swiped up
        systemBars = new SystemBars(this);
        systemBars.enableSwipeToToggleSystemBars();

        btnEasy = findViewById(R.id.btnEasy);
        btnMedium = findViewById(R.id.btnMedium);
        btnHard = findViewById(R.id.btnHard);

        btnEasy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the SignUp activity
                Intent intent = new Intent(Difficulty_Selection.this, Easy_Stage.class);
                startActivity(intent);
            }
        });

        btnMedium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the SignUp activity
                Intent intent = new Intent(Difficulty_Selection.this, Medium_Stage.class);
                startActivity(intent);
            }
        });

        btnHard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the SignUp activity
                Intent intent = new Intent(Difficulty_Selection.this, Hard_Stage.class);
                startActivity(intent);
            }
        });

    }




}