package com.example.thanhtoanhocphiservice.service;

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

    public List<Map<String, String>> thongTinHocPhi(String maSv){
        List<Map<String, String>> thongTinHP = repository.getThongTinHP(maSv);
        if(thongTinHP.isEmpty()) return null;
        return thongTinHP;
    }

    public int updateTrangThaiHP(String maSV, String nienKhoa, int hocKi){
        return repository.updateTrangThaiHP(maSV, nienKhoa, hocKi);
    }
}
