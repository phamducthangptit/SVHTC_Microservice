package com.example.thongtinservice.Repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import com.example.thongtinservice.Model.Lop;

public interface LopRepository extends JpaRepository<Lop, String> {

        @Query(value = "{call SP_COUNT_LOP()}", nativeQuery = true)
        Integer getSizeListLop();

        @Query(value = "{call SP_GET_LIST_LOP(:start, :size)}", nativeQuery = true)
        List<Map<String, Object>> getListLop(@Param("start") int start,
                        @Param("size") int size);

        @Query(value = "{call SP_FIND_LOP(:search)}", nativeQuery = true)
        List<Map<String, Object>> findListLop(@Param("search") String search);

        @Procedure(procedureName = "SP_DELETE_LOP")
        void deleteMonHoc(@Param("malop") String mamh);

        @Procedure(procedureName = "SP_INSERT_LOP")
        void insertLop(@Param("malop") String malop,
                        @Param("tenlop") String tenmh,
                        @Param("khoahoc") int sotlt,
                        @Param("makhoa") int sotth,
                        @Param("idhe") int sotc,
                        @Param("trangthai") boolean trangThai);

        // call sp kiểm tra môn học có tồn tại hay không?
        @Query(value = "{call SP_KIEM_TRA_MALOP(:malop)}", nativeQuery = true)
        Integer kiemTraLop(@Param("malop") String malop);

        // call sp kiểm tra môn học có thể xoá hay không?
        @Query(value = "{call SP_KIEM_TRA_XOA_LOP(:MALOP)}", nativeQuery = true)
        Integer kiemTraXoaLop(@Param("MALOP") String malop);

        @Procedure(procedureName = "SP_UPDATE_LOP")
        void updateLop(@Param("malop") String mamh,
                        @Param("tenlop") String tenmh,
                        @Param("khoahoc") int sotlt,
                        @Param("makhoa") int sotth,
                        @Param("idhe") int sotc,
                        @Param("trangthai") boolean trangThai);

        @Query(value = "{call SP_DANH_SACH_LOP_CUA_KHOA(:magv, :trangthai)}", nativeQuery = true)
        public List<Map<String, ?>> danhSachLopCuaKhoa(String magv, int trangthai);

        public Lop findBymalop(String malop);

        @Query(value = "{call SP_UPDATE_LOP(:malop, :tenlop, :khoahoc, :trangthai, :he)}", nativeQuery = true)
        public int updateLop(String malop, String tenlop, String khoahoc, int trangthai, int he);

        public void deleteBymalop(String malop);

        @Query(value = "{call SP_FIND_LIST_MA_LOP()}", nativeQuery = true)
        List<Map<String, Object>> findDanhSachLop();

}
