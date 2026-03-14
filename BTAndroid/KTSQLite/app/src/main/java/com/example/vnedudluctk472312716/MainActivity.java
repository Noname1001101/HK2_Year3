package com.example.vnedudluctk472312716;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText etTien, etNgay;
    Spinner spTaiKhoan, spDanhMuc;
    TextView tvTienMat, tvNganHang;
    ListView lvLichSu;
    TransactionDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Ánh xạ
        etTien = findViewById(R.id.et_amount);
        etNgay = findViewById(R.id.et_date);
        spTaiKhoan = findViewById(R.id.spinner_account);
        spDanhMuc = findViewById(R.id.spinner_category);
        tvTienMat = findViewById(R.id.tv_cash_balance);
        tvNganHang = findViewById(R.id.tv_bank_balance);
        lvLichSu = findViewById(R.id.lv_history);
        
        dao = new TransactionDAO(this);

        hienThiDuLieu();

        findViewById(R.id.btn_save).setOnClickListener(v -> {
            String tien = etTien.getText().toString();
            String ngay = etNgay.getText().toString();
            
            // Lấy vị trí chọn trong Spinner (acc_id và cat_id tính từ 1)
            int idAcc = spTaiKhoan.getSelectedItemPosition() + 1;
            int idCat = spDanhMuc.getSelectedItemPosition() + 1;

            if(!tien.isEmpty()) {
                if(dao.insertTransaction(Double.parseDouble(tien), ngay, idCat, idAcc)) {
                    Toast.makeText(this, "Đã lưu!", Toast.LENGTH_SHORT).show();
                    hienThiDuLieu(); // Cập nhật lại danh sách
                }
            }
        });
    }

    void hienThiDuLieu() {
        // Hiện số dư
        ArrayList<String> accounts = dao.getAllAccounts();
        tvTienMat.setText(accounts.get(0));
        tvNganHang.setText(accounts.get(1));

        // Hiện Spinner Danh mục
        ArrayList<String> cats = dao.getAllCategories();
        spDanhMuc.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, cats));

        // Hiện Spinner Tài khoản (chỉ tên)
        ArrayList<String> accNames = new ArrayList<>();
        accNames.add("Tiền mặt"); accNames.add("Ngân hàng");
        spTaiKhoan.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, accNames));

        // Hiện lịch sử giao dịch lên ListView
        ArrayList<String> history = dao.getTransactionHistory();
        lvLichSu.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, history));
    }
}
