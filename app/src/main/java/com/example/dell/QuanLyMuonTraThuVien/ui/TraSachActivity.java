package com.example.dell.QuanLyMuonTraThuVien.ui;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.dell.QuanLyMuonTraThuVien.R;
import com.example.dell.QuanLyMuonTraThuVien.adapter.SachAdapter;
import com.example.dell.QuanLyMuonTraThuVien.dao.SachDao;
import com.example.dell.QuanLyMuonTraThuVien.model.Sach;

import java.util.ArrayList;
import java.util.List;

public class TraSachActivity extends AppCompatActivity {
    public static List<Sach> dsSach = new ArrayList<>();
    ListView lvBooks;
    SachAdapter adapter = null;
    SachDao sachDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tra_sach);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        setTitle("Trả Sách");
        lvBooks = (ListView) findViewById(R.id.customlvsach);
        sachDao = new SachDao(TraSachActivity.this);
        dsSach = sachDao.getAllSach();
        adapter = new SachAdapter(dsSach, this,true);
        lvBooks.setAdapter(adapter);
        lvBooks.setTextFilterEnabled(true);
    }
}
