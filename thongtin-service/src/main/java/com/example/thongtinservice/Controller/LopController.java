package com.example.thongtinservice.Controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.thongtinservice.DTO.ApiResponse;
import com.example.thongtinservice.Service.HeService;
import com.example.thongtinservice.Service.KhoaService;
import com.example.thongtinservice.Service.LopService;

@RestController
@RequestMapping("/api/thong-tin/lop-hoc/")
public class LopController {
    @Autowired
    private LopService lopService;

    @Autowired
    private KhoaService khoaService;

    @Autowired
    private HeService heService;

    @GetMapping("so-luong-lop-hoc")
    public ApiResponse getSizeListLop() {
        return lopService.getSizeListLop();
    }

    @GetMapping("danh-sach-lop-hoc")
    public ApiResponse getListLopHoc(@RequestParam("start") int start,
            @RequestParam("size") int size) {
        return lopService.getListLop(start, size);
    }

    @GetMapping("danh-sach-khoa")
    public ApiResponse getListKhoa() {
        return khoaService.getListKhoa();
    }

    @GetMapping("danh-sach-he")
    public ApiResponse getListHe() {
        return heService.getListHe();
    }

    @PostMapping("them-lop-moi")
    public ApiResponse themLopMoi(@RequestBody Map<String, Object> data) {
        System.out.println(data);
        return lopService.themLop(data);
    }

    @PostMapping("thay-doi-lop")
    public ApiResponse thayDoiLop(@RequestBody Map<String, Object> data) {
        return lopService.updateLopHoc(data);
    }

    @GetMapping("xoa-lop-hoc")
    public ApiResponse xoaLopHoc(@RequestParam("malop") String malop) {
        return lopService.deleteLopHoc(malop);
    }

    @GetMapping("tim-lop-hoc")
    public ApiResponse timLopHoc(@RequestParam("search") String search) {
        System.out.println("check query: " + search);
        return lopService.timLop(search);
    }

}
