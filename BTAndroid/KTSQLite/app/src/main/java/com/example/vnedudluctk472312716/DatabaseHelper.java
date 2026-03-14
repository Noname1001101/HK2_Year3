package com.example.vnedudluctk472312716;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(Context context) {
        super(context, "QuanLyTaiChinh.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Tạo bảng Danh mục
        db.execSQL("CREATE TABLE Category (cat_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, type INTEGER)");
        // Tạo bảng Tài khoản
        db.execSQL("CREATE TABLE Account (acc_id INTEGER PRIMARY KEY AUTOINCREMENT, acc_name TEXT, balance REAL)");
        // Tạo bảng Giao dịch
        db.execSQL("CREATE TABLE TransactionTable (trans_id INTEGER PRIMARY KEY AUTOINCREMENT, amount REAL, date TEXT, cat_id INTEGER, acc_id INTEGER)");

        // Chèn dữ liệu mẫu (5 danh mục)
        db.execSQL("INSERT INTO Category (name, type) VALUES ('Lương', 1), ('Thưởng', 1), ('Ăn uống', 0), ('Tiền nhà', 0), ('Di chuyển', 0)");
        
        // Chèn 2 tài khoản mặc định
        db.execSQL("INSERT INTO Account (acc_name, balance) VALUES ('Tiền mặt', 5000000), ('Ngân hàng', 10000000)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS TransactionTable");
        db.execSQL("DROP TABLE IF EXISTS Category");
        db.execSQL("DROP TABLE IF EXISTS Account");
        onCreate(db);
    }
}
