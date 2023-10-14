package com.example.thuvienphuongnam.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.thuvienphuongnam.R;
import com.example.thuvienphuongnam.model.ThanhVien;

import java.util.ArrayList;

public class ThanhVienAdapter extends BaseAdapter {
    ArrayList<ThanhVien> arrayList;
    Context context;

    public ThanhVienAdapter(ArrayList<ThanhVien> arrayList, Context context) {
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
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_loaisach, null);
            viewHolder.txtMaTv = view.findViewById(R.id.item_lv_maloai);
            viewHolder.txtTentv = view.findViewById(R.id.item_lv_name);
            viewHolder.txtNamsinh = view.findViewById(R.id.item_lv_theloai);
//            viewHolder.tvCccd = view.findViewById(R.id.item_lv_cccd);

            viewHolder.temp1 = view.findViewById(R.id.temp_1);
            viewHolder.temp2 = view.findViewById(R.id.temp_2);
            viewHolder.temp3 = view.findViewById(R.id.temp_3);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        ThanhVien tv = arrayList.get(i);
        viewHolder.txtMaTv.setText(String.valueOf(tv.getMaTV()));
        viewHolder.txtTentv.setText(tv.getHoTen());
        viewHolder.txtNamsinh.setText(tv.getNamSinh());
        //viewHolder.tvCccd.setText(tv.getCccd());

        viewHolder.temp1.setText("Mã TV: ");
        viewHolder.temp2.setText("Tên TV: ");
        viewHolder.temp3.setText("Năm Sinh: ");


        return view;
    }

    public class ViewHolder {
        TextView txtMaTv, txtTentv, txtNamsinh, temp1, temp2, temp3, tvCccd;
    }
}
