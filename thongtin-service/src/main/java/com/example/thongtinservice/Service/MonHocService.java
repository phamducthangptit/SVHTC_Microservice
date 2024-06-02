package com.example.thongtinservice.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.thongtinservice.DTO.ApiResponse;
import com.example.thongtinservice.DTO.MonHocDTO;
import com.example.thongtinservice.Model.MonHoc;
import com.example.thongtinservice.Repository.MonHocRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * MonHocService
 */
@Service
public class MonHocService {
    @Autowired
    private MonHocRepository monHocRepository;

    public ApiResponse getSizeListMonHoc() {
        Integer dsMonHoc = monHocRepository.getSizeListMonHoc();
        if (dsMonHoc == null) {
            return new ApiResponse<Integer>(201, "Lấy danh sách môn học thất bại!", null);
        }
        return new ApiResponse<Integer>(200, "Lấy danh sách môn học thành công!", dsMonHoc);
    }

    public ApiResponse getListMonHoc(int start, int size) {
        List<MonHoc> dsMonHoc = monHocRepository.getListMonHoc(start, size);
        List<MonHocDTO> dsMonHocDTO = new ArrayList<>();
        if (dsMonHoc == null) {
            return new ApiResponse<List<MonHocDTO>>(201, "Lấy danh sách môn học thất bại!", null);
        }

        for (int i = 0; i < dsMonHoc.size(); i++) {
            MonHocDTO dto = new MonHocDTO(start + i + 1, dsMonHoc.get(i));
            dsMonHocDTO.add(dto);
        }
        return new ApiResponse<List<MonHocDTO>>(200, "Lấy danh sách môn học thành công!", dsMonHocDTO);
    }

    public ApiResponse findListMonHoc(String search) {
        List<MonHoc> dsMH = monHocRepository.findListMonHoc(search);
        List<MonHocDTO> dsMonHocDTO = new ArrayList<>();
        if (dsMH == null) {
            return new ApiResponse<List<MonHocDTO>>(202, "Không tìm thấy môn học!", null);
        }

        for (int i = 0; i < dsMH.size(); i++) {
            MonHocDTO dto = new MonHocDTO(i + 1, dsMH.get(i));
            dsMonHocDTO.add(dto);
        }
        return new ApiResponse<List<MonHocDTO>>(200, "Lấy danh sách môn học thành công!", dsMonHocDTO);
    }

    public ApiResponse insertMonHoc(MonHoc monHoc) {
        Integer kiemTraTrung = monHocRepository.kiemTraMonHoc(monHoc.getMaMH());
        if (kiemTraTrung == 1) {
            return new ApiResponse<Object>(203, "Môn học đã tồn tại", null);
        }
        // System.out.println("Check mon hoc");
        // System.out.println(kiemTraTrung);
        monHocRepository.insertMonHoc(monHoc.getMaMH(),
                monHoc.getTenMH(),
                monHoc.getSoTietLT(),
                monHoc.getSoTietTH(),
                monHoc.getSoTinChi());
        return new ApiResponse<Object>(200, "Thêm mới môn học thành công", null);
    }

    public ApiResponse updateMonHoc(MonHoc monHoc) {
        monHocRepository.updateMonHoc(monHoc.getMaMH(),
                monHoc.getTenMH(),
                monHoc.getSoTietLT(),
                monHoc.getSoTietTH(),
                monHoc.getSoTinChi());
        return new ApiResponse<Object>(200, "Cập nhật môn học thành công", null);
    }

    public ApiResponse deleteMonHoc(String maMH) {
        Integer kiemTraXoaMH = monHocRepository.kiemTraXoaMonHoc(maMH);
        if (kiemTraXoaMH == 1) {
            return new ApiResponse<Object>(203, "Môn học đang được giảng dạy không thể xoá!", null);
        }
        monHocRepository.deleteMonHoc(maMH);
        return new ApiResponse<Object>(200, "Xoá môn học thành công", null);
    }

}