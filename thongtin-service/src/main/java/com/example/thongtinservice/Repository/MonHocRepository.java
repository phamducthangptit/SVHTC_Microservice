package com.example.thongtinservice.Repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.thongtinservice.Model.MonHoc;

public interface MonHocRepository extends JpaRepository<MonHoc, String> {
    @Query(value = "{call SP_GET_LIST_MON_HOC(:start, :size)}", nativeQuery = true)
    List<MonHoc> getListMonHoc(@Param("start") int start,
            @Param("size") int size);

}
