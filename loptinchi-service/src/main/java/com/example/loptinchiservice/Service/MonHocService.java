package com.example.loptinchiservice.Service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.loptinchiservice.DTO.ApiResponse;
import com.example.loptinchiservice.Model.MonHoc;
import com.example.loptinchiservice.Repository.MonHocRepository;

@Service
public class MonHocService {
    @Autowired
    private MonHocRepository monHocRepository;

    public ApiResponse insertMonHoc(MonHoc monHoc) {
        monHocRepository.insertMonHoc(monHoc.getMaMH(),
                monHoc.getTenMH(),
                monHoc.getSoTietLT(),
                monHoc.getSoTietTH(),
                monHoc.getSoTinChi());
        return new ApiResponse<>(200, "Thêm mới môn học thành công", null);
    }
}
