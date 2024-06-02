package com.example.thongtinservice.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.thongtinservice.DTO.ApiResponse;
import com.example.thongtinservice.DTO.LopDTO;
import com.example.thongtinservice.Model.Lop;

import com.example.thongtinservice.Repository.LopRepository;

@Service
public class LopService {
    @Autowired
    private LopRepository lopRepository;

    public LopDTO mapLopDTO(Map<String, Object> lop, int stt) {
        LopDTO result = new LopDTO();
        result.setStt(stt);
        result.setMaLop((String) lop.get("MALOP"));
        result.setTenLop((String) lop.get("TENLOP"));
        result.setKhoaHoc((String) lop.get("KHOAHOC"));
        result.setTenHe((String) lop.get("TEN_HE"));
        result.setTenKhoa((String) lop.get("TENKHOA"));
        return result;
    }

    public ApiResponse getSizeListLop() {
        Integer size = lopRepository.getSizeListLop();
        if (size == null) {
            return new ApiResponse<Integer>(201, "Lấy danh sách lớp học thất bại!", null);
        }
        return new ApiResponse<Integer>(200, "Lấy danh sách lớp học thành công!", size);
    }

    public ApiResponse getListLop(int start, int size) {
        List<Map<String, Object>> dsLop = lopRepository.getListLop(start, size);
        List<LopDTO> dsLopDTO = new ArrayList<>();
        if (dsLop == null) {
            return new ApiResponse<List<LopDTO>>(201, "Lấy danh sách lớp học thất bại!", null);
        }

        for (int i = 0; i < dsLop.size(); i++) {
            LopDTO dto = mapLopDTO(dsLop.get(i), start + i + 1);
            dsLopDTO.add(dto);
        }
        return new ApiResponse<List<LopDTO>>(200, "Lấy danh sách lớp học thành công!", dsLopDTO);
    }

    public ApiResponse timLop(String search) {
        List<Map<String, Object>> dsLop = lopRepository.findListLop(search);
        List<LopDTO> dsLopDTO = new ArrayList<>();
        if (dsLop == null) {
            return new ApiResponse<List<LopDTO>>(201, "Lấy danh sách lớp học thất bại!", null);
        }

        for (int i = 0; i < dsLop.size(); i++) {
            LopDTO dto = mapLopDTO(dsLop.get(i), i + 1);
            dsLopDTO.add(dto);
        }
        return new ApiResponse<List<LopDTO>>(200, "Lấy danh sách lớp học thành công!", dsLopDTO);
    }

    public List<Lop> getAllLop() {
        return lopRepository.findAll();
    }

    public Lop lopTheoMaLop(String maLop) {
        if (maLop == null) {
            return null;
        }
        return lopRepository.findBymalop(maLop);
    }

}