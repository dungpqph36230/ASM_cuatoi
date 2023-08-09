package com.example.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.example.database.DbHeplper;

public class NguoiDungDao {
    private DbHeplper dbHeplper;

    public NguoiDungDao(Context context){
        dbHeplper = new DbHeplper(context);
    }

    public boolean checkLogin(String username, String password){
        SQLiteDatabase sqLiteDatabase = dbHeplper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("Select * From NGUOIDUNG Where tendangnhap = ? AND matkhau = ?" , new String[]{username,password});
        if (cursor.getCount() > 0 ){
            return true;
        }return false;
    }

    public boolean Register(String username, String password, String hoten){
        SQLiteDatabase sqLiteDatabase = dbHeplper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("tendangnhap", username);
        contentValues.put("matkhau", password);
        contentValues.put("hoten", hoten);

        long check = sqLiteDatabase.insert("NGUOIDUNG", null, contentValues);
        if (check != -1){
            return true;
        }return false;
    }
    //forgot
    public String ForgotPassword(String email){
        SQLiteDatabase sqLiteDatabase = dbHeplper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("Select matkhau From NGUOIDUNG Where tendangnhap = ?", new String[]{email});
        if (cursor.getCount() > 0){
            cursor.moveToFirst();
            return cursor.getString(0);
        }else {
            return " ";
        }
    }

}
