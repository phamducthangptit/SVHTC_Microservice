package com.example.thongtinservice.Controller;

import com.example.thongtinservice.ResponseDTO.DiemTongKetResponse;
import com.example.thongtinservice.Service.SinhVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/thong-tin/sinh-vien/")
public class SinhVienController {
    @Autowired
    private SinhVienService sinhVienService;
    @GetMapping("thong-tin-ca-nhan")
    private ResponseEntity<?> thongTinCaNhanSV(@RequestParam("ma-sv") String maSV){
        Map<String, ?> thongTin = sinhVienService.thongTinCaNhanSV(maSV);
        if(thongTin.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(thongTin, HttpStatus.OK);
    }

    @GetMapping("xem-diem")
    private ResponseEntity<?> xemDiem(@RequestParam("ma-sv") String maSV){
        List<List<DiemTongKetResponse>> listDiem = sinhVienService.xemDiem(maSV);
        if(listDiem != null){
            return new ResponseEntity<>(listDiem, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("nienkhoa-hocki")
    private ResponseEntity<?> nienKhoaHocKi(@RequestParam("ma-sv") String maSV){
        return new ResponseEntity<>(sinhVienService.getNienKhoaHocKi(maSV), HttpStatus.OK);
    }
}
