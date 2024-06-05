package com.example.loptinchiservice.Repository;

import com.example.loptinchiservice.Model.SinhVien;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Transactional
public interface SinhVienRepository extends JpaRepository<SinhVien, String> {
    @Query(value = "{call SP_GET_DANH_SACH_SV_LTC(:maltc)}", nativeQuery = true)
    List<Object[]> danhSachLtcTheoMaGV(@Param("maltc") int maLTC);
}
