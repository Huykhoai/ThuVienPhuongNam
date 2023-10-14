package com.example.thuvienphuongnam.model;

public class Sach {
    private int maSach;
    private String tenSach;
    private int giasach;
    private int maLoai;
    private int namXB;

    public Sach(int maSach, String tenSach, int giasach, int maLoai, int namXB) {
        this.maSach = maSach;
        this.tenSach = tenSach;
        this.giasach = giasach;
        this.maLoai = maLoai;
        this.namXB = namXB;
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

    public int getNamXB() {
        return namXB;
    }

    public void setNamXB(int namXB) {
        this.namXB = namXB;
    }
}
