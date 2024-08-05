package com.example.asmand2_vinhnqph45928.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.asmand2_vinhnqph45928.DbHelper.MyDbHelper;

import java.util.ArrayList;

public class SachDAO {
    private MyDbHelper dbHelper;

    public SachDAO(Context context){
        dbHelper=new MyDbHelper(context);
    }
    public ArrayList<SachDTO> getDS(){
        ArrayList<SachDTO> list=new ArrayList<>();
        SQLiteDatabase sqLiteDatabase=dbHelper.getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT MASACH,TENSACH,GIA,TENLOAI FROM SACH JOIN LOAISACH ON SACH.MALOAI=LOAISACH.MALOAI",null);
        if(cursor.getCount()>0){
            cursor.moveToFirst();
            do{
                list.add(new SachDTO(cursor.getString(0), cursor.getString(1), cursor.getInt(2), cursor.getString(3) ));

            }while (cursor.moveToNext());
        }
        return list;
    }
    public  boolean themsach(SachDTO sa){
        SQLiteDatabase sqLiteDatabase=dbHelper.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("MASACH",sa.getMaSach());
        contentValues.put("TENSACH",sa.getTenSach());
        contentValues.put("GIA",sa.getGia());
        contentValues.put("MALOAI",sa.getTenloai());

        long check=sqLiteDatabase.insert("SACH",null,contentValues);
        return check !=-1;
    }
    public  boolean UpdateSach(SachDTO sa){
        SQLiteDatabase sqLiteDatabase=dbHelper.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("MASACH",sa.getMaSach());
        contentValues.put("TENSACH",sa.getTenSach());
        contentValues.put("GIA",sa.getGia());
        contentValues.put("MALOAI",sa.getTenloai());
        long check=sqLiteDatabase.update("SACH",contentValues,"MASACH=?",new String[]{String.valueOf(sa.getMaSach())});
        return check !=-1;
    }
    public  boolean XoaSach(String id){
        SQLiteDatabase sqLiteDatabase=dbHelper.getWritableDatabase();
        int check=sqLiteDatabase.delete("SACH","MASACH=?",new String[]{String.valueOf(id)});
        return check !=-1;
    }
}
