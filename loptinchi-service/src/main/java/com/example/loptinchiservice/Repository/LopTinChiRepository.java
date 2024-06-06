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

        @Query(value = "EXEC SP_LOC_MA_KHOA", nativeQuery = true)
        List<String> locMaKhoa();

        @Query(value = "EXEC SP_LOC_NIEN_KHOA", nativeQuery = true)
        List<String> locNienKhoa();

        @Query(value = "EXEC SP_LOC_MON_HOC", nativeQuery = true)
        List<Map<String, Object>> locMonHoc();

        @Query(value = "EXEC SP_LOC_GV_KHOA :makhoa", nativeQuery = true)
        List<Map<String, Object>> locGiangVien(@Param("makhoa") String makhoa);
        @Query(value = "EXEC SP_LIST_LTC_SV:maltc", nativeQuery = true)
        List<String> LTCSV(@Param("maltc") int maltc);

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

        @Query(value = "EXEC SP_LOC_LOP_KHOA :makhoa", nativeQuery = true)
        List<String> danhSachLopKhoa(@Param("makhoa") String makhoa);

        @Query(value = "EXEC SP_LOC_LTC :makhoa, :nienkhoa, :hocki", nativeQuery = true)
        List<Map<String, Object>> locLTC(
                        @Param("makhoa") String makhoa,
                        @Param("nienkhoa") String nienkhoa,
                        @Param("hocki") int hocki);

        @Procedure(procedureName = "SP_THEM_LTC")
        void addLTC(
                        @Param("mamh") String mamh,
                        @Param("malop") String malop,
                        @Param("magv") String magv,
                        @Param("nienkhoa") String nienKhoa,
                        @Param("nhom") int nhom,
                        @Param("sosvtt") int soSVTT,
                        @Param("sosvtd") int soSVTD,
                        @Param("hocki") int hocKi,
                        @Param("makhoa") String maKhoa);

        @Procedure(procedureName = "SP_UPDATE_LTC")
        void updateLTC(
                        @Param("maltc") int maltc,
                        @Param("magv") String magv,
                        @Param("nhom") int nhom,
                        @Param("sosvtt") int soSVTT,
                        @Param("sosvtd") int soSVTD,
                        @Param("malop") String malop);

        @Procedure(procedureName = "SP_DELETE_LTC")
        void xoaLTC(
                        @Param("maltc") int maltc);

        @Query(value = "{call SP_DANH_SACH_LTC_THEO_MAGV(:magv)}", nativeQuery = true)
        List<Object[]> danhSachLtcTheoMaGV(@Param("magv") String maGV);

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
