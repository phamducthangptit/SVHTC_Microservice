package com.example.loptinchiservice.Model;

import jakarta.persistence.*;
import org.hibernate.annotations.Nationalized;

@Entity
@Table(name = "CHUONGTRINHDAOTAO")
public class ChuongTrinhDaoTao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CTDT", nullable = false)
    private Integer id;

    @Column(name = "HOCKY", nullable = false)
    private Integer hocky;

    @Nationalized
    @Column(name = "NIENKHOA", nullable = false, length = 9)
    private String nienkhoa;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getHocky() {
        return hocky;
    }

    public void setHocky(Integer hocky) {
        this.hocky = hocky;
    }

    public String getNienkhoa() {
        return nienkhoa;
    }

    public void setNienkhoa(String nienkhoa) {
        this.nienkhoa = nienkhoa;
    }

}