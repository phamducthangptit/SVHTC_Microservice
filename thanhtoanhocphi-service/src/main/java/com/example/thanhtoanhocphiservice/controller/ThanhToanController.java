package com.example.thanhtoanhocphiservice.controller;

import com.example.thanhtoanhocphiservice.dto.PaymentResponseUpdate;
import com.example.thanhtoanhocphiservice.service.HocPhiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/thanh-toan")
public class ThanhToanController {
    @Autowired
    private HocPhiService service;
    @GetMapping("/test")
    public ResponseEntity<?> test(){
        Map<String, String> tmp = new HashMap<>();
        tmp.put("title", "Phuong thuc get thanh toan");
        return new ResponseEntity<>(tmp, HttpStatus.OK);
    }
    @GetMapping("/thong-tin-hoc-phi")
    public ResponseEntity<List<Map<String, String>>> thongTinHocPhi(@RequestParam("ma-sv") String maSV){
        List<Map<String, String>> thongTin = service.thongTinHocPhi(maSV);
        if(thongTin != null){
            return new ResponseEntity<>(thongTin, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("/cap-nhat-trang-thai-hoc-phi")
    public ResponseEntity<?> updateTrangThaiHP(@RequestBody PaymentResponseUpdate paymentResponseUpdate){
        int x = service.updateTrangThaiHP(paymentResponseUpdate.getMaSV(), paymentResponseUpdate.getNienKhoa(), paymentResponseUpdate.getHocKi());
        if(x == 1)
            return new ResponseEntity<>(HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}

