package com.example.thongtinservice.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.thongtinservice.DTO.ApiResponse;
import com.example.thongtinservice.Model.Khoa;
import com.example.thongtinservice.Repository.KhoaRepository;

@Service
public class KhoaService {
    @Autowired
    private KhoaRepository khoaRepository;

    public ApiResponse getListKhoa() {
        List<Khoa> data = khoaRepository.getListKhoa();
        return new ApiResponse<List<Khoa>>(200, "Lấy danh sách khoa thàng công", data);
    }
}
