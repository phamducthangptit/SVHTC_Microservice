package com.example.loginservice.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "TAIKHOAN")
public class TaiKhoan {
    @Id
    @Column(name = "USERNAME")
    private String username;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "TRANGTHAI")
    private Boolean trangThai;

    @ManyToOne
    @JoinColumn(name = "IDQUYEN")
    private Quyen quyen;

    public TaiKhoan() {
    }

    public TaiKhoan(String username, String password, Boolean trangThai, Quyen quyen) {
        this.username = username;
        this.password = password;
        this.trangThai = trangThai;
        this.quyen = quyen;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Boolean trangThai) {
        this.trangThai = trangThai;
    }

    public Quyen getQuyen() {
        return quyen;
    }

    public void setQuyen(Quyen quyen) {
        this.quyen = quyen;
    }

}
