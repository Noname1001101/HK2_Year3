package com.example.account;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
public class M001RegisterFragment extends Fragment {

    SQLiteHelper helper;
    EditText edtEmail, edtPass, edtRePass;
    TextView tvRegister;
    ImageView ivBack;

    private Context mContext;
    public M001RegisterFragment()
    {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.m001_frg_register, container, false);

        mContext = getContext();
        helper = new SQLiteHelper(mContext);
        helper.openDB();
        helper.createTable();

        // Khởi tạo views
        edtEmail = view.findViewById(R.id.edt_email);
        edtPass = view.findViewById(R.id.edt_pass);
        edtRePass = view.findViewById(R.id.edt_re_pass);
        tvRegister = view.findViewById(R.id.tv_register);
        ivBack = view.findViewById(R.id.iv_back);

        // Xử lý sự kiện đăng ký
        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edtEmail.getText().toString().trim();
                String password = edtPass.getText().toString().trim();
                String rePassword = edtRePass.getText().toString().trim();
                register(email, password, rePassword);
            }
        });

        // Xử lý sự kiện quay lại màn hình login
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getParentFragmentManager().popBackStack();
            }
        });

        return view;
    }

    private void register(String mail, String pass,String repass){

        if (mail.isEmpty() || pass.isEmpty() || repass.isEmpty())
       {
           Toast.makeText(mContext,"Empty value",Toast.LENGTH_SHORT).show();
           return;

       }
       if (!pass.equals(repass)){
            Toast.makeText(mContext, "Password not match", Toast.LENGTH_SHORT).show();
            return;
        }

        // Kiểm tra email đã tồn tại chưa
        SharedPreferences pref = mContext.getSharedPreferences(MainActivity.SAVE_PREF,Context.MODE_PRIVATE);
        String savedPass = pref.getString(mail,null);

        // Lưu vào database
        Account acc = new Account(mail,pass);
        helper.insert(acc);

        // Lưu vào SharedPreferences
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(mail, pass);
        editor.apply();

        Toast.makeText(mContext, "Register successfully!", Toast.LENGTH_SHORT).show();

        // Quay lại màn hình login
        getParentFragmentManager().popBackStack();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (helper != null) {
            helper.closeDB();
        }
    }
}
