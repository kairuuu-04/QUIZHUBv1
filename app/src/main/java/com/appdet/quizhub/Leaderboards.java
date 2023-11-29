package com.appdet.quizhub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class Leaderboards extends AppCompatActivity {

    ListView lvLeaderdboards;
    Easy_Stage easy_stage = new Easy_Stage();
    Medium_Stage medium_stage = new Medium_Stage();
    Hard_Stage hard_stage = new Hard_Stage();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboards);

        lvLeaderdboards = findViewById(R.id.lvLeaderboards);


        Intent intent = getIntent();
        String receivedData = intent.getStringExtra("key");

////
////        ArrayList<String> dataList = new ArrayList<>();
////        dataList.add("Easy Score: " + easy_stage.getEasy_Score());
//
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dataList);
//        lvLeaderdboards.setAdapter(adapter);


    }
}