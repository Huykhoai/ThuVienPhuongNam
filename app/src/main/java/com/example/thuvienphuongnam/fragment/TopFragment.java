package com.example.thuvienphuongnam.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.thuvienphuongnam.Adapter.TopAdapter;
import com.example.thuvienphuongnam.R;
import com.example.thuvienphuongnam.dao.ThongkeDao;
import com.example.thuvienphuongnam.model.Top;

import java.util.ArrayList;
import java.util.List;


public class TopFragment extends Fragment {
    ListView listView;
    List<Top> arrayList;
    TopAdapter adapter;
    ThongkeDao dao;


    public TopFragment() {
        // Required empty public constructor
    }


    public static TopFragment newInstance() {
        TopFragment fragment = new TopFragment();
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
        View view =inflater.inflate(R.layout.fragment_top, container, false);
        listView = view.findViewById(R.id.top_listview);
        dao = new ThongkeDao(getActivity());
        arrayList = dao.getTop();
        adapter = new TopAdapter(arrayList,getContext());
        listView.setAdapter(adapter);
        return view;
    }
}