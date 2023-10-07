package com.example.thuvienphuongnam.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.thuvienphuongnam.database.DbHelper;
import com.example.thuvienphuongnam.model.LoaiSach;

import java.util.ArrayList;

public class LoaiSAchDao {
    SQLiteDatabase db ;
    DbHelper dbHelper;

    public LoaiSAchDao(Context context){
        dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public ArrayList<LoaiSach> getAll(){
        String sql = "Select * from LOAISACH";
        return getData(sql);
    }
    public long insert(LoaiSach loaiSach){
        ContentValues values = new ContentValues();
        values.put("nhasx",loaiSach.getNhaSX());
        values.put("tenloai", loaiSach.getTenLoai());
        return db.insert("LOAISACH", null,values);
    }
    public long update(LoaiSach loaiSach){
        ContentValues values = new ContentValues();
        values.put("nhasx", loaiSach.getNhaSX());
        values.put("tenloai",loaiSach.getTenLoai());
        return db.update("LOAISACH",values, "maloai=?", new String[]{String.valueOf(loaiSach.getMaLoai())});
    }
    public long delete(LoaiSach loaiSach){
        return db.delete("LOAISACH","maloai=?", new String[]{String.valueOf(loaiSach.getMaLoai())});
    }
    public ArrayList<LoaiSach> getData(String sql, String...selectionArgs){
         ArrayList<LoaiSach> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, selectionArgs);
        while (cursor.moveToNext()){
            list.add(new LoaiSach(cursor.getInt(0), cursor.getString(1), cursor.getString(2) ));
        }
        return list;
    }
}
