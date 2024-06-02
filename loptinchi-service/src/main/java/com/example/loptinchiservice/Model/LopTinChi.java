package com.example.loptinchiservice.Model;

import jakarta.persistence.*;
import org.hibernate.annotations.Nationalized;

@Entity
@Table(name = "LOPTINCHI")
public class LopTinChi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MALTC", nullable = false)
    private Integer id;

    @Nationalized
    @Column(name = "NIENKHOA", nullable = false, length = 9)
    private String nienkhoa;

    @Column(name = "HOCKY", nullable = false)
    private Integer hocky;

    @Column(name = "NHOM", nullable = false)
    private Integer nhom;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "MAGV", nullable = false)
    private GiangVien magv;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "MAKHOA", nullable = false)
    private Khoa makhoa;

    @Column(name = "SOSVTOITHIEU", nullable = false)
    private Integer sosvtoithieu;

    @Column(name = "DONGLOP", nullable = false)
    private Boolean donglop = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MALOP")
    private Lop malop;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNienkhoa() {
        return nienkhoa;
    }

    public void setNienkhoa(String nienkhoa) {
        this.nienkhoa = nienkhoa;
    }

    public Integer getHocky() {
        return hocky;
    }

    public void setHocky(Integer hocky) {
        this.hocky = hocky;
    }

    public Integer getNhom() {
        return nhom;
    }

    public void setNhom(Integer nhom) {
        this.nhom = nhom;
    }

    public GiangVien getMagv() {
        return magv;
    }

    public void setMagv(GiangVien magv) {
        this.magv = magv;
    }

    public Khoa getMakhoa() {
        return makhoa;
    }

    public void setMakhoa(Khoa makhoa) {
        this.makhoa = makhoa;
    }

    public Integer getSosvtoithieu() {
        return sosvtoithieu;
    }

    public void setSosvtoithieu(Integer sosvtoithieu) {
        this.sosvtoithieu = sosvtoithieu;
    }

    public Boolean getDonglop() {
        return donglop;
    }

    public void setDonglop(Boolean donglop) {
        this.donglop = donglop;
    }

    public Lop getMalop() {
        return malop;
    }

    public void setMalop(Lop malop) {
        this.malop = malop;
    }

}