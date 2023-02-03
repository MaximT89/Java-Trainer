package com.second.world.javatest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.second.world.javatest.core.App;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((App) getApplication()).getAppComponent().inject(this);



    }
}