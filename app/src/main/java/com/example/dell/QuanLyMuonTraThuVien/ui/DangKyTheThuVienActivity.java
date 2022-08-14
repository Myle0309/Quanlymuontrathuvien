package com.example.dell.QuanLyMuonTraThuVien.ui;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.dell.QuanLyMuonTraThuVien.R;
import com.example.dell.QuanLyMuonTraThuVien.databinding.ActivityDangKyTheThuVienBinding;

public class DangKyTheThuVienActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityDangKyTheThuVienBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDangKyTheThuVienBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        setupListener();
    }

    private void setupListener() {
        binding.btnDangKy.setOnClickListener(this);
        binding.Huy.setOnClickListener(this);
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

    }
}