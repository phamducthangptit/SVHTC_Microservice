package com.example.thanhtoanhocphiservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "sinhvien")
public class SinhVien {
    @Id
    @Column(name = "masv")
    private String maSv;
    @Column(name = "ho")
    private String ho;
    @Column(name = "ten")
    private String ten;

}
