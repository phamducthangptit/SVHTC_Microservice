package com.example.loptinchiservice.Model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import org.hibernate.annotations.Nationalized;

@Entity
@Table(name = "GIANGVIEN")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "magv")
public class GiangVien {
    @Id
    @Nationalized
    @Column(name = "MAGV", nullable = false, length = 10)
    private String magv;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "MAKHOA", nullable = false)
    private Khoa makhoa;

    @Nationalized
    @Column(name = "HO", nullable = false, length = 50)
    private String ho;

    @Nationalized
    @Column(name = "TEN", nullable = false, length = 10)
    private String ten;

    public GiangVien(String magv, Khoa makhoa, String ho, String ten) {
        this.magv = magv;
        this.makhoa = makhoa;
        this.ho = ho;
        this.ten = ten;
    }

    public String getMagv() {
        return magv;
    }

    public void setMagv(String magv) {
        this.magv = magv;
    }

    public Khoa getMakhoa() {
        return makhoa;
    }

    public void setMakhoa(Khoa makhoa) {
        this.makhoa = makhoa;
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