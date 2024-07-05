package com.example.buoi5.model;

import java.io.Serializable;

public class SinhVien implements Serializable {
    private String masv;
    private String tensv;
    private String gioitinh;
    private int namsinh;
    private int anhsv;

    public SinhVien(String masv, String tensv, String gioitinh, int namsinh, int anhsv) {
        this.masv = masv;
        this.tensv = tensv;
        this.gioitinh = gioitinh;
        this.namsinh = namsinh;
        this.anhsv = anhsv;
    }

    public String getMasv() {
        return masv;
    }

    public void setMasv(String masv) {
        this.masv = masv;
    }

    public String getTensv() {
        return tensv;
    }

    public void setTensv(String tensv) {
        this.tensv = tensv;
    }

    public String getGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(String gioitinh) {
        this.gioitinh = gioitinh;
    }

    public int getNamsinh() {
        return namsinh;
    }

    public void setNamsinh(int namsinh) {
        this.namsinh = namsinh;
    }

    public int getAnhsv() {
        return anhsv;
    }

    public void setAnhsv(int anhsv) {
        this.anhsv = anhsv;
    }
}
