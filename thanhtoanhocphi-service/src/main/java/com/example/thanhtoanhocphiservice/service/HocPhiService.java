package com.example.thanhtoanhocphiservice.service;

import com.example.thanhtoanhocphiservice.dto.ApiResponse;
import com.example.thanhtoanhocphiservice.dto.SinhVienHocPhi;
import com.example.thanhtoanhocphiservice.model.HocPhi;
import com.example.thanhtoanhocphiservice.repository.HocPhiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class HocPhiService {
    @Autowired
    private HocPhiRepository repository;

    public List<Map<String, String>> thongTinHocPhi(String maSv) {
        List<Map<String, String>> thongTinHP = repository.getThongTinHP(maSv);
        if (thongTinHP.isEmpty())
            return null;
        return thongTinHP;
    }

    public int updateTrangThaiHP(String maSV, String nienKhoa, int hocKi) {
        return repository.updateTrangThaiHP(maSV, nienKhoa, hocKi);
    }

    public ApiResponse insertHocPhi(SinhVienHocPhi sv) {
        System.out.println("Check insert hocphi");
        int kiemTraTonTai = repository.kiemTraTonTai(sv.getMaSV());
        System.out.println("kiểm tra tồn tại :");
        System.out.println(kiemTraTonTai);
        if (kiemTraTonTai == 1) {
            int soTienHienTai = repository.getSoTienHPSV(sv.getMaSV(), sv.getNienKhoa(), sv.getHocKi());
            int soTien1 = sv.getSoTien();
            sv.setSoTien(soTienHienTai + soTien1);
            repository.updateHocPhi(sv.getMaSV(), sv.getNienKhoa(), sv.getHocKi(), sv.getSoTien());
        } else {
            repository.insertSinhVien(sv.getMaSV(), sv.getHoSV(), sv.getTenSV());
            repository.insertHocPhi(sv.getMaSV(), sv.getNienKhoa(), sv.getHocKi(), sv.getSoTien());
        }

        System.out.println(sv.toString());
        return new ApiResponse<>(200, "Cập nhật học phí thành công!", null);
    }
}
