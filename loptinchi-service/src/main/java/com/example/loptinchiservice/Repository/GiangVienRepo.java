package com.example.loptinchiservice.Repository;

import com.example.loptinchiservice.Model.GiangVien;
import com.example.loptinchiservice.Model.SinhVien;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.util.List;
@Transactional
public interface GiangVienRepo extends JpaRepository<GiangVien, String> {
    @Query(value = "{call SP_LIST_GV_LTC(:magv)}", nativeQuery = true)
    List<String> danhSachLtcGV(@Param("magv") String magv);
    @Procedure(procedureName = "SP_THEM_GV")
    void themGiangVienMoi(
            @Param("magv") String magv,
            @Param("ho") String ho,
            @Param("ten") String ten,
            @Param("makhoa") String makhoa

    );
    @Procedure (procedureName = "SP_DELETE_GV")
    void xoaGiangVien(
            @Param("magv") String magv
    );
    @Procedure (procedureName = "SP_UPDATE_GV")
    void updateGiangVien(
            @Param("magv") String magv,
            @Param("ho") String ho,
            @Param("ten") String ten,
            @Param("makhoa") String makhoa
    );
}
