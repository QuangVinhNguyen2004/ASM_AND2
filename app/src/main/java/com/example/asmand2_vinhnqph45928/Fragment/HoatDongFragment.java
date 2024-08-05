package com.example.asmand2_vinhnqph45928.Fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asmand2_vinhnqph45928.Adapter.HoatDongAdapter;
import com.example.asmand2_vinhnqph45928.DAO.HDDAO;
import com.example.asmand2_vinhnqph45928.DTO.HoatDongDTO;
import com.example.asmand2_vinhnqph45928.R;

import java.util.ArrayList;

public class HoatDongFragment extends Fragment {
    ArrayList<HoatDongDTO> list;
    RecyclerView rchd;
    HDDAO hddao;
    HoatDongAdapter hdAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_hoatdong,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rchd=view.findViewById(R.id.rctv);
        ImageView them=view.findViewById(R.id.imgThemTV);

        hddao=new HDDAO(getContext());
        list=hddao.getDS();

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        rchd.setLayoutManager(linearLayoutManager);
        hdAdapter=new HoatDongAdapter(getContext(),list,hddao);
        rchd.setAdapter(hdAdapter);
        them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogTTV();
            }
        });
    }
    public void loadtv(){
        list=hddao.getDS();

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        rchd.setLayoutManager(linearLayoutManager);
        hdAdapter=new HoatDongAdapter(getContext(),list,hddao);
        rchd.setAdapter(hdAdapter);
    }
    public void DialogTTV(){
        AlertDialog.Builder builder=new AlertDialog.Builder(requireContext());
        View view=getLayoutInflater().inflate(R.layout.them_hoatdong,null);
        builder.setView(view);
        Dialog dialog=builder.create();
        dialog.show();

        EditText Ma=view.findViewById(R.id.edtMaHD);
        EditText ngayghi=view.findViewById(R.id.edtdate);
        EditText ghichu=view.findViewById(R.id.edtghichu);
        Button them=view.findViewById(R.id.btnADDTV);
        Button thoat=view.findViewById(R.id.btnThoat);
        them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ma=Ma.getText().toString();
                String ngay=ngayghi.getText().toString();
                String ghi=ghichu.getText().toString();

                if(ma.length()==0||ngay.length()==0||ghi.length()==0){
                    Toast.makeText(getContext(), "Không được để trống", Toast.LENGTH_SHORT).show();
                }else{
                    HoatDongDTO hd1=new HoatDongDTO(ma,ngay,ghi);
                    boolean check=hddao.themTV(hd1);
                    if(check){
                        Toast.makeText(getContext(), "Thêm hoạt động thành công", Toast.LENGTH_SHORT).show();
                        loadtv();
                        dialog.dismiss();
                    }else{
                        Toast.makeText(getContext(), "Thêm hoạt động thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        thoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }
}
