package com.example.loptinchiservice.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.loptinchiservice.DTO.ApiResponse;
import com.example.loptinchiservice.Repository.LopRepository;

@Service
public class LopService {
    @Autowired
    private LopRepository lopRepository;

    public ApiResponse themLopHocMoi(String maLop, String tenLop, String tenKhoa) {
        lopRepository.insertLop(maLop, tenLop, tenKhoa);
        return new ApiResponse<>(200, "Thêm lớp học thành công!", null);
    }

    public ApiResponse thayDoiLopHoc(String maLop, String tenLop, String tenKhoa) {
        lopRepository.updateLop(maLop, tenLop, tenKhoa);
        return new ApiResponse<>(200, "Thay đổi thông tin lớp thành công!", null);
    }

    public ApiResponse xoaLopHoc(String maLop) {
        int kiemTraXoaLop = lopRepository.kiemTraXoaLop(maLop);
        if (kiemTraXoaLop == 1) {
            return new ApiResponse<>(201, "Không thể xoá lớp học này!", null);
        }
        lopRepository.deleteLop(maLop);
        return new ApiResponse<>(200, "Xoá lớp học thành công!", null);
    }
}
