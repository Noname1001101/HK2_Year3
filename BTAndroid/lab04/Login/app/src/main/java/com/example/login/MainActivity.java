package com.example.login;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.Console;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.m001_act_login);

        EditText email = findViewById(R.id.email);
        EditText password = findViewById(R.id.password);
        Button btnLogin = findViewById(R.id.btn_login);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailText = email.getText().toString();
                String passwordText = password.getText().toString();
                if (emailText.trim().isEmpty() || passwordText.trim().isEmpty()) {
                    Toast.makeText(MainActivity.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }
                else {
                    TextView tv = new TextView(MainActivity.this);
                    tv.setText("Đăng nhập thành công!\nEmail: " + emailText + "\nvà mật khẩu: " + passwordText);
                    tv.setTextSize(20);
                    tv.setPadding(80, 50, 80, 50);


                    // 2.Tạo hình bo góc
                    GradientDrawable shape = new GradientDrawable();
                    int colorBackground = ContextCompat.getColor(MainActivity.this, R.color.teal_700);
                    shape.setColor(colorBackground);
                    shape.setCornerRadius(50);
                    tv.setBackground(shape);

                    // 3.Hiển thị Toast với View vừa tạo
                    Toast toast = new Toast(MainActivity.this);

                    toast.setDuration(Toast.LENGTH_LONG);
                    toast.setView(tv);
                    toast.show();


                }
            }
            });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main_layout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}