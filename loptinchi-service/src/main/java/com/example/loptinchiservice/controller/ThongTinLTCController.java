package com.example.loptinchiservice.controller;

import com.example.loptinchiservice.DTO.LopTinChiDTO;
import com.example.loptinchiservice.Model.GiangVien;
import com.example.loptinchiservice.Model.LopTinChi;
import com.example.loptinchiservice.Service.LopTinChiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/lop-tin-chi")
public class ThongTinLTCController {
    @Autowired
    LopTinChiService lopTinChiService;
    @GetMapping("/loc-ma-khoa")
    public ResponseEntity<List<String>> locMaKhoa() {
        try {
            List<String> maKhoaList = lopTinChiService.locMaKhoa();
            return new ResponseEntity<>(maKhoaList, HttpStatus.OK);
        } catch (Exception e) {

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/loc-nien-khoa")
    public ResponseEntity<List<String>> locNienKhoa() {
        try {
            List<String> nienKhoaList = lopTinChiService.locNienKhoa();
            return new ResponseEntity<>(nienKhoaList, HttpStatus.OK);
        } catch (Exception e) {

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/loc-gv-khoa")
    public ResponseEntity<List<GiangVien>> locGVKhoa(@RequestParam("ma-khoa") String makhoa) {
        return  ResponseEntity.ok(lopTinChiService.locGVKhoa(makhoa));
    }

    @GetMapping("/loc-ltc")
    public ResponseEntity<List<LopTinChiDTO>> locGVKhoa(@RequestParam("ma-khoa") String makhoa,
                                                        @RequestParam("nien-khoa") String nienkhoa,
                                                        @RequestParam("hoc-ki") int hocki

    ) {
        return  ResponseEntity.ok(lopTinChiService.locLTC(makhoa, nienkhoa, hocki));
    }
}

