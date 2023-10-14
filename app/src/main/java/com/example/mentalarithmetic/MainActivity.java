package com.example.mentalarithmetic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.util.DisplayMetrics;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get the display metrics
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();

        // Calculate the dp height and width
        float dpHeight = displayMetrics.heightPixels / displayMetrics.density;
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;

        // Use the dp values as needed
        Log.d("MyActivity", "DP Height: " + dpHeight);
        Log.d("MyActivity", "DP Width: " + dpWidth);

        Button buttonStart = (Button)findViewById(R.id.toGame);
        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Start.class);
                startActivity(intent);
            }
        });

        Button buttonSetting = (Button)findViewById(R.id.toSetting);
        buttonSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Setting.class);
                startActivity(intent);
            }
        });
    }



}