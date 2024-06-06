package com.example.loptinchiservice.Repository;

import com.example.loptinchiservice.Model.SinhVien;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Transactional
public interface SinhVienRepository extends JpaRepository<SinhVien, String> {
    @Query(value = "{call SP_GET_DANH_SACH_SV_LTC(:maltc)}", nativeQuery = true)
    List<Object[]> danhSachLtcTheoMaGV(@Param("maltc") int maLTC);
    @Query(value = "{call SP_LIST_SV_LTC(:masv)}", nativeQuery = true)
    List<String> danhSachLtcSV(@Param("masv") String masv);

    @Procedure(procedureName = "SP_THEM_SV")
    void themSV(
            @Param("masv") String masv,
            @Param("ho") String ho,
            @Param("ten") String ten,
            @Param("malop") String malop
    );
    @Procedure(procedureName = "SP_UPDATE_SV")
    void updateSV(
            @Param("masv") String masv,
            @Param("ho") String ho,
            @Param("ten") String ten,
            @Param("malop") String malop
    );
    @Procedure(procedureName = "SP_DELETE_SV")
    void xoaSV(
            @Param("masv") String masv
    );
}
