package com.example.thuvienphuongnam.fragment;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thuvienphuongnam.Adapter.SachAdapter;
import com.example.thuvienphuongnam.Adapter.SpinnerAdapterLoaiSach;
import com.example.thuvienphuongnam.R;
import com.example.thuvienphuongnam.dao.LoaiSAchDao;
import com.example.thuvienphuongnam.dao.PhieuMuonDao;
import com.example.thuvienphuongnam.dao.SachDAO;
import com.example.thuvienphuongnam.model.LoaiSach;
import com.example.thuvienphuongnam.model.PhieuMuon;
import com.example.thuvienphuongnam.model.Sach;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class SachFragment extends Fragment {
    ListView listView;
    FloatingActionButton fab;
    Sach sach;
    SachDAO dao;
    ArrayList<Sach> arrayList;
    ArrayList<Sach> tempArrayListSach;
    SachAdapter adapterSach;
    EditText edmaSach,edTenSach,edGiaThue, edNamXB,search;
    TextInputLayout tilmasach,tilname,tilgiathue,tilNamXB;
    int a;
    int temp=0;
    String maloaiSach;
    ArrayList<LoaiSach> listLoaisach;
    LoaiSAchDao loaiSachDao;
    ArrayList<PhieuMuon> listPhieuMuon;
    PhieuMuonDao phieuMuonDao;
    SpinnerAdapterLoaiSach spinnerAdapter;
    Button btnSapxep,btnSapxepMa;
    public SachFragment() {
        // Required empty public constructor
    }


    public static SachFragment newInstance() {
        SachFragment fragment = new SachFragment();
        Bundle args = new Bundle();

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
        View view =inflater.inflate(R.layout.fragment_sach, container, false);
        Anhxa(view);
        getData();
        return view;
    }
    private void Anhxa(View view){
        listView = view.findViewById(R.id.listviewSach);
        fab = view.findViewById(R.id.fab_sach);
        dao = new SachDAO(getActivity());
        tempArrayListSach = dao.getAll();
        listLoaisach = new ArrayList<>();
        loaiSachDao =new LoaiSAchDao(getActivity());
        listLoaisach = loaiSachDao.getAll();
        phieuMuonDao = new PhieuMuonDao(getActivity());
        listPhieuMuon = phieuMuonDao.getDanhsachPhieumuon();
        btnSapxepMa = view.findViewById(R.id.sapxepTheoMa);
        btnSapxepMa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Collections.sort(arrayList, new Comparator<Sach>() {
                    @Override
                    public int compare(Sach sach, Sach t1) {
                        return  sach.getGiasach()- t1.getGiasach();
                    }
                });
                adapterSach.notifyDataSetChanged();
            }
        });
        btnSapxep = view.findViewById(R.id.sapxep);
        btnSapxep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Collections.sort(arrayList, new Comparator<Sach>() {
                    @Override
                    public int compare(Sach sach, Sach t1) {
                        return sach.getTenSach().compareTo(t1.getTenSach());
                    }
                });
                adapterSach.notifyDataSetChanged();
            }
        });
        search = view.findViewById(R.id.search);
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                arrayList.clear();
                for (Sach sach: tempArrayListSach) {
                    if(String.valueOf(sach.getMaSach()).contains(charSequence)){
                        arrayList.add(sach);
                    }
                }
                adapterSach.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                a=-1;
                if (listLoaisach.size()==0){
                    Toast.makeText(getActivity(), "Bạn chưa thêm loại sách", Toast.LENGTH_SHORT).show();
                }else{
                    openDialog(Gravity.CENTER);
                }
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
        dao = new SachDAO(getActivity());
        arrayList = dao.getAll();
        adapterSach = new SachAdapter(arrayList,getActivity());
        listView.setAdapter(adapterSach);
    }
    private void openDialog(int gravity){
        final Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_themsach);

        Window window = dialog.getWindow();
        if(window == null){
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams windowAttributes = window.getAttributes();
        windowAttributes.gravity = gravity;
        window.setAttributes(windowAttributes);

        if(Gravity.CENTER == gravity){
            dialog.setCancelable(true);
        }else{
            dialog.setCancelable(false);
        }
        TextView tvTile = (TextView) dialog.findViewById(R.id.dialog_sach_tile);


        edmaSach = dialog.findViewById(R.id.dialog_sach_txtmasach);
        edTenSach = dialog.findViewById(R.id.dialog_sach_txtname);
        edGiaThue = dialog.findViewById(R.id.dialog_sach_txtgiathue);
        edNamXB = dialog.findViewById(R.id.dialog_sach_txtNamXB);
        Spinner spinner = (Spinner) dialog.findViewById(R.id.dialog_spn_loaisach);

        tilmasach = dialog.findViewById(R.id.dialog_sach_til_masach);
        tilname = dialog.findViewById(R.id.dialog_sach_til_name);
        tilgiathue = dialog.findViewById(R.id.dialog_sach_til_giathue);
        tilNamXB = dialog.findViewById(R.id.dialog_sach_til_namXB);
        Button btnadd = dialog.findViewById(R.id.dialog_sach_add);
        Button btncancel = dialog.findViewById(R.id.dialog_sach_cancel);

        edGiaThue.setInputType(InputType.TYPE_CLASS_NUMBER);
        edNamXB.setInputType(InputType.TYPE_CLASS_NUMBER);
        spinnerAdapter = new SpinnerAdapterLoaiSach(getContext(),listLoaisach);
        spinner.setAdapter(spinnerAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                maloaiSach = String.valueOf(listLoaisach.get(i).getMaLoai());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        dao = new SachDAO(getActivity());

        if(a==-1){
           tilmasach.setEnabled(false);
        if(arrayList.size()==0){
          edmaSach.setText("1");
        }else {
           sach = dao.getAll().get(arrayList.size()-1);
           edmaSach.setText(String.valueOf(sach.getMaSach()+1));
        }

        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate();
             if(temp==0){
               sach.setTenSach(edTenSach.getText().toString());
               sach.setGiasach(Integer.parseInt(edGiaThue.getText().toString()));
               sach.setMaLoai(Integer.parseInt(maloaiSach));
               sach.setNamXB(Integer.parseInt(edNamXB.getText().toString()));
               if(dao.insert(sach)>0){
                   Toast.makeText(getActivity(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                   dialog.dismiss();
                   getData();
               }else {
                   Toast.makeText(getActivity(), "Thêm thất bại", Toast.LENGTH_SHORT).show();

               }
             }
            }
        });
        btncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Huỷ thêm", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        }else {
           sach = dao.getAll().get(a);
           tvTile.setText("Sửa/Xóa Sách");
           btnadd.setText("Sửa");
           btncancel.setText("Xóa");

            edmaSach.setText(String.valueOf(sach.getMaSach()));
            edmaSach.setEnabled(false);
            edTenSach.setText(sach.getTenSach());
            edGiaThue.setText(String.valueOf(sach.getGiasach()));
            edNamXB.setText(String.valueOf(sach.getNamXB()));
            for (int i=0; i<spinner.getCount();i++){
               if(arrayList.get(a).getMaLoai()== listLoaisach.get(i).getMaLoai() ){
                   spinner.setSelection(i);
               }
            }
           btnadd.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   validate();
                   if(temp==0){
                       sach.setTenSach(edTenSach.getText().toString());
                       sach.setGiasach(Integer.parseInt(edGiaThue.getText().toString()));
                       sach.setMaLoai(Integer.parseInt(maloaiSach));
                       sach.setNamXB(Integer.parseInt(edNamXB.getText().toString()));
                       if (dao.update(sach)>0){
                           Toast.makeText(getActivity(), "Sửa thành công", Toast.LENGTH_SHORT).show();
                           dialog.dismiss();
                           getData();
                       }else{
                           Toast.makeText(getActivity(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                       }
                   }
               }
           });
            btncancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listPhieuMuon = phieuMuonDao.getDanhsachPhieumuon();
                 for(int i=0;i<listPhieuMuon.size();i++){
                   if(listPhieuMuon.get(i).getMaSach()== sach.getMaSach()){
                     temp++;
                       Toast.makeText(getActivity(), "Không thể xoá sách có trong phiếu mượn", Toast.LENGTH_SHORT).show();
                       break;
                   }
                 }
                 if(dao.delete(String.valueOf(sach.getMaSach()))>0){
                     Toast.makeText(getActivity(), "Xoá thành công", Toast.LENGTH_SHORT).show();
                     dialog.dismiss();
                     getData();
                 }else {
                     Toast.makeText(getActivity(), "Xoá thất bại", Toast.LENGTH_SHORT).show();
                 }
                }
            });
        }
        dialog.show();
    }
    private void validate(){
        if(edTenSach.getText().length()==0){
            tilname.setError("Tên sách không được để trống");
            temp++;
        }else{
            tilname.setError("");
        }
        if(edGiaThue.getText().length()==0){
            tilgiathue.setError("Giá thuế không được để trống");
            temp++;
        }else{
            tilgiathue.setError("");
        }
        if(edNamXB.getText().length()==0){
            tilNamXB.setError("Năm xuất bản không được để trống");
            temp++;
        }else{
            tilNamXB.setError("");
        }
    }
}