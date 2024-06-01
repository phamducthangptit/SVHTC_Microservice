package com.example.thongtinservice.Service;

import com.example.thongtinservice.Model.Lop;
import com.example.thongtinservice.Repository.LopRepository;
import com.example.thongtinservice.Repository.SinhVienRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;


@Service
public class LopService {

    @Autowired
    private LopRepository lopRepository;

    public List<Lop> getAllLop(){
        return lopRepository.findAll();
    }

    public Lop lopTheoMaLop(String maLop){
        if(maLop==null){
            return null;
        }
        return lopRepository.findBymalop(maLop);
    }

    @Autowired
    SinhVienRepository sinhVienRepository;

    public List<Map<String, ?>> danhSachLopCuaKhoa(String maGv, int trangThai){
        return lopRepository.danhSachLopCuaKhoa(maGv, trangThai);
    }

    public int addLop(Lop lop){
        int result = 0;
        if(lopRepository.findBymalop(lop.getMalop()) == null){
            if(lopRepository.save(lop) != null) result = 1;
        }
        return result;
    }


    @Transactional
    public int xoaLop(String maLop){
        Lop lop = lopRepository.findBymalop(maLop);
        if(lop != null && sinhVienRepository.findBymalop(lop) == null){
            lopRepository.deleteBymalop(maLop);
            return 1;
        } else {
            return 0;
        }
    }

    public List<Lop> findAll() {
        return lopRepository.findAll();
    }


    public Optional<Lop> timLopTheoMa(String maLop) {
        return lopRepository.findById(maLop);
    }

    public List<Map<String,Object>> findDanhSachLop() {
        return lopRepository.findDanhSachLop();
    }


}

