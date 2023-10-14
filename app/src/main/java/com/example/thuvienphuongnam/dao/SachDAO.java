package com.example.thuvienphuongnam.dao;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.thuvienphuongnam.database.DbHelper;
import com.example.thuvienphuongnam.model.Sach;

import java.util.ArrayList;
import java.util.List;

public class SachDAO {
    DbHelper dbHelper;
    SQLiteDatabase db;
    public SachDAO(Context context){
        dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }
    //lay toan bo dau sach co trong thu vien
    public ArrayList<Sach> getAll(){
        String sql = "select * from Sach";
        return getData(sql);
    }
    public long insert(Sach sach){
        ContentValues values = new ContentValues();
        values.put("tensach", sach.getTenSach());
        values.put("giathue",sach.getGiasach());
        values.put("maloai",sach.getMaLoai());
        values.put("namXB",sach.getNamXB());
        return db.insert("SACH", null,values);
    }
    public long update(Sach odj){
        ContentValues values = new ContentValues();
        values.put("tenSach",odj.getTenSach());
        values.put("giaThue",odj.getGiasach());
        values.put("maLoai",odj.getMaLoai());
        values.put("namXB",odj.getNamXB());
        return db.update("SACH",values,"masach=?",new String[]{String.valueOf(odj.getMaSach())});
    }
    public int delete(String id){
        return db.delete("SACH","masach=?",new String[]{id});
    }
    public Sach getID(String id){
        String sql = "SELECT * FROM SACH WHERE masach=?";
        List<Sach> list = getData(sql,id);
        return list.get(0);
    }
   public ArrayList<Sach> getData(String sql, String...selectionArgs){
        ArrayList<Sach> list = new ArrayList<>();
       Cursor cursor = db.rawQuery(sql,selectionArgs);
       if(cursor.getCount() !=0){
           cursor.moveToFirst();
           do {
                   list.add(new Sach(cursor.getInt(0), cursor.getString(1), cursor.getInt(2), cursor.getInt(3),cursor.getInt(4)));
           }while (cursor.moveToNext());
       }
        return list;
   }
}
