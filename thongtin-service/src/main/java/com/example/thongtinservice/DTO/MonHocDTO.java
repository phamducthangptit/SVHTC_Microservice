package com.example.thongtinservice.DTO;

import com.example.thongtinservice.Model.MonHoc;

public class MonHocDTO {
    private int stt;
    private MonHoc MonHoc;

    public MonHocDTO() {

    }

    public MonHocDTO(int stt, MonHoc monHoc) {
        this.stt = stt;
        MonHoc = monHoc;
    }

    public int getStt() {
        return stt;
    }

    public void setStt(int stt) {
        this.stt = stt;
    }

    public MonHoc getMonHoc() {
        return MonHoc;
    }

    public void setMonHoc(MonHoc monHoc) {
        MonHoc = monHoc;
    }

}
