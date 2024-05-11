package com.example.thanhtoanhocphiservice.repository;

import com.example.thanhtoanhocphiservice.id.HocPhiId;
import com.example.thanhtoanhocphiservice.model.HocPhi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface HocPhiRepository extends JpaRepository<HocPhi, HocPhiId> {
    @Query(value = "{call sp_get_thong_tin_hp(:masv)}", nativeQuery = true)
    public List<Map<String, String>> getThongTinHP(@Param("masv") String maSV);

    @Query(value = "{call sp_update_trang_thai_hp(:masv, :nienkhoa, :hocki)}", nativeQuery = true)
    public int updateTrangThaiHP(@Param("masv") String maSV, @Param("nienkhoa") String nienKhoa, @Param("hocki") int hocKi);
}
