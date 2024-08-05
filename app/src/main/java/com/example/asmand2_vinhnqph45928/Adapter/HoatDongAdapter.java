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

import com.example.asmand2_vinhnqph45928.DAO.HDDAO;
import com.example.asmand2_vinhnqph45928.DTO.HoatDongDTO;
import com.example.asmand2_vinhnqph45928.R;

import java.util.ArrayList;

public class HoatDongAdapter extends RecyclerView.Adapter<HoatDongAdapter.ViewHoler>{
    private Context context;
    private ArrayList<HoatDongDTO> list;

    private HDDAO hddao;

    public HoatDongAdapter(Context context, ArrayList<HoatDongDTO> list, HDDAO hddao) {
        this.context = context;
        this.list = list;
        this.hddao = hddao;
    }

    @NonNull
    @Override
    public ViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=((Activity)context).getLayoutInflater();
        View view=inflater.inflate(R.layout.item_hoatdong,parent,false);
        return new ViewHoler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoler holder, int position) {
        holder.ma.setText(list.get(position).getMahd());
        holder.ngay.setText(list.get(position).getNgayghi());
        holder.ghichu.setText(list.get(position).getGhichu());

        holder.hinhxoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean check=hddao.XoaTV(list.get(holder.getAdapterPosition()).getMahd());
                if(check){
                    Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                    list.clear();
                    list=hddao.getDS();
                    notifyDataSetChanged();
                }else{
                    Toast.makeText(context, "Xóa thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });
        holder.hinhsua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UpdateDiaLog(list.get(holder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHoler extends RecyclerView.ViewHolder{
        TextView ma,ngay,ghichu;
        ImageView hinhxoa,hinhsua;
        public ViewHoler(@NonNull View itemView) {
            super(itemView);
            ma=itemView.findViewById(R.id.txtMaHD);
            ngay=itemView.findViewById(R.id.txtdate);
            ghichu=itemView.findViewById(R.id.txtGhiChu);


            hinhsua=itemView.findViewById(R.id.hinhsua);
            hinhxoa=itemView.findViewById(R.id.hinhdelete);

        }
    }
    public void UpdateDiaLog(HoatDongDTO hd){
        AlertDialog.Builder builder1=new AlertDialog.Builder(context);
        LayoutInflater inflater=((Activity)context).getLayoutInflater();
        View view1=inflater.inflate(R.layout.update_hoatdong,null);
        builder1.setView(view1);
        Dialog Dialog1=builder1.create();
        Dialog1.show();
        Button Thoat=view1.findViewById(R.id.btnThoat);
        Button Sua=view1.findViewById(R.id.btnUPTV);
        EditText date=view1.findViewById(R.id.edtdate);
        EditText ghichu=view1.findViewById(R.id.edtGhiChu);



        date.setText(hd.getNgayghi().toString());
        ghichu.setText(hd.getGhichu().toString());






        Sua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ngaymoi=date.getText().toString();
                String ghichumoi= ghichu.getText().toString();


                if(ngaymoi.length()==0||ghichumoi.length()==0){
                    Toast.makeText(context, "Nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }else{
                    HoatDongDTO hd1=new HoatDongDTO(hd.getMahd(),ngaymoi,ghichumoi);
                    boolean check=hddao.UpdateTV(hd1);

                    if(check){
                        Toast.makeText(context, "Cập nhật hoạt động thành công", Toast.LENGTH_SHORT).show();
                        list.clear();
                        list=hddao.getDS();
                        notifyDataSetChanged();
                        Dialog1.dismiss();
                    }else{
                        Toast.makeText(context, "Cập nhật hoạt động thất bại", Toast.LENGTH_SHORT).show();
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
