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

import com.example.asmand2_vinhnqph45928.Adapter.SachAdapter;
import com.example.asmand2_vinhnqph45928.DAO.SachDAO;
import com.example.asmand2_vinhnqph45928.R;

import java.util.ArrayList;

public class VanDongFragment extends Fragment {
    ArrayList<SachDTO> list;
    RecyclerView rcSach;
    SachDAO sachDAO;
    SachAdapter sachAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_vandong,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rcSach=view.findViewById(R.id.rcsach);
        ImageView hinhthem=view.findViewById(R.id.imgThemSach);

        sachDAO=new SachDAO(getContext());
        list=sachDAO.getDS();

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        rcSach.setLayoutManager(linearLayoutManager);
        sachAdapter =new SachAdapter(getContext(),list,sachDAO);
        rcSach.setAdapter(sachAdapter);
        hinhthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            DialogTA();
            }
        });
    }
    public void load(){
        list=sachDAO.getDS();

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        rcSach.setLayoutManager(linearLayoutManager);
        sachAdapter =new SachAdapter(getContext(),list,sachDAO);
        rcSach.setAdapter(sachAdapter);
    }
    public void DialogTA(){
        AlertDialog.Builder builder=new AlertDialog.Builder(requireContext());
        View view=getLayoutInflater().inflate(R.layout.them_vandong,null);
        builder.setView(view);
        Dialog dialog=builder.create();
        dialog.show();

        EditText Ma=view.findViewById(R.id.edtMaSach);
        EditText Ten=view.findViewById(R.id.edtTenSach);
        EditText Gia=view.findViewById(R.id.edtGia);
        EditText Maloai=view.findViewById(R.id.edtMaloai);
        Button them=view.findViewById(R.id.btnADDSACH);
        Button thoat=view.findViewById(R.id.btnThoat);
        them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String masach=Ma.getText().toString();
                String tensach=Ten.getText().toString();
                Integer gia= Integer.valueOf(Gia.getText().toString());
                String maloai=Maloai.getText().toString();
                if(maloai.length()==0||masach.length()==0||tensach.length()==0||Gia.getText().toString().length()==0){
                    Toast.makeText(getContext(), "Không được để trống", Toast.LENGTH_SHORT).show();
                }else{
                    SachDTO sachDTO=new SachDTO(masach,tensach,gia,maloai);
                    boolean check=sachDAO.themsach(sachDTO);
                    if(check){
                        Toast.makeText(getContext(), "Thêm sách thành công", Toast.LENGTH_SHORT).show();
                        load();
                        dialog.dismiss();
                    }else{
                        Toast.makeText(getContext(), "Thêm sách thất bại", Toast.LENGTH_SHORT).show();
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
