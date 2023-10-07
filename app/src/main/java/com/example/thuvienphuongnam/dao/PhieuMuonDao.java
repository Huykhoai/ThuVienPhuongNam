package com.example.thuvienphuongnam.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.thuvienphuongnam.database.DbHelper;
import com.example.thuvienphuongnam.model.PhieuMuon;

import java.sql.Date;
import java.util.ArrayList;

public class PhieuMuonDao {
    DbHelper dbHelper;
    SQLiteDatabase db;
    public PhieuMuonDao(Context context){
        dbHelper = new DbHelper(context);
        db =dbHelper.getWritableDatabase();
    }
    public ArrayList<PhieuMuon> getDanhsachPhieumuon(){
        String sql ="SELECT pm.mapm, pm.matt, pm.masach, pm.ngay,pm.trasach, pm.tienthue, pm.matv  FROM PHIEUMUON pm,THUTHU tt, SACH sc, THANHVIEN tv WHERE pm.matt = tt.matt AND pm.masach = sc.masach and pm.matv = tv.matv";
        return getData(sql);
    }
    public long insert(PhieuMuon pm ){
        ContentValues values = new ContentValues();
        values.put("matt", pm.getMaTT());
        values.put("matv", pm.getMaTV());
        values.put("masach", pm.getMaSach());
        values.put("ngay", pm.getNgay());
        values.put("tienthue", pm.getTienThue());
        values.put("trasach", pm.getTraSach());
        return db.insert("PHIEUMUON", null,values);
    }
    public long update(PhieuMuon pm ){
        ContentValues values = new ContentValues();
        values.put("matt", pm.getMaTT());
        values.put("matv", pm.getMaTV());
        values.put("masach", pm.getMaSach());
        values.put("ngay",pm.getNgay());
        values.put("tienthue", pm.getTienThue());
        values.put("trasach", pm.getTraSach());
        return db.update("PHIEUMUON",values,"mapm=?",new String[]{String.valueOf(pm.getMaPM())});
    }
    public int delete(String id){
        return db.delete("PHIEUMUON", "mapm=?",new String[]{id});
    }
    @SuppressLint("Range")
    public ArrayList<PhieuMuon> getData(String sql, String...selectionArgs){
       ArrayList<PhieuMuon> list = new ArrayList<>();
       Cursor c = db.rawQuery(sql,selectionArgs);
       while (c.moveToNext()){
           PhieuMuon obj = new PhieuMuon();
           obj.maPM = Integer.parseInt(c.getString(c.getColumnIndex("mapm")));
           obj.maTT = c.getString(c.getColumnIndex("matt"));
           obj.maTV = Integer.parseInt(c.getString(c.getColumnIndex("matv")));
           obj.maSach = Integer.parseInt(c.getString(c.getColumnIndex("masach")));
           obj.tienThue = Integer.parseInt(c.getString(c.getColumnIndex("tienthue")));
           obj.ngay = c.getString(c.getColumnIndex("ngay"));
           obj.traSach = Integer.parseInt(c.getString(c.getColumnIndex("trasach")));
           list.add(obj);
       }
       return list;
    }
}
