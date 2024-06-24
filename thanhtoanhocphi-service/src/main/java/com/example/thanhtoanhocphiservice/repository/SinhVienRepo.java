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

    @Query(value = "{call SP_XEM_HOC_PHI_SV (:masvT)}", nativeQuery = true)
    List<Map<String,Object>> hocphiSV(
            @Param("masvT") String masv
    );
    @Query(value = "{call SP_XEM_DSSV_HOC_PHI(:nienkhoaT, :hockiT)}", nativeQuery = true)
    List<Map<String,Object>> DSSVhocphi(
            @Param("nienkhoaT") String nienkhoa,
             @Param("hockiT") int hocki
    );
    @Query(value = "{call SP_LOC_NIEN_KHOA}", nativeQuery = true)
    List<String> locNienKhoa();
}
