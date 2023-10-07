package com.example.thuvienphuongnam.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.thuvienphuongnam.R;
import com.example.thuvienphuongnam.model.ThuThu;

import java.util.ArrayList;
import java.util.List;

public class ThuThuAdapter extends BaseAdapter {
    List<ThuThu> arrayList;
    Context context;
    String a="*";
    public ThuThuAdapter(List<ThuThu> arrayList, Context context) {
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
        ViewHolder viewHolder= new ViewHolder();

        if(view==null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_loaisach, null);
            viewHolder.tvMatt = view.findViewById(R.id.item_lv_maloai);
            viewHolder.tvName = view.findViewById(R.id.item_lv_name);
            viewHolder.tvPass = view.findViewById(R.id.item_lv_theloai);

            viewHolder.temp1 =view.findViewById(R.id.temp_1);
            viewHolder.temp2 =view.findViewById(R.id.temp_2);
            viewHolder.temp3 =view.findViewById(R.id.temp_3);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.temp1.setText("Mã Thủ Thư: ");
        viewHolder.temp2.setText("Tên Thủ Thư: ");
        viewHolder.temp3.setText("Mật Khẩu: ");

        ThuThu thuThu = arrayList.get(i);
        viewHolder.tvMatt.setText(thuThu.getMaTT());
        viewHolder.tvName.setText(thuThu.getHoTen());
        String temp = thuThu.getMatKhau();
        for (int j=0;j<temp.length();j++){
          a=a.concat("*");
        }
        viewHolder.tvPass.setText(a);
        a="";
        return view;
    }
    public class ViewHolder{
        TextView tvMatt,tvName,tvPass,temp1,temp2,temp3;
    }
}
