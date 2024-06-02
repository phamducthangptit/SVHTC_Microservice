package com.example.loptinchiservice.controller;

import com.example.loptinchiservice.responsedto.LopTinChiResponse;
import com.example.loptinchiservice.responsedto.SinhVienLtcResponse;
import com.example.loptinchiservice.Service.LopTinChiService;
import com.example.loptinchiservice.Service.SinhVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/lop-tin-chi")
public class LopTinChiController {
    @Autowired
    private LopTinChiService lopTinChiService;

    @Autowired
    private SinhVienService sinhVienService;

    @GetMapping("/danh-sach-ltc")
    public ResponseEntity<?> danhSachLtcTheoMaGV(@RequestParam("ma-gv") String maGV) {
        List<LopTinChiResponse> result = lopTinChiService.danhSachLtcTheoMaGv(maGV);
        if (result == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/danh-sach-sinh-vien")
    public ResponseEntity<?> danhSachSinhVienLTC(@RequestParam("maltc") int maLTC) {
        List<SinhVienLtcResponse> result = sinhVienService.danhSachSinhVienLtc(maLTC);
        if (result == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
