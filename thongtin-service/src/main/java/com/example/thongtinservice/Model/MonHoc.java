package com.example.thongtinservice.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import org.hibernate.annotations.Nationalized;
import java.util.List;

@Entity
@Table(name = "MONHOC")
public class MonHoc {
    @Id
    @Column(name = "MAMH", nullable = false, length = 10)
    private String maMH;

    @Column(name = "TENMH", nullable = false, length = 50)
    private String tenMH;

    @Column(name = "SOTIET_LT", nullable = false)
    private Integer soTietLT;

    @Column(name = "SOTIET_TH", nullable = false)
    private Integer soTietTH;

    @Column(name = "SOTINCHI")
    private Integer soTinChi;

    @OneToMany(mappedBy = "monHoc")
    private List<Diem> diem;

    public MonHoc() {
    }

    public MonHoc(String maMH, String tenMH, Integer soTietLT, Integer soTietTH, Integer soTinChi, List<Diem> diem) {
        this.maMH = maMH;
        this.tenMH = tenMH;
        this.soTietLT = soTietLT;
        this.soTietTH = soTietTH;
        this.soTinChi = soTinChi;
        this.diem = diem;
    }

    public String getMaMH() {
        return maMH;
    }

    public void setMaMH(String maMH) {
        this.maMH = maMH;
    }

    public String getTenMH() {
        return tenMH;
    }

    public void setTenMH(String tenMH) {
        this.tenMH = tenMH;
    }

    public Integer getSoTietLT() {
        return soTietLT;
    }

    public void setSoTietLT(Integer soTietLT) {
        this.soTietLT = soTietLT;
    }

    public Integer getSoTietTH() {
        return soTietTH;
    }

    public void setSoTietTH(Integer soTietTH) {
        this.soTietTH = soTietTH;
    }

    public Integer getSoTinChi() {
        return soTinChi;
    }

    public void setSoTinChi(Integer soTinChi) {
        this.soTinChi = soTinChi;
    }

    public List<Diem> getDiem() {
        return diem;
    }

    public void setDiem(List<Diem> diem) {
        this.diem = diem;
    }

    @Override
    public String toString() {
        return "MonHoc [maMH=" + maMH + ", tenMH=" + tenMH + ", soTietLT=" + soTietLT + ", soTietTH=" + soTietTH
                + ", soTinChi=" + soTinChi + ", diem=" + diem + "]";
    }

}