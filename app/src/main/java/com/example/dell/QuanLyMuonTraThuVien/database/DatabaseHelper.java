package com.example.dell.QuanLyMuonTraThuVien.database;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.dell.QuanLyMuonTraThuVien.dao.ChiTietMuonSachDao;
import com.example.dell.QuanLyMuonTraThuVien.dao.MuonSachDao;
import com.example.dell.QuanLyMuonTraThuVien.dao.NguoiDungDao;
import com.example.dell.QuanLyMuonTraThuVien.dao.PhieuMuonDao;
import com.example.dell.QuanLyMuonTraThuVien.dao.SachDao;
import com.example.dell.QuanLyMuonTraThuVien.dao.TheLoaiDao;
import com.example.dell.QuanLyMuonTraThuVien.dao.TheThuVienDao;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "dbBookManager";
    public static final int VESION=1;


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VESION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(NguoiDungDao.SQL_NGUOI_DUNG);
        db.execSQL(TheLoaiDao.SQL_THE_LOAI);
        db.execSQL(SachDao.SQL_SACH);
        db.execSQL(MuonSachDao.SQL_HOA_DON);
        db.execSQL(TheThuVienDao.SQL_THE_THU_VIEN);
        db.execSQL(PhieuMuonDao.SQL_PHIEU_MUON);
        db.execSQL(ChiTietMuonSachDao.SQL_HOA_DON_CHI_TIET);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop table if exists "+NguoiDungDao.TABLE_NAME);
        db.execSQL("Drop table if exists "+TheLoaiDao.TABLE_NAME);
        db.execSQL("Drop table if exists "+SachDao.TABLE_NAME);
        db.execSQL("Drop table if exists "+ MuonSachDao.TABLE_NAME);
        db.execSQL("Drop table if exists "+ ChiTietMuonSachDao.TABLE_NAME);

        onCreate(db);
    }
}
