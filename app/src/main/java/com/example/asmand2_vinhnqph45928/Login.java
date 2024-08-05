package com.example.asmand2_vinhnqph45928;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asmand2_vinhnqph45928.DAO.NguoiDungDAO;
import com.example.asmand2_vinhnqph45928.DTO.NguoiDungDTO;

public class Login extends AppCompatActivity {
private NguoiDungDAO nguoiDungDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        EditText edtUser=findViewById(R.id.edtUser);
        EditText edtPass=findViewById(R.id.edtPass);
        Button btnLogin=findViewById(R.id.btnLogin);
        TextView txtForgot=findViewById(R.id.txtForgot);
        TextView txtSignUp=findViewById(R.id.txtSignUp);
        nguoiDungDAO=new NguoiDungDAO(this);
        txtForgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, QuenMatKhau.class);
                startActivity(intent);
            }
        });
        txtSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, SignUp.class);
                startActivity(intent);
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user=edtUser.getText().toString();
                String pass=edtPass.getText().toString();
                boolean check=nguoiDungDAO.CheckLogin(user,pass);
                if(check){
                    Toast.makeText(Login.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Login.this, MainActivity.class));
                }else{
                    Toast.makeText(Login.this, "Đăng nhập thất bại.Vui lòng kiểm tra lại!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}