package com.example.thongtinservice.model;

import jakarta.persistence.*;
import org.hibernate.annotations.Nationalized;

@Entity
@Table(name = "SINHVIEN")
public class SinhVien {
    @Id
    @Nationalized
    @Column(name = "MASV", nullable = false, length = 10)
    private String masv;

    @Nationalized
    @Column(name = "HO", nullable = false, length = 50)
    private String ho;

    @Nationalized
    @Column(name = "TEN", nullable = false, length = 10)
    private String ten;

    @Column(name = "PHAI", nullable = false)
    private Boolean phai = false;

    @Nationalized
    @Column(name = "DIACHI", length = 100)
    private String diachi;

    @Column(name = "NGAYSINH")
    private String ngaysinh;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "MALOP", nullable = false)
    private Lop malop;

    @Column(name = "DANGHIHOC", nullable = false)
    private Boolean danghihoc = false;


    @Column(name = "SDT")
    private String sdt;

    @Column(name = "HINHANH")
    private String hinhanh;
    @Column(name = "EMAIL")
    private String email;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "MASV")
    @MapsId
    private TaiKhoan taiKhoan;

    public SinhVien() {
    }

    public SinhVien(String masv, String ho, String ten, Boolean phai, String diachi, String ngaysinh, Lop malop, Boolean danghihoc, String sdt, String hinhanh, String email) {
        this.masv = masv;
        this.ho = ho;
        this.ten = ten;
        this.phai = phai;
        this.diachi = diachi;
        this.ngaysinh = ngaysinh;
        this.malop = malop;
        this.danghihoc = danghihoc;
        this.sdt = sdt;
        this.hinhanh = hinhanh;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public TaiKhoan getTaiKhoan() {
        return taiKhoan;
    }

    public void setTaiKhoan(TaiKhoan taiKhoan) {
        this.taiKhoan = taiKhoan;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
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

    public Boolean getPhai() {
        return phai;
    }

    public void setPhai(Boolean phai) {
        this.phai = phai;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getNgaysinh() {
        return ngaysinh;
    }

    public void setNgaysinh(String ngaysinh) {
        this.ngaysinh = ngaysinh;
    }

    public Lop getMalop() {
        return malop;
    }

    public void setMalop(Lop malop) {
        this.malop = malop;
    }

    public Boolean getDanghihoc() {
        return danghihoc;
    }

    public String getHinhanh() {
        return hinhanh;
    }


}