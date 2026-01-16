package com.example.lab02;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class M000ActSplash extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Gọi giao diện con chim cánh cụt
        setContentView(R.layout.m001_act_splash);

        LinearLayout llBackground = findViewById(R.id.ll_background);
        ImageView ivIcon = findViewById(R.id.iv_icon);

        String[] arrColors = {
                "#3F51B5",
                "#FF5722",
                "#4CAF50",
                "#9C27B0",
                "#00BCD4",
                "#FF9800"
        };

        int[] arrIcons = {
                R.drawable.penguin,
                R.drawable.dog,
                R.drawable.cat,
                R.drawable.dragon

        };

        Random random = new Random();
        //Random màu
        int randomColorIndex = random.nextInt(arrColors.length);
        llBackground.setBackgroundColor(Color.parseColor(arrColors[randomColorIndex]));

        //Random icon
        int randomIconIndex = random.nextInt(arrIcons.length);
        ivIcon.setImageResource(arrIcons[randomIconIndex]);




    }
}