package com.example.loptinchiservice.Repository;

import com.example.loptinchiservice.DTO.LopTinChiDTO;
import com.example.loptinchiservice.Model.GiangVien;
import com.example.loptinchiservice.Model.LopTinChi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface LopTinChiRepository extends JpaRepository<LopTinChi, Integer> {
    @Query(value = "EXEC SP_LOC_MA_KHOA", nativeQuery = true)
    List<String> locMaKhoa();
    @Query(value = "EXEC SP_LOC_NIEN_KHOA", nativeQuery = true)
    List<String> locNienKhoa();
    @Query(value = "EXEC SP_LOC_GV_KHOA :makhoa", nativeQuery = true)
    List<GiangVien> locGiangVien(@Param("makhoa") String makhoa);


    @Query(value = "EXEC SP_LOC_LTC :makhoa, :nienkhoa, :hocki", nativeQuery = true)
    List<Map<String,Object>> locLTC(
            @Param("makhoa") String makhoa,
            @Param("nienkhoa") String nienkhoa,
            @Param("hocki") int hocki
    );

}
