package com.example.dell.QuanLyMuonTraThuVien.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.dell.QuanLyMuonTraThuVien.database.DatabaseHelper;
import com.example.dell.QuanLyMuonTraThuVien.model.PhieuMuon;
import com.example.dell.QuanLyMuonTraThuVien.model.TheThuVien;

import java.text.SimpleDateFormat;

public class PhieuMuonDao {

    private SQLiteDatabase db;
    private DatabaseHelper dbHelper;

    public static final String TABLE_NAME = "PhieuMuon";
    public static final String SQL_PHIEU_MUON = "CREATE TABLE PhieuMuon(" +
            "HoTen text," +
            " Truong text," +
            "Lop text," +
            "DiaChi text," +
            " SoDienThoai text primary key," +
            "NgayDangKy date);";
    public static final String TAG = "PhieuMuonDao";

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public PhieuMuonDao(Context context) {
        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public int insertPhieuMuon(PhieuMuon theThuVien) {
        ContentValues values = new ContentValues();
        values.put("HoTen", theThuVien.getHoTen());
        values.put("Truong", theThuVien.getTruong());
        values.put("Lop", theThuVien.getLop());
        values.put("DiaChi", theThuVien.getDiaChi());
        values.put("SoDienThoai", theThuVien.getSoDienThoai());
        values.put("NgayDangKy", sdf.format(theThuVien.getNgayDangKy()));
        try {
            if (db.insert(TABLE_NAME, null, values) == -1) {
                return -1;
            }
        } catch (Exception ex) {
            Log.e(TAG, ex.toString());
        }
        return 1;
    }
}
