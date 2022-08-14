package com.example.dell.QuanLyMuonTraThuVien.model;

import java.util.Date;

public class MuonSach {
    private String maMuonSach;
    private Date ngayMuon;

    public MuonSach(String maMuonSach, Date ngayMuon) {
        this.maMuonSach = maMuonSach;
        this.ngayMuon = ngayMuon;
    }

    public MuonSach(String maMuonSach, String s) {

    }

    public MuonSach() {

    }

    public String getMaMuonSach() {
        return maMuonSach;
    }
    public void setMaMuonSach(String maMuonSach) {
        this.maMuonSach = maMuonSach;
    }

    public CharSequence getNgayMuon() { return (CharSequence) ngayMuon; }
    public void setNgayMuon(Date ngayMuon) {
        this.ngayMuon = ngayMuon;
    }
}
