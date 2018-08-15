package com.example.tuans.model;

import java.io.Serializable;

/**
 * Created by tuans on 09/12/2017.
 */

public class SinhVien implements Serializable{
    private int ma;
    private  String ten;

    public SinhVien() {
    }

    public SinhVien(int ma, String ten) {
        this.ma = ma;
        this.ten = ten;
    }

    @Override
    public String toString() {
        return this.ma+" "+this.ten;
    }

    public int getMa() {
        return ma;
    }

    public void setMa(int ma) {
        this.ma = ma;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }
}
