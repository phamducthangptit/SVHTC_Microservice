package com.example.loptinchiservice.Repository;

import com.example.loptinchiservice.Model.Lop;
import com.example.loptinchiservice.Model.LopTinChi;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

@Transactional
public interface LopTinChiRepository extends JpaRepository<LopTinChi, Integer> {
        @Query(value = "{call SP_DANH_SACH_LTC_THEO_MAGV(:magv)}", nativeQuery = true)
        List<Object[]> danhSachLtcTheoMaGV(@Param("magv") String maGV);

        @Query(value = "{call SP_GET_LIST_LTC_DK(:namhoc, :hocki, :malop, :masv)}", nativeQuery = true)
        List<Map<String, Object>> getDanhSachLTCDeDangKi(
                        @Param("namhoc") String namhoc,
                        @Param("hocki") int hocki,
                        @Param("malop") String malop,
                        @Param("masv") String masv);

        @Query(value = "{call SP_GET_LIST_LTC_DA_DK(:namhoc, :hocki, :masv)}", nativeQuery = true)
        List<Map<String, Object>> getDanhSachLTCSVDaDangKi(
                        @Param("namhoc") String namhoc,
                        @Param("hocki") int hocki,
                        @Param("masv") String masv);

        @Query(value = "{call SP_GET_SO_LUONG_SV_DK(:maltc)}", nativeQuery = true)
        int getSoLuongDaDKTheoMaLTC(@Param("maltc") int maltc);

        @Procedure(procedureName = "SP_INSERT_DANGKI")
        void insertSVDangKi(@Param("maltc") int maltc,
                        @Param("masv") String masv);

        @Query(value = "{call SP_GET_THONG_TIN_LOP_123()}", nativeQuery = true)
        List<Map<String, Object>> getThongTinLop123();

        @Procedure(procedureName = "SP_DELETE_DANGKI")
        void deleteSVDangKi(@Param("maltc") int maltc,
                        @Param("masv") String masv);
}
