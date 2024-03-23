package com.example.recyclerviewexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.airbnb.lottie.LottieAnimationView;

public class MainActivity2 extends AppCompatActivity {
    LottieAnimationView lottie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Intent intent = new Intent(MainActivity2.this,MainActivity.class);

        //we want to run splash screen at specific time
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(intent);
                lottie.setAnimation(R.raw.lottie2);
                lottie.playAnimation();
            }
        },4000);


    }
}