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
import android.widget.TextView;
import android.widget.Toast;

import com.example.thuvienphuongnam.Adapter.ThanhVienAdapter;
import com.example.thuvienphuongnam.R;
import com.example.thuvienphuongnam.dao.PhieuMuonDao;
import com.example.thuvienphuongnam.dao.ThanhVienDao;
import com.example.thuvienphuongnam.model.PhieuMuon;
import com.example.thuvienphuongnam.model.ThanhVien;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;


public class ThanhvienFragment extends Fragment {
    FloatingActionButton fab;
    ListView listView;
    ArrayList<ThanhVien> listTV;
    ArrayList<ThanhVien> tempListTv;
    ThanhVienAdapter thanhVienAdapter;
    ThanhVienDao thanhVienDao;
    ThanhVien thanhVien;
    int a;
    int temp = 0;
    EditText edMatv;
    EditText edTenTv;
    EditText edNamSinh;
    EditText search;
    TextInputLayout tilMatv, tilName, tilNamsinh, tvCccd;

    ArrayList<PhieuMuon> listPhieumuon;
    PhieuMuonDao phieuMuonDao;

    public ThanhvienFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static ThanhvienFragment newInstance() {
        ThanhvienFragment fragment = new ThanhvienFragment();
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
        View view = inflater.inflate(R.layout.fragment_thanhvien, container, false);
        Anhxa(view);
        getData();
        return view;
    }

    private void getData() {
        thanhVienDao = new ThanhVienDao(getActivity());
        listTV = thanhVienDao.getALL();
        thanhVienAdapter = new ThanhVienAdapter(listTV, getContext());
        listView.setAdapter(thanhVienAdapter);
    }

    private void openDialog(int gravity) {
        Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_themtt);

        Window window = dialog.getWindow();
        if (window == null) {
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams windowArributes = window.getAttributes();
        windowArributes.gravity = gravity;
        window.setAttributes(windowArributes);

        if (Gravity.CENTER == gravity) {
            dialog.setCancelable(true);
        } else {
            dialog.setCancelable(false);
        }

        TextView title = dialog.findViewById(R.id.item_tvtile);

        edMatv = dialog.findViewById(R.id.item_txtnameuser);
        edTenTv = dialog.findViewById(R.id.item_txtname);
        edNamSinh = dialog.findViewById(R.id.item_txtpass);
        //edCccd = dialog.findViewById(R.id.item_tv_cccd);


        tilMatv = dialog.findViewById(R.id.add_til_username);
        tilName = dialog.findViewById(R.id.add_til_name);
        tilNamsinh = dialog.findViewById(R.id.add_til_pass);
       // tvCccd = dialog.findViewById(R.id.add_til_cccd);

        Button btnAdd = dialog.findViewById(R.id.dialog_add_add);
        Button btnCancel = dialog.findViewById(R.id.dialog_add_cancel);

        thanhVienDao = new ThanhVienDao(getActivity());
        edNamSinh.setInputType(InputType.TYPE_CLASS_NUMBER);

        if (a == -1) {
            title.setText("Thêm Thành Viên");

            tilMatv.setHint("Mã Thành Viên");
            tilName.setHint("Tên Thành Viên");
            tilNamsinh.setHint("Năm Sinh");

            edMatv.setEnabled(false);
            if (listTV.size() == 0) {
                edMatv.setText("1");
            } else {
                thanhVien = thanhVienDao.getALL().get(listTV.size() - 1);
                edMatv.setText(String.valueOf(thanhVien.getMaTV() + 1));
            }
            btnAdd.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    validate();

                    if (temp == 0) {
                        thanhVien.setHoTen(edTenTv.getText().toString());
                        thanhVien.setNamSinh(String.valueOf(edNamSinh.getText().toString()));
                        //thanhVien.setCccd(edCccd.getText().toString());
                        if (thanhVienDao.insert(thanhVien) > 0) {
                            Toast.makeText(getActivity(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                            getData();
                        } else {
                            Toast.makeText(getActivity(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });
            btnCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });
        } else {
            title.setText("Sửa Xóa Thành Viên");
            tilMatv.setHint("Mã Thành Viên");
            tilName.setHint("Tên Thành Viên");
            tilNamsinh.setHint("Năm Sinh");

            thanhVien = thanhVienDao.getALL().get(a);

            edMatv.setText(String.valueOf(thanhVien.getMaTV()));
            edMatv.setEnabled(false);
            edTenTv.setText(thanhVien.getHoTen());
            edNamSinh.setText(String.valueOf(thanhVien.getNamSinh()));
            //edCccd.setText(thanhVien.getCccd());

            btnAdd.setText("Sửa");
            btnCancel.setText("Xóa");

            btnAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    validate();
                    if (temp == 0) {
                        thanhVien.setHoTen(edTenTv.getText().toString());
                        thanhVien.setNamSinh(String.valueOf(edNamSinh.getText().toString()));
                        //thanhVien.setCccd(edCccd.getText().toString());

                        if (thanhVienDao.update(thanhVien) > 0) {
                            Toast.makeText(getActivity(), "Sửa thành công", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                            getData();
                        } else {
                            Toast.makeText(getActivity(), "Sửa thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });
            btnCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    for (int i = 0; i < listPhieumuon.size(); i++) {
                        if (thanhVien.getMaTV() == listPhieumuon.get(i).getMaTV()) {
                            Toast.makeText(getActivity(), "không thể xóa thành viên có trong phiếu mượn", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if (temp == 0) {
                            if (thanhVienDao.delete(String.valueOf(thanhVien.getMaTV())) > 0) {
                                Toast.makeText(getActivity(), "Xóa Thành công", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                                getData();
                            } else {
                                Toast.makeText(getActivity(), "Xóa Thất Bại", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }
            });
        }
        dialog.show();
    }

    private void Anhxa(View view) {
        fab = view.findViewById(R.id.qlthanhvien_fab);
        listView = view.findViewById(R.id.qlthanhvien_listview);
        phieuMuonDao = new PhieuMuonDao(getContext());
        listPhieumuon = phieuMuonDao.getDanhsachPhieumuon();
        thanhVienDao = new ThanhVienDao(getActivity());
        tempListTv = thanhVienDao.getALL();
        search = view.findViewById(R.id.saerch);
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                listTV.clear();
                for (ThanhVien tv: tempListTv) {
                    if(String.valueOf(tv.getMaTV()).contains(charSequence.toString())){
                        listTV.add(tv);
                    }
                }
                thanhVienAdapter.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                a = -1;
                openDialog(Gravity.CENTER);
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                a = i;
                openDialog(Gravity.CENTER);
            }
        });
    }

    private void validate() {
        if (edTenTv.getText().length() == 0) {
            tilName.setError("Tên thành viên không được để trống");
            temp++;
        } else {
            tilName.setError("");
        }
        if (edNamSinh.getText().length() == 0) {
            tilNamsinh.setError("Năm Sinh không được để trống");
            temp++;
        } else {
            tilNamsinh.setError("");
        }
//        if (edCccd.getText().length() == 0) {
//            tvCccd.setError("CCCD không được để trống");
//            temp++;
//        } else {
//            tvCccd.setError("");
//        }
    }
}