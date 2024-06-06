package com.example.thongtinservice.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.thongtinservice.Model.He;

/**
 * HeRepository
 */
public interface HeRepository extends JpaRepository<He, Integer> {

    @Query(value = "{call SP_GET_LIST_HE()}", nativeQuery = true)
    List<He> getListHe();
}