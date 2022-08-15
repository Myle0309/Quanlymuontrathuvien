package com.example.dell.QuanLyMuonTraThuVien.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.dell.QuanLyMuonTraThuVien.R;
import com.example.dell.QuanLyMuonTraThuVien.dao.NguoiDungDao;
import com.example.dell.QuanLyMuonTraThuVien.dao.TheThuVienDao;
import com.example.dell.QuanLyMuonTraThuVien.model.NguoiDung;

import java.text.SimpleDateFormat;

public class ViewTheThuVienActivity extends AppCompatActivity {

    NguoiDungDao nguoiDungDao;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.the_thuvien);
        nguoiDungDao = new NguoiDungDao(getApplicationContext());
        fillData();
    }


    @SuppressLint("SimpleDateFormat")
    private void fillData() {
        String userName = getIntent().getStringExtra("USER_NAME");
        NguoiDung user = nguoiDungDao.getUser(userName);
        ((TextView)findViewById(R.id.tvTextPersonName)).setText(user.getTheThuVien().getHoTen());
        ((TextView)findViewById(R.id.tvTextTruong)).setText(user.getTheThuVien().getTruong());
        ((TextView)findViewById(R.id.tvLop)).setText(user.getPhieuMuon().getLop());
        ((TextView)findViewById(R.id.tvNienKhoa)).setText(user.getTheThuVien().getNienKhoa());
        ((TextView)findViewById(R.id.tvSDT)).setText(user.getTheThuVien().getSoDienThoai());
        ((TextView)findViewById(R.id.tvNgayDK)).setText(new SimpleDateFormat("dd-MM-yyyy").format(user.getTheThuVien().getNgayDangKy()));
    }
}
