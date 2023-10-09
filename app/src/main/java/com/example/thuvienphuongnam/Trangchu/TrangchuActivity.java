package com.example.thuvienphuongnam.Trangchu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.thuvienphuongnam.R;
import com.example.thuvienphuongnam.dao.ThuThuDao;
import com.example.thuvienphuongnam.fragment.DangxuatFragment;
import com.example.thuvienphuongnam.fragment.DoanhthuFragment;
import com.example.thuvienphuongnam.fragment.LoaiSachFragment;
import com.example.thuvienphuongnam.fragment.MatkhauFragment;
import com.example.thuvienphuongnam.fragment.PhieuMuonFragment;
import com.example.thuvienphuongnam.fragment.SachFragment;
import com.example.thuvienphuongnam.fragment.ThanhvienFragment;
import com.example.thuvienphuongnam.fragment.ThuthuFragment;
import com.example.thuvienphuongnam.fragment.TopFragment;
import com.example.thuvienphuongnam.model.ThuThu;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class TrangchuActivity extends AppCompatActivity {
      Toolbar toolbar;
      NavigationView navigationView;
      DrawerLayout drawerLayout;
      List<ThuThu> thuthuList;
      ThuThuDao thuThuDao;
      ThuThu thuthu;
      TextView nameuser;
      View view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trangchu);
        toolbar = findViewById(R.id.toolBar);
        navigationView = findViewById(R.id.navigation);
        drawerLayout = findViewById(R.id.drawerLayout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout,toolbar, 0,0);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        replaceFragment(new SachFragment());
        navigationView.getMenu().findItem(R.id.mQLSach).setChecked(true);
        toolbar.setNavigationIcon(R.drawable.baseline_menu_24);
        thuthuList = new ArrayList<>();
        thuThuDao = new ThuThuDao(getApplicationContext());
        view =navigationView.getHeaderView(0);
        nameuser = view.findViewById(R.id.login_nameuser);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout = findViewById(R.id.drawerLayout);
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                if (item.getItemId()==R.id.mQLPhieuMuon){
                    fragment = new PhieuMuonFragment();
                }
                if (item.getItemId()==R.id.mQLLoaiSach){
                    fragment = new LoaiSachFragment();

                }
                if (item.getItemId()==R.id.mQLSach){
                    fragment = new SachFragment();

                }
                if (item.getItemId()==R.id.mQLThanhVien){
                    fragment = new ThanhvienFragment();

                }
                if (item.getItemId()==R.id.mQLMatKhau){
                    fragment = new MatkhauFragment();

                }
                if (item.getItemId()==R.id.mQLDangXuat){
                    fragment = new DangxuatFragment();

                }
                if (item.getItemId()==R.id.mQLDoanhThu){
                    fragment = new DoanhthuFragment();

                }
                if (item.getItemId()==R.id.mQLTopSach){
                    fragment = new TopFragment();

                }
                if (item.getItemId()==R.id.mQLThem){
                    fragment = new ThuthuFragment();

                }
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.frameLayout, fragment)
                        .commit();
                drawerLayout.closeDrawer(GravityCompat.START);
                setTitle(item.getTitle());

                return false;
            }
        });

        Intent intent = getIntent();
        String user = intent.getStringExtra("user");
        thuthuList = thuThuDao.getAll();
        if(user!= null && user.equalsIgnoreCase("admin")){
             navigationView.getMenu().findItem(R.id.mQLThem).setVisible(true);
        }else {
            navigationView.getMenu().findItem(R.id.mQLThem).setVisible(false);
        }
        for (int i=0;i<thuthuList.size();i++){
           if(thuthuList.get(i).getMaTT().equals(user)){
             nameuser.setText("Xin Chào Thủ Thư: "+thuthuList.get(i).getHoTen());
             return;
           }
        }
    }
    private void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frameLayout, fragment);
        transaction.addToBackStack(null); // Nếu bạn muốn thêm vào Back Stack
        setTitle("Sách");
        transaction.commit();
    }
}