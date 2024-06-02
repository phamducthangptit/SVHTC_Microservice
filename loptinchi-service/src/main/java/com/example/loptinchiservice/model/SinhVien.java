package com.example.loptinchiservice.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Nationalized;

@Data
@Entity
@Table(name = "SINHVIEN")
@AllArgsConstructor
@NoArgsConstructor
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
}
