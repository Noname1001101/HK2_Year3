package com.example.englishlearning;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class VocabularyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view_vocabulary);

        // 1. Lấy ListView từ giao diện
        ListView lvVocabulary = findViewById(R.id.lv_vocabulary);

        // 2. Nhận tên chủ đề được truyền từ MainActivity sang (để biết user chọn bài nào)
        Intent intent = getIntent();
        String topicName = intent.getStringExtra("TOPIC_NAME");

        TextView tvTitle = findViewById(R.id.tv_title);
        if (tvTitle != null) {
            tvTitle.setText(topicName); // Ghi đè chữ cũ bằng tên chủ đề mới
        }

        // 3. Tạo dữ liệu từ vựng giả lập (Dummy data)
        List<String> words = new ArrayList<>();

        if (topicName != null) {
            if (topicName.equals(getString(R.string.txt_ecentials))) {
                // Nếu là chủ đề Essentials
                words.add("1. Hello - Xin chào");
                words.add("2. Thank you - Cảm ơn");
                words.add("3. Sorry - Xin lỗi");
                words.add("4. Please - Vui lòng");

            } else if (topicName.equals(getString(R.string.txt_traveling))) {
                // Nếu là chủ đề While Traveling
                words.add("1. Ticket - Vé");
                words.add("2. Passport - Hộ chiếu");
                words.add("3. Luggage - Hành lý");
                words.add("4. Flight - Chuyến bay");

            } else if (topicName.equals(getString(R.string.txt_medical))) {
                // Nếu là chủ đề Help / Medical
                words.add("1. Doctor - Bác sĩ");
                words.add("2. Hospital - Bệnh viện");
                words.add("3. Medicine - Thuốc");

            }

            // 4. Đưa dữ liệu vào ListView thông qua ArrayAdapter
            ArrayAdapter<String> adapter = new ArrayAdapter<>(
                    this,
                    android.R.layout.simple_list_item_1, // Giao diện 1 dòng chữ mặc định của Android
                    words
            );
            lvVocabulary.setAdapter(adapter);
        }
    }
}