package com.example.thuvienphuongnam.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.thuvienphuongnam.database.DbHelper;
import com.example.thuvienphuongnam.model.ThuThu;

import java.util.ArrayList;
import java.util.List;

public class ThuThuDao {
    DbHelper dbHelper ;
    SQLiteDatabase db;
    public ThuThuDao(Context context){
        dbHelper = new DbHelper(context);
        db = dbHelper.getReadableDatabase();
    }
    //dang nhap
    public int CheckThuthu(String mtt, String matkhau){
       String sql = "SELECT * FROM THUTHU WHERE matt =? AND matkhau =?";
       List<ThuThu> list =  getData(sql,mtt,matkhau);
       if(list.size()==0){
           return -1;
       }
       return 1;
    }
    public List<ThuThu> getAll(){
        String sql = "SELECT * FROM THUTHU";
        return getData(sql);
    }
    public long insertAdmin(){
     ContentValues values= new ContentValues();
     values.put("matt","admin");
     values.put("hoten","ADMIN");
     values.put("matkhau","admin");
     return db.insert("THUTHU", null,values);
    }
    public long insert(ThuThu thuThu){
        ContentValues values = new ContentValues();
        values.put("matt", thuThu.getMaTT());
        values.put("hoten",thuThu.getHoTen());
        values.put("matkhau",thuThu.getMatKhau());
        return db.insert("THUTHU",null, values);
    }
    public long update(ThuThu thuThu){
        ContentValues values = new ContentValues();
        values.put("matt", thuThu.getMaTT());
        values.put("hoten",thuThu.getHoTen());
        values.put("matkhau",thuThu.getMatKhau());
       return db.update("THUTHU",values,"matt=?",new String[]{String.valueOf(thuThu.getMaTT())});
    }
    public int delete(String id){
        return db.delete("THUTHU","matkhau=?",new String[]{id});
    }
    public ThuThu getID(String id){
        String sql = "SELECT * FROM THUTHU WHERE matt=?";
        List<ThuThu> list = getData(sql,id);
        return list.get(0);
    }
    @SuppressLint("Range")
    public List<ThuThu> getData(String sql, String...selectionArgs){
        List<ThuThu> list = new ArrayList<ThuThu>();
        Cursor c = db.rawQuery(sql,selectionArgs);
        while (c.moveToNext()){
            ThuThu obj = new ThuThu();
            obj.maTT = c.getString(c.getColumnIndex("matt"));
            obj.hoTen = c.getString(c.getColumnIndex("hoten"));
            obj.matKhau = c.getString(c.getColumnIndex("matkhau"));
            list.add(obj);
        }
        return list;
    }
}
