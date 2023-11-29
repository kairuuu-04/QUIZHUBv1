package com.appdet.quizhub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class homepage extends AppCompatActivity {

    Button btnStart, btnScores, btnExit;

    private SystemBars systemBars;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        // to call the SystemBars class that immerse the screen and reveal if swiped up
        systemBars = new SystemBars(this);
        systemBars.enableSwipeToToggleSystemBars();


        btnStart = findViewById(R.id.btnEasy);
        btnScores = findViewById(R.id.btnMedium);
        btnExit = findViewById(R.id.btnHard);


        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the Difficulty_Selection activity
                Intent intent = new Intent(homepage.this, Difficulty_Selection.class);
                startActivity(intent);
            }
        });

        btnScores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the SignUp activity
                Intent intent = new Intent(homepage.this, Leaderboards.class);
                startActivity(intent);
            }
        });

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish all activities and exit the app
                finishAffinity();
            }
        });




    }

}
