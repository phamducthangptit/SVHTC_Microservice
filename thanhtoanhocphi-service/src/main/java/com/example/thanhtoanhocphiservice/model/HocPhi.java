package com.example.thanhtoanhocphiservice.model;

import com.example.thanhtoanhocphiservice.id.HocPhiId;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "hocphi")
public class HocPhi {
    @EmbeddedId
    private HocPhiId id;

    @Column(name = "sotien")
    private Integer soTien;

    @Column(name = "ngaydong")
    private Date ngayDong;
}
