package com.example.englishlearning;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private static final int[] ID_DRAWABLES = {R.drawable.ecentials, R.drawable.traveling,
            R.drawable.medical,
            R.drawable.hotel, R.drawable.restaurant, R.drawable.bar,
            R.drawable.store, R.drawable.work, R.drawable.time, R.drawable.education,
            R.drawable.entertainment};
    private static final int[] ID_TEXTS = {R.string.txt_ecentials, R.string.txt_traveling, R.string.txt_medical,
            R.string.txt_hotel, R.string.txt_restaurant, R.string.txt_bar,
            R.string.txt_store, R.string.txt_work, R.string.txt_time, R.string.txt_education, R.string.txt_entertainment};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        LinearLayout lnMain = findViewById(R.id.ln_main);
        lnMain.removeAllViews();
        //Tạo ra các Item topic động và add vào LinearLayout
        for (int i = 0; i < ID_DRAWABLES.length; i++) {
            View v = LayoutInflater.from(this).inflate(R.layout.item_topic, null);
            ImageView ivTopic = v.findViewById(R.id.iv_topic);
            TextView tvTopic = v.findViewById(R.id.tv_topic);
            ivTopic.setImageResource(ID_DRAWABLES[i]);
            tvTopic.setText(ID_TEXTS[i]);

            //Quy định không gian chiếm của mỗi item view = 1
            LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    1.0f
            );
            v.setLayoutParams(param);
            lnMain.addView(v);

            final int position = i;
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(MainActivity.this, VocabularyActivity.class);
                    intent.putExtra("TOPIC_NAME", getString(ID_TEXTS[position]));
                    startActivity(intent);
                }
            });
        }

    }
}
