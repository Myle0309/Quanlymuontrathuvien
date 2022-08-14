package com.example.dell.QuanLyMuonTraThuVien.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.dell.QuanLyMuonTraThuVien.Constant;
import com.example.dell.QuanLyMuonTraThuVien.R;
import com.example.dell.QuanLyMuonTraThuVien.model.NguoiDung;
import com.google.gson.Gson;

public class HomeActivity extends AppCompatActivity {

    private LinearLayout docgia;
    private LinearLayout thoat;
    private LinearLayout dkTheThuVien;
    private LinearLayout sach;
    private LinearLayout muonsach;
    private LinearLayout trasach;
    private LinearLayout dkPhieuMuon;
    private SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        docgia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, NguoidungActivity.class);
                startActivity(intent);
            }
        });
        sach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent b = new Intent(HomeActivity.this, SachActivity.class);
                startActivity(b);
            }
        });
        muonsach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String defaultUser = new Gson().toJson(new NguoiDung());
                NguoiDung user = new Gson().fromJson(pref.getString(Constant.KEY_USER, defaultUser), NguoiDung.class);
                if (user.getHasLibraryCard() != null && user.getHasLibraryCard()) {
                    Intent c = new Intent(HomeActivity.this, MuonSachActivity.class);
                    startActivity(c);
                } else {
                    Toast.makeText(HomeActivity.this, "Bạn chưa có thẻ thư viện vui lòng đăng ký trước", Toast.LENGTH_SHORT).show();
                }
            }
        });
        dkPhieuMuon.setOnClickListener(v -> {
            String defaultUser = new Gson().toJson(new NguoiDung());
            NguoiDung user = new Gson().fromJson(pref.getString(Constant.KEY_USER, defaultUser), NguoiDung.class);
            if (user.getHasLibraryCard() != null && user.getHasLibraryCard()) {
                Intent c = new Intent(HomeActivity.this, DangKyPhieuMuonActivity.class);
                startActivity(c);
            } else {
                Toast.makeText(HomeActivity.this, "Bạn chưa có thẻ thư viện vui lòng đăng ký trước", Toast.LENGTH_SHORT).show();
            }

        });

        dkTheThuVien.setOnClickListener(v -> {
            Intent c = new Intent(HomeActivity.this, DangKyTheThuVienActivity.class);
            startActivity(c);
        });
        thoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
                builder.setMessage("Bạn có muốn thoát chương trình ?");
                builder.setTitle("Bạn có muốn thoát chương trình ?");
                builder.setPositiveButton("Ok", (dialogInterface, i) -> {
                    finish();
                });
                builder.setNegativeButton("Cancel", (dialogInterface, i) -> {
                    dialogInterface.cancel();
                });
                builder.create();
                builder.show();
            }
        });
    }

    private void initView() {
        docgia = (LinearLayout) findViewById(R.id.Docgia);
        thoat = (LinearLayout) findViewById(R.id.Thoat);
        dkTheThuVien = (LinearLayout) findViewById(R.id.theloai);
        sach = (LinearLayout) findViewById(R.id.sach);
        muonsach = (LinearLayout) findViewById(R.id.Muonsach);
        dkPhieuMuon = findViewById(R.id.btnDkPhieu);
    }
}
