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
public interface SinhVienRepo extends JpaRepository<SinhVien, String> {

    @Query(value = "{call SP_XEM_HOC_PHI_SV (:masv)}", nativeQuery = true)
    List<Map<String,Object>> hocphiSV(
            @Param("masv") String masv
    );
    @Query(value = "{call SP_XEM_DSSV_HOC_PHI}", nativeQuery = true)
    List<Map<String,Object>> DSSVhocphi();
}
