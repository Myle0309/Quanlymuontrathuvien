package com.example.dell.QuanLyMuonTraThuVien.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.dell.QuanLyMuonTraThuVien.database.DatabaseHelper;
import com.example.dell.QuanLyMuonTraThuVien.model.NguoiDung;
import com.example.dell.QuanLyMuonTraThuVien.model.PhieuMuon;
import com.example.dell.QuanLyMuonTraThuVien.model.TheThuVien;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;

public class TheThuVienDao {
    private SQLiteDatabase db;
    private DatabaseHelper dbHelper;

    public static final String TABLE_NAME = "TheThuVien";
    public static final String SQL_THE_THU_VIEN = "CREATE TABLE TheThuVien(HoTen " +
            "text, Truong text,Lop text,NienKhoa text, SoDienThoai text primary key,NgayDangKy date,isHasPhieuMuon boolean);";
    public static final String TAG = "TheThuVienDao";

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public TheThuVienDao(Context context) {
        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public int insertTheThuVien(TheThuVien theThuVien) {
        ContentValues values = new ContentValues();
        values.put("HoTen", theThuVien.getHoTen());
        values.put("Truong", theThuVien.getTruong());
        values.put("Lop", theThuVien.getLop());
        values.put("NienKhoa", theThuVien.getNienKhoa());
        values.put("SoDienThoai", theThuVien.getSoDienThoai());
        values.put("NgayDangKy", sdf.format(theThuVien.getNgayDangKy()));
        values.put("isHasPhieuMuon", theThuVien.getHasPhieuMuon());
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
