package com.example.asmand2_vinhnqph45928;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.asmand2_vinhnqph45928.Adapter.ThongTinAdapter;
import com.example.asmand2_vinhnqph45928.DAO.ThongTinDAO;
import com.example.asmand2_vinhnqph45928.DTO.ThongTinDTO;

import java.util.ArrayList;

public class thongtin extends AppCompatActivity {
private ThongTinDAO thongTinDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loaisach);
        RecyclerView rctt=findViewById(R.id.rvthongtin);


        thongTinDAO =new ThongTinDAO(this);
        ArrayList<ThongTinDTO> list=thongTinDAO.getDS();

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        rctt.setLayoutManager(linearLayoutManager);
        ThongTinAdapter loaiAdapter=new ThongTinAdapter(this,list,thongTinDAO);
        rctt.setAdapter(loaiAdapter);
    }
}