package com.example.thongtinservice.repository;

import com.example.thongtinservice.model.SinhVien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface SinhVienRepository extends JpaRepository<SinhVien, String> {
    @Query(value = "{call SP_THONG_TIN_CA_NHAN_SINH_VIEN(:masv)}", nativeQuery = true)
    Map<String, ?> thongTinCaNhanSinhVien(@Param("masv") String maSV);

    @Query(value = "{call SP_GET_NIENKHOA_HOCKI_SV(:masv)}", nativeQuery = true)
    List<Object[]> getNienKhoaHocKi(@Param("masv") String maSV);

    @Query(value = "{call SP_GET_DIEM_SV(:masv)}", nativeQuery = true)
    List<Object[]> xemDiem(@Param("masv") String maSV);
}
