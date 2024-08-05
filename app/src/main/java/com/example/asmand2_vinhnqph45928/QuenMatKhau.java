package com.example.asmand2_vinhnqph45928;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.asmand2_vinhnqph45928.DAO.NguoiDungDAO;

public class QuenMatKhau extends AppCompatActivity {
    private NguoiDungDAO nguoiDungDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quen_mat_khau);

        nguoiDungDAO=new NguoiDungDAO(this);
        EditText name=findViewById(R.id.TenQuen);
        EditText emailDL=findViewById(R.id.emailquen);
        EditText mkmoi=findViewById(R.id.mkmoiquen);
        EditText mklaimoi=findViewById(R.id.mklaimoiquen);
        Button btnDL=findViewById(R.id.btnDatLai);
        btnDL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email=emailDL.getText().toString();
                String mk=mkmoi.getText().toString();
                String mklai=mklaimoi.getText().toString();
                boolean check=nguoiDungDAO.CheckEmail(email,mk);

                if(check&&mk.equals(mklai)){
                    Toast.makeText(QuenMatKhau.this, "Đặt lại thành công", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(QuenMatKhau.this, Login.class);
                    startActivity(intent);

                }else if(!mk.equals(mklai)){
                    Toast.makeText(QuenMatKhau.this, "Nhập lại mật khẩu không khớp!", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(QuenMatKhau.this, "Email hoặc mật khẩu không khớp.Vui lòng kiểm tra lại!", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}