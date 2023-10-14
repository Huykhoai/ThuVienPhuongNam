package com.example.thuvienphuongnam.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.thuvienphuongnam.R;
import com.example.thuvienphuongnam.model.Sach;

import java.util.ArrayList;

public class SachAdapter extends BaseAdapter {
   private ArrayList<Sach> arrayList;
   private Context context;

    public SachAdapter(ArrayList<Sach> arrayList, Context context) {
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
        if(view==null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_sach, null);
            viewHolder.txtMaSach= view.findViewById(R.id.item_sach_masach);
            viewHolder.txtTenSach= view.findViewById(R.id.item_sach_tensach);
            viewHolder.txtGia= view.findViewById(R.id.item_sach_giathue);
            viewHolder.txtMaLoai= view.findViewById(R.id.item_sach_maloai);
            viewHolder.txtNamXB= view.findViewById(R.id.item_sach_namXB);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        Sach sach = arrayList.get(i);
        viewHolder.txtMaSach.setText("Mã: "+String.valueOf(sach.getMaSach()));
        viewHolder.txtTenSach.setText("Ten: "+sach.getTenSach());
        viewHolder.txtGia.setText("Giá: "+String.valueOf(sach.getGiasach()));
        viewHolder.txtMaLoai.setText("Mã Loại: "+ String.valueOf(sach.getMaLoai()));
        viewHolder.txtNamXB.setText("Năm XB: "+String.valueOf(sach.getNamXB()));
        return view;
    }
    public class ViewHolder{
        TextView txtMaSach,txtTenSach,txtGia,txtNamXB,txtMaLoai;
    }
}
