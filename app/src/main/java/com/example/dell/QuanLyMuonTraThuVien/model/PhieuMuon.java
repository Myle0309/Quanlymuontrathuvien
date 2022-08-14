package com.example.dell.QuanLyMuonTraThuVien.model;

import java.util.Date;

public class PhieuMuon {
    String HoTen;
    String Truong;
    String Lop;
    String DiaChi;
    String SoDienThoai;
    Date NgayDangKy;

    public PhieuMuon(){
        HoTen = "";
        Truong = "";
        Lop = "";
        DiaChi = "";
        SoDienThoai = "";
        NgayDangKy = new Date();
    }

    public PhieuMuon(String hoTen, String truong, String lop, String diaChi, String soDienThoai, Date ngayDangKy) {
        HoTen = hoTen;
        Truong = truong;
        Lop = lop;
        DiaChi = diaChi;
        SoDienThoai = soDienThoai;
        NgayDangKy = ngayDangKy;
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

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String diaChi) {
        DiaChi = diaChi;
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
