package com.example.thuvienphuongnam.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.thuvienphuongnam.MainActivity;
import com.example.thuvienphuongnam.R;
import com.example.thuvienphuongnam.Trangchu.TrangchuActivity;
import com.example.thuvienphuongnam.dao.ThuThuDao;
import com.example.thuvienphuongnam.model.ThuThu;
import com.google.android.material.textfield.TextInputLayout;


public class MatkhauFragment extends Fragment {

        EditText edOldPass,edNewPass,edReNewPass;
        Button btnSave,btnCancel;
        ThuThuDao thuThuDao;
        TextInputLayout tilOldPass,tilNewpass,tilReNewPass;

    public MatkhauFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static MatkhauFragment newInstance() {
        MatkhauFragment fragment = new MatkhauFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_matkhau, container, false);
        Anhxa(view);
        ActionBtn();
        // Inflate the layout for this fragment
        return view;
    }

    private void ActionBtn() {
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edOldPass.setText("");
                edNewPass.setText("");
                edReNewPass.setText("");

                tilOldPass.setError("");
                tilNewpass.setError("");
                tilReNewPass.setError("");

            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences spf = getActivity().getSharedPreferences("THONGTIN", Context.MODE_PRIVATE);
                String user = spf.getString("USERNAME", "");

                if(user.length()==0){
                    Intent intent = getActivity().getIntent();
                    String user1 = intent.getStringExtra("user");
                    user = user1;
                }
                if(validate()>0){
                    ThuThu thuThu = thuThuDao.getID(user);
                    thuThu.matKhau = edNewPass.getText().toString();
                    thuThuDao.update(thuThu);
                    if(thuThuDao.update(thuThu)>0){
                        Toast.makeText(getActivity(), "Thay đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
                        edOldPass.setText("");
                        edNewPass.setText("");
                        edReNewPass.setText("");
                        Intent intent = new Intent(getActivity(), TrangchuActivity.class);
                        startActivity(intent);
                    }else {
                        Toast.makeText(getActivity(), "Thay đổi mật khẩu thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
    private int validate(){
        int check = 1;
        int temp = 0;
        if(edOldPass.getText().length()==0){
           tilOldPass.setError("Bạn chưa nhập mật khẩu cũ!");
           temp++;
           check=-1;
        }else {
            tilOldPass.setError("");
        }
        if(edNewPass.getText().equals(edOldPass.getText().toString())){
            tilNewpass.setError("Mật khẩu mới không được trùng vói mật khẩu cũ!");
            temp++;
            check=-1;
        }else {
            tilNewpass.setError("");
        }
        if(edNewPass.getText().length()==0){
            tilNewpass.setError("Bạn chưa nhập mật khẩu mới!");
            temp++;
            check=-1;
        }else {
            tilNewpass.setError("");
        }
        if(edReNewPass.getText().length()==0){
            tilReNewPass.setError("Bạn chưa xác nhận lại mật khẩu!");
            temp++;
            check=-1;
        }else {
            tilReNewPass.setError("");
        }
        if(temp==0){
           SharedPreferences spf = getContext().getSharedPreferences("THONGTIN",Context.MODE_PRIVATE);
           String oldpass = spf.getString("PASSWORD", "");

           if(oldpass.length()==0){
               Intent intent = getActivity().getIntent();
               String oldPass1 = intent.getStringExtra("pass");
               oldpass = oldPass1;
           }
          String newpass = edNewPass.getText().toString();
           String reNewpass= edReNewPass.getText().toString();

           if(!oldpass.equals(edOldPass.getText().toString())){
               tilOldPass.setError("Mật khẩu cũ không đúng!");
               check=-1;
           }
           if(!newpass.equals(reNewpass)){
               tilReNewPass.setError("mật khẩu không trùng khớp!");
               check=-1;
           }

        }
        return check;
    }

    private void Anhxa(View view) {
        edOldPass = view.findViewById(R.id.pass_oldpass);
        edNewPass = view.findViewById(R.id.pass_newpass);
        edReNewPass = view.findViewById(R.id.pass_newpasscheck);
        tilOldPass = view.findViewById(R.id.pass_tilOldpass);
        tilNewpass= view.findViewById(R.id.pass_tilnewpass);
        tilReNewPass = view.findViewById(R.id.pass_tilnewpasscheck);
        btnSave = view.findViewById(R.id.pass_btnsave);
        btnCancel = view.findViewById(R.id.pass_btncancel);
        thuThuDao = new ThuThuDao(getActivity());
    }

}