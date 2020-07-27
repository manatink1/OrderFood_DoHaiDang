package com.example.a40_day05_foodorder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.example.a40_day05_foodorder.databinding.ActivitySplashScreenBinding;

public class SplashScreen extends AppCompatActivity {
    ActivitySplashScreenBinding binding;
    Animation animation;
    private static final int SPLASH_TIME = 2000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_splash_screen);
        animation = AnimationUtils.loadAnimation(getBaseContext(),R.anim.animation_splashscreen);
        binding.image.setAnimation(animation);
        binding.tvContent.setAnimation(animation);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getBaseContext(),MainActivity.class));
                finish();
            }
        },SPLASH_TIME);
    }
}