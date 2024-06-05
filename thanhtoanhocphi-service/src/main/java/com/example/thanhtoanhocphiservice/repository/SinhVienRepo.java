package com.example.thanhtoanhocphiservice.repository;


import com.example.thanhtoanhocphiservice.model.SinhVien;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

@Transactional
public interface SinhVienRepo extends JpaRepository<String, SinhVien> {
    @Procedure(procedureName = "SP_THEM_SV")
    void themSV(
            @Param("masv") String masv,
            @Param("ho") String ho,
            @Param("ten") String ten
    );
    @Procedure(procedureName = "SP_UPDATE_SV")
    void updateSV(
            @Param("masv") String masv,
            @Param("ho") String ho,
            @Param("ten") String ten
    );
    @Procedure(procedureName = "SP_DELETE_SV")
    void xoaSV(
            @Param("masv") String masv
    );
    @Query(value = "EXEC SP_XEM_HOC_PHI_SV :masv", nativeQuery = true)
    List<Map<String,Object>> hocphiSV(
            @Param("masv") String masv
    );
}
