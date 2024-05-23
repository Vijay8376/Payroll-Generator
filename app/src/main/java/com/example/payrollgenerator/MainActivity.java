package com.example.payrollgenerator;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView topBanner = findViewById(R.id.topBanner);
        ImageView bannerGrey = findViewById(R.id.bannerGrey);
        ImageView bannerMagenta = findViewById(R.id.bannerMagenta);
        ImageView videoButtons = findViewById(R.id.videoButtons);
        ImageView bottomBorder = findViewById(R.id.bottomBorder);
        ImageView bottomVideo = findViewById(R.id.bottomVideo);
        ImageButton addButton = findViewById(R.id.addButton);
        ImageButton viewButton = findViewById(R.id.viewButton);
        ImageButton editButton = findViewById(R.id.editButton);
        ImageButton deleteButton = findViewById(R.id.deleteButton);
        ImageButton generateButton = findViewById(R.id.generateButton);
        TextView titleText = findViewById(R.id.titleText);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) TextView fifthbutton = findViewById(R.id.generateText);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });

        viewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DisplayActivity.class);
                startActivity(intent);
            }
        });

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, EditActivity.class);
                startActivity(intent);
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DeleteActivity.class);
                startActivity(intent);
            }
        });

        generateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GenerateActivity.class);
                startActivity(intent);
            }
        });


    }
}