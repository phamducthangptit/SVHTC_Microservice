package com.example.loptinchiservice.controller;

import com.example.loptinchiservice.DTO.GiangVienDTO;
import com.example.loptinchiservice.DTO.LopTinChiDTO;
import com.example.loptinchiservice.DTO.MonHocDTO;
import com.example.loptinchiservice.Model.GiangVien;
import com.example.loptinchiservice.Model.LopTinChi;
import com.example.loptinchiservice.Service.LopTinChiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/loc-mon-hoc")
    public ResponseEntity<List<MonHocDTO>> locMonHoc() {
        try {
            List<MonHocDTO> monHocList = lopTinChiService.locMonHoc();
            return new ResponseEntity<>(monHocList, HttpStatus.OK);
        } catch (Exception e) {

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/loc-gv-khoa")
    public ResponseEntity<List<GiangVienDTO>> locGVKhoa(@RequestParam("ma-khoa") String makhoa) {
        return  ResponseEntity.ok(lopTinChiService.locGVKhoa(makhoa));
    }

    @GetMapping("/loc-ltc")
    public ResponseEntity<List<LopTinChiDTO>> locGVKhoa(@RequestParam("ma-khoa") String makhoa,
                                                        @RequestParam("nien-khoa") String nienkhoa,
                                                        @RequestParam("hoc-ki") int hocki

    ) {
        return  ResponseEntity.ok(lopTinChiService.locLTC(makhoa, nienkhoa, hocki));
    }
    @GetMapping("/loc-lop-khoa")
    public ResponseEntity<List<String>> locLopKhoa(@RequestParam("ma-khoa") String makhoa) {
        try {
            List<String> loplist = lopTinChiService.danhSachLopKhoa(makhoa);
            return new ResponseEntity<>(loplist, HttpStatus.OK);
        } catch (Exception e) {

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @RequestMapping(value = "/them-ltc", method = RequestMethod.POST)
    public ResponseEntity<LopTinChiDTO> themLTC(@Validated @RequestBody LopTinChiDTO ltc){
        int x = lopTinChiService.themLTC(ltc.getMamh(), ltc.getMalop(), ltc.getMagv(), ltc.getNienkhoa(), ltc.getNhom(), ltc.getSosvtt(), ltc.getSosvtd(), ltc.getHocki(), ltc.getMakhoa());
        if(x == 1)
            return new ResponseEntity<>(ltc, HttpStatus.OK);
        return new ResponseEntity<>(ltc, HttpStatus.BAD_REQUEST);
    }
    @RequestMapping(value = "/update-ltc", method = RequestMethod.POST)
    public ResponseEntity<LopTinChiDTO> updateLTC(@Validated @RequestBody LopTinChiDTO ltc){
        int x = lopTinChiService.updateLTC(ltc.getMaltc(), ltc.getMagv(), ltc.getNhom(), ltc.getSosvtt(), ltc.getSosvtd());
        if(x == 1)
            return new ResponseEntity<>(ltc, HttpStatus.OK);
        return new ResponseEntity<>(ltc, HttpStatus.BAD_REQUEST);
    }
}

