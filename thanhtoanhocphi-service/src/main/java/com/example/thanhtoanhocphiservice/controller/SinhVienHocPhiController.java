package com.example.thanhtoanhocphiservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.thanhtoanhocphiservice.dto.ApiResponse;
import com.example.thanhtoanhocphiservice.dto.SinhVienHocPhi;
import com.example.thanhtoanhocphiservice.service.HocPhiService;

import jakarta.ws.rs.POST;

@RestController
@RequestMapping("/api/thanh-toan/hoc-phi")
public class SinhVienHocPhiController {

    @Autowired
    private HocPhiService hocPhiService;

    @PostMapping("/them-hoc-phi")
    public ApiResponse themHocPhi(@RequestBody SinhVienHocPhi sv) {
        return hocPhiService.insertHocPhi(sv);
    }

}
