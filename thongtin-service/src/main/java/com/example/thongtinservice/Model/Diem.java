package com.example.thongtinservice.Model;

import org.hibernate.annotations.Nationalized;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "DIEM")
public class Diem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "MASV", nullable = false)
    private SinhVien sinhVien;

    @ManyToOne
    @JoinColumn(name = "MAMH", nullable = false)
    private MonHoc monHoc;

    @Nationalized
    @Column(name = "NIENKHOA", nullable = false, length = 9)
    private String nienKhoa;

    @Column(name = "DIEM_CC")
    private Integer diemCc;

    @Column(name = "DIEM_GK")
    private Double diemGk;

    @Column(name = "DIEM_CK")
    private Double diemCk;

    @Column(name = "HOCKI", nullable = false)
    private Integer hocky;

    public Diem() {
    }

    public Diem(Integer id, SinhVien sinhVien, MonHoc monHoc, String nienKhoa, Integer diemCc, Double diemGk,
            Double diemCk, Integer hocky) {
        this.id = id;
        this.sinhVien = sinhVien;
        this.monHoc = monHoc;
        this.nienKhoa = nienKhoa;
        this.diemCc = diemCc;
        this.diemGk = diemGk;
        this.diemCk = diemCk;
        this.hocky = hocky;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public SinhVien getSinhVien() {
        return sinhVien;
    }

    public void setSinhVien(SinhVien sinhVien) {
        this.sinhVien = sinhVien;
    }

    public MonHoc getMonHoc() {
        return monHoc;
    }

    public void setMonHoc(MonHoc monHoc) {
        this.monHoc = monHoc;
    }

    public String getNienKhoa() {
        return nienKhoa;
    }

    public void setNienKhoa(String nienKhoa) {
        this.nienKhoa = nienKhoa;
    }

    public Integer getDiemCc() {
        return diemCc;
    }

    public void setDiemCc(Integer diemCc) {
        this.diemCc = diemCc;
    }

    public Double getDiemGk() {
        return diemGk;
    }

    public void setDiemGk(Double diemGk) {
        this.diemGk = diemGk;
    }

    public Double getDiemCk() {
        return diemCk;
    }

    public void setDiemCk(Double diemCk) {
        this.diemCk = diemCk;
    }

    public Integer getHocky() {
        return hocky;
    }

    public void setHocky(Integer hocky) {
        this.hocky = hocky;
    }

    @Override
    public String toString() {
        return "Diem [id=" + id + ", sinhVien=" + sinhVien + ", monHoc=" + monHoc + ", nienKhoa=" + nienKhoa
                + ", diemCc=" + diemCc + ", diemGk=" + diemGk + ", diemCk=" + diemCk + ", hocky=" + hocky + "]";
    }

}
