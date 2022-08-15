package com.example.dell.QuanLyMuonTraThuVien.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.dell.QuanLyMuonTraThuVien.R;
import com.example.dell.QuanLyMuonTraThuVien.model.MuonSach;

import java.util.ArrayList;
import java.util.List;

public class MuonSachActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnMuonSach;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_muon_sach);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        initView();
    }


    private void initView() {
        btnMuonSach = findViewById(R.id.btnMuonSach);
        btnMuonSach.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnMuonSach:
                muonSach();
                break;
            default:
                break;
        }
    }

    private void muonSach() {
        Intent a = new Intent(MuonSachActivity.this,ThemSachActivity.class);
        startActivity(a);
    }
}
