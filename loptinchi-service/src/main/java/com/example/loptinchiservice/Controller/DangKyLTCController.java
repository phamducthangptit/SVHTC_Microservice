package com.example.loptinchiservice.Controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.loptinchiservice.DTO.ApiResponse;
import com.example.loptinchiservice.Service.LopTinChiService;

@RestController
@RequestMapping("/api/lop-tin-chi/dang-ky/")
public class DangKyLTCController {

    @Autowired
    private LopTinChiService lopTinChiService;

    @GetMapping("danh-sach-ltc-de-dang-ky")
    public ApiResponse getDSLTCDeDangKy(@RequestParam("malop") String malop,
            @RequestParam("masv") String masv) {
        return lopTinChiService.getDSLTCDeDK(malop, masv);
    }

    @GetMapping("danh-sach-ltc-da-dang-ky")
    public ApiResponse getDSLTCDaDangKy(@RequestParam("masv") String masv) {
        return lopTinChiService.getDSLTCDaDK(masv);
    }

    @PostMapping("dang-ky-moi")
    public ApiResponse dangKyLTC(@RequestBody Map<String, Object> data) {
        return lopTinChiService.dangKyLTC((Integer) data.get("maltc"), (String) data.get("masv"),
                (Integer) data.get("svtoida"));
    }

    @PostMapping("huy-dang-ky")
    public ApiResponse huyDangKyLTC(@RequestBody Map<String, Object> data) {
        return lopTinChiService.huyDangKyLTC((Integer) data.get("maltc"), (String) data.get("masv"));
    }

    @GetMapping("danh-sach-thong-tin-lop")
    public ApiResponse layDanhSachLop() {
        return lopTinChiService.layThongTinLop123();
    }
}
