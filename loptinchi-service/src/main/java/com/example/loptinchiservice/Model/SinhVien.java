package com.example.loptinchiservice.Model;
import jakarta.persistence.*;
import org.hibernate.annotations.Nationalized;

@Entity
@Table(name = "SINHVIEN")
public class SinhVien {
    @Id
    @Nationalized
    @Column(name = "MASV", nullable = false, length = 10)
    private String masv;

    @Nationalized
    @Column(name = "HO", nullable = false, length = 50)
    private String ho;

    @Nationalized
    @Column(name = "TEN", nullable = false, length = 10)
    private String ten;
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "MALOP", nullable = false)
    private Lop malop;


    public SinhVien() {
    }

    public String getMasv() {
        return masv;
    }

    public void setMasv(String masv) {
        this.masv = masv;
    }

    public String getHo() {
        return ho;
    }

    public void setHo(String ho) {
        this.ho = ho;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public Lop getMalop() {
        return malop;
    }

    public void setMalop(Lop malop) {
        this.malop = malop;
    }
}

