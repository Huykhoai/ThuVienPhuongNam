package com.example.thuvienphuongnam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.thuvienphuongnam.Trangchu.TrangchuActivity;
import com.example.thuvienphuongnam.dao.ThuThuDao;
import com.example.thuvienphuongnam.model.ThuThu;

import java.util.List;

public class ManHinhChaoActivity extends AppCompatActivity {

    List<ThuThu> arrayList;
    ThuThuDao dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh_chao);

        dao = new ThuThuDao(getApplicationContext());
        arrayList = dao.getAll();
        if (arrayList.size() == 0) {
            dao.insertAdmin();
        }
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        }, 5000);
    }
}