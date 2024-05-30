package com.example.thongtinservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import org.hibernate.annotations.Nationalized;

@Entity
@Table(name = "NGANH")
public class Nganh {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_NGANH", nullable = false)
    @JsonProperty("ID_NGANH")
    private Integer id;

    @Nationalized
    @Column(name = "TEN", nullable = false, length = 50)
    @JsonProperty("TEN")
    private String ten;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

}