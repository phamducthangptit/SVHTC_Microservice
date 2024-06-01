package com.example.loptinchiservice.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.Nationalized;

@Entity
@Table(name = "MONHOC")
public class MonHoc {
    @Id
    @Nationalized
    @Column(name = "MAMH", nullable = false, length = 10)
    @JsonProperty("MAMH")
    private String mamh;

    @Nationalized
    @Column(name = "TENMH", nullable = false, length = 50)
    @JsonProperty("TENMH")
    private String tenmh;

    @Column(name = "SOTIET_LT", nullable = false)
    @JsonProperty("SOTIET_LT")
    private Integer sotietLt;

    @JsonProperty("SOTIET_TH")
    @Column(name = "SOTIET_TH", nullable = false)
    private Integer sotietTh;

    @Column(name = "SOTINCHI")
    @JsonProperty("SOTINCHI")
    private Integer sotinchi;

    public String getMamh() {
        return mamh;
    }

    public void setMamh(String mamh) {
        this.mamh = mamh;
    }

    public String getTenmh() {
        return tenmh;
    }

    public void setTenmh(String tenmh) {
        this.tenmh = tenmh;
    }

    public Integer getSotietLt() {
        return sotietLt;
    }

    public void setSotietLt(Integer sotietLt) {
        this.sotietLt = sotietLt;
    }

    public Integer getSotietTh() {
        return sotietTh;
    }

    public void setSotietTh(Integer sotietTh) {
        this.sotietTh = sotietTh;
    }

    public Integer getSotinchi() {
        return sotinchi;
    }

    public void setSotinchi(Integer sotinchi) {
        this.sotinchi = sotinchi;
    }

}