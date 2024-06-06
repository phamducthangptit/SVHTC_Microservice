package com.example.thongtinservice.Repository;

import com.example.thongtinservice.Model.GiangVien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GiangVienRepository extends JpaRepository<GiangVien, String> {
    @Query(value = "{call SP_DANH_SACH_SINH_VIEN_DA_CO_DIEM(:nienkhoa, :hocki, :mamh)}", nativeQuery = true)
    List<Object[]> danhSachSinhVienDaCoDiem(@Param("nienkhoa") String nienKhoa,
                                            @Param("hocki") int hocKi,
                                            @Param("mamh") String maMH);

    @Procedure(procedureName = "SP_UPDATE_DIEM_SINH_VIEN")
    void updateDiem(@Param("masv") String maSv,
                    @Param("nienkhoa") String nienKhoa,
                    @Param("hocki") int hocKi,
                    @Param("mamh") String maMH,
                    @Param("diemcc") int diemCC,
                    @Param("diemgk") float diemGK,
                    @Param("diemck") float diemCK);
}
