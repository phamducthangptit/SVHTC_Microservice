package com.example.loptinchiservice.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import com.example.loptinchiservice.Model.Lop;

public interface LopRepository extends JpaRepository<Lop, String> {

    @Procedure(procedureName = "SP_INSERT_LOP")
    void insertLop(@Param("malop") String maLop,
            @Param("tenlop") String tenLop,
            @Param("makhoa") String maKhoa);

    @Procedure(procedureName = "SP_UPDATE_LOP")
    void updateLop(@Param("malop") String maLop,
            @Param("tenlop") String tenLop,
            @Param("makhoa") String maKhoa);

    @Procedure(procedureName = "SP_DELETE_LOP")
    void deleteLop(@Param("malop") String maLop);

    @Query(value = "{call SP_KIEM_TRA_XOA_LOP(:MALOP)}", nativeQuery = true)
    int kiemTraXoaLop(@Param("MALOP") String maLop);

}
