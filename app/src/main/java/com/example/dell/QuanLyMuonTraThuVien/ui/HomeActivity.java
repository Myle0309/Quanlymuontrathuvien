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
import com.example.dell.QuanLyMuonTraThuVien.dao.NguoiDungDao;
import com.example.dell.QuanLyMuonTraThuVien.dao.SachDao;
import com.example.dell.QuanLyMuonTraThuVien.model.NguoiDung;
import com.google.gson.Gson;

public class HomeActivity extends AppCompatActivity {

    private LinearLayout docgia;
    private LinearLayout thoat;
    private LinearLayout dkTheThuVien;
    private LinearLayout dsSachDaMuon;
    private LinearLayout muonsach;
    private LinearLayout trasach;
    private LinearLayout dkPhieuMuon;
    private SharedPreferences pref;
    private LinearLayout thethuvien;

    NguoiDungDao nguoiDungDao;
    String defaultUser;
    NguoiDung user;

    SachDao sachDao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();
        nguoiDungDao = new NguoiDungDao(getApplicationContext());
        sachDao= new SachDao(getApplicationContext());
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        getUser();
        docgia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, NguoidungActivity.class);
                startActivity(intent);
            }
        });
        dsSachDaMuon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent b = new Intent(HomeActivity.this, DanhSachSachActivity.class);
                startActivity(b);
            }
        });
        muonsach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (user.getTheThuVien().getHasPhieuMuon()) {
                    Intent c = new Intent(HomeActivity.this, MuonSachActivity.class);
                    startActivity(c);
                } else {
                    Toast.makeText(HomeActivity.this, "Bạn chưa phiếu mượn vui lòng đăng ký trước", Toast.LENGTH_SHORT).show();
                }
            }
        });
        dkPhieuMuon.setOnClickListener(v -> {
            if (user.getHasLibraryCard() != null && user.getHasLibraryCard()) {
                if (!user.getTheThuVien().getHasPhieuMuon()) {
                    Intent c = new Intent(HomeActivity.this, DangKyPhieuMuonActivity.class);
                    startActivity(c);
                } else {
                    Toast.makeText(HomeActivity.this, "Bạn đã có thẻ thư viện và phiếu mượn rồi.", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(HomeActivity.this, "Bạn không có thẻ thư viện vui lòng đăng ký trước.", Toast.LENGTH_SHORT).show();
            }
        });

        dkTheThuVien.setOnClickListener(v -> {
            if (user.getHasLibraryCard() != null && !user.getHasLibraryCard()) {
                Intent c = new Intent(HomeActivity.this, DangKyTheThuVienActivity.class);
                startActivity(c);
            } else {
                Toast.makeText(HomeActivity.this, "Bạn đã có thẻ thư viện rồi.", Toast.LENGTH_SHORT).show();
            }
        });

        trasach.setOnClickListener(v -> {
            if(sachDao.getAllSach().size() > 0){
                Intent c = new Intent(HomeActivity.this, TraSachActivity.class);
                startActivity(c);
            }else{
                Toast.makeText(HomeActivity.this, "Bạn ko có sách", Toast.LENGTH_SHORT).show();
            }
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

    private void getUser() {
        defaultUser = new Gson().toJson(new NguoiDung());
        String userID = new Gson().fromJson(pref.getString(Constant.KEY_USER, defaultUser), NguoiDung.class).getUserName();
        user = nguoiDungDao.getUser(userID);
    }

    private void initView() {
        docgia = (LinearLayout) findViewById(R.id.Docgia);
        thoat = (LinearLayout) findViewById(R.id.Thoat);
        dkTheThuVien = (LinearLayout) findViewById(R.id.btnDkTheThuVien);
        trasach = findViewById(R.id.trasach);
        dsSachDaMuon = (LinearLayout) findViewById(R.id.dsSachDaMuon);
        muonsach = (LinearLayout) findViewById(R.id.Muonsach);
        dkPhieuMuon = findViewById(R.id.btnDkPhieu);
    }
}
