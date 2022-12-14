package com.example.dell.QuanLyMuonTraThuVien.model;

import java.io.Serializable;
import java.util.Date;

public class TheThuVien  implements Serializable {
    String HoTen;
    String Truong;
    String Lop;
    String NienKhoa;
    String SoDienThoai;
    Date NgayDangKy;
    Boolean isHasPhieuMuon;

    public Boolean getHasPhieuMuon() {
        return isHasPhieuMuon;
    }

    public void setHasPhieuMuon(Boolean hasPhieuMuon) {
        isHasPhieuMuon = hasPhieuMuon;
    }

    public TheThuVien() {
        HoTen = "";
        Truong = "";
        Lop = "";
        NienKhoa = "";
        SoDienThoai = "";
        isHasPhieuMuon = false;
        NgayDangKy = new Date();
    }

    public TheThuVien(String hoTen, String truong, String lop, String nienKhoa, String soDienThoai, Date ngayDangKy,Boolean isHasPhieuMuon) {
        HoTen = hoTen;
        Truong = truong;
        Lop = lop;
        NienKhoa = nienKhoa;
        SoDienThoai = soDienThoai;
        NgayDangKy = ngayDangKy;
        this.isHasPhieuMuon = isHasPhieuMuon;
    }

    public String getHoTen() {
        return HoTen;
    }

    public void setHoTen(String hoTen) {
        HoTen = hoTen;
    }

    public String getTruong() {
        return Truong;
    }

    public void setTruong(String truong) {
        Truong = truong;
    }

    public String getLop() {
        return Lop;
    }

    public void setLop(String lop) {
        Lop = lop;
    }

    public String getNienKhoa() {
        return NienKhoa;
    }

    public void setNienKhoa(String nienKhoa) {
        NienKhoa = nienKhoa;
    }

    public String getSoDienThoai() {
        return SoDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        SoDienThoai = soDienThoai;
    }

    public Date getNgayDangKy() {
        return NgayDangKy;
    }

    public void setNgayDangKy(Date ngayDangKy) {
        NgayDangKy = ngayDangKy;
    }
}
