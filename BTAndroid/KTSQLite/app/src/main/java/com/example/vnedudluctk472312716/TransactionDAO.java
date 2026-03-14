package com.example.vnedudluctk472312716;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;

public class TransactionDAO {
    SQLiteDatabase db;

    public TransactionDAO(Context context) {
        DatabaseHelper dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    // Lấy danh sách tài khoản
    public ArrayList<String> getAllAccounts() {
        ArrayList<String> list = new ArrayList<>();
        Cursor c = db.rawQuery("SELECT * FROM Account", null);
        while (c.moveToNext()) {
            // Lấy tên và số dư ghép thành chuỗi để hiển thị cho dễ
            list.add(c.getString(1) + ": " + c.getDouble(2) + "đ");
        }
        c.close();
        return list;
    }

    // Lấy danh sách danh mục
    public ArrayList<String> getAllCategories() {
        ArrayList<String> list = new ArrayList<>();
        Cursor c = db.rawQuery("SELECT * FROM Category", null);
        while (c.moveToNext()) {
            list.add(c.getString(1)); // Chỉ lấy tên danh mục
        }
        c.close();
        return list;
    }

    // Thêm giao dịch mới (Viết cực kỳ cơ bản)
    public boolean insertTransaction(double amount, String date, int catId, int accId) {
        ContentValues v = new ContentValues();
        v.put("amount", amount);
        v.put("date", date);
        v.put("cat_id", catId);
        v.put("acc_id", accId);
        long r = db.insert("TransactionTable", null, v);
        return r != -1;
    }

    // Lấy lịch sử giao dịch dạng chuỗi để hiện lên ListView luôn
    public ArrayList<String> getTransactionHistory() {
        ArrayList<String> list = new ArrayList<>();
        String sql = "SELECT t.amount, t.date, c.name FROM TransactionTable t, Category c WHERE t.cat_id = c.cat_id";
        Cursor c = db.rawQuery(sql, null);
        while (c.moveToNext()) {
            list.add(c.getString(1) + " | " + c.getString(2) + " : " + c.getDouble(0) + "đ");
        }
        c.close();
        return list;
    }
}
