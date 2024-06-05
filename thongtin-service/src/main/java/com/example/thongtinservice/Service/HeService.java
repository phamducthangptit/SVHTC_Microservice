package com.example.thongtinservice.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.thongtinservice.DTO.ApiResponse;
import com.example.thongtinservice.Model.He;
import com.example.thongtinservice.Repository.HeRepository;

@Service
public class HeService {
    @Autowired
    private HeRepository heRepository;

    public ApiResponse getListHe() {
        List<He> data = heRepository.getListHe();
        return new ApiResponse<List<He>>(200, "Lấy danh sách hệ thành công!", data);
    }
}
