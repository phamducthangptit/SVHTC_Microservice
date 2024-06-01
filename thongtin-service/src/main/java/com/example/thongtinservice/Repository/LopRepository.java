package com.example.thongtinservice.Repository;

import com.example.thongtinservice.Model.Lop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface LopRepository extends JpaRepository<Lop, String> {
    @Query(value = "{call SP_DANH_SACH_LOP_CUA_KHOA(:magv, :trangthai)}", nativeQuery = true)
    public List<Map<String, ?>> danhSachLopCuaKhoa(String magv, int trangthai);

    public Lop findBymalop(String malop);

    @Query(value = "{call SP_UPDATE_LOP(:malop, :tenlop, :khoahoc, :trangthai, :he)}", nativeQuery = true)
    public int updateLop(String malop, String tenlop, String khoahoc, int trangthai, int he);

    public void deleteBymalop(String malop);

    @Query(value = "{call SP_FIND_LIST_MA_LOP()}", nativeQuery = true)
    List<Map<String,Object>> findDanhSachLop();


}
