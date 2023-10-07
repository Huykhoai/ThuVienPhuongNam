package com.example.thuvienphuongnam.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thuvienphuongnam.Adapter.LoaiSachAdapter;
import com.example.thuvienphuongnam.MainActivity;
import com.example.thuvienphuongnam.R;
import com.example.thuvienphuongnam.dao.LoaiSAchDao;
import com.example.thuvienphuongnam.dao.SachDAO;
import com.example.thuvienphuongnam.model.LoaiSach;
import com.example.thuvienphuongnam.model.Sach;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;


public class LoaiSachFragment extends Fragment {
      FloatingActionButton fab;

     ArrayList<LoaiSach> arrayList;
     LoaiSAchDao LoaisachDao;
     LoaiSachAdapter adapter;
     ListView listView;
     LoaiSach loaiSach;
     SachDAO sachDao;
     ArrayList<Sach> listSach;
     int a;
    int temp=0;
     TextInputLayout txtMaloai,txtTenLoai,txtNhaSx;
     EditText edTenLoai,edNhaSx,edMaLoai;
    public LoaiSachFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_loai_sach, container, false);
        Anhxa(view);
       getData();
        return view;
    }
    public void openDialog(int gravity){
        final Dialog dialog =new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_themtt);

        Window window = dialog.getWindow();
        if(window== null){
          return;
        }
         window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        WindowManager.LayoutParams windowArributes =window.getAttributes();
        windowArributes.gravity= gravity;
        window.setAttributes(windowArributes);
        if(Gravity.CENTER == gravity){
          dialog.setCancelable(true);
        }else {
            dialog.setCancelable(false);
        }
        TextView tvTitle = dialog.findViewById(R.id.item_tvtile);
        txtMaloai = dialog.findViewById(R.id.add_til_username);
        txtTenLoai = dialog.findViewById(R.id.add_til_pass);
        txtNhaSx = dialog.findViewById(R.id.add_til_name);

        edMaLoai = dialog.findViewById(R.id.item_txtnameuser);
        edNhaSx = dialog.findViewById(R.id.item_txtname);
        edTenLoai = dialog.findViewById(R.id.item_txtpass);

        Button btnAdd = dialog.findViewById(R.id.dialog_add_add);
        Button btnCancel = dialog.findViewById(R.id.dialog_add_cancel);
        LoaisachDao = new LoaiSAchDao(getActivity());
        if(a==-1){
          tvTitle.setText("Thêm Loại Sách");
          txtMaloai.setHint("Mã Loại");
          txtNhaSx.setHint("Nhà Sản Xuất");
          txtTenLoai.setHint("Tên Loại");

          edMaLoai.setEnabled(false);
          if(arrayList.size()==0){
            edMaLoai.setText("1");
          }else {
           loaiSach = LoaisachDao.getAll().get(arrayList.size()-1);
           edMaLoai.setText(String.valueOf(loaiSach.getMaLoai()+1));
          }
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (temp ==0){
                    loaiSach.setNhaSX(edNhaSx.getText().toString());
                    loaiSach.setTenLoai(edTenLoai.getText().toString());
                    if(LoaisachDao.insert(loaiSach)>0){
                        Toast.makeText(getActivity(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                        getData();
                    }else {
                        Toast.makeText(getActivity(), "Thêm thất bại", Toast.LENGTH_SHORT).show();

                    }
                }else {
                    temp=0;
                }

            }
        });
            btnCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getActivity(), "Huỷ thêm", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            });
        }else {
          tvTitle.setText("Sửa/Xóa Loại Sách");
          txtMaloai.setHint("Mã Loại");
          txtTenLoai.setHint("Thể Loại");
          txtNhaSx.setHint("Nhà Sản Xuất");

          btnAdd.setText("Sửa");
          btnCancel.setText("Xóa");

          loaiSach =arrayList.get(a);
          edMaLoai.setText(String.valueOf(loaiSach.getMaLoai()));
          edMaLoai.setEnabled(false);
          edTenLoai.setText(loaiSach.getTenLoai());
          edNhaSx.setText(loaiSach.getNhaSX());

          btnAdd.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  if (temp ==0){
                      loaiSach = new LoaiSach();
                      loaiSach.setMaLoai(Integer.parseInt(edMaLoai.getText().toString()));
                      loaiSach.setNhaSX(edNhaSx.getText().toString());
                      loaiSach.setTenLoai(edTenLoai.getText().toString());
                      if(LoaisachDao.update(loaiSach)>0){
                          Toast.makeText(getActivity(), "Sửa thành công", Toast.LENGTH_SHORT).show();
                          dialog.dismiss();
                          getData();
                      }else {
                          Toast.makeText(getActivity(), "Sửa thất bại", Toast.LENGTH_SHORT).show();

                      }
                  }else {
                      temp=0;
                  }
              }
          });
          btnCancel.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {

               listSach = sachDao.getAll();
               for (int i=0;i<listSach.size();i++){
                   if(listSach.get(i).getMaSach() == loaiSach.getMaLoai()){
                       Toast.makeText(getActivity(), "Không thể xóa loại sách có trong sách", Toast.LENGTH_SHORT).show();
                       temp++;
                       break;
                   }
               }
                  if(temp==0){
                      if(LoaisachDao.delete(loaiSach)>0){
                          Toast.makeText(getActivity(), "Xoá thành công", Toast.LENGTH_SHORT).show();
                          dialog.dismiss();
                          getData();
                      }else {
                          Toast.makeText(getActivity(), "Xoá thất bại", Toast.LENGTH_SHORT).show();
                      }
                  }
              }
          });
        }

        dialog.show();
    }
    public void getData(){
         LoaisachDao = new LoaiSAchDao(getContext());
         arrayList = LoaisachDao.getAll();
         adapter = new LoaiSachAdapter(arrayList, getContext());
        listView.setAdapter(adapter);
    }
    public void Anhxa(View view){
         listView = view.findViewById(R.id.listView_loaisach);
         fab = view.findViewById(R.id.fab_qlLoaiSach);
         sachDao = new SachDAO(getContext());
         listSach = sachDao.getAll();
         fab.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 a=-1;
                 openDialog(Gravity.CENTER);
             }
         });
         listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
             @Override
             public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                 a= i;
                 openDialog(Gravity.CENTER);
             }
         });
    }
}