package com.example.thuvienphuongnam.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.thuvienphuongnam.R;
import com.example.thuvienphuongnam.model.Top;

import java.util.ArrayList;
import java.util.List;

public class TopAdapter extends BaseAdapter {
    List<Top> list;
    Context context;
    int temp =0;

    public TopAdapter(List<Top> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = new ViewHolder();
        if(view == null){
            temp++;
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_lv_top, null);
            viewHolder.top = view.findViewById(R.id.item_top);
            viewHolder.ten = view.findViewById(R.id.item_lv_top_sach);
            viewHolder.soluong =view.findViewById(R.id.item_lv_top_so);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        Top top = list.get(i);
        viewHolder.top.setText(String.valueOf(temp));
        viewHolder.ten.setText(top.getTensach());
        viewHolder.soluong.setText(String.valueOf(top.getSoluong()));

        return view;
    }
    public class ViewHolder{
        TextView top,ten,soluong;
    }
}
