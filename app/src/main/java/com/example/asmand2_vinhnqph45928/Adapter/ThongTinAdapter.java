package com.example.asmand2_vinhnqph45928.Adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asmand2_vinhnqph45928.DAO.ThongTinDAO;
import com.example.asmand2_vinhnqph45928.DTO.ThongTinDTO;
import com.example.asmand2_vinhnqph45928.R;

import java.util.ArrayList;

public class ThongTinAdapter extends RecyclerView.Adapter<ThongTinAdapter.ViewHolder> {
    private Context context;
    private ArrayList<ThongTinDTO> list;

    private ThongTinDAO thongTinDAO;

    public ThongTinAdapter(Context context, ArrayList<ThongTinDTO> list, ThongTinDAO thongTinDAO) {
        this.context = context;
        this.list = list;
        this.thongTinDAO = thongTinDAO;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=((Activity)context).getLayoutInflater();
        View view=inflater.inflate(R.layout.item_thongtin,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    holder.txtGT.setText(list.get(position).getGioitinh());
    holder.txtCC.setText(list.get(position).getChieucao());
    holder.txtCN.setText(list.get(position).getCannang());

    holder.btnupdate.setOnClickListener(new View.OnClickListener() {
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

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtGT,txtCC,txtCN;
        Button btnupdate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtGT=itemView.findViewById(R.id.txtGT);
            txtCC=itemView.findViewById(R.id.txtCC);
            txtCN=itemView.findViewById(R.id.txtCN);

            btnupdate=itemView.findViewById(R.id.btnupdate);


        }
    }
    public void UpdateDiaLog(ThongTinDTO tt){
        AlertDialog.Builder builder1=new AlertDialog.Builder(context);
        LayoutInflater inflater=((Activity)context).getLayoutInflater();
        View view1=inflater.inflate(R.layout.update_thongtin,null);
        builder1.setView(view1);
        Dialog Dialog1=builder1.create();
        Dialog1.show();
        Button Thoat=view1.findViewById(R.id.btnThoat);
        Button Them=view1.findViewById(R.id.btnUPLS);
        EditText gt=view1.findViewById(R.id.edtGT);
        EditText cc=view1.findViewById(R.id.edtCC);
        EditText cn=view1.findViewById(R.id.edtCN);


        gt.setText(tt.getGioitinh().toString());
        cc.setText(String.valueOf(tt.getChieucao()));
        cn.setText(String.valueOf(tt.getCannang()));


        Them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String GT=gt.getText().toString();
                Integer CC= Integer.valueOf(cc.getText().toString());
                Integer CN= Integer.valueOf(cn.getText().toString());
                if(GT.length()==0||cc.getText().toString().length()==0||cn.getText().toString().length()==0){
                    Toast.makeText(context, "Nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }else{
                    ThongTinDTO tt1=new ThongTinDTO(tt.getId(),GT,CC,CN);
                    boolean check=thongTinDAO.UpdateLS(tt1);

                    if(check){
                        Toast.makeText(context, "Cập nhật thông tin thành công", Toast.LENGTH_SHORT).show();
                        list.clear();
                        list=thongTinDAO.getDS();
                        notifyDataSetChanged();
                        Dialog1.dismiss();
                    }else{
                        Toast.makeText(context, "Cập nhật thông tin thất bại", Toast.LENGTH_SHORT).show();
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
