package com.example.account;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import androidx.annotation.Nullable;


public class M000LoginFragment extends Fragment {
    SQLiteHelper helper;
    EditText edtEmail, edtPass;
    TextView btnLogin, tvRegister;
    private Context mContext;

    public M000LoginFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.m000_frg_login, container, false);

        mContext = getContext();
        helper = new SQLiteHelper(mContext);
        helper.openDB();
        helper.createTable();


        // Khởi tạo views
        edtEmail = view.findViewById(R.id.edt_email);
        edtPass = view.findViewById(R.id.edt_pass);
        btnLogin = view.findViewById(R.id.tv_login);
        tvRegister = view.findViewById(R.id.tv_register);

        // Xử lý sự kiện login
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edtEmail.getText().toString().trim();
                String password = edtPass.getText().toString().trim();
                login(email, password);
            }
        });

        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getParentFragmentManager().beginTransaction()
                        .replace(R.id.ln_main, new M001RegisterFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });
        return view;
    }

    private void login(String mail, String pass) {

        if (mail.isEmpty() || pass.isEmpty()) {
            Toast.makeText(mContext, "Empty value", Toast.LENGTH_SHORT).show();
            return;
        }
        SharedPreferences pref = mContext.getSharedPreferences(MainActivity.SAVE_PREF, Context.MODE_PRIVATE);
        String savedPass = pref.getString(mail, null);

        if (savedPass == null)
        {
            Toast.makeText(mContext,"Email is not existed!",Toast.LENGTH_SHORT).show();
            return;
        }

        if (!pass.equals(savedPass))
        {
            Toast.makeText(mContext,"Password is not correct!",Toast.LENGTH_SHORT).show();
            return;
        }

        if (helper.login(mail,pass)) {
            Toast.makeText(mContext, "Login account successfully!", Toast.LENGTH_SHORT).show();
            return;
        }
        else {
            Toast.makeText(mContext, "Wrong email or password", Toast.LENGTH_SHORT).show();
        }
    }

}
