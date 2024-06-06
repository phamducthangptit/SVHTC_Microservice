package com.example.thanhtoanhocphiservice.repository;

import com.example.thanhtoanhocphiservice.id.HocPhiId;
import com.example.thanhtoanhocphiservice.model.HocPhi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface HocPhiRepository extends JpaRepository<HocPhi, HocPhiId> {
        @Query(value = "{call sp_get_thong_tin_hp(:masv)}", nativeQuery = true)
        public List<Map<String, String>> getThongTinHP(@Param("masv") String maSV);

        @Query(value = "{call sp_update_trang_thai_hp(:masv, :nienkhoa, :hocki)}", nativeQuery = true)
        public int updateTrangThaiHP(@Param("masv") String maSV, @Param("nienkhoa") String nienKhoa,
                        @Param("hocki") int hocKi);

        @Procedure(procedureName = "SP_INSERT_HP")
        void insertHocPhi(@Param("maSV") String maSV,
                        @Param("nienKhoa") String nienKhoa,
                        @Param("hocKi") int hocKi,
                        @Param("soTien") int soTien);

        @Procedure(procedureName = "SP_UPDATE_HP")
        void updateHocPhi(@Param("maSV") String maSV,
                        @Param("nienKhoa") String nienKhoa,
                        @Param("hocKi") int hocKi,
                        @Param("soTien") int soTien);

        @Procedure(procedureName = "SP_INSERT_SV")
        void insertSinhVien(@Param("maSV") String maSV,
                        @Param("hoSV") String hoSV,
                        @Param("tenSV") String tenSV);

        @Query(value = "{call SP_KIEM_TRA_TON_TAI_SV(:masv)}", nativeQuery = true)
        int kiemTraTonTai(@Param("masv") String maSV);

        @Query(value = "{call SP_GET_HP_SV(:maSV, :nienKhoa, :hocKi)}", nativeQuery = true)
        int getSoTienHPSV(@Param("maSV") String maSV,
                        @Param("nienKhoa") String nienKhoa,
                        @Param("hocKi") int hocKi);

}
