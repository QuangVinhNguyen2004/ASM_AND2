package com.example.asmand2_vinhnqph45928.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.asmand2_vinhnqph45928.DbHelper.MyDbHelper;

import java.util.ArrayList;

public class TTDAO {
    private MyDbHelper dbHelper;
    public TTDAO(Context context){
        dbHelper=new MyDbHelper(context);
    }
    public ArrayList<ThuThuDTO> getDS(){
        ArrayList<ThuThuDTO> list=new ArrayList<>();
        SQLiteDatabase sqLiteDatabase=dbHelper.getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT * FROM THANHVIEN",null);
        if(cursor.getCount()>0){
            cursor.moveToFirst();
            do{
                list.add(new ThuThuDTO(cursor.getString(0), cursor.getString(1), cursor.getString(2),cursor.getString(3)));

            }while (cursor.moveToNext());
        }
        return list;
    }


    public  boolean themTT(ThuThuDTO tt){
        SQLiteDatabase sqLiteDatabase=dbHelper.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("MATT",tt.getMaTT());
        contentValues.put("TENTT",tt.getTenTT());
        contentValues.put("DIACHI",tt.getDiaChi());
        contentValues.put("SDT",tt.getSDT());

        long check=sqLiteDatabase.insert("THANHVIEN",null,contentValues);
        return check !=-1;
    }
    public  boolean UpdateTT(ThuThuDTO tt){
        SQLiteDatabase sqLiteDatabase=dbHelper.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("MATT",tt.getMaTT());
        contentValues.put("TENTT",tt.getTenTT());
        contentValues.put("DIACHI",tt.getDiaChi());
        contentValues.put("SDT",tt.getSDT());
        long check=sqLiteDatabase.update("THUTHU",contentValues,"MATT=?",new String[]{String.valueOf(tt.getMaTT())});
        return check !=-1;
    }
    public  boolean XoaTT(String id){
        SQLiteDatabase sqLiteDatabase=dbHelper.getWritableDatabase();
        int check=sqLiteDatabase.delete("THUTHU","MATT=?",new String[]{String.valueOf(id)});
        return check !=-1;
    }
}
