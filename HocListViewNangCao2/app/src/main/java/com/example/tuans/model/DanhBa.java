package com.example.tuans.model;

import java.io.Serializable;

/**
 * Created by tuans on 07/12/2017.
 */

public class DanhBa implements Serializable{
    private int ma;
    private String ten;
    private String chuoi;

    public DanhBa() {
    }

    public DanhBa(int ma, String ten, String chuoi) {
        this.ma = ma;
        this.ten = ten;
        this.chuoi = chuoi;
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

    public String getChuoi() {
        return chuoi;
    }

    public void setChuoi(String chuoi) {
        this.chuoi = chuoi;
    }
}
