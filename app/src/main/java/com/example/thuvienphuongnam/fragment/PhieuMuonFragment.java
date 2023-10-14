package com.example.thuvienphuongnam.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thuvienphuongnam.Adapter.PhieuMuonAdapter;
import com.example.thuvienphuongnam.Adapter.SpinnerAdapterSach;
import com.example.thuvienphuongnam.Adapter.SpinnerAdapterThanhvien;
import com.example.thuvienphuongnam.R;
import com.example.thuvienphuongnam.dao.PhieuMuonDao;
import com.example.thuvienphuongnam.dao.SachDAO;
import com.example.thuvienphuongnam.dao.ThanhVienDao;
import com.example.thuvienphuongnam.model.PhieuMuon;
import com.example.thuvienphuongnam.model.Sach;
import com.example.thuvienphuongnam.model.ThanhVien;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;


public class PhieuMuonFragment extends Fragment {
    ListView listView;
    FloatingActionButton  fab;

    SpinnerAdapterSach spinnerAdapterSach;
    SpinnerAdapterThanhvien  spinnerAdapterThanhvien;

    ArrayList<PhieuMuon> listPhieumuon;
    PhieuMuonDao phieuMuonDao;
    PhieuMuon phieuMuon;
    ArrayList<ThanhVien> listThanhvien;
    ThanhVienDao thanhVienDao;
    ArrayList<Sach> listSach;
    SachDAO sachDAO;
    Sach sach;
    String maThanhVien,maSach;
   int a;
    Calendar c =Calendar.getInstance();
    SimpleDateFormat spd = new SimpleDateFormat("yyyy-MM-dd");
    public PhieuMuonFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static PhieuMuonFragment newInstance() {
        PhieuMuonFragment fragment = new PhieuMuonFragment();
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
        View view = inflater.inflate(R.layout.fragment_phieu_muon,container,false);
        AnhXa(view);
        getData();
        return view;
    }

    private void getData() {
         phieuMuonDao = new PhieuMuonDao(getContext());
         listPhieumuon = phieuMuonDao.getDanhsachPhieumuon();
        PhieuMuonAdapter adapter = new PhieuMuonAdapter(listPhieumuon,getContext());
        listView.setAdapter(adapter);
    }

