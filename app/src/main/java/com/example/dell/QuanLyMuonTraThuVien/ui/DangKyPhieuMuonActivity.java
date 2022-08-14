package com.example.dell.QuanLyMuonTraThuVien.ui;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.dell.QuanLyMuonTraThuVien.Constant;
import com.example.dell.QuanLyMuonTraThuVien.R;
import com.example.dell.QuanLyMuonTraThuVien.dao.NguoiDungDao;
import com.example.dell.QuanLyMuonTraThuVien.dao.PhieuMuonDao;
import com.example.dell.QuanLyMuonTraThuVien.dao.TheThuVienDao;
import com.example.dell.QuanLyMuonTraThuVien.model.NguoiDung;
import com.example.dell.QuanLyMuonTraThuVien.model.PhieuMuon;
import com.example.dell.QuanLyMuonTraThuVien.model.TheThuVien;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DangKyPhieuMuonActivity extends AppCompatActivity implements View.OnClickListener {
    PhieuMuonDao phieuMuonDao;
    TheThuVienDao theThuVienDao;
    private SharedPreferences pref;

    NguoiDungDao nguoiDungDao;
    View btnDangKy;
    View BtnHuy;
    EditText edtHotenPM, edtTruongPM, edtLopdkThe, edtDiaChi, edtSoDienThoai, edtNgaydkThe;
    NguoiDung user;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky_phieu_muon);
        initView();
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        phieuMuonDao = new PhieuMuonDao(DangKyPhieuMuonActivity.this);
        theThuVienDao = new TheThuVienDao(DangKyPhieuMuonActivity.this);
        nguoiDungDao = new NguoiDungDao(getApplicationContext());
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        getUser();
        setupListener();
    }

    private void getUser() {
        String userInString = pref.getString(Constant.KEY_USER, new Gson().toJson(new NguoiDung()));
        user = new Gson().fromJson(userInString, NguoiDung.class);
        edtSoDienThoai.setText(user.getPhone());
        edtHotenPM.setText(user.getHoTen());
        TheThuVien theThuVien = user.getTheThuVien();
        edtNgaydkThe.setText(sdf.format(theThuVien.getNgayDangKy()));
        edtLopdkThe.setText(theThuVien.getLop());
        edtTruongPM.setText(theThuVien.getTruong());
    }

    private void initView() {
        btnDangKy = findViewById(R.id.DkPM);
        BtnHuy = findViewById(R.id.btnHuy);
        edtHotenPM = findViewById(R.id.edtHotenPM);
        edtTruongPM = findViewById(R.id.edtTruongPM);
        edtDiaChi = findViewById(R.id.edtDiaChi);
        edtLopdkThe = findViewById(R.id.edtLop);
        edtSoDienThoai = findViewById(R.id.edtSodienthoai);
        edtNgaydkThe = findViewById(R.id.edtNgayLapPhieu);
    }

    public void showDatePicker() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        // thiet lap thong tin cho date picker
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Integer n = year;
                Integer t = month;
                Integer d = dayOfMonth;
                edtNgaydkThe.setText(d.toString() + "-" + t.toString() + "-" + n.toString());
            }
        }, year, month, day);
        datePickerDialog.show();
    }

    private void setupListener() {
        btnDangKy.setOnClickListener(this);
        BtnHuy.setOnClickListener(this);
        edtNgaydkThe.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    showDatePicker();
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.DkPM:
                dangKyPhieuMuon();
                break;
            case R.id.btnHuy:
                break;
            default:
                break;
        }
    }

    private void dangKyPhieuMuon() {
        PhieuMuon phieuMuon = new PhieuMuon(
                edtHotenPM.getText().toString(),
                edtTruongPM.getText().toString(),
                edtLopdkThe.getText().toString(),
                edtDiaChi.getText().toString(),
                user.getPhone(), new Date()
        );
        user.setPhieuMuon(phieuMuon);
        try {
            if (validateForm() > 0) {
                if (phieuMuonDao.insertPhieuMuon(user.getPhieuMuon()) > 0) {
                    user.getTheThuVien().setHasPhieuMuon(true);
                    nguoiDungDao.updatePhieuMuon(user);
                    pref.edit().putString(Constant.KEY_USER, new Gson().toJson(user)).commit();
                    theThuVienDao.updateTheThuVien(user);
                    Toast.makeText(getApplicationContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(this,HomeActivity.class));
                } else {
                    Toast.makeText(getApplicationContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        } catch (Exception e) {
            Log.e("Error", e.toString());
        }
    }

    private int validateForm() {
        int check = 1;
        if (edtHotenPM.getText().length() == 0
                || edtTruongPM.getText().length() == 0
                || edtLopdkThe.getText().length() == 0
                || edtDiaChi.getText().length() == 0
                || edtSoDienThoai.getText().length() == 0
                || edtNgaydkThe.getText().length() == 0
        ) {
            Toast.makeText(getApplicationContext(), "Bạn phải nhập đủ thông tin", Toast.LENGTH_SHORT).show();
            check = -1;
        }
        return check;
    }
}