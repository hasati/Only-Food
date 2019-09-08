package com.example.cool.foodie;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cool.foodie.breakfastMenu.BreakFastActivity;
import com.example.cool.foodie.dinnerMenu.DinnerActivity;
import com.example.cool.foodie.loginUtility.LoginActivity;
import com.example.cool.foodie.lunchMenu.LunchActivity;


public class EattingChoiceActivity extends AppCompatActivity {

    private TextView textview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        final String userName = intent.getStringExtra("userName");
        setTitle("Hi "+userName);


        setContentView(R.layout.activity_eatting_choice);

        //if user presses on breakfast button

        findViewById(R.id.breakFastButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(EattingChoiceActivity.this,BreakFastActivity.class);
                intent.putExtra("userName",userName);
                startActivity(intent);
            }
        });

        //if user presses on lunch button
        findViewById(R.id.lunchButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            //    startActivity(new Intent(EattingChoiceActivity.this,LunchActivity.class));
                Toast.makeText(getApplicationContext(),"Still is Development", Toast.LENGTH_SHORT).show();

            }
        });

        // if user presses on dinner button
        findViewById(R.id.dinnerButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // startActivity(new Intent(EattingChoiceActivity.this,DinnerActivity.class));
                Toast.makeText(getApplicationContext(),"Still is Development", Toast.LENGTH_SHORT).show();
            }
        });

    }


    @Override
    public void onBackPressed(){
        Intent intent=new Intent(EattingChoiceActivity.this, MainActivity.class);
        startActivity(intent);
    }


}
