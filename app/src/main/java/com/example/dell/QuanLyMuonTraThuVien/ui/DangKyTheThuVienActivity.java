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
import com.example.dell.QuanLyMuonTraThuVien.dao.TheThuVienDao;
import com.example.dell.QuanLyMuonTraThuVien.model.NguoiDung;
import com.example.dell.QuanLyMuonTraThuVien.model.TheThuVien;
import com.google.gson.Gson;

import java.util.Calendar;
import java.util.Date;

public class DangKyTheThuVienActivity extends AppCompatActivity implements View.OnClickListener {
    TheThuVienDao theThuVienDao;
    private SharedPreferences pref;

    NguoiDungDao nguoiDungDao;
    View btnDangKy;
    View BtnHuy;
    EditText edtHotendkthe, edtTruongdkThe, edtLopdkThe, edtNienkhoadkThe, edtSoDienThoai, edtNgaydkThe;
    NguoiDung user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky_the_thu_vien);
        initView();
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        theThuVienDao = new TheThuVienDao(DangKyTheThuVienActivity.this);
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
    }

    private void initView() {
        btnDangKy = findViewById(R.id.btnDangKy);
        BtnHuy = findViewById(R.id.Huy);
        edtHotendkthe = findViewById(R.id.edtHotendkthe);
        edtTruongdkThe = findViewById(R.id.edtTruongdkThe);
        edtNienkhoadkThe = findViewById(R.id.edtNienkhoadkThe);
        edtLopdkThe = findViewById(R.id.edtLopdkThe);
        edtSoDienThoai = findViewById(R.id.edtSoDienThoai);
        edtNgaydkThe = findViewById(R.id.edtNgaydkThe);
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
            case R.id.btnDangKy:
                dangKyTheThuVien();
                break;
            case R.id.Huy:
                break;
            default:
                break;
        }
    }

    private void dangKyTheThuVien() {
        TheThuVien theThuVien = new TheThuVien(
                edtHotendkthe.getText().toString(),
                edtTruongdkThe.getText().toString(),
                edtLopdkThe.getText().toString(),
                edtNienkhoadkThe.getText().toString(),
                user.getPhone(), new Date(),
                false
        );
        user.setTheThuVien(theThuVien);
        try {
            if (validateForm() > 0) {
                if (theThuVienDao.insertTheThuVien(user.getTheThuVien()) > 0) {
                    user.setHasLibraryCard(true);
                    nguoiDungDao.updateTheThuVien(user);
                    pref.edit().putString(Constant.KEY_USER, new Gson().toJson(user)).commit();
                    Toast.makeText(getApplicationContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), DangKyPhieuMuonActivity.class);
                    startActivity(intent);
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
        if (edtHotendkthe.getText().length() == 0
                || edtTruongdkThe.getText().length() == 0
                || edtLopdkThe.getText().length() == 0
                || edtNienkhoadkThe.getText().length() == 0
                || edtSoDienThoai.getText().length() == 0
                || edtNgaydkThe.getText().length() == 0
        ) {
            Toast.makeText(getApplicationContext(), "Bạn phải nhập đủ thông tin", Toast.LENGTH_SHORT).show();
            check = -1;
        }
        return check;
    }
}