package com.example.thanhtoanhocphiservice.dto;

public class HocPhiSVDTO {
    private String nienkhoa;
    private int hocki;
    private int sotien;
    private int dathu;
    private int conno;
    public HocPhiSVDTO()
    {

    }

    public HocPhiSVDTO(String nienkhoa, int hocki, int sotien, int dathu, int conno) {
        this.nienkhoa = nienkhoa;
        this.hocki = hocki;
        this.sotien = sotien;
        this.dathu = dathu;
        this.conno = conno;
    }

    public String getNienkhoa() {
        return nienkhoa;
    }

    public void setNienkhoa(String nienkhoa) {
        this.nienkhoa = nienkhoa;
    }

    public int getHocki() {
        return hocki;
    }

    public void setHocki(int hocki) {
        this.hocki = hocki;
    }

    public int getSotien() {
        return sotien;
    }

    public void setSotien(int sotien) {
        this.sotien = sotien;
    }

    public int getDathu() {
        return dathu;
    }

    public void setDathu(int dathu) {
        this.dathu = dathu;
    }

    public int getConno() {
        return conno;
    }

    public void setConno(int conno) {
        this.conno = conno;
    }
}
