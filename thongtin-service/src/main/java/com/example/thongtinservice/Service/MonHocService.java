package com.example.thongtinservice.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.thongtinservice.DTO.ApiResponse;
import com.example.thongtinservice.Model.MonHoc;
import com.example.thongtinservice.Repository.MonHocRepository;
import java.util.List;

/**
 * MonHocService
 */
@Service
public class MonHocService {
    @Autowired
    private MonHocRepository monHocRepository;

    public ApiResponse getListMonHoc(int start, int size) {
        List<MonHoc> dsMonHoc = monHocRepository.getListMonHoc(start, size);
        if (dsMonHoc == null) {
            return new ApiResponse<List<MonHoc>>(201, "Lấy danh sách môn học thất bại!", null);
        }
        return new ApiResponse<List<MonHoc>>(200, "Lấy danh sách môn học thành công!", dsMonHoc);
    }

}