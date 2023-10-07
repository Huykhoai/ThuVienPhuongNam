package com.example.thuvienphuongnam.model;

public class Sach {
    private int maSach;
    private String tenSach;
    private int giasach;
    private int maLoai;

    public Sach(int maSach, String tenSach, int giasach, int maLoai) {
        this.maSach = maSach;
        this.tenSach = tenSach;
        this.giasach = giasach;
        this.maLoai = maLoai;
    }

    public Sach() {

    }

    public int getMaSach() {
        return maSach;
    }

    public void setMaSach(int maSach) {
        this.maSach = maSach;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public int getGiasach() {
        return giasach;
    }

    public void setGiasach(int giasach) {
        this.giasach = giasach;
    }

    public int getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(int maLoai) {
        this.maLoai = maLoai;
    }
}
