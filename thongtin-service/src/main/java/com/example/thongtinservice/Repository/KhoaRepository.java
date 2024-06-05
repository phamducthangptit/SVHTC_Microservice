package com.example.thongtinservice.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.thongtinservice.Model.Khoa;

public interface KhoaRepository extends JpaRepository<Khoa, String> {
    @Query(value = "{call SP_GET_LIST_KHOA()}", nativeQuery = true)
    List<Khoa> getListKhoa();
}
