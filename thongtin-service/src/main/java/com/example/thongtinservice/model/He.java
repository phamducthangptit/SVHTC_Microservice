package com.example.thongtinservice.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import org.hibernate.annotations.Nationalized;

@Entity
@Table(name = "HE")
public class He {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_HE", nullable = false)
    @JsonProperty("ID_HE")
    private Integer id;

    @Nationalized
    @Column(name = "TEN_HE", nullable = false, length = 80)
    @JsonProperty("TEN_HE")
    private String tenHe;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTenHe() {
        return tenHe;
    }

    public void setTenHe(String tenHe) {
        this.tenHe = tenHe;
    }

}