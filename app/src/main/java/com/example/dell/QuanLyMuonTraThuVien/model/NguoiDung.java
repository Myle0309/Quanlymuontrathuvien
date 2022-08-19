package com.example.dell.QuanLyMuonTraThuVien.model;

import java.io.Serializable;

public class NguoiDung  implements Serializable {
    String userName;
    String password;
    String phone;
    String hoTen;
    Boolean isHasLibraryCard;
    TheThuVien theThuVien;
    PhieuMuon phieuMuon;

    public NguoiDung() {
        userName = "";
        password = "";
        phone ="";
        hoTen = "";
    }

    public NguoiDung(String userName, String password, String phone, String hoTen) {
        this.userName = userName;
        this.password = password;
        this.phone = phone;
        this.hoTen = hoTen;
        this.isHasLibraryCard = false;
        this.theThuVien = new TheThuVien();
        this.phieuMuon = new PhieuMuon();
    }

    public Boolean getHasLibraryCard() {
        return isHasLibraryCard;
    }

    public void setHasLibraryCard(Boolean hasLibraryCard) {
        isHasLibraryCard = hasLibraryCard;
    }

    public TheThuVien getTheThuVien() {
        return theThuVien;
    }

    public void setTheThuVien(TheThuVien theThuVien) {
        this.theThuVien = theThuVien;
    }

    public NguoiDung(String userName, String password, String phone, String hoTen, Boolean isHasLibraryCard, TheThuVien theThuVien) {
        this(userName, password, phone, hoTen);
        this.isHasLibraryCard = isHasLibraryCard;
        this.theThuVien = theThuVien;
    }

    public PhieuMuon getPhieuMuon() {
        return phieuMuon;
    }

    public void setPhieuMuon(PhieuMuon phieuMuon) {
        this.phieuMuon = phieuMuon;
    }

    public NguoiDung(String userName, String password, String phone, String hoTen, Boolean isHasLibraryCard, TheThuVien theThuVien, PhieuMuon phieuMuon) {
        this(userName, password, phone, hoTen,isHasLibraryCard,theThuVien);
        this.phieuMuon = phieuMuon;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    @Override
    public String toString() {
        return "NguoiDung {" +
                "userName = '" + userName + '\'' +
                ", password = ' " + password + '\'' +
                ", phone = ' " + phone + '\'' +
                ", hoTen = ' " + hoTen + '\'' +
                '}';
    }
}
