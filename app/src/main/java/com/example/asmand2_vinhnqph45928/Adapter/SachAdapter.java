package com.example.asmand2_vinhnqph45928.Adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asmand2_vinhnqph45928.DAO.SachDAO;
import com.example.asmand2_vinhnqph45928.R;

import java.util.ArrayList;

public class SachAdapter extends RecyclerView.Adapter<SachAdapter.ViewHoler>{
    private Context context;
    private ArrayList<SachDTO> list;

    private SachDAO sachDAO;

    public SachAdapter(Context context, ArrayList<SachDTO> list, SachDAO sachDAO) {
        this.context = context;
        this.list = list;
        this.sachDAO = sachDAO;
    }

    @NonNull
    @Override
    public SachAdapter.ViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=((Activity)context).getLayoutInflater();
        View view=inflater.inflate(R.layout.item_vandong,parent,false);
        return new ViewHoler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SachAdapter.ViewHoler holder, int position) {
    holder.masach.setText(list.get(position).getMaSach());
    holder.tensach.setText(list.get(position).getTenSach());
    holder.gia.setText(String.valueOf(list.get(position).getGia()));
    holder.tenloai.setText(list.get(position).getTenloai());
    holder.hinhsua.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
          UpdateDiaLog(list.get(holder.getAdapterPosition()));
        }
    });
    holder.hinhxoa.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            boolean check =sachDAO.XoaSach(list.get(holder.getAdapterPosition()).getMaSach());
            if(check){
                Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                list.clear();
                list=sachDAO.getDS();
                notifyDataSetChanged();
            }else{
                Toast.makeText(context, "Xóa thất bại", Toast.LENGTH_SHORT).show();
            }
        }
    });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHoler extends RecyclerView.ViewHolder{
        TextView masach,tensach,gia,tenloai;
        ImageView hinhxoa,hinhsua;
        public ViewHoler(@NonNull View itemView) {
            super(itemView);
            masach=itemView.findViewById(R.id.txtMaSach);
            tensach=itemView.findViewById(R.id.txtTenSach);
            gia=itemView.findViewById(R.id.txtGia);
            tenloai=itemView.findViewById(R.id.txtLoai);

            hinhsua=itemView.findViewById(R.id.hinhsua);
            hinhxoa=itemView.findViewById(R.id.hinhdelete);

        }
    }
    public void UpdateDiaLog(SachDTO sa){
        AlertDialog.Builder builder1=new AlertDialog.Builder(context);
        LayoutInflater inflater=((Activity)context).getLayoutInflater();
        View view1=inflater.inflate(R.layout.update_vandong,null);
        builder1.setView(view1);
        Dialog Dialog1=builder1.create();
        Dialog1.show();
        Button Thoat=view1.findViewById(R.id.btnThoat);
        Button Them=view1.findViewById(R.id.btnUPS);
        EditText ten=view1.findViewById(R.id.edtTenSach);
        EditText gia=view1.findViewById(R.id.edtGia);
        EditText maloai=view1.findViewById(R.id.edtMaLoaiup);


        ten.setText(sa.getTenSach().toString());
        gia.setText(String.valueOf(sa.getGia().toString()));




        Them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tenmoi=ten.getText().toString();
                Integer giamoi= Integer.valueOf(gia.getText().toString());
                String loaimoi=maloai.getText().toString();

                if(tenmoi.length()==0||gia.getText().toString().length()==0||loaimoi.length()==0){
                    Toast.makeText(context, "Nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }else{
                    SachDTO sa1=new SachDTO(sa.getMaSach().toString(),tenmoi,giamoi,loaimoi);
                    boolean check=sachDAO.UpdateSach(sa1);

                    if(check){
                        Toast.makeText(context, "Cập nhật sách thành công", Toast.LENGTH_SHORT).show();
                        list.clear();
                        list=sachDAO.getDS();
                        notifyDataSetChanged();
                        Dialog1.dismiss();
                    }else{
                        Toast.makeText(context, "Cập nhật sách thất bại", Toast.LENGTH_SHORT).show();
                    }
                }


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


