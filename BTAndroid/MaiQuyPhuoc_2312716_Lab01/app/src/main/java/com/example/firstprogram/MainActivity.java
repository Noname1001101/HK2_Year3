package com.example.firstprogram;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import java.util.Random;



public class MainActivity extends AppCompatActivity {

    //Phép tính
    EditText txtX, txtY;
    TextView txtResult;
    Button btnPlus, btnMinus, btnMultiply, btnDivide, btnModulo;

    //Ảnh
    Button btnCamera;
    ImageView imgPhoto;

    //Random số và tung Xúc Xắc
    TextView txtNumber;
    Button btnRandom;

    ImageView imgDice;
    Button btnRoll;

    EditText edtPhone;
    Button btnCall, btnSms;


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

        initControl();
    }

    private void initControl() {

        //Phép tính
        txtX = findViewById(R.id.txtX);
        txtY = findViewById(R.id.txtY);
        txtResult = findViewById(R.id.txtResult);
        btnPlus = findViewById(R.id.btnPlus);
        btnMinus = findViewById(R.id.btnMinus);
        btnMultiply = findViewById(R.id.btnMultiply);
        btnDivide = findViewById(R.id.btnDivide);
        btnModulo = findViewById(R.id.btnModulo);

        //Ảnh
        imgPhoto = findViewById(R.id.imgPhoto);
        btnCamera = findViewById(R.id.btnCamera);

        //Random
        txtNumber = findViewById(R.id.txtNumber);
        btnRandom = findViewById(R.id.btnRandom);

        //Xúc Xắc
        imgDice = findViewById(R.id.imgDice);
        btnRoll = findViewById(R.id.btnRoll);

        //gọi & nhắn tin
        edtPhone = findViewById(R.id.edtPhone);
        btnCall = findViewById(R.id.btnCall);
        btnSms = findViewById(R.id.btnSms);


        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lấy số điện thoại từ ô nhập
                String phoneNo = edtPhone.getText().toString();

                // Nếu chưa nhập số thì không làm gì cả
                if (phoneNo.isEmpty()) return;

                // Tạo Intent để mở bàn phím quay số (ACTION_DIAL an toàn hơn ACTION_CALL)
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(android.net.Uri.parse("tel:" + phoneNo));
                startActivity(intent);
            }
        });


        // 3. Xử lý nút NHẮN TIN (Code đã sửa lỗi)
        btnSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNo = edtPhone.getText().toString();

                // Kiểm tra xem đã nhập số chưa
                if (phoneNo.isEmpty()) {
                    android.widget.Toast.makeText(MainActivity.this, "Vui lòng nhập số Điện thoại!", android.widget.Toast.LENGTH_SHORT).show();
                    return;
                }

                try {
                    // Cách 1: Dùng Intent gửi tin nhắn (Khuyên dùng)
                    Intent intent = new Intent(Intent.ACTION_SENDTO);
                    intent.setData(android.net.Uri.parse("smsto:" + phoneNo));
                    intent.putExtra("sms_body", "Xin chào, đây là tin nhắn thử nghiệm.");
                    startActivity(intent);

                } catch (Exception e) {
                    // Nếu máy ảo không có app nhắn tin thì sẽ hiện lỗi này
                    android.widget.Toast.makeText(MainActivity.this, "Lỗi: Không tìm thấy ứng dụng nhắn tin", android.widget.Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        });


        //Random
        btnRandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random random = new Random();
                int number = random.nextInt(100);
                txtNumber.setText(String.valueOf(number));
            }
        });

        //Xúc xắc
        btnRoll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Random random = new Random();
              int val = random.nextInt(6) + 1;
              int resImage = R.drawable.dice1;
              switch (val) {
                  case 1:
                      resImage = R.drawable.dice1;
                      break;
                  case 2:
                      resImage = R.drawable.dice2;
                      break;
                  case 3:
                      resImage = R.drawable.dice3;
                      break;
                  case 4:
                      resImage = R.drawable.dice4;
                      break;
                  case 5:
                      resImage = R.drawable.dice5;
                      break;
                  case 6:
                      resImage = R.drawable.dice6;
                      break;

              }
              imgDice.setImageResource(resImage);
            }
        });

        //Ảnh
        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 100);
            }
        });

        //Phép tính
        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    int x = Integer.parseInt(txtX.getText().toString());
                    int y = Integer.parseInt(txtY.getText().toString());
                    int result = x + y;
                    txtResult.setText(String.valueOf(result));
            }
        });

        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    int x = Integer.parseInt(txtX.getText().toString());
                    int y = Integer.parseInt(txtY.getText().toString());
                    int result = x - y;
                    txtResult.setText(String.valueOf(result));
            }
        });

        btnMultiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    int x = Integer.parseInt(txtX.getText().toString());
                    int y = Integer.parseInt(txtY.getText().toString());
                    int result = x * y;
                    txtResult.setText(String.valueOf(result));
            }
        });

        btnDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    int x = Integer.parseInt(txtX.getText().toString());
                    int y = Integer.parseInt(txtY.getText().toString());
                    if (y == 0) {
                        txtResult.setText("Không thể chia cho 0");
                        return;
                    }
                    int result = x / y;
                    txtResult.setText(String.valueOf(result));
            }
        });

        btnModulo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    int x = Integer.parseInt(txtX.getText().toString());
                    int y = Integer.parseInt(txtY.getText().toString());
                    int result = x % y;
                    txtResult.setText(String.valueOf(result));
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        if (requestCode == 100 && resultCode == RESULT_OK && data != null){
            Bitmap bitmap = (Bitmap)data.getExtras().get("data");
            imgPhoto.setImageBitmap(bitmap);
        }
        super.onActivityResult(requestCode,resultCode,data);
    }

}