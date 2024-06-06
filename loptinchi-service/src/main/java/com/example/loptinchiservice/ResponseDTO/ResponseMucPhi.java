package com.example.loptinchiservice.ResponseDTO;

public class ResponseMucPhi {
    private String maSV;
    private String maMH;

    public ResponseMucPhi() {
    }

    public ResponseMucPhi(String maSV, String maMH) {
        this.maSV = maSV;
        this.maMH = maMH;
    }

    public String getMaSV() {
        return maSV;
    }

    public void setMaSV(String maSV) {
        this.maSV = maSV;
    }

    public String getMaMH() {
        return maMH;
    }

    public void setMaMH(String maMH) {
        this.maMH = maMH;
    }

}