package com.example.thuvienphuongnam.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.thuvienphuongnam.MainActivity;
import com.example.thuvienphuongnam.R;
import com.example.thuvienphuongnam.Trangchu.TrangchuActivity;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DangxuatFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DangxuatFragment extends Fragment {


    public DangxuatFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static DangxuatFragment newInstance() {
        DangxuatFragment fragment = new DangxuatFragment();
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
        View view = inflater.inflate(R.layout.fragment_dangxuat, container, false);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent =new Intent(getContext(), MainActivity.class);
                startActivity(intent);
            }
        }, 3000);
        return view;
    }
}