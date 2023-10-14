package com.example.thuvienphuongnam.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {
    public DbHelper(Context context) {
        super(context, "DANGKIMONHOC", null, 8);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String dbThuThu = "CREATE TABLE THUTHU(matt text primary key, hoten text, matkhau text)";
        sqLiteDatabase.execSQL(dbThuThu);

        String dbThanhVien = "CREATE TABLE THANHVIEN(matv integer primary key , hoten text, namsinh text)";
        sqLiteDatabase.execSQL(dbThanhVien);

        String dbLoaiSach = "CREATE TABLE LOAISACH(maloai integer primary key , tenloai text,nhasx text)";
        sqLiteDatabase.execSQL(dbLoaiSach);

        String dbSach = "CREATE TABLE SACH(masach integer primary key , tensach text, giathue integer,maloai integer references LOAISACH(maloai),namXB ingteger)";
        sqLiteDatabase.execSQL(dbSach);

        String dbPhieuMuon = "CREATE TABLE PHIEUMUON(mapm integer primary key ,matv integer references THANHVIEN(matv),matt text references THUTHU(matt), masach integer references SACH(masach), ngay text, trasach integer, tienthue integer)";
        sqLiteDatabase.execSQL(dbPhieuMuon);

        //data mau
        sqLiteDatabase.execSQL("INSERT INTO LOAISACH VALUES (1, 'Thieu Nhi', 'Kim Dong'),(2,'Tinh Cam','Kim lan'), (3,'Giao Khoa','Huy nguyen')");
        sqLiteDatabase.execSQL("INSERT INTO SACH VALUES (1,'Doremon', 2500,1,2020), (2,'Thang cuoi', 1000, 1,2021),(3,'Lap trinh android', 2000,3, 2023)");

        //sqLiteDatabase.execSQL("INSERT INTO THUTHU VALUES('duyle', 'Duy Le', '123456')");

        sqLiteDatabase.execSQL("INSERT INTO THANHVIEN VALUES(1, 'Nguyen Quang Huy', '2003')");
        sqLiteDatabase.execSQL("INSERT INTO THANHVIEN VALUES(2, 'Le Tuan Anh', '2003')");

        sqLiteDatabase.execSQL("INSERT INTO PHIEUMUON VALUES(1,1,'thuthu02',2,'22-03-2023', 1, 2500)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        if (i != i1) {
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS THUTHU");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS THANHVIEN");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS LOAISACH");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS SACH");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS PHIEUMUON");
            onCreate(sqLiteDatabase);
        }
    }
}
