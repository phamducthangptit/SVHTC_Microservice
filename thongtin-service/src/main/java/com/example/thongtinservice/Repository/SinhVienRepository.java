package com.example.thongtinservice.Repository;

import com.example.thongtinservice.DTO.SinhVienDTO;
import com.example.thongtinservice.Model.Lop;
import com.example.thongtinservice.Model.SinhVien;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;

import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;
@Transactional
public interface SinhVienRepository extends JpaRepository<SinhVien, String> {

    @Query(value = "{call THONGTIN_SINHVIEN(:MASV)}", nativeQuery = true)
    public Map<String, Object> thongtinSV(@Param("MASV") String masv);

    @Procedure(procedureName = "SP_THEM_SV_LOP")
    void themSinhVienMoi(
            @Param("masv") String masv,
            @Param("ho") String ho,
            @Param("ten") String ten,
            @Param("ngaysinh") String ngaysinh,
            @Param("phai") Boolean phai,
            @Param("sdt") String sdt,
            @Param("diachi") String diachi,
            @Param("malop") String malop,
            @Param("danghihoc") Boolean danghihoc,
            @Param("hinhanh") String hinhanh,
            @Param("email") String email,
            @Param("password") String password
    );

    @Procedure(procedureName = "SP_DELETE_SV")
    void xoaSinhVien(
            @Param("masv") String masv
    );



    @Query(value = "EXEC SP_LOC_MA_LOP", nativeQuery = true)
    List<String> locMaLop();

    @Procedure(procedureName = "SP_UPDATE_INFO_SV")
    void updateSinhVien(
            @Param("masv") String masv,
            @Param("ho") String ho,
            @Param("ten") String ten,
            @Param("ngaysinh") String ngaysinh,
            @Param("phai") Boolean phai,
            @Param("sdt") String sdt,
            @Param("diachi") String diachi,
            @Param("malop") String malop,
            @Param("danghihoc") Boolean danghihoc,
            @Param("hinhanh") String hinhanh,
            @Param("email") String email
    );


    @Query(value = "EXEC SP_LOC_DANH_SACH_LOP :malop", nativeQuery = true)
    List<SinhVien> danhSachSinhVien(@Param("malop") String malop);

    @Procedure(procedureName = "SP_DA_NGHI_HOC")
    void updateDaNghiHoc(
            @Param("masv") String masv

    );
    @Query(value = "EXEC SP_TIM_SINH_VIEN :masv", nativeQuery = true)
    SinhVienDTO timSinhVien(@Param("masv") String masv);

    @Query(value = "{call SP_THONG_TIN_CA_NHAN_SINH_VIEN(:masv)}", nativeQuery = true)
    Map<String, ?> thongTinCaNhanSinhVien(@Param("masv") String maSV);

    @Query(value = "{call SP_GET_NIENKHOA_HOCKI_SV(:masv)}", nativeQuery = true)
    List<Object[]> getNienKhoaHocKi(@Param("masv") String maSV);

    @Query(value = "{call SP_GET_DIEM_SV(:masv)}", nativeQuery = true)
    List<Object[]> xemDiem(@Param("masv") String maSV);
}

