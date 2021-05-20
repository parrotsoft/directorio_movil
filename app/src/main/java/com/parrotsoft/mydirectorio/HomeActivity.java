package com.parrotsoft.mydirectorio;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import static com.parrotsoft.mydirectorio.R.layout.activity_home;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_home);
    }
}