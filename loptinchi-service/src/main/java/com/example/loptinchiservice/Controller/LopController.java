package com.example.loptinchiservice.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.loptinchiservice.DTO.ApiResponse;
import com.example.loptinchiservice.DTO.LopDTOService;
import com.example.loptinchiservice.Model.Lop;
import com.example.loptinchiservice.Service.LopService;

@RestController
@RequestMapping("/api/lop-tin-chi/")
public class LopController {
    @Autowired
    private LopService lopService;

    @PostMapping("them-lop-hoc")
    public ApiResponse themLop(@RequestBody LopDTOService data) {
        System.out.println(data.getTenLop());
        System.out.println(data.getMaLop());
        return lopService.themLopHocMoi(data.getMaLop(), data.getTenLop(), data.getMaKhoa());
    }

    @PostMapping("thay-doi-lop")
    public ApiResponse thayDoiLop(@RequestBody LopDTOService data) {
        System.out.println(data.getTenLop());
        return lopService.thayDoiLopHoc(data.getMaLop(), data.getTenLop(), data.getMaKhoa());
    }

    @PostMapping("xoa-lop-hoc")
    public ApiResponse xoaLopHoc(@RequestBody String maLop) {
        System.out.println(maLop);
        return lopService.xoaLopHoc(maLop);
    }
}
