package com.example.lab02;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        // 1. Ánh xạ View
        Button btSplash = findViewById(R.id.bt_splash);
        Button btProfile = findViewById(R.id.bt_profile);

        // 2. Đăng ký sự kiện click
        btSplash.setOnClickListener(this);
        btProfile.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // 3. Xử lý khi click vào từng nút
        if (v.getId() == R.id.bt_splash) {
            // Mở màn hình Splash
            Intent intent = new Intent(this, M000ActSplash.class);
            startActivity(intent);
        } else if (v.getId() == R.id.bt_profile) {
            // Mở màn hình Profile
            Intent intent = new Intent(this, M00ActProfile.class);
            startActivity(intent);
        }
    }


}