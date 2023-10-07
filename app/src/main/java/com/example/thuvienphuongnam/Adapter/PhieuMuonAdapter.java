package com.example.thuvienphuongnam.Adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thuvienphuongnam.R;
import com.example.thuvienphuongnam.dao.SachDAO;
import com.example.thuvienphuongnam.dao.ThanhVienDao;
import com.example.thuvienphuongnam.model.PhieuMuon;
import com.example.thuvienphuongnam.model.Sach;
import com.example.thuvienphuongnam.model.ThanhVien;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class PhieuMuonAdapter extends BaseAdapter {
    ArrayList<PhieuMuon> arrayList;
    Context context;

    ThanhVienDao thanhvienDao;
    SachDAO sachDao;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    public PhieuMuonAdapter(ArrayList<PhieuMuon> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = new ViewHolder();
        if(view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_recyclerview, null);
            viewHolder.tvmapm = view.findViewById(R.id.item_phieumuon_ma);
            viewHolder.tvtentv = view.findViewById(R.id.item_phieumuon_tentv);
            viewHolder.tvtensach = view.findViewById(R.id.item_phieumuon_tensach);
            viewHolder.tvtienthue = view.findViewById(R.id.item_phieumuon_tienthue);
            viewHolder.tvngay = view.findViewById(R.id.item_phieumuon_ngaymuon);
            viewHolder.tvtrasach = view.findViewById(R.id.item_phieumuon_trangthai);

            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
           PhieuMuon phieuMuon = arrayList.get(i);

         viewHolder.tvmapm.setText("Mã: "+phieuMuon.getMaPM());

         thanhvienDao =new ThanhVienDao(context.getApplicationContext());
         ThanhVien tv = thanhvienDao.getID(String.valueOf(phieuMuon.getMaTV()));
         viewHolder.tvtentv.setText("Tên Tv: "+tv.getHoTen());

         sachDao = new SachDAO(context.getApplicationContext());
         Sach sach = sachDao.getID(String.valueOf(phieuMuon.getMaSach()));
         viewHolder.tvtensach.setText("Tên Sách: "+sach.getTenSach());

         viewHolder.tvngay.setText(phieuMuon.getNgay());

         viewHolder.tvtienthue.setText("Giá: "+ phieuMuon.getTienThue());

         if(phieuMuon.getTraSach()==1){
             viewHolder.tvtrasach.setTextColor(Color.BLUE);
             viewHolder.tvtrasach.setText("Đã Trả Sách");
         }else {
             viewHolder.tvtrasach.setTextColor(Color.RED);
             viewHolder.tvtrasach.setText("Chưa Trả Sách");
         }
        return view;
    }

    public class ViewHolder {
         TextView tvmapm,tvtentv,tvtensach,tvtienthue,tvngay,tvtrasach;

    }
}
