package com.modestsoftware.mbload;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;


public class Splash_screen extends AppCompatActivity {


    ImageView logo;


    Animation logoanim2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);


        logo = findViewById(R.id.logo);



        logoanim2= AnimationUtils.loadAnimation(Splash_screen.this, R.anim.logo_anim2);
        logo.setAnimation(logoanim2);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {


                Intent myIntent = new Intent(Splash_screen.this, MainActivity.class);
                startActivity(myIntent);
                finish();

            }
        },2000);




    }




}