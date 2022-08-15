package com.example.dell.QuanLyMuonTraThuVien.model;

import java.util.Date;

public class MuonSach {
    private String maMuonSach;
    private Date ngayMuon;

    public MuonSach(){
        maMuonSach = "";
        ngayMuon = new Date();
    }

    public MuonSach(String maMuonSach, Date ngayMuon) {
        this.maMuonSach = maMuonSach;
        this.ngayMuon = ngayMuon;
    }

    public String getMaMuonSach() {
        return maMuonSach;
    }
    public void setMaMuonSach(String maMuonSach) {
        this.maMuonSach = maMuonSach;
    }

    public Date getNgayMuon() {
        return ngayMuon;
    }

    public void setNgayMuon(Date ngayMuon) {
        this.ngayMuon = ngayMuon;
    }
}
