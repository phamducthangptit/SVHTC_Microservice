package com.example.loptinchiservice.ResponseDTO;

public class SinhVienHocPhi {
    private String maSV;
    private String hoSV;
    private String tenSV;
    private String nienKhoa;
    private int hocKi;
    private int soTien;

    public SinhVienHocPhi() {

    }

    public SinhVienHocPhi(String maSV, String hoSV, String tenSV, String nienKhoa, int hocKi, int soTien) {
        this.maSV = maSV;
        this.hoSV = hoSV;
        this.tenSV = tenSV;
        this.nienKhoa = nienKhoa;
        this.hocKi = hocKi;
        this.soTien = soTien;
    }

    public String getMaSV() {
        return maSV;
    }

    public void setMaSV(String maSV) {
        this.maSV = maSV;
    }

    public String getHoSV() {
        return hoSV;
    }

    public void setHoSV(String hoSV) {
        this.hoSV = hoSV;
    }

    public String getTenSV() {
        return tenSV;
    }

    public void setTenSV(String tenSV) {
        this.tenSV = tenSV;
    }

    public String getNienKhoa() {
        return nienKhoa;
    }

    public void setNienKhoa(String nienKhoa) {
        this.nienKhoa = nienKhoa;
    }

    public int getHocKi() {
        return hocKi;
    }

    public void setHocKi(int hocKi) {
        this.hocKi = hocKi;
    }

    public int getSoTien() {
        return soTien;
    }

    public void setSoTien(int soTien) {
        this.soTien = soTien;
    }

}