    private void AnhXa(View view){
          listView = view.findViewById(R.id.listviewPhieumuon);
          fab = view.findViewById(R.id.floattingbtn);
           phieuMuon = new PhieuMuon();
          thanhVienDao = new ThanhVienDao(getActivity());
          listThanhvien = thanhVienDao.getALL();
          sachDAO = new SachDAO(getActivity());
          listSach = sachDAO.getAll();
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
    private void openDialog(int gravity){
        Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_phieumuon);


        Window window = dialog.getWindow();
        if(window== null){
            return;
        }
        //set chieu dai chieu rong
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.WHITE));

        WindowManager.LayoutParams windowAttributes =window.getAttributes();
        windowAttributes.gravity = gravity;
        window.setAttributes(windowAttributes);

        if(Gravity.CENTER==gravity){
            dialog.setCancelable(true);
        }else {
            dialog.setCancelable(false);
        }

        TextView tvTilte= dialog.findViewById(R.id.dialog_phieumuon_title);
        TextView tvNgay = dialog.findViewById(R.id.dialog_phieumuon_ngaythue);
        TextView tvTienthue = dialog.findViewById(R.id.dialog_phieumuon_tienthue);

        EditText edMaphieumuon = dialog.findViewById(R.id.dialog_phieumuon_txtmaphiuemuon);
        TextInputLayout tilMaphieumuon = dialog.findViewById(R.id.dialog_tilMaphieumuon);

        Spinner spn_tenThanhVien = dialog.findViewById(R.id.dialog_spn_tenthanhvien);
        Spinner spn_tenSach = dialog.findViewById(R.id.dialog_spn_tensach);

        Button btnAdd= dialog.findViewById(R.id.dialog_phieumuon_add);
        Button btnCancel = dialog.findViewById(R.id.dialog_phieumuon_cancel);

        CheckBox checkBox = dialog.findViewById(R.id.dialog_phieumuon_checkBox);

        spinnerAdapterThanhvien = new SpinnerAdapterThanhvien(getActivity(), listThanhvien );
        spn_tenThanhVien.setAdapter(spinnerAdapterThanhvien);
        spn_tenThanhVien.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
               maThanhVien = String.valueOf(listThanhvien.get(i).getMaTV());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spinnerAdapterSach = new SpinnerAdapterSach(getActivity(),listSach);
        spn_tenSach.setAdapter(spinnerAdapterSach);
        spn_tenSach.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                maSach = String.valueOf(listSach.get(i).getMaSach());
                sach = sachDAO.getAll().get(i);
                tvTienthue.setText(String.valueOf(sach.getGiasach()));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        phieuMuonDao = new PhieuMuonDao(getActivity());
        tilMaphieumuon.setEnabled(false);

        String datetime = spd.format(c.getTime());

        Intent intent = getActivity().getIntent();
        String user = intent.getStringExtra("user");

         if(a==-1){
           tvNgay.setText(datetime);
           checkBox.setEnabled(false);

           listPhieumuon = phieuMuonDao.getDanhsachPhieumuon();
           if(listPhieumuon.size()==0){
               edMaphieumuon.setText("1");
           }else {
               phieuMuon = phieuMuonDao.getDanhsachPhieumuon().get(listPhieumuon.size()-1);
               edMaphieumuon.setText(String.valueOf(phieuMuon.getMaPM()+1));
           }
           btnAdd.setOnClickListener(new View.OnClickListener() {

               @Override
               public void onClick(View view) {
                 phieuMuon.setMaTT(user);
                 phieuMuon.setMaSach(Integer.parseInt(maSach));
                 phieuMuon.setMaTV(Integer.parseInt(maThanhVien));
                 phieuMuon.setNgay(datetime);
                 phieuMuon.setTienThue(Integer.parseInt(tvTienthue.getText().toString()));
                 phieuMuon.setTraSach(0);

                 if(phieuMuonDao.insert(phieuMuon)>0){
                     Toast.makeText(getActivity(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                     dialog.dismiss();
                     getData();
                 }else {
                     Toast.makeText(getActivity(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                 }
               }
           });
           btnCancel.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                dialog.dismiss();
               }
           });
        }else {
           tvTilte.setText("Sửa Xóa Phiếu Mượn");
           btnAdd.setText("Sửa");
           btnCancel.setText("Xóa");
           phieuMuon = phieuMuonDao.getDanhsachPhieumuon().get(a);

           edMaphieumuon.setText(String.valueOf(phieuMuon.getMaPM()));
           tvNgay.setText(String.valueOf(phieuMuon.getNgay()));
           if(phieuMuon.getTraSach()==1){
               checkBox.setChecked(true);
           }else {
               checkBox.setChecked(false);
           }

           for (int i=0;i<spn_tenThanhVien.getCount();i++){
              if(listPhieumuon.get(a).getMaTV() == listThanhvien.get(i).getMaTV()){
                  spn_tenThanhVien.setSelection(i);
              }
           }
           for(int i=0;i<spn_tenSach.getCount();i++){
               if(listPhieumuon.get(a).getMaSach() == listSach.get(i).getMaSach()){
                   spn_tenSach.setSelection(i);
               }
           }
           btnAdd.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   phieuMuon.setMaTT(user);
                   phieuMuon.setMaPM(Integer.parseInt(edMaphieumuon.getText().toString()));
                   phieuMuon.setMaTV(Integer.parseInt(maThanhVien));
                   phieuMuon.setMaSach(Integer.parseInt(maSach));
                   phieuMuon.setNgay(tvNgay.getText().toString());
                   phieuMuon.setTienThue(Integer.parseInt(tvTienthue.getText().toString()));
                   if(checkBox.isChecked()){
                     phieuMuon.setTraSach(1);
                   }else {
                       phieuMuon.setTraSach(0);
                   }
                   if(phieuMuonDao.update(phieuMuon)>0){
                       Toast.makeText(getActivity(), "Sửa thành công", Toast.LENGTH_SHORT).show();
                       dialog.dismiss();
                       getData();
                   }else {
                       Toast.makeText(getActivity(), "Sửa thất bại", Toast.LENGTH_SHORT).show();
                   }
               }
           });
           btnCancel.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   if(phieuMuonDao.delete(String.valueOf(phieuMuon.getMaPM()))>0){
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
}
