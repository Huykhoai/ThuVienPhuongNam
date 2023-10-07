package com.example.thuvienphuongnam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.thuvienphuongnam.Trangchu.TrangchuActivity;
import com.example.thuvienphuongnam.dao.SachDAO;
import com.example.thuvienphuongnam.dao.ThuThuDao;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {
    EditText edUser,edPass;
    Button btnDangnhap;
    String strUser,strPass;
    CheckBox cb;
    int temp=0;
    ThuThuDao thuThuDao ;
    TextInputLayout tilpass,tiluser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Đăng Nhập");
        setContentView(R.layout.activity_main);
        edUser = findViewById(R.id.login_edusername);
        edPass = findViewById(R.id.login_edpassword);
        tiluser = findViewById(R.id.login_tilusername);
        tilpass = findViewById(R.id.login_tilpassword);
        btnDangnhap = findViewById(R.id.login_btnlogin);
        cb= findViewById(R.id.login_checkBox);


        SharedPreferences pref =getSharedPreferences("THONGTIN",MODE_PRIVATE);
        edUser.setText(pref.getString("USERNAME", ""));
        edPass.setText(pref.getString("PASSWORD", ""));
        cb.setChecked(pref.getBoolean("REMEMBER", false));
        btnDangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkLogin();
            }

        });
    }
    private void checkLogin() {
        thuThuDao = new ThuThuDao(getApplicationContext());
        strUser = edUser.getText().toString();
        strPass = edPass.getText().toString();
        if (strUser.isEmpty()){
            tiluser.setError("Tên đăng nhập không được để trống");
            temp++;
        }else {
            tiluser.setError("");
        }
        if(strPass.isEmpty()){
            tilpass.setError("Mật khẩu đăng nhập không được để trống");
            temp++;
        }else {
            tilpass.setError("");
        }
        if(temp==0){
            if(thuThuDao.CheckThuthu(strUser,strPass)>0){
                tiluser.setError("");
                tilpass.setError("");
                Toast.makeText(this, "Login Thành công", Toast.LENGTH_SHORT).show();
                rememberUser(strUser,strPass,cb.isChecked());

                Intent intent = new Intent(this, TrangchuActivity.class);
                intent.putExtra("user", strUser);
                intent.putExtra("pass", strPass);
                startActivity(intent);
                finish();
            }else {
                tiluser.setError("Tên đăng nhập hoặc mật khẩu không đúng");
                tilpass.setError("Tên đăng nhập hoặc mật khẩu không đúng");
            }

        }
    }
    private void rememberUser(String u, String p, boolean status){
        SharedPreferences spf = getSharedPreferences("THONGTIN", MODE_PRIVATE);
        SharedPreferences.Editor editor =spf.edit();
        if(!status){
            editor.clear();
        }else {
            editor.putString("USERNAME", u);
            editor.putString("PASSWORD",p);
            editor.putBoolean("REMENBER", status);
        }
        editor.commit();
    }
}