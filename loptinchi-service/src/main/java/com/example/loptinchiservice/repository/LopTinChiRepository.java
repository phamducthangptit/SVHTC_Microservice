package com.example.loptinchiservice.repository;

import com.example.loptinchiservice.model.LopTinChi;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Transactional
public interface LopTinChiRepository extends JpaRepository<LopTinChi, Integer> {
    @Query(value = "{call SP_DANH_SACH_LTC_THEO_MAGV(:magv)}", nativeQuery = true)
    List<Object[]> danhSachLtcTheoMaGV(@Param("magv") String maGV);
}
