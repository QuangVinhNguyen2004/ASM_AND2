package com.example.asmand2_vinhnqph45928;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.asmand2_vinhnqph45928.DAO.NguoiDungDAO;

public class SignUp extends AppCompatActivity {
private NguoiDungDAO nguoiDungDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        nguoiDungDAO = new NguoiDungDAO(this); // Khởi tạo đối tượng NguoiDungDAO
        EditText ten=findViewById(R.id.edtUserup);
        EditText mk=findViewById(R.id.edtPassup);
        EditText formk=findViewById(R.id.edtforup);
        EditText name=findViewById(R.id.edtName);
        EditText email=findViewById(R.id.edtEmail);
        Button signup=findViewById(R.id.btnSignUp);
        Button thoat=findViewById(R.id.btnThoatup);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tenDK=ten.getText().toString();
                String mkDK=mk.getText().toString();
                String forDK=formk.getText().toString();
                String fullname=name.getText().toString();
                String Email1=email.getText().toString();
                if(!mkDK.equals(forDK)){
                    Toast.makeText(SignUp.this, "Mật khẩu không khớp", Toast.LENGTH_SHORT).show();
                }else{
                    boolean check=nguoiDungDAO.Register(tenDK,mkDK,fullname,Email1);
                    if(check){
                        Toast.makeText(SignUp.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(SignUp.this, Login.class);
                        startActivity(intent);
                    }
                }
            }
        });
        thoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUp.this, Login.class);
                startActivity(intent);
            }
        });
    }
}