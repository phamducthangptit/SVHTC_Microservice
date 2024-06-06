package com.example.loptinchiservice.DTO;



public class SinhVienDTO {
    private String masv;
    private String ho;
    private String ten;
    private String malop;
    public SinhVienDTO()
    {

    }

    public SinhVienDTO(String masv, String ho, String ten, String malop) {
        this.masv = masv;
        this.ho = ho;
        this.ten = ten;
        this.malop = malop;
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

    public String getMalop() {
        return malop;
    }

    public void setMalop(String malop) {
        this.malop = malop;
    }
}
