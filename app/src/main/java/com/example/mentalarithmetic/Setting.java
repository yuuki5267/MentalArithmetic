package com.example.mentalarithmetic;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Setting extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        Button buttonSetting = (Button)findViewById(R.id.submit_setting);
        buttonSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = getSharedPreferences("MyPrefs", MODE_PRIVATE).edit();
                EditText text_digit = (EditText) findViewById(R.id.edittext_digit);
                EditText totalnumber = (EditText) findViewById(R.id.edittext_totalnumber);

                editor.putInt("text_digit", Integer.parseInt(text_digit.getText().toString()));
                editor.putInt("totalnumber", Integer.parseInt(totalnumber.getText().toString()));
                editor.apply();

                Intent intent = new Intent(Setting.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}