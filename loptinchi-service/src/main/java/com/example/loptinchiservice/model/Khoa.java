package com.example.loptinchiservice.Model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.Nationalized;

@Entity
@Table(name = "KHOA")
public class Khoa {
    @Id
    @Nationalized
    @Column(name = "MAKHOA", nullable = false, length = 10)
    private String makhoa;

    @Nationalized
    @Column(name = "TENKHOA", nullable = false, length = 50)
    private String tenkhoa;

    public String getMakhoa() {
        return makhoa;
    }

    public void setMakhoa(String makhoa) {
        this.makhoa = makhoa;
    }

    public String getTenkhoa() {
        return tenkhoa;
    }

    public void setTenkhoa(String tenkhoa) {
        this.tenkhoa = tenkhoa;
    }

}