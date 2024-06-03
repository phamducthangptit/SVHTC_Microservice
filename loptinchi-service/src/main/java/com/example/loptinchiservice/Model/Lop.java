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
@Table(name = "LOP")
public class Lop {
    @Id
    @Nationalized
    @Column(name = "MALOP", nullable = false, length = 10)
    private String malop;

    @Nationalized
    @Column(name = "TENLOP", nullable = false, length = 50)
    private String tenlop;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "MAKHOA", nullable = false)
    private Khoa makhoa;
}
