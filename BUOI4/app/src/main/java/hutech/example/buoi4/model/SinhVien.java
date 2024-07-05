package hutech.example.buoi4.model;

public class SinhVien {
    private String masv;
    private String hovaten;
    private String gioitinh;
    private int namsinh;
    private int anhsv;

    public SinhVien(String masv, String hovaten, String gioitinh, int namsinh, int anhsv) {
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

    public int getAnhsv() {
        return anhsv;
    }

    public void setAnhsv(int anhsv) {
        this.anhsv = anhsv;
    }


}
