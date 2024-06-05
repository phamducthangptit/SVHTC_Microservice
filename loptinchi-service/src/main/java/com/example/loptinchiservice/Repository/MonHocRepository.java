package com.example.loptinchiservice.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import com.example.loptinchiservice.Model.MonHoc;

public interface MonHocRepository extends JpaRepository<MonHoc, String> {

    @Procedure(procedureName = "SP_INSERT_MON_HOC")
    void insertMonHoc(@Param("mamh") String mamh,
            @Param("tenmh") String tenmh,
            @Param("sotlt") int sotlt,
            @Param("sotth") int sotth,
            @Param("sotc") int sotc);

}
