package com.example.thongtinservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.thongtinservice.DTO.ApiResponse;
import com.example.thongtinservice.Service.LopService;

@RestController
@RequestMapping("/api/thong-tin/lop-hoc/")
public class LopController {
    @Autowired
    private LopService lopService;

    @GetMapping("so-luong-lop-hoc")
    public ApiResponse getSizeListLop() {
        return lopService.getSizeListLop();
    }

    @GetMapping("danh-sach-lop-hoc")
    public ApiResponse getListLopHoc(@RequestParam("start") int start,
            @RequestParam("size") int size) {
        return lopService.getListLop(start, size);
    }

}
