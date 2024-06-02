package com.example.loptinchiservice.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;
import org.hibernate.annotations.Nationalized;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class DangKyId implements Serializable {
    private static final long serialVersionUID = -5058280995356249564L;
    @Column(name = "MALTC", nullable = false)
    private Integer maltc;

    @Nationalized
    @Column(name = "MASV", nullable = false, length = 10)
    private String masv;

    public Integer getMaltc() {
        return maltc;
    }

    public void setMaltc(Integer maltc) {
        this.maltc = maltc;
    }

    public String getMasv() {
        return masv;
    }

    public void setMasv(String masv) {
        this.masv = masv;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        DangKyId entity = (DangKyId) o;
        return Objects.equals(this.masv, entity.masv) &&
                Objects.equals(this.maltc, entity.maltc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(masv, maltc);
    }

}
