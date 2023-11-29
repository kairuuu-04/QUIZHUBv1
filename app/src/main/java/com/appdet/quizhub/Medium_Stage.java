package com.appdet.quizhub;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Medium_Stage extends AppCompatActivity {

    TextView tvCounter, tvQuestion;
    ImageView imgFlags;
    Button btnOption1, btnOption2, btnOption3, btnOption4;
    ImageButton imgBtnBackArrow;

    int medium_Score = 0;

    private int mediumQuestionIndex = 0;
    private SystemBars systemBars;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medium_stage);

        // to call the SystemBars class that immerse the screen and reveal if swiped up
        systemBars = new SystemBars(this);
        systemBars.enableSwipeToToggleSystemBars();

        imgFlags = findViewById(R.id.imgFlags);

        tvCounter = findViewById(R.id.tvCounter);
        tvQuestion = findViewById(R.id.tvQuestion);
        btnOption1 = findViewById(R.id.btnOption1);
        btnOption2 = findViewById(R.id.btnOption2);
        btnOption3 = findViewById(R.id.btnOption3);
        btnOption4 = findViewById(R.id.btnOption4);

        imgBtnBackArrow = findViewById(R.id.imgBtnBackArrow);


        //        loads the Question from Easy Level
        mediumLoadQuestion();

        btnOption1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answerChecker(btnOption1.getText().toString());
            }
        });

        btnOption2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answerChecker(btnOption2.getText().toString());
            }
        });

        btnOption3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answerChecker(btnOption3.getText().toString());
            }
        });

        btnOption4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answerChecker(btnOption4.getText().toString());
            }
        });

        imgBtnBackArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }


    public void mediumLoadQuestion() {
        // Randomize the options in buttons
        int randomIndex = new Random().nextInt(Medium_Difficulty.Medium_Options.length);
        String[] optionSet = Medium_Difficulty.Medium_Options[mediumQuestionIndex];
        List<String> optionList = Arrays.asList(optionSet);
        Collections.shuffle(optionList);

        tvCounter.setText((mediumQuestionIndex + 1) + "/" + Medium_Difficulty.Medium_Questions.length);

        btnOption1.setText(optionList.get(0));
        btnOption2.setText(optionList.get(1));
        btnOption3.setText(optionList.get(2));
        btnOption4.setText(optionList.get(3));

        int resourceId = getResources().getIdentifier(Medium_Difficulty.Medium_Questions[mediumQuestionIndex],
                "drawable", getPackageName());
        imgFlags.setBackgroundResource(resourceId);
    }


    private void answerChecker(String selectedOption) {
        String correctAnswer = Medium_Difficulty.Medium_Country[mediumQuestionIndex];
        if (selectedOption.equals(correctAnswer)) {
            Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show();
            medium_Score++;

        } else {
            Toast.makeText(this, "Wrong!", Toast.LENGTH_SHORT).show();

        }

        mediumQuestionIndex++;
        if (mediumQuestionIndex < Medium_Difficulty.Medium_Country.length) {
            mediumLoadQuestion();
        } else {
            // End of questions

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Quiz Completed")
                    .setMessage("Congratulations! You have completed the quiz.\n\nYour Score: " + medium_Score + "\nDo you want to restart?")
                    .setPositiveButton("Yes", (dialog, which) -> {
                        // Restart the quiz
                        recreate();
                    })
                    .setNegativeButton("No", (dialog, which) -> {
                        // Go to the homepage or any desired activity
                        Intent intent = new Intent(Medium_Stage.this, homepage.class);
                        startActivity(intent);
                        finish();
                    })
                    .setCancelable(false)
                    .show();


            tvQuestion.setText("End of questions.\n" + " Your Score : " + medium_Score);

        }
    }








}