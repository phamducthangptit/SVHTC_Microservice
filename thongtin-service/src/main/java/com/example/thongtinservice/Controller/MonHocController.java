package com.example.thongtinservice.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.thongtinservice.DTO.ApiResponse;
import com.example.thongtinservice.Model.MonHoc;
import com.example.thongtinservice.Service.MonHocService;
import org.springframework.http.HttpHeaders;

@RestController
@RequestMapping("/api/thong-tin/mon-hoc/")
public class MonHocController {
    @Autowired
    private MonHocService monHocService;

    @GetMapping("so-luong-mon-hoc")
    public ApiResponse getSizeListMonHoc() {
        return monHocService.getSizeListMonHoc();
    }

    @GetMapping("danh-sach-mon-hoc")
    public ApiResponse getListMonHoc(@RequestParam("start") int start,
            @RequestParam("size") int size) {

        return monHocService.getListMonHoc(start, size);
    }

    @GetMapping("tim-mon-hoc")
    public ApiResponse timMonHoc(@RequestParam("search") String search) {
        return monHocService.findListMonHoc(search);
    }

    @PostMapping("them-mon-hoc")
    public ApiResponse themMonHoc(@RequestBody MonHoc monHoc, @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        System.out.println("Token: " + token);
        // System.out.print("check data them mon hoc: ");
        // System.out.println(monHoc.toString());
        return monHocService.insertMonHoc(monHoc);
    }

    @PostMapping("chinh-sua-mon-hoc")
    public ApiResponse chinhSuaMonHoc(@RequestBody MonHoc monHoc,
            @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        System.out.print("check data chinh sua mon hoc: ");
        System.out.println(monHoc.toString());
        return monHocService.updateMonHoc(monHoc);
    }

    @GetMapping("xoa-mon-hoc")
    public ApiResponse xoaMonHoc(@RequestParam("mamh") String mamh,
            @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        return monHocService.deleteMonHoc(mamh);
    }

}
