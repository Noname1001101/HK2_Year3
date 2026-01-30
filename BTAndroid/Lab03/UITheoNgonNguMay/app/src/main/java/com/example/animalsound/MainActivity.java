package com.example.animalsound;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.m003_act_detail);
        View mainview = findViewById(R.id.main_layout);
        if (mainview != null) {
            ViewCompat.setOnApplyWindowInsetsListener(mainview, (v, insets) -> {
                Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                return insets;
            });
        }

        ImageButton ibTranslate = findViewById(R.id.ib_translate);
        if (ibTranslate != null)
        {
            ibTranslate.setOnClickListener(v ->  {
                String currentLang = getResources().getConfiguration().locale.getLanguage();
                if(currentLang.equals("vi")){
                    setlocale("en");
                }
                else{
                    setlocale("vi");
                }
            });
            }
        }

    private void setlocale(String langCode) {
        Locale locale = new Locale(langCode);
        locale.setDefault(locale);

        Resources resources = getResources();
        Configuration config = resources.getConfiguration();
        config.setLocale(locale);
        resources.updateConfiguration(config, resources.getDisplayMetrics());
        recreate();
    }
}
