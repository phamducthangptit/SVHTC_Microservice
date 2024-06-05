package com.example.thanhtoanhocphiservice.dto;

public class SinhVienDTO {
    private String masv;
    private String ho;
    private String ten;

    public SinhVienDTO()
    {

    }

    public SinhVienDTO(String masv, String ho, String ten) {
        this.masv = masv;
        this.ho = ho;
        this.ten = ten;

    }

    public String getMasv() {
        return masv;
    }

    public void setMasv(String masv) {
        this.masv = masv;
    }

    public String getHo() {
        return ho;
    }

    public void setHo(String ho) {
        this.ho = ho;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

}
