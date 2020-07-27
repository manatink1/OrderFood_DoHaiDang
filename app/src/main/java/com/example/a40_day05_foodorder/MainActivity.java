package com.example.a40_day05_foodorder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.a40_day05_foodorder.databinding.ActivityMainBinding;

import java.util.regex.Pattern;

import Fragment.Login;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        Login lo = new Login();
        getSupportFragmentManager().beginTransaction().replace(R.id.Container,lo).commit();

    }


}