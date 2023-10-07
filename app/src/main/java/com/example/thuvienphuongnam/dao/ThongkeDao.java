package com.example.thuvienphuongnam.dao;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.thuvienphuongnam.database.DbHelper;
import com.example.thuvienphuongnam.model.Sach;
import com.example.thuvienphuongnam.model.Top;

import java.util.ArrayList;
import java.util.List;

public class ThongkeDao {
    DbHelper dbHelper;
    SQLiteDatabase db;
    Context context;
    public ThongkeDao(Context context) {
        this.context = context;
         dbHelper = new DbHelper(context);
         db = dbHelper.getWritableDatabase();
    }

    @SuppressLint("Range")
    public int getDoanhThu(String tuNgay, String denNgay){
     String sql = "SELECT SUM(tienthue) as doanhthu FROM PHIEUMUON WHERE ngay BETWEEN ? AND ? ";
     List<Integer> list = new ArrayList<>();
        Cursor c = db.rawQuery(sql,new String[]{tuNgay,denNgay});
       while (c.moveToNext()){
          try{
            list.add(Integer.parseInt(c.getString(c.getColumnIndex("doanhthu"))));
          }catch (Exception e){
              e.printStackTrace();
          }
       }
       return list.get(0);
    }
    @SuppressLint("Range")
    public List<Top> getTop(){
       String sql = "SELECT masach,COUNT(masach) as soluong FROM PHIEUMUON GROUP BY masach ORDER BY soluong DESC limit 10";
       List<Top> list = new ArrayList<>();
       SachDAO sachDAO = new SachDAO(context);
       Cursor cursor = db.rawQuery(sql, null);
       while (cursor.moveToNext()){
           Top top = new Top();
           Sach sach = sachDAO.getID(cursor.getString(cursor.getColumnIndex("masach")));
           top.setTensach(sach.getTenSach());
           top.setSoluong(Integer.parseInt(cursor.getString(cursor.getColumnIndex("soluong"))));
           list.add(top);
       }
       return list;
    }
}
