package com.example.dell.QuanLyMuonTraThuVien.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.dell.QuanLyMuonTraThuVien.database.DatabaseHelper;
import com.example.dell.QuanLyMuonTraThuVien.model.MuonSach;
import com.example.dell.QuanLyMuonTraThuVien.model.ChiTietMuonSach;
import com.example.dell.QuanLyMuonTraThuVien.model.Sach;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ChiTietMuonSachDao {
    private SQLiteDatabase db;
    private DatabaseHelper dbHelper;

    public static final String TABLE_NAME = "ChiTietMuonSach";
    public static final String SQL_HOA_DON_CHI_TIET = "CREATE TABLE ChiTietMuonSach( maMuonSach text NOT NULL, maSach text NOT NULL,soLuong INTEGER,tinhTrang text NOT NULL);";
    public static final String TAG = "ChiTietMuonSachDao";

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public ChiTietMuonSachDao(Context context) {
        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();

    }

    //insert
    public int insertChiTietMuonSach(ChiTietMuonSach hd) {
        ContentValues values = new ContentValues();
        values.put("maMuonSach", hd.getHoaDon().getMaMuonSach());
        values.put("maSach", hd.getSach().getMaSach());
        values.put("soLuong", hd.getSoLuongMuon());
        values.put("tinhTrang", hd.getTinhTrang());
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
    public List<ChiTietMuonSach> getAllChiTietMuonSach() {
        List<ChiTietMuonSach> dsChiTietMuonSach = new ArrayList<>();
        String sSQL = "SELECT MuonSach.maMuonSach, MuonSach.ngayMuon,"+
                "Sach.maSach, Sach.maTheLoai, Sach.tenSach,Sach.tacGia, Sach.NXB, Sach.giaBan,"+
                "Sach.soLuong,ChiTietMuonSach.soLuong FROM ChiTietMuonSach INNER JOIN MuonSach"+
                " on ChiTietMuonSach.maMuonSach = MuonSach.maMuonSach INNER JOIN Sach on Sach.maSach = ChiTietMuonSach.maSach";

        Cursor c =db.rawQuery(sSQL,null);
        c.moveToFirst();
        try {
            while (c.isAfterLast()==false){
                ChiTietMuonSach ee= new ChiTietMuonSach();
                ee.setHoaDon(new MuonSach(c.getString(0),sdf.parse(c.getString(1))));
                ee.setSach(new Sach(c.getString(2),c.getString(3),c.getString(4),c.getString(5),
                        c.getString(6),c.getInt(7),c.getInt(8)));
                ee.setSoLuongMuon(c.getInt(9));
                dsChiTietMuonSach.add(ee);
                Log.d("//======", ee.toString());
                c.moveToNext();

            }
            c.close();
        } catch (Exception e) {
           Log.d(TAG,e.toString());
        }
        return dsChiTietMuonSach;
    }

    //getAll
    public List<ChiTietMuonSach> getAllChiTietMuonSachID(String maMuonSach){
        List<ChiTietMuonSach> dsChiTietMuonSach = new ArrayList<>();
        String sSQL = "SELECT maMuonSach,MuonSach.maMuonSach, MuonSach.ngayMuon,"+
                "Sach.maSach, Sach.maTheLoai, Sach.tenSach,    Sach.tacGia, Sach.NXB, Sach.giaBan,"+
                "Sach.soLuong,ChiTietMuonSach.soLuong FROM ChiTietMuonSach INNER JOIN MuonSach"+
                " on ChiTietMuonSach.maMuonSach = MuonSach.maMuonSach INNER JOIN Sach on Sach.maSach = ChiTietMuonSach.maSach";
        Cursor c=db.rawQuery(sSQL,null);
        c.moveToFirst();
        try {
            while (c.isAfterLast()==false){
                ChiTietMuonSach ee= new ChiTietMuonSach();
                ee.setHoaDon(new MuonSach(c.getString(0),sdf.parse(c.getString(1))));
                ee.setSach(new Sach(c.getString(2),c.getString(3),c.getString(4),c.getString(5),
                        c.getString(6),c.getInt(7),c.getInt(8)));
                ee.setSoLuongMuon(c.getInt(9));
                dsChiTietMuonSach.add(ee);
                Log.d("//======", ee.toString());
                c.moveToNext();
            }
            c.close();
        } catch (Exception e) {
            Log.d(TAG,e.toString());
        }
        return dsChiTietMuonSach;
    }

    //update
    public int updateChiTietMuonSach(ChiTietMuonSach hd) {
        ContentValues values = new ContentValues();
        values.put("maMuonSach", hd.getHoaDon().getMaMuonSach());
        values.put("maSach", hd.getSach().getMaSach());
        values.put("soLuong", hd.getSoLuongMuon());
        int result = db.update(TABLE_NAME, values, "maMuonSach=?", new
                String[]{String.valueOf(hd.getHoaDon())});
        if (result == 0) {
            return -1;
        }
        return 1;
    }

    //delete
    public  int deleteChiTietMuonSachByID(String maMuonSach){
        int result = db.delete(TABLE_NAME, "maMuonSach=?", new
                String[]{maMuonSach});
        if (result==0)
            return  -1;
        return 1;
    }

    public boolean checkMuonSach(String maMuonSach){
        String[] columns = {"maMuonSach"};
        String selection = "maMuonSach=?";
        String [] selectionArgs ={maMuonSach};
        Cursor c = null;
        try {
            c=db.query(TABLE_NAME,columns,selection,selectionArgs,null,null,null);
            c.moveToFirst();
            int i =c.getCount();
            c.close();
            if (i<=0){
                return false;
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }
}


