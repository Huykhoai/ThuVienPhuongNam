package com.example.thuvienphuongnam.fragment;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.InputType;
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

import com.example.thuvienphuongnam.Adapter.ThuThuAdapter;
import com.example.thuvienphuongnam.R;
import com.example.thuvienphuongnam.dao.PhieuMuonDao;
import com.example.thuvienphuongnam.dao.ThuThuDao;
import com.example.thuvienphuongnam.model.PhieuMuon;
import com.example.thuvienphuongnam.model.ThuThu;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;


public class ThuthuFragment extends Fragment {
     ListView listView;
     FloatingActionButton fab;

     List<ThuThu> arrayList;
     ThuThuDao thuThuDao;
     ThuThu thuThu;
     ThuThuAdapter adapter;
     int a;
     int temp =0;

     EditText edMatt,edName,edPass;
     TextInputLayout tilMatt,tilName,tilPass;
    public ThuthuFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static ThuthuFragment newInstance(String param1, String param2) {
        ThuthuFragment fragment = new ThuthuFragment();
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_thuthu, container, false);
        Anhxa(view);
        getData();
        return view;
    }

    private void Anhxa(View view) {
        fab = view.findViewById(R.id.add_fab);
        listView = view.findViewById(R.id.add_listview);
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
                a=i;
                openDialog(Gravity.CENTER);
            }
        });
    }
    private void getData(){
        thuThuDao = new ThuThuDao(getActivity());
        arrayList = thuThuDao.getAll();
        adapter = new ThuThuAdapter(arrayList,getActivity());
        listView.setAdapter(adapter);
    }
    private void openDialog(int gravity){
        Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_themtt);

        Window window = dialog.getWindow();
        if(window==null){
            return;
        }
       window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams windowAttributes = window.getAttributes();
        windowAttributes.gravity = gravity;
        window.setAttributes(windowAttributes);

        if(Gravity.CENTER == gravity){
            dialog.setCancelable(true);
        }else {
            dialog.setCancelable(false);
        }
        TextView tvTitile = dialog.findViewById(R.id.item_tvtile);
        edMatt = dialog.findViewById(R.id.item_txtnameuser);
        edName = dialog.findViewById(R.id.item_txtname);
        edPass = dialog.findViewById(R.id.item_txtpass);

        tilMatt = dialog.findViewById(R.id.add_til_username);
        tilName = dialog.findViewById(R.id.add_til_name);
        tilPass = dialog.findViewById(R.id.add_til_pass);

        Button btnAdd = dialog.findViewById(R.id.dialog_add_add);
        Button btnCacel = dialog.findViewById(R.id.dialog_add_cancel);

        thuThuDao = new ThuThuDao(getActivity());
        if(a==-1){
            btnAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    validate();
                       thuThu = new ThuThu();
                    for (int i=0;i<arrayList.size();i++){
                        if(arrayList.get(i).getMaTT().equals(edMatt.getText().toString())){
                            tilMatt.setError("Mã thủ thư đã tồn tại!");
                            temp++;
                            break;
                        }
                    }
                    if(temp==0){
                        thuThu.setMaTT(edMatt.getText().toString());
                        thuThu.setHoTen(edName.getText().toString());
                        thuThu.setMatKhau(edPass.getText().toString());

                        if(thuThuDao.insert(thuThu)>0){
                            Toast.makeText(getActivity(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                            getData();
                        }else {
                            Toast.makeText(getActivity(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });
            btnCacel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getActivity(), "Huỷ thêm", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            });
        }else {
            tvTitile.setText("Sửa/Xóa Thủ Thư");
            btnAdd.setText("Sửa");
            btnCacel.setText("Xóa");

            thuThu = thuThuDao.getAll().get(a);

            edPass.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

            edMatt.setText(thuThu.getMaTT());
            edMatt.setEnabled(false);
            edName.setText(thuThu.getHoTen());
            edPass.setText(thuThu.getMatKhau());
            if (thuThu.getMaTT().equals("admin")){
                Toast.makeText(getActivity(), "Không thể xóa hoặc sửa admin", Toast.LENGTH_SHORT).show();
                return;
            }
            btnAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                  validate();
                    if(temp==0){
                        thuThu.setMaTT(edMatt.getText().toString());
                        thuThu.setHoTen(edName.getText().toString());
                        thuThu.setMatKhau(edPass.getText().toString());

                        if(thuThuDao.update(thuThu)>0){
                            Toast.makeText(getActivity(), "Sửa thành công", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                            getData();
                        }else {
                            Toast.makeText(getActivity(), "Sửa thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });
            btnCacel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    PhieuMuonDao phieuMuonDao = new PhieuMuonDao(getActivity());
                    ArrayList<PhieuMuon> phiemuonList = phieuMuonDao.getDanhsachPhieumuon();
                    for (int i=0;i<phiemuonList.size();i++){
                        if(thuThu.getMaTT() == phiemuonList.get(i).getMaTT()){
                            temp++;
                            Toast.makeText(getActivity(), "Không thể xoá thủ thư có trong phiếu mượn", Toast.LENGTH_SHORT).show();
                            break;
                        }
                    }

                    if(temp==0){
                        if(thuThuDao.delete(thuThu.getMaTT())>0){
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
    private void validate(){
        if(edMatt.getText().length()==0){
            tilMatt.setError("Mã Thủ Thư không được để trống");
            temp++;
        }else{
            tilMatt.setError("");
        }
        if(edName.getText().length()==0){
            tilName.setError("Tên Thủ Thư không được để trống");
            temp++;
        }else{
            tilName.setError("");
        }
        if(edPass.getText().length()==0){
            tilPass.setError("Mật khẩu không được để trống");
            temp++;
        }else{
            tilPass.setError("");
        }
    }

}