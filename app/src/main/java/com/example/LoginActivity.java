package com.example;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.Dao.NguoiDungDao;
import com.example.asm_cuatoi.R;
import com.example.util.SendMail;

public class LoginActivity extends AppCompatActivity {

    private NguoiDungDao nguoiDungDao;
    private SendMail sendMail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText edtUser = findViewById(R.id.edtUser);
        EditText edtPass = findViewById(R.id.edtPass);
        Button btnLogin = findViewById(R.id.btnLogin);
        TextView txtForgot = findViewById(R.id.txtForgot);
        TextView txtSignup = findViewById(R.id.txtSignup);

        nguoiDungDao = new NguoiDungDao(this);
        sendMail = new SendMail();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = edtUser.getText().toString();
                String pass = edtPass.getText().toString();
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                boolean check = nguoiDungDao.checkLogin(user, pass);

                if (check){
                    Toast.makeText(LoginActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();

                }else {
                    Toast.makeText(LoginActivity.this, "Đăng nhập thất bại, vui lòng kiểm tra lại!!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        txtSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

        txtForgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 showDialogForgot();
            }
        });
    }

    private void showDialogForgot(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_forgot, null);
        builder.setView(view);

        AlertDialog alertDialog = builder.create();
        alertDialog.setCancelable(false);
        alertDialog.show();

        EditText edtEmail = view.findViewById(R.id.edtEmail);
        Button btnSend = view.findViewById(R.id.btnSend);
        Button btnCancel = view.findViewById(R.id.btnCancel);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = edtEmail.getText().toString();
                String matkhau = nguoiDungDao.ForgotPassword(email);
//                Toast.makeText(LoginActivity.this, matkhau, Toast.LENGTH_SHORT).show();
                if (matkhau.equals("")){
                    Toast.makeText(LoginActivity.this, "Không tìm thấy tài khoản của bạn!", Toast.LENGTH_SHORT).show();
                }else {
                    sendMail.Send(LoginActivity.this, email, "Lấy Lại Mật Khẩu", "Mật khẩu của bạn là: "+matkhau);
                    alertDialog.dismiss();
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });

    }
}