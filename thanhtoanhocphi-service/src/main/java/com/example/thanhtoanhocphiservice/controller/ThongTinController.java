package com.example.thanhtoanhocphiservice.controller;

import com.example.thanhtoanhocphiservice.dto.HocPhiSVDTO;
import com.example.thanhtoanhocphiservice.dto.SinhVienDTO;
import com.example.thanhtoanhocphiservice.service.SinhVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/thanh-toan/thong-tin")
public class ThongTinController {
    @Autowired
    SinhVienService sinhVienService;
    @RequestMapping(value = "/them-sv", method = RequestMethod.POST)
    public ResponseEntity<?> themSV(@Validated @RequestBody SinhVienDTO sv){
        if(sinhVienService.themSV(sv) == 0){
            return ResponseEntity.badRequest().build();
        } else {
            return ResponseEntity.ok().build();
        }
    }
    @RequestMapping(value = "/update-sv", method = RequestMethod.POST)
    public ResponseEntity<?> updateSV(@Validated @RequestBody SinhVienDTO sv){
        if(sinhVienService.updateSV(sv) == 0){
            return ResponseEntity.badRequest().build();
        } else {
            return ResponseEntity.ok().build();
        }
    }
    @RequestMapping(value = "/xoa-sv", method = RequestMethod.GET)
    public ResponseEntity<?> xoaSV(@Validated @RequestParam("masv") String masv){
        if(sinhVienService.xoaSV(masv) == 0){
            return ResponseEntity.badRequest().build();
        } else {
            return ResponseEntity.ok().build();
        }
    }
    @GetMapping("/xem-hocphi-sv")
    public ResponseEntity<List<HocPhiSVDTO>> locGVKhoa(@RequestParam("masv") String masv) {
        return  ResponseEntity.ok(sinhVienService.xemHP(masv));
    }
}
