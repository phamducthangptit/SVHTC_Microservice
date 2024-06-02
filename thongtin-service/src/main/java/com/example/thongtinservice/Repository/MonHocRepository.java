package com.example.thongtinservice.Repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import com.example.thongtinservice.Model.MonHoc;

public interface MonHocRepository extends JpaRepository<MonHoc, String> {
    @Query(value = "{call SP_GET_LIST_MON_HOC(:start, :size)}", nativeQuery = true)
    List<MonHoc> getListMonHoc(@Param("start") int start,
            @Param("size") int size);

    @Query(value = "{call SP_COUNT_MON_HOC()}", nativeQuery = true)
    Integer getSizeListMonHoc();

    @Query(value = "{call SP_FIND_MON_HOC(:search)}", nativeQuery = true)
    List<MonHoc> findListMonHoc(@Param("search") String search);

    @Procedure(procedureName = "SP_DELETE_MON_HOC")
    void deleteMonHoc(@Param("mamh") String mamh);

    @Procedure(procedureName = "SP_INSERT_MON_HOC")
    void insertMonHoc(@Param("mamh") String mamh,
            @Param("tenmh") String tenmh,
            @Param("sotlt") int sotlt,
            @Param("sotth") int sotth,
            @Param("sotc") int sotc);

    // call sp kiểm tra môn học có tồn tại hay không?
    @Query(value = "{call SP_KIEM_TRA_MAMH(:mamh)}", nativeQuery = true)
    Integer kiemTraMonHoc(@Param("mamh") String mamh);

    // call sp kiểm tra môn học có thể xoá hay không?
    @Query(value = "{call SP_KIEM_TRA_XOA_MH(:MAMH)}", nativeQuery = true)
    Integer kiemTraXoaMonHoc(@Param("MAMH") String mamh);

    @Procedure(procedureName = "SP_UPDATE_MON_HOC")
    void updateMonHoc(@Param("mamh") String mamh,
            @Param("tenmh") String tenmh,
            @Param("sotlt") int sotlt,
            @Param("sotth") int sotth,
            @Param("sotc") int sotc);

}
