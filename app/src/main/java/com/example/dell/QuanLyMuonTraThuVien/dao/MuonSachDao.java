package com.example.dell.QuanLyMuonTraThuVien.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.dell.QuanLyMuonTraThuVien.database.DatabaseHelper;
import com.example.dell.QuanLyMuonTraThuVien.model.MuonSach;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class MuonSachDao {
    private SQLiteDatabase db;
    private DatabaseHelper dbHelper;

    public static final String TABLE_NAME = "MuonSach";
    public static final String SQL_HOA_DON = "CREATE TABLE MuonSach(maMuonSach text primary key, ngayMuon date);";
    public static final String TAG = "MuonSachDao";

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public MuonSachDao(Context context) {
        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    //insert
    public int insertMuonSach(MuonSach muonsach) {
        ContentValues values = new ContentValues();
        values.put("maMuonSach", muonsach.getMaMuonSach());
        values.put("ngayMuon", sdf.format(muonsach.getNgayMuon()));
        try {
            if (db.insert(TABLE_NAME, null, values) == -1) {
                return -1;
            }
        } catch (Exception ex) {
            Log.e(TAG, ex.toString());
        }
        return 1;
    }
    //getall
    public List<MuonSach> getAllMuonSach()  throws ParseException {
        List<MuonSach> dsMuonSach = new ArrayList<>();
        Cursor c = db.query(TABLE_NAME, null, null, null, null, null, null);
        c.moveToFirst();
        while (c.isAfterLast() == false) {
            MuonSach ee = new MuonSach();
            ee.setMaMuonSach(c.getString(0));
            ee.setNgayMuon(sdf.parse(c.getString(1)));
            dsMuonSach.add(ee);
            Log.d("//======", ee.toString());
            c.moveToNext();
        }
        c.close();
        return dsMuonSach;
    }
    //update
    public int updateMuonSach(MuonSach ms) {
        ContentValues values = new ContentValues();
        values.put("maMuonSach", ms.getMaMuonSach());
        values.put("ngayMuon", ms.getNgayMuon().toString());

        int result = db.update(TABLE_NAME, values, "maMuonSach=?", new
                String[]{ms.getMaMuonSach()});
        if (result == 0) {
            return -1;
        }
        return 1;
    }
    //delete
    public  int deleteMuonSach(String mamuonsach){
        int result = db.delete(TABLE_NAME, "maMuonSach=?", new
                String[]{mamuonsach});
        if (result==0)
            return  -1;
        return 1;
    }
}

