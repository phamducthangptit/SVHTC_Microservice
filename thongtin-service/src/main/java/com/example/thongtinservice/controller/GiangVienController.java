package com.example.thongtinservice.controller;

import com.example.thongtinservice.responseservice.LopTinChiResponse;
import com.example.thongtinservice.Service.NhapDiemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/thong-tin/giang-vien")
public class GiangVienController {
    @Autowired
    private NhapDiemService nhapDiemService;

    @GetMapping("/danh-sach-ltc")
    public ResponseEntity<?> danhSachLtcTheoMaGv(@RequestParam("ma-gv") String maGV) {
        List<LopTinChiResponse> result = nhapDiemService.danhSachLtcTheoMaGV(maGV);
        if (result != null && !result.isEmpty()) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
