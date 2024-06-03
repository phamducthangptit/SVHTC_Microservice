package com.example.loptinchiservice.DTO;

public class MonHocDTO {
    private String mamh;
    private String tenmh;
    public MonHocDTO()
    {

    }

    public MonHocDTO(String mamh, String tenmh) {
        this.mamh = mamh;
        this.tenmh = tenmh;
    }

    public String getMamh() {
        return mamh;
    }

    public void setMamh(String mamh) {
        this.mamh = mamh;
    }

    public String getTenmh() {
        return tenmh;
    }

    public void setTenmh(String tenmh) {
        this.tenmh = tenmh;
    }
}
