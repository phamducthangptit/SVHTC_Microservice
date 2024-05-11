package com.example.thanhtoanhocphiservice.id;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.annotations.Nationalized;

@Embeddable
public class HocPhiId {
    private static final long serialVersionUID = 417954944571108266L;
    @Nationalized
    @Column(name = "masv", nullable = false, length = 10)
    private String maSV;

    @Nationalized
    @Column(name = "nienkhoa", nullable = false, length = 9)
    private String nienKhoa;

    @Column(name = "hocki", nullable = false)
    private Integer hocKi;
}
