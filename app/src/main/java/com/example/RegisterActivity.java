package com.example;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.Dao.NguoiDungDao;

import com.example.asm_cuatoi.R;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        EditText edtUser = findViewById(R.id.edtUser);
        EditText edtPass = findViewById(R.id.edtPass);
        EditText edtRePass = findViewById(R.id.edtRePass);
        EditText edtFullname = findViewById(R.id.edtFullname);
        Button btnSignup = findViewById(R.id.btnSignup);
        Button btnGoBack = findViewById(R.id.btnGoBack);

        NguoiDungDao nguoiDungDao = new NguoiDungDao(this);

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = edtUser.getText().toString();
                String pass = edtPass.getText().toString();
                String repass = edtRePass.getText().toString();
                String fullname = edtFullname.getText().toString();

                if(!pass.equals(repass)){
                    Toast.makeText(RegisterActivity.this, "Nhập lại mật khẩu không khớp nhau. Kiểm tra lại!", Toast.LENGTH_SHORT).show();
                }else {
                    boolean check = nguoiDungDao.Register(user, pass, fullname);
                    if (check){
                        Toast.makeText(RegisterActivity.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                        finish();
                    }else {
                        Toast.makeText(RegisterActivity.this, "Đăng ký thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btnGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}