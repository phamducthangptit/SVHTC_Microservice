package com.example.loptinchiservice.Controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.loptinchiservice.DTO.ApiResponse;
import com.example.loptinchiservice.Model.MonHoc;
import com.example.loptinchiservice.Service.MonHocService;

@RestController
@RequestMapping("/api/lop-tin-chi/")
public class MonHocController {
    @Autowired
    private MonHocService monHocService;

    @PostMapping("them-mon-hoc")
    public ApiResponse themMonHoc(@RequestBody MonHoc monHoc) {
        System.out.println(monHoc.toString());
        return monHocService.insertMonHoc(monHoc);
    }
}
