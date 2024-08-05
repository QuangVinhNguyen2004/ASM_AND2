package com.example.asmand2_vinhnqph45928.DbHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDbHelper extends SQLiteOpenHelper {
    public  MyDbHelper(Context context){
        super(context,"MOB",null,8);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sNguoiDung="CREATE TABLE NGUOIDUNG(tendangnhap TEXT PRIMARY KEY,matkhau TEXT,hoten TEXT,email TEXT)";
        sqLiteDatabase.execSQL(sNguoiDung);
        String qCONGVIEC="CREATE TABLE CONGVIEC(id TEXT PRIMARY KEY,name TEXT,content TEXT,status TEXT,batdau TEXT,ketthuc TEXT)";
        sqLiteDatabase.execSQL(qCONGVIEC);
        String dNguoiDung="INSERT INTO NGUOIDUNG VALUES('vinhnq','123','vinh','vinh@gmail.com')";
        sqLiteDatabase.execSQL(dNguoiDung);
        String dCongviec="INSERT INTO CONGVIEC VALUES('CV01','Thống kê','Thống kê doanh thu','đang làm','22/12/2023','02/01/2024')";
        sqLiteDatabase.execSQL(dCongviec);





        String qThongTin="CREATE TABLE THONGTIN(ID TEXT PRIMARY KEY,GT TEXT,CHIEUCAO INT,CN INT)";
        sqLiteDatabase.execSQL(qThongTin);
        String dThongTin="INSERT INTO THONGTIN VALUES('01','nam','170','60')";
        sqLiteDatabase.execSQL(dThongTin);

        String qHOATDONG="CREATE TABLE HOATDONG(MAHD TEXT PRIMARY KEY,DATE TEXT,GHICHU TEXT)";
        sqLiteDatabase.execSQL(qHOATDONG);
        String dHOATDONG="INSERT INTO HOATDONG VALUES('hd01','28/07/2024','hom nay chay duoc 10km')";
        sqLiteDatabase.execSQL(dHOATDONG);

        String qVANDONG="CREATE TABLE VANDONG(MAVD TEXT PRIMARY KEY,DATE TEXT,MUCTIEU TEXT,HT TEXT)";
        sqLiteDatabase.execSQL(qVANDONG);
        String dVANDONG="INSERT INTO VANDONG VALUES('vd01','28/07/2024','15KM','10KM')";
        sqLiteDatabase.execSQL(dVANDONG);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
         if(i != i1){
             sqLiteDatabase.execSQL("DROP TABLE IF EXISTS NGUOIDUNG");
             sqLiteDatabase.execSQL("DROP TABLE IF EXISTS NHANVIEN");
             sqLiteDatabase.execSQL("DROP TABLE IF EXISTS THANHVIEN");



             sqLiteDatabase.execSQL("DROP TABLE IF EXISTS THONGTIN");
             sqLiteDatabase.execSQL("DROP TABLE IF EXISTS HOATDONG");
             sqLiteDatabase.execSQL("DROP TABLE IF EXISTS VANDONG");

             onCreate(sqLiteDatabase);
         }
    }
}
