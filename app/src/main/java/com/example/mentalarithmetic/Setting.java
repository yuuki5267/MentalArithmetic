package com.example.mentalarithmetic;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class Setting extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        Button buttonSetting = (Button)findViewById(R.id.submit_setting);

        //Implement spinner for selecting game mode
        Spinner gamemode = (Spinner) findViewById(R.id.spinner_gameplay);
        String[] items = new String[]{"Addition Only", "Subtraction", "May have negative value"};
        //create an adapter to describe how the items are displayed, adapters are used in several places in android.
        //There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(Setting.this, android.R.layout.simple_spinner_dropdown_item, items);
        //set the spinners adapter to the previously created one.
        gamemode.setAdapter(adapter);

        buttonSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = getSharedPreferences("MyPrefs", MODE_PRIVATE).edit();
                EditText text_digit = (EditText) findViewById(R.id.edittext_digit);
                EditText totalnumber = (EditText) findViewById(R.id.edittext_totalnumber);

                if(!text_digit.getText().toString().isEmpty())
                    editor.putInt("text_digit", Integer.parseInt(text_digit.getText().toString()));
                if(!totalnumber.getText().toString().isEmpty())
                    editor.putInt("totalnumber", Integer.parseInt(totalnumber.getText().toString()));
                editor.putString("gamemode",gamemode.getSelectedItem().toString());
                editor.apply();

                System.out.println(gamemode.getSelectedItem().toString());


                Intent intent = new Intent(Setting.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}