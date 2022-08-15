package com.example.dell.QuanLyMuonTraThuVien.ui;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.dell.QuanLyMuonTraThuVien.R;
import com.example.dell.QuanLyMuonTraThuVien.dao.MuonSachDao;
import com.example.dell.QuanLyMuonTraThuVien.model.MuonSach;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ThemMuonSachActivity extends AppCompatActivity {
    private Button showmuonsach;

    private EditText edMaHoaDonHoaDon;
    private EditText tvChonNgay;
    private Button btnChonNgay;
    private Button btnThemThemHoaDon;
    private Button btnHuy_ThemHoaDon;
    MuonSachDao muonSachDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_muon_sach);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        initView();
        showmuonsach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ThemMuonSachActivity.this, MuonSachActivity.class);
                startActivity(intent);
            }
        });
        btnThemThemHoaDon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    btnThem_ThemMuonSach();
                } catch (ParseException e) {
                    e.printStackTrace();
                    Toast.makeText(ThemMuonSachActivity.this, "Có lỗi xảy ra ko thể thêm sách", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnHuy_ThemHoaDon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnChonNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker();
            }
        });
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
                tvChonNgay.setText(d.toString() + "-" + t.toString() + "-" + n.toString());
            }
        }, year, month, day);
        datePickerDialog.show();
    }

    private void btnThem_ThemMuonSach() throws ParseException {
        muonSachDao = new MuonSachDao(ThemMuonSachActivity.this);
        @SuppressLint("SimpleDateFormat") MuonSach muonSach = new MuonSach(edMaHoaDonHoaDon.getText().toString(),
                new SimpleDateFormat("dd-MM-yyy").parse(tvChonNgay.getText().toString()));
        try {
            if (validateForm() > 0) {
                if (muonSachDao.insertMuonSach(muonSach) > 0) {
                    Toast.makeText(getApplicationContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                    Intent b = new Intent(ThemMuonSachActivity.this, MuonSachActivity.class);
                    startActivity(b);
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
        if (edMaHoaDonHoaDon.getText().length() == 0 || tvChonNgay.getText().length() == 0) {
            Toast.makeText(getApplicationContext(), "Bạn phải nhập đủ thông tin", Toast.LENGTH_SHORT).show();
            check = -1;
        }
        if (edMaHoaDonHoaDon.getText().length() > 5) {
            edMaHoaDonHoaDon.setError("Mã không quá 5 kí tự");
        }
        return check;
    }

    private void initView() {
        showmuonsach = (Button) findViewById(R.id.showmuonsach);
        edMaHoaDonHoaDon = (EditText) findViewById(R.id.edMaHoaDon_HoaDon);
        tvChonNgay = (EditText) findViewById(R.id.tvChonNgay);
        btnChonNgay = (Button) findViewById(R.id.btnChonNgay);
        btnThemThemHoaDon = (Button) findViewById(R.id.btnThem_ThemHoaDon);
        btnHuy_ThemHoaDon = (Button) findViewById(R.id.btnHuy_ThemHoaDon);
    }
}
