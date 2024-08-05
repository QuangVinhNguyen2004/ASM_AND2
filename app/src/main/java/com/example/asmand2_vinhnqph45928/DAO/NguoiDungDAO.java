package com.example.asmand2_vinhnqph45928.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.asmand2_vinhnqph45928.DbHelper.MyDbHelper;

public class NguoiDungDAO {
    private MyDbHelper dbHelper;
    public NguoiDungDAO(Context context){
        dbHelper=new MyDbHelper(context);
    }
    //login
    public boolean CheckLogin(String usename,String password){
        SQLiteDatabase sqLiteDatabase=dbHelper.getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT * FROM NGUOIDUNG WHERE tendangnhap=? AND matkhau=?",new String[]{usename,password});
        return cursor.getCount()>0;

    }
    public  boolean CheckEmail(String email,String mk){

        SQLiteDatabase sqLiteDatabase=dbHelper.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("matkhau",mk);

        long check=sqLiteDatabase.update("NGUOIDUNG",contentValues,"email=?",new String[]{email});
        return check !=-1;
    }

    public boolean Register(String username,String password,String hoten,String Email){
        SQLiteDatabase sqLiteDatabase=dbHelper.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("tendangnhap",username);
        contentValues.put("matkhau",password);
        contentValues.put("hoten",hoten);
        contentValues.put("email",Email);

        long check=sqLiteDatabase.insert("NGUOIDUNG",null,contentValues);
        return check !=-1;

    }

}
