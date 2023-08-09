package com.example.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.database.DbHeplper;
import com.example.model.Product;

import java.util.ArrayList;

public class SanPhamDao {
    private DbHeplper dbHeplper;

    public SanPhamDao(Context context){
        dbHeplper = new DbHeplper(context);
    }

    //lấy danh sách sản phẩm
    public ArrayList<Product> getDS(){
        SQLiteDatabase sqLiteDatabase = dbHeplper.getReadableDatabase();
        ArrayList<Product> list = new ArrayList<>();

        Cursor cursor = sqLiteDatabase.rawQuery("Select * From SANPHAM", null);
        Log.e("TAG", "Size of list: " + list.size());

        if (cursor.getCount()> 0 ){
            cursor.moveToFirst();
            do {
                 list.add(new Product(cursor.getInt(0), cursor.getString(1), cursor.getInt(2), cursor.getInt(3)));
            }while (cursor.moveToNext());
        }
        return list;
    }

    public boolean themSPmoi(Product product){
         SQLiteDatabase sqLiteDatabase = dbHeplper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("tenSP", product.getTenSP());
        contentValues.put("giaBan", product.getGiaBan());
        contentValues.put("soLuong", product.getSoLuong());

        long check = sqLiteDatabase.insert("SANPHAM", null, contentValues);
        return check != -1;

    }
}
