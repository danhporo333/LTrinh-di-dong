package hutech.example.quan_ly_sv.model;

import java.io.Serializable;

public class SinhVien implements Serializable {
    private String masv;
    private String hovaten;
    private String gioitinh;
    private int namsinh;
    private String anhsv;

    public SinhVien() {
    }

    public SinhVien(String masv, String hovaten, String gioitinh, int namsinh, String anhsv) {
        this.masv = masv;
        this.hovaten = hovaten;
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

    public String getHovaten() {
        return hovaten;
    }

    public void setHovaten(String hovaten) {
        this.hovaten = hovaten;
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

    public String getAnhsv() {
        return anhsv;
    }
    public void setAnhsv(String anhsv) {
        this.anhsv = anhsv;
    }
}
