package com.example.dell.QuanLyMuonTraThuVien.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.dell.QuanLyMuonTraThuVien.R;
import com.example.dell.QuanLyMuonTraThuVien.dao.SachDao;
import com.example.dell.QuanLyMuonTraThuVien.dao.TheLoaiDao;
import com.example.dell.QuanLyMuonTraThuVien.model.Sach;
import com.example.dell.QuanLyMuonTraThuVien.model.Theloai;

import java.util.ArrayList;
import java.util.List;

public class ThemSachActivity extends AppCompatActivity {
    SachDao sachDAO;
    EditText edMaSach, edTenSach, edNXB, edTacGia, edGiaBia, edSoLuong;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_sach);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        setTitle("THÊM SÁCH");
        edMaSach = (EditText) findViewById(R.id.edMaSach_ThemSach);
        edTenSach = (EditText) findViewById(R.id.edTenSach_ThemSach);
        edNXB = (EditText) findViewById(R.id.edNXB_ThemSach);
        edTacGia = (EditText) findViewById(R.id.edTacGia_ThemSach);
        edGiaBia = (EditText) findViewById(R.id.edGia_ThemSach);
        edSoLuong = (EditText)findViewById(R.id.edSoluong_ThemSach);

        setTitle("Thêm Sách");

    }
    public void showSpinner(View view){
        sachDAO = new SachDao(ThemSachActivity.this);
        sachDAO.getAllSach();
    }

    public void addBook(View view){
        sachDAO = new SachDao(ThemSachActivity.this);
        Sach sach = new
                Sach(edMaSach.getText().toString(),edTenSach.getText().toString(),
                edTacGia.getText().toString(),edNXB.getText().toString(),
                Double.parseDouble(edGiaBia.getText().toString()),Integer.parseInt(edSoLuong.getText
                ().toString()));
        try {
            if (sachDAO.inserSach(sach) > 0) {
                Toast.makeText(getApplicationContext(), "Thêm thành công",
                        Toast.LENGTH_SHORT).show();
                Intent a = new Intent(ThemSachActivity.this, DanhSachSachActivity.class);
                startActivity(a);
            } else {
                Toast.makeText(getApplicationContext(), "Thêm thất bại",
                        Toast.LENGTH_SHORT).show();
            }
        } catch (Exception ex) {
            Log.e("Error", ex.toString());
        }
    }
    public void CancelSach(View view){
        finish();
    }

}
