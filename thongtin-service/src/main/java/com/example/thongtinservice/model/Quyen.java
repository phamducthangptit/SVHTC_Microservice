package com.example.thongtinservice.Model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "QUYEN")
public class Quyen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDQUYEN", nullable = false)
    private Integer idQuyen;

    @Column(name = "TENQUYEN")
    private String tenQuyen;

    @OneToMany(mappedBy = "quyen",fetch = FetchType.LAZY)
    private List<TaiKhoan> taiKhoan;

    public Quyen() {
    }
    public Quyen(Integer idQuyen, String tenQuyen) {
        this.idQuyen = idQuyen;
        this.tenQuyen = tenQuyen;
    }

    public Integer getIdQuyen() {
        return idQuyen;
    }

    public void setIdQuyen(Integer idQuyen) {
        this.idQuyen = idQuyen;
    }

    public String getTenQuyen() {
        return tenQuyen;
    }

    public void setTenQuyen(String tenQuyen) {
        this.tenQuyen = tenQuyen;
    }

    public List<TaiKhoan> getTaiKhoan() {
        return taiKhoan;
    }

    public void setTaiKhoan(List<TaiKhoan> taiKhoan) {
        this.taiKhoan = taiKhoan;
    }
}
