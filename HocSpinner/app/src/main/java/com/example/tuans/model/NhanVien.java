package com.example.tuans.model;

/**
 * Created by tuans on 12/03/2017.
 */

public class NhanVien {
    private String ten;
    private String thuBatDauCongTac;
    private int soNgay;

    public NhanVien() {
    }

    public NhanVien(String ten, String thuBatDauCongTac, int soNgay) {
        this.ten = ten;
        this.thuBatDauCongTac = thuBatDauCongTac;
        this.soNgay = soNgay;
    }

    public int getSoNgay() {
        return soNgay;
    }

    public void setSoNgay(int soNgay) {
        this.soNgay = soNgay;
    }

    public String getThuBatDauCongTac() {
        return thuBatDauCongTac;
    }

    public void setThuBatDauCongTac(String thuBatDauCongTac) {
        this.thuBatDauCongTac = thuBatDauCongTac;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    @Override
    public String toString() {
        return "NhanVien{" +
                "ten='" + ten + '\'' +
                ", thuBatDauCongTac='" + thuBatDauCongTac + '\'' +
                ", soNgay=" + soNgay +
                '}';
    }
}
