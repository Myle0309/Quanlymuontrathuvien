package com.example.dell.QuanLyMuonTraThuVien.model;

public class NguoiDung {
    String userName;
    String password;
    String phone;
    String hoTen;
    Boolean isHasLibraryCard;
    TheThuVien theThuVien;

    public NguoiDung() {
    }

    public NguoiDung(String userName, String password, String phone, String hoTen) {
        this.userName = userName;
        this.password = password;
        this.phone = phone;
        this.hoTen = hoTen;
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
