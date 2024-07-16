package com.example.mentalarithmetic;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class Start extends AppCompatActivity {
    TextView[] numbers = new TextView[10];

    private int count = 3;

    private int decreaseCount(){
        count--;
        return count;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.playing_scene);


        numbers[0] = findViewById(R.id.number1);
        numbers[1] = findViewById(R.id.number2);
        numbers[2] = findViewById(R.id.number3);
        numbers[3] = findViewById(R.id.number4);
        numbers[4] = findViewById(R.id.number5);
        numbers[5] = findViewById(R.id.number6);
        numbers[6] = findViewById(R.id.number7);
        numbers[7] = findViewById(R.id.number8);
        numbers[8] = findViewById(R.id.number9);
        numbers[9] = findViewById(R.id.number10);

        //debug only
        TextView debug = findViewById(R.id.debug);

        //Take from settings
        SharedPreferences prefs = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        int text_digit = prefs.getInt("text_digit", 4);
        int totalnumber = prefs.getInt("totalnumber", 4);

        //debug
        String gamemode = prefs.getString("gamemode", "lol");
        debug.setText(gamemode);
        int finalAnswer = 0;
        switch (gamemode){
            case "Addition Only":
                finalAnswer = addition(totalnumber,text_digit);
                break;
            case "May have negative value":
                finalAnswer = subNegative(totalnumber,text_digit);
                break;
            default:
                finalAnswer = addition(totalnumber,text_digit);
                break;
        }

        EditText answer = findViewById(R.id.answer);
        Button submitButton = findViewById(R.id.submit_answer);
        TextView comment = findViewById(R.id.comment);
        final int diuniaseng = finalAnswer;
        ConstraintLayout startLayout = findViewById(R.id.playingLayout);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputText = answer.getText().toString();
                try {
                    int jawapan = Integer.parseInt(inputText);
                    if (diuniaseng != jawapan) {
                        int chance = decreaseCount();
                        comment.setText("Bodoh, macam ini pun boleh salah , chances left:" + chance);
                        if (chance <= 0) {
                            comment.setText("Out of chances, Press anywhere to continue");
                            startLayout.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    recreate();
                                    answer.setText("");
                                }
                            });
                        }
                    }
                    else {
                        comment.setText("Congrats!!! you're better than 3 years old kiddo");
                        startLayout.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                recreate();
                                answer.setText("");
                            }
                        });
                    }
                }
                 catch (NumberFormatException e){
                    Log.e("NumberFormatException", "Error converting to integer: " + e.getMessage());
                    comment.setText("Null value leh, adui, you want my program to crash is it?");
                }
            }


        });


        Button buttonReset = (Button)findViewById(R.id.reset);
        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recreate();
            }
        });

        Button buttonBackMenu = (Button)findViewById(R.id.back_menu);
        buttonBackMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private int addition(int totalnumber, int text_digit ){
        Random rand = new Random();
        int quiz = totalnumber;
        int digits = 1;
        for(int i = 0 ; i < text_digit; i++){
            digits = digits * 10;
        }
        int sum = 0;
        int jawapan = 0;
        for (int i = 0; i < quiz; i++) {
            int num = rand.nextInt(digits) + 1 ;
            numbers[i].setText(String.valueOf(num));
            numbers[i].setTextSize(20);
            sum = sum + num;
        }

        return sum;
    }

    private int subNegative(int totalnumber, int text_digit ){
        Random rand = new Random();
        int quiz = totalnumber;
        int digits = 1;
        for(int i = 0 ; i < text_digit; i++){
            digits = digits * 10;
        }
        int sum = 0;
        int jawapan = 0;

        for (int i = 0; i < quiz; i++) {
            boolean positive = rand.nextBoolean();
            int num = 0;
            if(!positive) {
                num = -1 * rand.nextInt(digits) + 1;
            }else{
                num =  rand.nextInt(digits) + 1;
            }
            System.out.println(num);
            numbers[i].setText(String.valueOf(num));
            numbers[i].setTextSize(20);
            sum = sum + num;

        }

        return sum;
    }
}