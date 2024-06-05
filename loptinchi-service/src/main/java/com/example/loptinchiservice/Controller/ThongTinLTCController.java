package com.example.loptinchiservice.Controller;

import com.example.loptinchiservice.DTO.GiangVienDTO;
import com.example.loptinchiservice.DTO.LopTinChiDTO;
import com.example.loptinchiservice.DTO.MonHocDTO;
import com.example.loptinchiservice.DTO.SinhVienDTO;
import com.example.loptinchiservice.Repository.GiangVienRepo;
import com.example.loptinchiservice.Service.GiangVienService;
import com.example.loptinchiservice.Service.LopTinChiService;
import com.example.loptinchiservice.Service.SinhVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lop-tin-chi")
public class ThongTinLTCController {
    @Autowired
    LopTinChiService lopTinChiService;
    @Autowired
    SinhVienService sinhVienService;
    @Autowired
    GiangVienService giangVienService;
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
        int x = lopTinChiService.updateLTC(ltc.getMaltc(), ltc.getMagv(), ltc.getNhom(), ltc.getSosvtt(), ltc.getSosvtd(), ltc.getMalop());
        if(x == 1)
            return new ResponseEntity<>(ltc, HttpStatus.OK);
        return new ResponseEntity<>(ltc, HttpStatus.BAD_REQUEST);
    }
    @RequestMapping(value = "/xoa-ltc", method = RequestMethod.GET)
    public ResponseEntity<?> xoaLTC(@RequestParam("maltc") int maltc){
        int x = lopTinChiService.xoaLTC(maltc);
        if(x == 1)
            return new ResponseEntity<>(HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

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
    @RequestMapping(value = "/them-gv", method = RequestMethod.POST)
    public ResponseEntity<?> themGV(@Validated @RequestBody GiangVienDTO gv){
        if(giangVienService.themGV(gv) == 0){
            return ResponseEntity.badRequest().build();
        } else {
            return ResponseEntity.ok().build();
        }
    }
    @RequestMapping(value = "/update-gv", method = RequestMethod.POST)
    public ResponseEntity<?> updateGV(@Validated @RequestBody GiangVienDTO gv){
        if(giangVienService.updateGV(gv) == 0){
            return ResponseEntity.badRequest().build();
        } else {
            return ResponseEntity.ok().build();
        }
    }
    @RequestMapping(value = "/xoa-gv", method = RequestMethod.GET)
    public ResponseEntity<?> xoaGV(@Validated @RequestParam("magv") String magv){
        if(giangVienService.xoaGV(magv) == 0){
            return ResponseEntity.badRequest().build();
        } else {
            return ResponseEntity.ok().build();
        }
    }
}

