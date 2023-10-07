package com.example.thuvienphuongnam.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.thuvienphuongnam.R;
import com.example.thuvienphuongnam.model.LoaiSach;

import java.util.ArrayList;
import java.util.List;

public class LoaiSachAdapter extends BaseAdapter {
      private ArrayList<LoaiSach> arrayList;
      private Context context;

    public LoaiSachAdapter(ArrayList<LoaiSach> arrayList, Context context) {
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

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder = new ViewHolder();
           if(convertView==null){
               LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
              convertView =inflater.inflate(R.layout.item_loaisach, null);
              viewHolder.maLoai= convertView.findViewById(R.id.item_lv_maloai);
              viewHolder.NhaSx = convertView.findViewById(R.id.item_lv_theloai);
              viewHolder.tenLoai= convertView.findViewById(R.id.item_lv_name);
              convertView.setTag(viewHolder);
           }else {
               viewHolder= (ViewHolder) convertView.getTag();
           }
           LoaiSach ls = arrayList.get(position);
           viewHolder.maLoai.setText(String.valueOf(ls.getMaLoai()));
           viewHolder.NhaSx.setText(ls.getNhaSX());
           viewHolder.tenLoai.setText(ls.getTenLoai());
        return convertView;
    }

    public class ViewHolder{
        TextView maLoai, NhaSx, tenLoai;
    }
}
