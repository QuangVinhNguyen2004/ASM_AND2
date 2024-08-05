package com.example.asmand2_vinhnqph45928.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asmand2_vinhnqph45928.Adapter.ThongTinAdapter;
import com.example.asmand2_vinhnqph45928.DAO.ThongTinDAO;
import com.example.asmand2_vinhnqph45928.DTO.ThongTinDTO;
import com.example.asmand2_vinhnqph45928.R;

import java.util.ArrayList;

public class ThongTinFragment extends Fragment {
    ArrayList<ThongTinDTO> list;
    RecyclerView rctt;
    ThongTinDAO ttDAO;
    ThongTinAdapter ttAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_thongtin,container,false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rctt=view.findViewById(R.id.rvtt);


        ttDAO =new ThongTinDAO(getContext());
        list=ttDAO.getDS();

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        rctt.setLayoutManager(linearLayoutManager);
        ttAdapter=new ThongTinAdapter(getContext(),list,ttDAO);
        rctt.setAdapter(ttAdapter);
        ttAdapter.notifyDataSetChanged();

    }
    public void loaddata(){
        list.clear();
        list=ttDAO.getDS();
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        rctt.setLayoutManager(linearLayoutManager);
        ttAdapter=new ThongTinAdapter(getContext(),list,ttDAO);
        rctt.setAdapter(ttAdapter);
    }

}
