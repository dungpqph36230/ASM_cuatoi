package com.example.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHeplper extends SQLiteOpenHelper {

    public DbHeplper(Context context){
        super(context, "AND103", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String nguoiDung = "CREATE TABLE NGUOIDUNG(tendangnhap TEXT PRIMARY KEY, matkhau TEXT, hoten TEXt)";
        sqLiteDatabase.execSQL(nguoiDung);
        String sanPham = "CREATE TABLE SANPHAM(maSP INTEGER PRIMARY KEY AUTOINCREMENT, tenSP TEXT, giaBan INTEGER, soLuong INTEGER)";
        sqLiteDatabase.execSQL(sanPham);

        String bangNguoiDung = "INSERT INTO NGUOIDUNG VALUES('dinhnt','1234','ĐịnhNT'),('dungpq','1109','Dũng')";
        sqLiteDatabase.execSQL(bangNguoiDung);
        String bangSanPham = "INSERT INTO SANPHAM VALUES(1,'Bánh quy bơ LU Pháp',45000,10),(2,'Snack mực lăn muối ớt',8000,52 ),(3,'Snack khoai tây Lays',12000,38),(4,'Bánh gạo ONE ONE',30000,11),(5,'Kẹo Sữa sô cô la',25000,30)";
        sqLiteDatabase.execSQL(bangSanPham);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        if (i != i1){
            db.execSQL("Drop Table if exists NGUOIDUNG");
            db.execSQL("Drop Table if exists SANPHAM");
            onCreate(db);
        }
    }
}
