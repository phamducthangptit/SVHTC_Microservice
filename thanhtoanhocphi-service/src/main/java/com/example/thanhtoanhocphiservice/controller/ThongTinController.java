package com.example.thanhtoanhocphiservice.controller;

import com.example.thanhtoanhocphiservice.dto.HocPhiList;
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

    @GetMapping("/xem-hoc-phi-sv")
    public ResponseEntity<List<HocPhiSVDTO>> xemHP(@RequestParam("masv") String masv) {
        System.out.println(masv);
        return  ResponseEntity.ok(sinhVienService.xemHP(masv));
    }
    @GetMapping("/xem-dssv-hoc-phi")
    public ResponseEntity<List<HocPhiList>> DSSVHP() {
        return  ResponseEntity.ok(sinhVienService.DSSVHP());
    }
}
