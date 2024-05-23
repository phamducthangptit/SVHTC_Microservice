package com.example.thongtinservice.Model;

import jakarta.persistence.*;
import org.hibernate.annotations.Nationalized;

@Entity
@Table(name = "MUCPHI")
public class MucPhi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PHI", nullable = false)
    private Integer id;

    @Nationalized
    @Column(name = "KHOA", nullable = false, length = 9)
    private String khoa;

    @Column(name = "NAMHOC", nullable = false)
    private Integer namhoc;

    @Column(name = "LOAI", nullable = false)
    private Integer loai;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_HE", nullable = false)
    private He idHe;

    @Column(name = "MUC_PHI", nullable = false)
    private Integer mucPhi;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKhoa() {
        return khoa;
    }

    public void setKhoa(String khoa) {
        this.khoa = khoa;
    }

    public Integer getNamhoc() {
        return namhoc;
    }

    public void setNamhoc(Integer namhoc) {
        this.namhoc = namhoc;
    }

    public Integer getLoai() {
        return loai;
    }

    public void setLoai(Integer loai) {
        this.loai = loai;
    }

    public He getIdHe() {
        return idHe;
    }

    public void setIdHe(He idHe) {
        this.idHe = idHe;
    }

    public Integer getMucPhi() {
        return mucPhi;
    }

    public void setMucPhi(Integer mucPhi) {
        this.mucPhi = mucPhi;
    }

}