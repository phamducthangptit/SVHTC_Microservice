package com.example.loptinchiservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Nationalized;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "KHOA")
public class Khoa {
    @Id
    @Nationalized
    @Column(name = "MAKHOA", nullable = false, length = 10)
    private String makhoa;

    @Nationalized
    @Column(name = "TENKHOA", nullable = false, length = 50)
    private String tenkhoa;
}