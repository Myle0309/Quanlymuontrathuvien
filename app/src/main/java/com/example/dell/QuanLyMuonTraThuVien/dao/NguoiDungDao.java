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

import java.util.ArrayList;
import java.util.List;

public class NguoiDungDao {
    private SQLiteDatabase db;
    private DatabaseHelper dbHelper;

    public static final String TABLE_NAME = "NguoiDung";
    public static final String SQL_NGUOI_DUNG = "CREATE TABLE NguoiDung(username " +
            "text primary key, password text, phone text,hoten text,isHasLibraryCard boolean,theThuVien text, phieuMuon text);";
    public static final String TAG = "NguoiDungDao";

    public NguoiDungDao(Context context) {
        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();

    }

    public NguoiDungDao(DatabaseHelper dbHelper) {
        this.dbHelper = dbHelper;
    }

    //insert
    public int insertNguoiDung(NguoiDung nd) {
        ContentValues values = new ContentValues();
        values.put("username", nd.getUserName());
        values.put("password", nd.getPassword());
        values.put("phone", nd.getPhone());
        values.put("hoten", nd.getHoTen());
        values.put("isHasLibraryCard", nd.getHasLibraryCard());
        values.put("theThuVien", new Gson().toJson(nd.getTheThuVien()));
        values.put("phieuMuon", new Gson().toJson(nd.getPhieuMuon()));
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
    public List<NguoiDung> getAllNguoiDung() {
        List<NguoiDung> dsNguoiDung = new ArrayList<>();
        Cursor c = db.query(TABLE_NAME, null, null, null, null, null, null);
        c.moveToFirst();
        while (c.isAfterLast() == false) {
            NguoiDung ee = new NguoiDung();
            ee.setUserName(c.getString(0));
            ee.setPassword(c.getString(1));
            ee.setPhone(c.getString(2));
            ee.setHoTen(c.getString(3));
            ee.setHasLibraryCard(c.getString(4).equals("1"));
            ee.setTheThuVien(new Gson().fromJson(c.getString(5),TheThuVien.class));
            ee.setPhieuMuon(new Gson().fromJson(c.getString(6),PhieuMuon.class));
            dsNguoiDung.add(ee);
            Log.d("//======", ee.toString());
            c.moveToNext();
        }
        c.close();
        return dsNguoiDung;
    }

    //update


    public int updateNguoiDung(NguoiDung nd) {
        ContentValues values = new ContentValues();
        values.put("username", nd.getUserName());
        values.put("password", nd.getPassword());
        values.put("phone", nd.getPhone());
        values.put("hoten", nd.getHoTen());
        int result = db.update(TABLE_NAME, values, "username=?", new
                String[]{nd.getUserName()});
        if (result == 0) {
            return -1;
        }
        return 1;
    }


    public int updateTheThuVien(NguoiDung nd) {
        ContentValues values = new ContentValues();
        values.put("isHasLibraryCard", nd.getHasLibraryCard() ? 1 : 0);
        values.put("theThuVien", new Gson().toJson(nd.getTheThuVien()));
        int result = db.update(TABLE_NAME, values, "username=?", new
                String[]{nd.getUserName()});
        if (result == 0) {
            return -1;
        }
        return 1;
    }


    public int updatePhieuMuon(NguoiDung nd) {
        ContentValues values = new ContentValues();
        values.put("phieuMuon", new Gson().toJson(nd.getPhieuMuon()));
        int result = db.update(TABLE_NAME, values, "username=?", new
                String[]{nd.getUserName()});
        if (result == 0) {
            return -1;
        }
        return 1;
    }

    //change pass

    public int changePasswordNguoiDung(NguoiDung nd) {
        ContentValues values = new ContentValues();
        values.put("username", nd.getUserName());
        values.put("password", nd.getPassword());
        int result = db.update(TABLE_NAME, values, "username=?", new
                String[]{nd.getUserName()});
        if (result == 0) {
            return -1;
        }
        return 1;
    }

    public int changePassword(String username, NguoiDung user) {
        db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("password", user.getPassword());


        // updating row
        return db.update(TABLE_NAME, values, "username = ?",
                new String[]{username});
    }

    public int updateInfoNguoiDung(String username, String phone, String name) {
        ContentValues values = new ContentValues();

        values.put("hoten", name);
        values.put("phone", phone);
        int result = db.update(TABLE_NAME, values, "username=?", new
                String[]{username});
        if (result == 0) {
            return -1;
        }
        return 1;
    }

    //delete

    public int deleteNguoiDungByID(String username) {
        int result = db.delete(TABLE_NAME, "username=?", new String[]{username});
        if (result == 0)
            return -1;
        return 1;
    }

    //check login

    public NguoiDung getUser(String username) {

        NguoiDung user = null;

        SQLiteDatabase db = dbHelper.getReadableDatabase();

        // Truyen vao Ten bang, array bao gom ten cot, ten cot khoa chinh, gia tri khoa chinh, cac tham so con lai la null

        Cursor cursor = db.query(TABLE_NAME, new String[]{"username", "password", "phone", "hoten, isHasLibraryCard,theThuVien,phieuMuon "}, "username=?", new String[]{username}, null, null, null, null);

        // moveToFirst : kiem tra xem cursor co chua du lieu khong, ham nay tra ve gia tri la true or false
        if (cursor != null && cursor.moveToFirst()) {

            String user_name = cursor.getString(cursor.getColumnIndex("username"));

            String password = cursor.getString(cursor.getColumnIndex("password"));

            String phone = cursor.getString(cursor.getColumnIndex("phone"));

            String hoten = cursor.getString(cursor.getColumnIndex("hoten"));
            Boolean isHasLibraryCard;
            if (cursor.getString(cursor.getColumnIndex("isHasLibraryCard")).equals("1")) {
                isHasLibraryCard = true;
            } else {
                isHasLibraryCard = false;
            }

            TheThuVien theThuVien = new Gson().fromJson(cursor.getString(cursor.getColumnIndex("theThuVien")), TheThuVien.class);
            PhieuMuon phieuMuon = new Gson().fromJson(cursor.getString(cursor.getColumnIndex("phieuMuon")), PhieuMuon.class);
            // khoi tao user voi cac gia tri lay duoc
            user = new NguoiDung(user_name, password, phone, hoten, isHasLibraryCard, theThuVien, phieuMuon);
        }
        cursor.close();
        return user;
    }
}
