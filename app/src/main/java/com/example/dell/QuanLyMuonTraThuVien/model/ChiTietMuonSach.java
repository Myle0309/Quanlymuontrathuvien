package com.example.dell.QuanLyMuonTraThuVien.model;

public class ChiTietMuonSach {
    private MuonSach muonSach;
    private Sach sach;
    private int soLuongMuon;
    private String tinhTrang;

    public ChiTietMuonSach() {
        this.muonSach = muonSach;
        this.sach = sach;
        this.soLuongMuon = soLuongMuon;
        this.tinhTrang = tinhTrang;
    }


    public MuonSach getHoaDon() {
        return muonSach;
    }
    public void setHoaDon(MuonSach muonSach) {
        this.muonSach = muonSach;
    }

    public Sach getSach() {
        return sach;
    }
    public void setSach(Sach sach) {
        this.sach = sach;
    }

    public int getSoLuongMuon() {
        return soLuongMuon;
    }
    public void setSoLuongMuon(int soLuongMuon) { this.soLuongMuon = soLuongMuon; }

    public String getTinhTrang() { return tinhTrang; }
    public void setTinhTrang(String tinhTrang) { this.tinhTrang = tinhTrang; }

    @Override
    public String toString() {
        return "HoaDonChiTiet { " +
                ",muonSach = " + muonSach.toString()+
                ",sach = " +sach.toString()+
                ",soLuongMuon = " +soLuongMuon+
                ",tinhTrang = " + tinhTrang.toString()+
                '}';
    }
}
