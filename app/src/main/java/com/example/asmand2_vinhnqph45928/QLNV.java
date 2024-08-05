package com.example.asmand2_vinhnqph45928;

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

import java.util.ArrayList;

public class QLNV extends Fragment {

    RecyclerView rc_nv;
    ArrayList<CongViecDTO> listCV;
    CongViecDAO congviecDAO;
    CongViecAdapter CVAdapter;
    ImageView Themnv;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.layout_nhanvien,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rc_nv=view.findViewById(R.id.rc_nhanvien);
        Themnv=view.findViewById(R.id.imgThemNV);
        congviecDAO=new CongViecDAO(getContext());


        listCV=congviecDAO.getDS();

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        rc_nv.setLayoutManager(linearLayoutManager);
        CVAdapter=new CongViecAdapter(getContext(),listCV,congviecDAO);
        rc_nv.setAdapter(CVAdapter);
        CVAdapter.notifyDataSetChanged();
        Themnv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                OpenDiaLog();

            }
        });

}
public void loadDATA(){
    listCV=congviecDAO.getDS();
    LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
    rc_nv.setLayoutManager(linearLayoutManager);
    CVAdapter=new CongViecAdapter(getContext(),listCV,congviecDAO);
    rc_nv.setAdapter(CVAdapter);
}
    public void OpenDiaLog(){
        AlertDialog.Builder builder1=new AlertDialog.Builder(requireContext());
        View view1=getLayoutInflater().inflate(R.layout.diaglog_add,null);
        builder1.setView(view1);
        Dialog Dialog1=builder1.create();
        Dialog1.show();
        Button Thoat=view1.findViewById(R.id.btnThoat);
        Button Them=view1.findViewById(R.id.btnADDNV);
        EditText Ma=view1.findViewById(R.id.edtMaNV);
        EditText Ten=view1.findViewById(R.id.edtTenNV);
        EditText ND=view1.findViewById(R.id.edtContent);
        EditText Trangthai=view1.findViewById(R.id.edtStatus);
        EditText Batdau=view1.findViewById(R.id.edtStart);
        EditText KetThuc=view1.findViewById(R.id.edtEnd);


        Them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String MATHEM=Ma.getText().toString();
                String TENTHEM=Ten.getText().toString();
                String NDTHEM=ND.getText().toString();
                String TTTHEM=Trangthai.getText().toString();
                String batdau=Batdau.getText().toString();
                String ketthuc=KetThuc.getText().toString();

                String TTmoi="mới tạo";
                if(TTTHEM.equals("0")){
                     TTmoi="mới tạo";
                }else if(TTTHEM.equals("1")){
                    TTmoi="đang làm";
                }else if(TTTHEM.equals("2")){
                    TTmoi="hoàn thành";
                }else{
                    TTmoi="Cho vào thùng rác";
                }
                if(MATHEM.length()==0||TENTHEM.length()==0||NDTHEM.length()==0||TTTHEM.length()==0||batdau.length()==0||ketthuc.length()==0){
                    Toast.makeText(getContext(), "Nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }else{
                    CongViecDTO cv=new CongViecDTO(MATHEM,TENTHEM,NDTHEM,TTmoi,batdau,ketthuc);
                    boolean check=congviecDAO.themCV(cv);
                    if(check){
                        Toast.makeText(getContext(), "Thêm nhân viên thành công", Toast.LENGTH_SHORT).show();
                        loadDATA();
                        Dialog1.dismiss();
                    }else{
                        Toast.makeText(getContext(), "Thêm nhân viên thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
                CVAdapter.notifyDataSetChanged();

            }
        });
        Thoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog1.dismiss();
            }
        });

    }





}

