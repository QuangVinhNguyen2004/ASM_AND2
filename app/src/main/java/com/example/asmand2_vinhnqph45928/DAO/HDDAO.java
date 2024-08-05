package com.example.asmand2_vinhnqph45928.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.asmand2_vinhnqph45928.DTO.HoatDongDTO;
import com.example.asmand2_vinhnqph45928.DbHelper.MyDbHelper;

import java.util.ArrayList;

public class HDDAO {
    private MyDbHelper dbHelper;
    public HDDAO(Context context){
        dbHelper=new MyDbHelper(context);
    }
    public ArrayList<HoatDongDTO> getDS(){
        ArrayList<HoatDongDTO> list=new ArrayList<>();
        SQLiteDatabase sqLiteDatabase=dbHelper.getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT * FROM HOATDONG",null);
        if(cursor.getCount()>0){
            cursor.moveToFirst();
            do{
                list.add(new HoatDongDTO(cursor.getString(0),cursor.getString(1),cursor.getString(2)));

            }while (cursor.moveToNext());
        }
        return list;
    }


    public  boolean themTV(HoatDongDTO hd){
        SQLiteDatabase sqLiteDatabase=dbHelper.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("MAHD",hd.getMahd());
        contentValues.put("DATE",hd.getGhichu());
        contentValues.put("GHICHU",hd.getNgayghi());


        long check=sqLiteDatabase.insert("HOATDONG",null,contentValues);
        return check !=-1;
    }
    public  boolean UpdateTV(HoatDongDTO hd){
        SQLiteDatabase sqLiteDatabase=dbHelper.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("MAHD",hd.getMahd());
        contentValues.put("DATE",hd.getGhichu());
        contentValues.put("GHICHU",hd.getNgayghi());
        long check=sqLiteDatabase.update("HOATDONG",contentValues,"MAHD=?",new String[]{String.valueOf(hd.getMahd())});
        return check !=-1;
    }
    public  boolean XoaTV(String id){
        SQLiteDatabase sqLiteDatabase=dbHelper.getWritableDatabase();
        int check=sqLiteDatabase.delete("HOATDONG","MAHD=?",new String[]{String.valueOf(id)});
        return check !=-1;
    }
}
