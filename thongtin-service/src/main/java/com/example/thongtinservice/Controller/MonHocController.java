package com.example.thongtinservice.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.thongtinservice.DTO.ApiResponse;
import com.example.thongtinservice.Service.MonHocService;

@RestController
@RequestMapping("/api/thong-tin/mon-hoc/")
public class MonHocController {
    @Autowired
    private MonHocService monHocService;

    @GetMapping("danh-sach-mon-hoc")
    public ApiResponse getListMonHoc(@RequestParam("start") int start,
            @RequestParam("size") int size) {
        return monHocService.getListMonHoc(start, size);
    }

}
