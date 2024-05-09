package com.example.loptinchiservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/lop-tin-chi")
public class LopTinChiController {
    @GetMapping("/test")
    public ResponseEntity<?> test(){
        Map<String, String> tmp = new HashMap<>();
        tmp.put("title", "Phuong thuc get lop tin chi");
        return new ResponseEntity<>(tmp, HttpStatus.OK);
    }
}
