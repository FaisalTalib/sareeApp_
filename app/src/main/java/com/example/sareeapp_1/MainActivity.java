package com.example.sareeapp_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity  {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_main);


    }

    public void onClickImageEmergencyOrder(View view) {
        ImageButton EmergencyOrder = (ImageButton) findViewById(R.id.f1);
        EmergencyOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, EmergencyPageForm.class);
                startActivity(intent);
            }
        });

    }

    public void onClickImageF4(View view) {
        ImageButton PermitPage = (ImageButton) findViewById(R.id.f5);
        PermitPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PermitPage.class);
                startActivity(intent);
            }
        });

    }

    public void onClickImageF1 (View view) {
        ImageButton ExternalComlaint = (ImageButton) findViewById(R.id.f2);
        ExternalComlaint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ExternalComplaintPage.class);
                startActivity(intent);
            }
        });

    }

    public void onClickImageF2 (View view) {
        ImageButton Suggestions = (ImageButton) findViewById(R.id.f3);
        Suggestions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SuggestionsPage.class);
                startActivity(intent);
            }
        });

    }

    public void onClickImageF3 (View view) {
        ImageButton Permit = (ImageButton) findViewById(R.id.f4);
        Permit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PermitPage.class);
                startActivity(intent);
            }
        });

    }



}