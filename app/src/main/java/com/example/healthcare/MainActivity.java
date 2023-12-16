package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

public class MainActivity extends AppCompatActivity {

//    LottieAnimationView lottie;
    TextView welcomeCenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        lottie = findViewById(R.id.lottieAnimation);
        welcomeCenter = findViewById(R.id.welcomeCenter);

        Intent itr = new Intent(this, loginActivity.class);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(itr);
                finish();
            }
        },3000);

//        Animation anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.scaleanim);
//        lottie.startAnimation(anim);

        Animation anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.scaleanim);
        welcomeCenter.startAnimation(anim);


    }
}