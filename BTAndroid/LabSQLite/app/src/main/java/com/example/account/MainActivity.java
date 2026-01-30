package com.example.account;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;




public class MainActivity extends AppCompatActivity {

    public static final String SAVE_PREF = "save_pref";
    SQLiteHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        InitialDB();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.ln_main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        gotoLoginScreen();
    }

    private void InitialDB(){
        helper = new SQLiteHelper(this);
        helper.openDB();
        helper.createTable();
    }

    private void gotoLoginScreen() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.ln_main, new M000LoginFragment())
                .commit();
    }

}

