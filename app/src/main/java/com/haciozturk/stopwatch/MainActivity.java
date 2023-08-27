package com.haciozturk.stopwatch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    Chronometer chronometer;
    Button btnStart, btnReset, btnPause;
    ImageView imageView;
    
    long pauseoff=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        chronometer = findViewById(R.id.chronometer);
        btnStart = findViewById(R.id.btnStart);
        btnPause = findViewById(R.id.btnPause);
        btnReset = findViewById(R.id.btnReset);
        imageView = findViewById(R.id.imageView);


        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chronometer.setBase(SystemClock.elapsedRealtime()-pauseoff);
                chronometer.start();
                btnStart.setVisibility(View.INVISIBLE);
                btnPause.setVisibility(View.VISIBLE);
                imageView.setImageDrawable(getDrawable(R.drawable.pause));

            }
        });
        btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pauseoff = SystemClock.elapsedRealtime() - chronometer.getBase();
                chronometer.stop();
                btnPause.setVisibility(View.INVISIBLE);
                btnStart.setVisibility(View.VISIBLE);
                imageView.setImageDrawable(getDrawable(R.drawable.start));
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chronometer.setBase(SystemClock.elapsedRealtime());
                chronometer.stop();
                btnStart.setVisibility(View.VISIBLE);
                btnPause.setVisibility(View.INVISIBLE);
                imageView.setImageDrawable(getDrawable(R.drawable.start));

                pauseoff =0;
            }
        });
    }
}