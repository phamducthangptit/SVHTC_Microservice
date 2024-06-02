package com.example.loptinchiservice.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Nationalized;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "LOPTINCHI")
public class LopTinChi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MALTC", nullable = false)
    private Integer id;

    @Nationalized
    @Column(name = "NIENKHOA", nullable = false, length = 9)
    private String nienkhoa;

    @Column(name = "HOCKY", nullable = false)
    private Integer hocky;

    @Column(name = "NHOM", nullable = false)
    private Integer nhom;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "MAGV", nullable = false)
    private GiangVien magv;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "MAKHOA", nullable = false)
    private Khoa makhoa;

    @Column(name = "SOSVTOITHIEU", nullable = false)
    private Integer sosvtoithieu;

    @Column(name = "HUYLOP", nullable = false)
    private Boolean huylop = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MALOP")
    private Lop malop;
}
