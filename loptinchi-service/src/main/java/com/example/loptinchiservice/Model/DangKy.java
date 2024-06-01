package com.example.loptinchiservice.Model;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "DANGKY")
public class DangKy {
    @EmbeddedId
    private DangKyId id;

}
