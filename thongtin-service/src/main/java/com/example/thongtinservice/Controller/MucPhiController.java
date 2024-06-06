package com.example.thongtinservice.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.thongtinservice.DTO.ApiResponse;
import com.example.thongtinservice.ResponseDTO.ResponseMucPhi;
import com.example.thongtinservice.Service.MucPhiService;

@RestController
@RequestMapping("/api/thong-tin/muc-phi/")
public class MucPhiController {
    @Autowired
    private MucPhiService mucPhiService;

    @PostMapping("lay-muc-phi")
    public ApiResponse getMucPhi(@RequestBody ResponseMucPhi data) {
        return mucPhiService.getMucPhi(data);
    }
}
