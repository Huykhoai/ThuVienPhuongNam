package com.example.thuvienphuongnam.fragment;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thuvienphuongnam.R;
import com.example.thuvienphuongnam.dao.ThongkeDao;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;


public class DoanhthuFragment extends Fragment {

       Button btnDoanhthu;
       ImageView btnTuNgay,btnDenNgay;
       EditText edTuNgay,edDenNgay;
       SimpleDateFormat spd =new SimpleDateFormat("yyyy-MM-dd");
       int mYear,mMonth,mDay;
       TextView tv;
       int temp =0;
    DatePickerDialog.OnDateSetListener dateDenNgay,dateTuNgay;

    public DoanhthuFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static DoanhthuFragment newInstance() {
        DoanhthuFragment fragment = new DoanhthuFragment();
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
        View view = inflater.inflate(R.layout.fragment_doanhthu, container, false);
        Anhxa(view);
        buttonDanhThu();

         dateTuNgay =new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
              mYear = i;
              mMonth = i1;
              mDay=i2;
                GregorianCalendar c = new GregorianCalendar(mYear,mMonth,mDay);
                edTuNgay.setText(spd.format(c.getTime()));
            }
        };
         dateDenNgay = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfYear) {
                mYear = year;
                mMonth = monthOfYear;
                mDay = dayOfYear;
                GregorianCalendar c = new GregorianCalendar(mYear,mMonth,mDay);
                edDenNgay.setText(spd.format(c.getTime()));
            }
        };
        return view;
    }

    private void buttonDanhThu() {
        btnTuNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dpd =new DatePickerDialog(getActivity(),0,dateTuNgay,mYear,mMonth,mDay);
                dpd.show();
            }
        });
        btnDenNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dpd =new DatePickerDialog(getActivity(),0,dateDenNgay,mYear,mMonth,mDay);
                dpd.show();
            }
        });
        btnDoanhthu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tuNgay = edTuNgay.getText().toString();
                String denNgay = edDenNgay.getText().toString();
            if(tuNgay.isEmpty()||denNgay.isEmpty()){
                Toast.makeText(getActivity(), "Không được để trống!", Toast.LENGTH_SHORT).show();
                temp++;
            }else {
                String[] temptuNgay = tuNgay.split("-");
                String[] tempdenNgay = denNgay.split("-");

                String newTuNgay ="";
                String newDenNgay="";

                int intTuNgay = Integer.parseInt(newTuNgay.concat(temptuNgay[0]).concat(temptuNgay[1]).concat(temptuNgay[2]));
                int intDenNgay = Integer.parseInt(newDenNgay.concat(tempdenNgay[0]).concat(tempdenNgay[1]).concat(tempdenNgay[2]));
                if(intTuNgay>intDenNgay){
                    Toast.makeText(getActivity(), "Lỗi, từ ngày phải bé hơn đến ngày", Toast.LENGTH_SHORT).show();
                    temp++;
                }
            }
            if(temp==0){
                ThongkeDao dao = new ThongkeDao(getActivity());
                tv.setTextColor(Color.WHITE);
                tv.setText("Doanh Thu: "+ dao.getDoanhThu(tuNgay,denNgay));
            }
            }
        });
    }

    private void Anhxa(View view) {
        btnDoanhthu = view.findViewById(R.id.doanhthu_btn_tinh);
        btnTuNgay = view.findViewById(R.id.doanhthu_img_tungay);
        btnDenNgay = view.findViewById(R.id.doanhthu_img_denngay);
        edTuNgay = view.findViewById(R.id.doanhthu_edt_tungay);
        edDenNgay = view.findViewById(R.id.doanhthu_edt_denngay);
        tv = view.findViewById(R.id.doanhthu_tv);


    }

}