package com.example.thuvienphuongnam.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorWrapper;
import android.database.sqlite.SQLiteDatabase;

import com.example.thuvienphuongnam.database.DbHelper;
import com.example.thuvienphuongnam.model.ThanhVien;

import java.util.ArrayList;

public class ThanhVienDao {
    DbHelper dbHelper;
    SQLiteDatabase db;
    public ThanhVienDao(Context context){
        dbHelper= new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }
    public ArrayList<ThanhVien> getALL(){
        String sql = "SELECT * FROM THANHVIEN";
        return getData(sql);
    }
    public long insert(ThanhVien tv){
        ContentValues values = new ContentValues();

        values.put("hoten", tv.getHoTen());
        values.put("namsinh", tv.getNamSinh());
       // values.put("cccd", tv.getCccd());
        return db.insert("THANHVIEN", null, values);
    }
    public long update(ThanhVien tv){
        ContentValues values = new ContentValues();
        values.put("hoten", tv.getHoTen());
        values.put("namsinh", tv.getNamSinh());
        //values.put("cccd", tv.getCccd());

        return db.update("THANHVIEN",values,"matv=?",new String[]{String.valueOf(tv.getMaTV())});
    }
    public int delete(String id){
        return db.delete("THANHVIEN","matv=?",new String[]{id});
    }
    public  ThanhVien getID(String id){
        String sql = "SELECT * FROM THANHVIEN WHERE matv=?";
        ArrayList<ThanhVien> list = getData(sql,id);
        return list.get(0);
    }
    public ArrayList<ThanhVien> getData(String sql, String...selectionArgs){
         ArrayList<ThanhVien> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql,selectionArgs);
        while (cursor.moveToNext()){
            list.add(new ThanhVien(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2)
                    )
            );
        }
        return list;
    }
}
