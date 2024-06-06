package com.example.thongtinservice.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.thongtinservice.Model.MonHoc;
import com.example.thongtinservice.Model.MucPhi;

public interface MucPhiRepository extends JpaRepository<MucPhi, Integer> {
    @Query(value = "{call SP_GET_MUC_PHI(:MASV, :MAMH)}", nativeQuery = true)
    Integer getMucPhi(@Param("MASV") String maSV,
            @Param("MAMH") String maMH);
}
