package com.example.loptinchiservice.DTO;

public class GiangVienDTO {
    private String magv;
    private String ho;
    private String ten;
    public GiangVienDTO(){

    }

    public GiangVienDTO(String magv, String ho, String ten) {
        this.magv = magv;
        this.ho = ho;
        this.ten = ten;
    }

    public String getMagv() {
        return magv;
    }

    public void setMagv(String magv) {
        this.magv = magv;
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
