package com.example.asmand2_vinhnqph45928.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.asmand2_vinhnqph45928.DTO.ThongTinDTO;
import com.example.asmand2_vinhnqph45928.DbHelper.MyDbHelper;

import java.util.ArrayList;

public class ThongTinDAO {
    private MyDbHelper dbHelper;
    public ThongTinDAO(Context context){
        dbHelper=new MyDbHelper(context);
    }
    public ArrayList<ThongTinDTO> getDS(){
        ArrayList<ThongTinDTO> list=new ArrayList<>();
        SQLiteDatabase sqLiteDatabase=dbHelper.getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT * FROM THONGTIN",null);
        if(cursor.getCount()>0){
            cursor.moveToFirst();
            do{
                list.add(new ThongTinDTO(cursor.getString(0),cursor.getString(1),cursor.getInt(2),cursor.getInt(3) ));

            }while (cursor.moveToNext());
        }
        return list;
    }


    public  boolean UpdateLS(ThongTinDTO tt){
        SQLiteDatabase sqLiteDatabase=dbHelper.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("ID",tt.getId());
        contentValues.put("GT",tt.getGioitinh());
        contentValues.put("CHIEUCAO",tt.getGioitinh());
        contentValues.put("CANNANG",tt.getGioitinh());

        long check=sqLiteDatabase.update("THONGTIN",contentValues,"ID=?",new String[]{String.valueOf(tt.getId())});
        return check !=-1;
    }



}
